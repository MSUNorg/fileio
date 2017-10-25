/*
 * Copyright 2015-2020 msun.com All right reserved.
 */
package com.msun.fileio.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lamfire.utils.StringUtils;

/**
 * 文件上传下载
 * 
 * @author zxc May 25, 2016 4:27:37 PM
 */
@Component
public class FileHelper {

    static final Logger    _ = LoggerFactory.getLogger(FileHelper.class);

    @Value("${file.path}")
    private String         FILE_PATH;
    @Autowired
    private ResourceLoader resourceLoader;

    public String upload2save(MultipartFile uploadfile) {
        if (uploadfile == null || uploadfile.isEmpty()) return "";
        String filename = uploadfile.getOriginalFilename();
        if (StringUtils.isEmpty(filename)) return "";

        OutputStream os = null;
        try {
            String filepath = Paths.get(FILE_PATH, filename + "_" + uuid()).toString();
            InputStream instream = uploadfile.getInputStream();
            os = new FileOutputStream(new File(filepath));
            IOUtils.copy(instream, os);
            return filepath;
        } catch (Throwable e) {
            _.error("upload2save file error!", e);
        } finally {
            IOUtils.closeQuietly(os);
        }
        return "";
    }

    public Resource file(String file) {
        try {
            if (resourceLoader.getResource("file:" + Paths.get(file).toString()).getFile().exists()) {
                return resourceLoader.getResource("file:" + Paths.get(file).toString());
            }
        } catch (Exception e) {
            _.error("resourceLoader.getResource error!", e);
        }
        return resourceLoader.getResource("classpath:static/images/noimages.png");
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String md5(InputStream input) {
        String md5 = "";
        try {
            md5 = DigestUtils.md5DigestAsHex(IOUtils.toByteArray(input));
            IOUtils.closeQuietly(input);
        } catch (Exception e) {
            _.error("FileMD5 error!", e);
        }
        return md5;
    }
}
