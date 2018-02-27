package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: BookingManage 
 * @Description: 预约管理
 * @author 乔巴
 * @date 2017年08月23日  
 *
 */
public class BookingManage implements Serializable{

	private static final long serialVersionUID = -5796894628834714656L;
	
	/**
	 * 主鍵id
	 */
	private Long id;
	/**
	 * 创建预约订单的用户id
	 */
	private Integer createUserId;
	/**
	 * 店鋪编号
	 */
	private String storeId;
	/**
	 * 父店编号 
	 */
	private String parentStoreId;
	/**
	 * 操作者id
	 */
	private String operatorId;
	/**
	 * 预约状态
	 *  "0"代表已预约,"1"代表进行中,"2"代表已完成
	 */
	private String bookingStatus;
	/**
	 * 顾客姓名
	 */
	private String customerName;
	/**
	 * 顾客性别
	 */
	private String customerSex;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 已付定金
	 */
	private BigDecimal paid;
	/**
	 * 待付尾款
	 */
	private BigDecimal  unpay;
	/**
	 * 每一份新订单的总金额
	 */
	private BigDecimal oneTotal;
	/**
	 * 开票人(纹身师)
	 */
	private String drawer;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 预约提醒时间
	 */
	private Date sendTime;
	
	/**
	 * 预约提醒是否推送 （0，未推送，1已推送s）
	 */
	private String sendStatus;
	/**
	 * 推送状态设置 
	 * '0'代表未推送,'1'代表已推送
	 */
	private String pushs;
	
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getPushs() {
		return pushs;
	}
	public void setPushs(String pushs) {
		this.pushs = pushs;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getParentStoreId() {
		return parentStoreId;
	}
	public void setParentStoreId(String parentStoreId) {
		this.parentStoreId = parentStoreId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getDrawer() {
		return drawer;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public BigDecimal getPaid() {
		return paid;
	}
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}
	public BigDecimal getUnpay() {
		return unpay;
	}
	public void setUnpay(BigDecimal unpay) {
		this.unpay = unpay;
	}
	public BigDecimal getOneTotal() {
		return oneTotal;
	}
	public void setOneTotal(BigDecimal oneTotal) {
		this.oneTotal = oneTotal;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "BookingManage [id=" + id + ", createUserId=" + createUserId + ", storeId=" + storeId
				+ ", parentStoreId=" + parentStoreId + ", operatorId=" + operatorId + ", bookingStatus=" + bookingStatus
				+ ", customerName=" + customerName + ", customerSex=" + customerSex + ", contactWay=" + contactWay
				+ ", paid=" + paid + ", unpay=" + unpay + ", oneTotal=" + oneTotal + ", drawer=" + drawer + ", phone="
				+ phone + ", remark=" + remark + ", beginTime=" + beginTime + ", endTime=" + endTime + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", sendTime=" + sendTime + ", sendStatus=" + sendStatus
				+ ", pushs=" + pushs + "]";
	}
}
