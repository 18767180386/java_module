package com.admin.entity;

import java.util.Date;

public class OccupationPicInfo {
    private long picid;
    private int occupationId;
    private String thumbnailUrl;
    private String picUrl;
    private Date addtime;



    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public long getPicid() {
        return picid;
    }

    public void setPicid(long picid) {
        this.picid = picid;
    }

}
