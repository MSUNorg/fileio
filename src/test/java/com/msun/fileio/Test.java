/*
 * Copyright 2015-2020 msun.com All right reserved.
 */
package com.msun.fileio;

import com.lamfire.utils.StringUtils;

/**
 * @author zxc Jun 17, 2016 7:23:39 PM
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("java.library.path: ");
        System.out.println(System.getProperty("java.library.path"));

        System.out.println(StringUtils.substringBeforeLast("app_category_id_gte", "_"));
        System.out.println(StringUtils.substringAfterLast("app_category_id_gte", "_"));
    }
}
