package com.admin.entity;

public enum  RequestCode {
    RegSucc("成功", 0),RegFail("失败", -101),RegNoName("账号为空", -102),RegNoPass("密码为空", -103),UserNameExist("账号存在", -104),
    LoginSucc("登录成功",0),LoginFail("登录失败",-201),LoginNoName("账号为空",-202),LoginNoPass("密码为空",-203),LoginNameNoExist("账号不存在",-204),LoginPassFail("密码错误",-205);
    private RequestCode(String name , int index ){
        this.name = name ;
        this.index = index ;
    }

    private String name ;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
