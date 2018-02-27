package com.admin.entity;

public enum  Response {
    LOGIN_SUCESS("loginSuc","hrm登录成功"),LOGIN_FAILURE("loginFail","hrm登录失败"),LOGINOUT_SUCESS("logout","hrm登出成功"),EMPTY_ERROR("001", "参数为空"), TIME_ERROR("002", "时间错误"), SIGN_ERROR("003",
            "签名错误"), METHOD_ERROR("004", "方法错误"), PROP_ERROR("005", "属性错误"), LOGIC_ERROR(
            "006", "逻辑错误"),GETREQUEST_SUCESS("000","请求成功");

    private String errorCode;
    private String msg;

    private Response(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public static String getMsg(String errorCode) {
        for (Response res : Response.values()) {
            if (res.getErrorCode().equals(errorCode)) {
                return res.getMsg();
            }
        }
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
