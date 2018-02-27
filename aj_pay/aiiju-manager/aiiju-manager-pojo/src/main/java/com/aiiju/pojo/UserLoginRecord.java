package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: UserLoginRecord
 * @Description: 登录退出状态
 * @author 宗介
 * @date 2017年5月22日 下午3:34:10
 *
 */
public class UserLoginRecord implements Serializable {

    private static final long serialVersionUID = -5491523141180867465L;

    /**
     * id
     */
    private Long id;


    /**
     * 手机号
     */
    private String phone;

   
    /**
     * 店铺编号
     */
    private String storeId;




    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 退出时间
     */
    private Date logoutTime;
    
    
    /**
     * 在线时长
     */
    private int onlineTime;
    
    
    //当前状态；1在线，0离线
    private String currentStatus;
    
    private String  phoneType;
    
    private String OS;
    
    private String phoneId;

    private String token;
    
    
    private String equipmentCode;
    
    
    /**
     * user表主键
     */
    private long userId;
    
    
    
    

	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogouTime() {
		return logoutTime;
	}

	public void setLogouTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public int getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


    
}
