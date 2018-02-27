package com.admin.entity;

import java.util.Date;
import java.util.List;

public class OccupationCommentInfo {
    private int commentId;
    private String commentContent;
    private int commentUid;
    private int commentNum;
    private Date commentTime;
    private int praiseNum;
    private int occupation_id;
    private List<OccupationReplyInfo>  replyInfoList;
    private UserInfo userInfo;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentUid() {
        return commentUid;
    }

    public void setCommentUid(int commentUid) {
        this.commentUid = commentUid;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getOccupation_id() {
        return occupation_id;
    }

    public void setOccupation_id(int occupation_id) {
        this.occupation_id = occupation_id;
    }

    public List<OccupationReplyInfo> getReplyInfoList() {
        return replyInfoList;
    }

    public void setReplyInfoList(List<OccupationReplyInfo> replyInfoList) {
        this.replyInfoList = replyInfoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


}
