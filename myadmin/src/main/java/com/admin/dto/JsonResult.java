package com.admin.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/13.
 */
public class JsonResult<T> implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private T data;
    private String code;
    private String msg;

    public JsonResult(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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

    @Override
    public String toString() {
        return msg + code;
    }
}