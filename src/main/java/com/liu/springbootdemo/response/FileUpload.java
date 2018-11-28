package com.liu.springbootdemo.response;

import java.io.Serializable;

/**
 * 文件上传完成后返回的对象
 */
public class FileUpload implements Serializable{
    private String url;
    private long filesize;
    private long createtime;
    private String formatfilesize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getFormatfilesize() {
        return formatfilesize;
    }

    public void setFormatfilesize(String formatfilesize) {
        this.formatfilesize = formatfilesize;
    }
}
