package com.liu.springbootdemo.response;

import java.io.Serializable;

public class BaseResult<T> implements Serializable{
    private String code;
    private String msg;
    private T object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
