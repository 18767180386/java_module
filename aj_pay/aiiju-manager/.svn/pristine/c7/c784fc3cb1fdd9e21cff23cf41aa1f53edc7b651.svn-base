package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: AppAuth 
 * @Description: 支付宝 第三方授权
 * @author 小飞 
 * @date 2016年11月29日 上午11:03:20 
 *
 */
public class AppAuth implements Serializable{

	private static final long serialVersionUID = 8766525772371551317L;
	
	private Integer id;
	/**
	 * /商户授权令牌   
	 */
	private String appAuthToken;
	/**
	 * 授权商户的ID       2088开头
	 */
	private String userId;
	/**
	 * 授权商户的AppId 应用id
	 */
	private String authAppId;
	/**
	 * 令牌有效期  
	 */
	private String expiresIn;
	/**
	 * 刷新令牌有效期 
	 */
	private String reExpiresIn;
	/**
	 * 刷新令牌时使用
	 */
	private String appRefreshToken;
	/**
	 * 店铺编号
	 */
	private String storeId;
	
	private Date createDate;
	
	private Date modifyDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppAuthToken() {
		return appAuthToken;
	}
	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthAppId() {
		return authAppId;
	}
	public void setAuthAppId(String authAppId) {
		this.authAppId = authAppId;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getReExpiresIn() {
		return reExpiresIn;
	}
	public void setReExpiresIn(String reExpiresIn) {
		this.reExpiresIn = reExpiresIn;
	}
	public String getAppRefreshToken() {
		return appRefreshToken;
	}
	public void setAppRefreshToken(String appRefreshToken) {
		this.appRefreshToken = appRefreshToken;
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
	
	@Override
	public String toString() {
		return "AppAuth [id=" + id + ", appAuthToken=" + appAuthToken
				+ ", userId=" + userId + ", authAppId=" + authAppId
				+ ", expiresIn=" + expiresIn + ", reExpiresIn=" + reExpiresIn
				+ ", appRefreshToken=" + appRefreshToken + ", storeId="
				+ storeId + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}
	
}
