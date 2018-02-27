package com.admin.dto;

/**
 * Created by Administrator on 2017/7/15.
 */
public class Result {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String[] baseCose = { "000", "-001" };
    private String count = "";
    /** 响应数据 */
    private Object data;
    /** 响应分页 */
    /** 响应状态 */
    private Boolean status = true;
    /** 响应消息 */
    private String message;

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(String message, Boolean status) {
        this.set(message, status);
    }

    public Result(String message, Boolean status, String count, Object data) {
        this.set(message, status);
        this.data = data;
        this.count = count;
    }

    public void set(String message, Boolean status) {
        this.status = status;
        this.message = message;
        this.count = "";
        this.data = "";
    }

    public void set(String message, Boolean status, String count, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        this.message = this.status ? "成功" : "失败";
        this.count = "";
        this.data = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCommonJson() {
        return "";// JsonGenerateUtil.getCommonJson(status==true?SUCCESS:ERROR,
        // message, count==null?"":count, data==null?"":data);
    }

}
