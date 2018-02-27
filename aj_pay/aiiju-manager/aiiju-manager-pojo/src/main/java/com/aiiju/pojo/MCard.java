package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: MCard
 * @Description: 会员卡
 * @author 小飞
 * @date 2016年11月8日 上午11:17:21
 *
 */
public class MCard implements Serializable {

    private static final long serialVersionUID = 2264899787642344255L;

    private Long id;

    /**
     * 会员卡名称
     */
    private String name;

    /**
     * 会员折扣
     */
    private String discount;

    /**
     * 使用须知
     */
    private String notice;

    /**
     * 会员期限 1:无期限 2：有期限
     */
    private Byte limitTime;
    /**
     * 有效期开始时间
     */
    private Date startDate;
    /**
     * 有效期结束时间
     */
    private Date endDate;

    /**
     * 会员卡状态 1:生效 2:失效 3:尚未生效 4：禁用
     */
    private Byte status;
    /**
     * 是否过期  1：未过期  2：过期
     */
    private Byte isPast;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    public Date modifyDate;

    /**
     * 店铺编号
     */
    private String storeId;

    /**
     * 条形码 区分同一张会员卡下不同会员唯一标识
     */
    private String code;
    /**
     * 会员卡类型 1：普通  
     */
    private Byte type;
    /**
     * 该卡下的会员数
     */
    private Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Byte getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Byte limitTime) {
        this.limitTime = limitTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Byte getIsPast() {
        return isPast;
    }

    public void setIsPast(Byte isPast) {
        this.isPast = isPast;
    }
    
    @Override
    public String toString() {
        return "MCard [id=" + id + ", name=" + name + ", discount=" + discount + ", notice=" + notice
                + ", limitTime=" + limitTime + ", startDate=" + startDate + ", endDate=" + endDate
                + ", status=" + status + ", isPast=" + isPast + ", storeId=" + storeId + ", code=" + code
                + ", type=" + type + ", num=" + num + "]";
    }
    
}
