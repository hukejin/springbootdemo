package com.liu.springbootdemo.utils;

import java.util.UUID;

/**
 * 数据库id生成器
 */
public class IdgeneratorUtil {

    public static String returnId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
