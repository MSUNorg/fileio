/*
 * Copyright 2015-2020 msun.com All right reserved.
 */
package com.msun.fileio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lamfire.json.JSON;
import com.lamfire.utils.StringUtils;
import com.msun.fileio.support.JsonResult;

/**
 * @author zxc Aug 18, 2016 11:23:35 AM
 */
@RestController
@RequestMapping("/v1/api")
@Api(value = "文件服务", description = "文件上传测试")
public class FileController extends BaseController {

    @ResponseBody
    @ApiOperation(value = "POST FileUpload", httpMethod = "POST", response = JsonResult.class, notes = "文件上传")
    @RequestMapping(value = "/fileupload ", produces = { "application/json" }, method = RequestMethod.POST)
    JSON upload(@RequestParam() MultipartFile file) throws Exception {
        String path = fileHelper.upload2save(file);
        if (StringUtils.isEmpty(path)) return fail("failed");
        return ok("success");
    }
}
