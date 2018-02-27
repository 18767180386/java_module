package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: MPoints
 * @Description: 会员积分
 * @author 宗介
 * @date 2017年5月15日 上午11:17:21
 *
 */
public class MPointsDetail implements Serializable {

    private static final long serialVersionUID = 2264899787642344255L;

    private Long id;

    /**
     * 会员号
     */
    private String phone;
    
    /**
     *商品编号
     */
    private String storeId;
    
    /**
     *交易编号
     */
    private String dealSerialNum;
    
    /**
     * 交易时间
     */
    private Date dealTime;
    
    /**
     *获取积分
     */
    private int getPointsValue;  
    
    /**
     * 使用积分
     */
    private int usedPointsValue;
    
    
    
    /**
     * 有效状态；1有效；0无效
     */
    private String status;

    
    
    
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getDealSerialNum() {
		return dealSerialNum;
	}

	public void setDealSerialNum(String dealSerialNum) {
		this.dealSerialNum = dealSerialNum;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public int getGetPointsValue() {
		return getPointsValue;
	}

	public void setGetPointsValue(int getPointsValue) {
		this.getPointsValue = getPointsValue;
	}

	public int getUsedPointsValue() {
		return usedPointsValue;
	}

	public void setUsedPointsValue(int usedPointsValue) {
		this.usedPointsValue = usedPointsValue;
	}

    
}
