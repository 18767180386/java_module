package com.admin.entity;

import java.util.Date;
import java.util.List;

public class OccupationInfo {
    private int id;
    private int userId;
    private String title;
    private String author;
    private String content;
    private int  commentnum;
    private Date createtime;
    private int msgType;

    private List<OccupationPicInfo> occupationPicInfos;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private UserInfo  userInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OccupationPicInfo> getOccupationPicInfos() {
        return occupationPicInfos;
    }

    public void setOccupationPicInfos(List<OccupationPicInfo> occupationPicInfos) {
        this.occupationPicInfos = occupationPicInfos;
    }
}
