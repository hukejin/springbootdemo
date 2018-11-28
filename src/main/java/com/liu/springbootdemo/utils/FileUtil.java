package com.liu.springbootdemo.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    /**
     * 文件上传工具类服务方法
     * @param file 文件
     * @param filePath 存储dir
     * @param fileName 文件名
     * @return
     */
    public static File uploadFile(byte[] file, String filePath, String fileName){

        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            File file1 = new File(filePath + "/" + fileName);
            if(!file1.exists())
                file1.createNewFile();

            FileOutputStream out = new FileOutputStream(file1);
            out.write(file);
            out.flush();
            out.close();

            return file1;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件后缀名
     * @param filename
     * @return
     */
    public static String getExtension(String filename){
        if(StringUtils.isEmpty(filename))
            return "";

        int index = filename.lastIndexOf(".");
        if(index == -1)
            return "";
        return filename.substring(index,filename.length());
    }

    /**
     * 将文件大小format为KB/MB/GB/TB
     * @param filesize
     * @return
     */
    public static String getFileSizeFormat(long filesize){
        long TB = 1024L * 1024L * 1024L * 1024L;
        long GB = 1024L * 1024L * 1024L;
        long MB = 1024L * 1024L;
        long KB = 1024;
        if(filesize == 0)
            return "0KB";
        else if(filesize > TB){
            return (filesize / TB) + "T";
        }else if(filesize > GB){
            return (filesize / GB) + "G";
        }else if(filesize > MB){
            return (filesize / MB) + "MB";
        }else if(filesize > KB){
            return (filesize / KB) + "KB";
        }else{
            return filesize + "B";
        }
    }
}
