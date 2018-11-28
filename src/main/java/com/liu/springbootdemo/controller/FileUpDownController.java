package com.liu.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.liu.springbootdemo.response.BaseResult;
import com.liu.springbootdemo.response.FileUpload;
import com.liu.springbootdemo.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件的上传下载功能
 */
@RestController
public class FileUpDownController {

    @Value("${filebasepath}")
    private String basefilepath;
    @Value("${fileimgpath}")
    private String fileimgpath;
    @Value("${fileaudio}")
    private String fileaudio;
    @Value("${filevideo}")
    private String filevideo;
    @Value("${fileoffice}")
    private String fileoffice;
    @Value("${hosturl}")
    private String hosturl;
    @Value("${port}")
    private String port;
    @Value("${filecache}")
    private String filecache;

    @RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        BaseResult baseResult = new BaseResult();


        // 文件名称生成策略（日期时间+uuid ）
        UUID uuid = UUID.randomUUID();
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(d);
        //文件存储根路径
        String relaPath = filecache + basefilepath;
        // 获取文件的扩展名
        String extension = FileUtil.getExtension(file.getOriginalFilename()).trim().toLowerCase();
        //文件路径
        String filedir = "";
        if(extension.contains("jpg")||extension.contains("png")||extension.contains("jpeg")||extension.contains("bmp")||extension.contains("gif"))
            filedir = fileimgpath;
        else if(extension.contains("mp3")||extension.contains("wav")||extension.contains("wma")||extension.contains("ogg")||extension.contains("aac")||extension.contains("mod")||extension.contains("amr"))
            filedir = fileaudio;
        else if(extension.contains("mp4")||extension.contains("mpg")||extension.contains("mpeg")||extension.contains("avi")||extension.contains("rm")||extension.contains("rmvb")||extension.contains("wmv")||extension.contains("mov"))
            filedir = filevideo;
        else
            filedir = fileoffice;
        // 文件名
        String fileName = formatDate + "-" + uuid + "." + extension;


        try {
            File file1 = FileUtil.uploadFile(file.getBytes(),relaPath + filedir,fileName);

            if(file1 != null){
                FileUpload fileUpload = new FileUpload();
                fileUpload.setUrl(hosturl+ ":" + port + "/" + basefilepath + "/" +  filedir + "/" + fileName);
                fileUpload.setFilesize(file.getSize());
                fileUpload.setCreatetime(System.currentTimeMillis());
                fileUpload.setFormatfilesize(FileUtil.getFileSizeFormat(file.getSize()));

                baseResult.setCode("0");
                baseResult.setMsg("文件上传成功");
                baseResult.setObject(fileUpload);
            }else{
                baseResult.setCode("1");
                baseResult.setMsg("文件上传失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("2");
            baseResult.setMsg(e.getMessage());
        }


        return JSON.toJSONString(baseResult);
    }

    @RequestMapping("/multip/upload")
    public String filesUpload(HttpServletRequest request){
        BaseResult baseResult = new BaseResult();
        List<FileUpload> fileUploads = new ArrayList<>();
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        if(files == null || files.size() == 0){
            baseResult.setCode("1");
            baseResult.setMsg("没有发现文件");
        }else {
            for (int i = 0; i < files.size(); i++) {
                MultipartFile multipartFile = files.get(i);
                // 文件名称生成策略（日期时间+uuid ）
                UUID uuid = UUID.randomUUID();
                Date d = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String formatDate = format.format(d);
                //文件存储根路径
                String relaPath = filecache + basefilepath;
                // 获取文件的扩展名
                String extension = FileUtil.getExtension(multipartFile.getOriginalFilename()).trim().toLowerCase();
                //文件路径
                String filedir = "";
                if(extension.contains("jpg")||extension.contains("png")||extension.contains("jpeg")||extension.contains("bmp")||extension.contains("gif"))
                    filedir = fileimgpath;
                else if(extension.contains("mp3")||extension.contains("wav")||extension.contains("wma")||extension.contains("ogg")||extension.contains("aac")||extension.contains("mod")||extension.contains("amr"))
                    filedir = fileaudio;
                else if(extension.contains("mp4")||extension.contains("mpg")||extension.contains("mpeg")||extension.contains("avi")||extension.contains("rm")||extension.contains("rmvb")||extension.contains("wmv")||extension.contains("mov"))
                    filedir = filevideo;
                else
                    filedir = fileoffice;
                // 文件名
                String fileName = formatDate + "-" + uuid + "." + extension;
                try {
                    File file1 = FileUtil.uploadFile(multipartFile.getBytes(),relaPath + filedir,fileName);

                    if(file1 != null){
                        FileUpload fileUpload = new FileUpload();
                        fileUpload.setUrl(hosturl+ ":" + port + "/" + basefilepath + "/" +  filedir + "/" + fileName);
                        fileUpload.setFilesize(multipartFile.getSize());
                        fileUpload.setCreatetime(System.currentTimeMillis());
                        fileUpload.setFormatfilesize(FileUtil.getFileSizeFormat(multipartFile.getSize()));
                        fileUploads.add(fileUpload);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }

            if(fileUploads.size() != files.size()){
                baseResult.setCode("2");
                baseResult.setMsg("文件上传失败");
            }else{
                baseResult.setCode("0");
                baseResult.setMsg("文件上传成功");
                baseResult.setObject(fileUploads);
            }
        }
        return JSON.toJSONString(baseResult);
    }

}
