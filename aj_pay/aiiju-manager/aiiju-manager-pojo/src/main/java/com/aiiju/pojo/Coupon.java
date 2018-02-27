package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @ClassName: Coupon 
 * @Description: 优惠券（未使用）
 * @author 小飞 
 * @date 2016年11月8日 上午11:42:45 
 *
 */
public class Coupon implements Serializable{

	private static final long serialVersionUID = 8295486979479586627L;
	
	private Long id;
	/**
	 * 优惠券名称		
	 */
	private String name;
	/**
	 * 发放总量
	 */
	private Integer num;
	/**
	 * 优惠面值
	 */
	private String couponValue;
	/**
	 * 生效时间
	 */
	private Date useDate;
	/**
	 * 过期时间
	 */
	private Date expireDate;
	/**
	 * 每人限领
	 */
	private Integer limitNum;
	/**
	 * 是否到期前4天提醒客户一次  1:是 2：否
	 */
	private Byte isNotice;
	/**
	 * 是否允许分享领取链接 1:是 2：否
	 */
	private Byte isShare;
	/**
	 * 使用说明
	 */
	private String remark;
	/**
	 * 状态（与时间相关） 1:未开始 2：进行中 3：已结束
	 */
	private Byte timeStatus;
	/**
	 * 使用状态（与发放相关） 1：可使用  2：禁用
	 */
	private Byte useStatus;
	/**
	 * 店铺编号
	 */
	private String storeId;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date modifyDate;
	
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public Byte getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(Byte isNotice) {
		this.isNotice = isNotice;
	}
	public Byte getIsShare() {
		return isShare;
	}
	public void setIsShare(Byte isShare) {
		this.isShare = isShare;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Byte getTimeStatus() {
		return timeStatus;
	}
	public void setTimeStatus(Byte timeStatus) {
		this.timeStatus = timeStatus;
	}
	public Byte getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Byte useStatus) {
		this.useStatus = useStatus;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
