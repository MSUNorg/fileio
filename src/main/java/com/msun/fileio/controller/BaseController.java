/*
 * Copyright 2015-2020 msun.com All right reserved.
 */
package com.msun.fileio.controller;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;
import com.lamfire.logger.LoggerFactory;
import com.msun.fileio.support.FileHelper;

/**
 * @author zxc Jun 14, 2016 3:43:28 PM
 */
@Component
public class BaseController {

    protected static final Logger _ = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected FileHelper          fileHelper;

    @Autowired
    protected HttpServletRequest  request;

    @Value("${server.port}")
    protected String              server_port;

    public static JSON fail() {
        return result(900, null);
    }

    public static JSON ok() {
        return result(200, null);
    }

    public static JSON fail(Object data) {
        return result(900, data);
    }

    public static JSON ok(Object data) {
        return result(200, data);
    }

    public static JSON result(int status, Object children) {
        JSON json = new JSON();
        json.put("status", status);
        if (children != null) json.put("data", children);
        return json;
    }

    public String path(String path) {
        return "http://" + localIp() + ":" + server_port + "/file?f=" + path;
    }

    /**
     * 获得本机的ip地址
     * 
     * @return 本机ip地址
     */
    public static String localIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception e) {
            _.error("getLocalIp error", e);
            return "127.0.0.1";
        }
    }
}
