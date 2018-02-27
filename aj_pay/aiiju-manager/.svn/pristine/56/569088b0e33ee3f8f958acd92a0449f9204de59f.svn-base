package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: DealAccount
 * @Description: 交易对应付款账户信息
 * @author zong 
 * @date 2017年6月17日 上午11:03:20 
 *
 */
public class DealAccount implements Serializable{

	private static final long serialVersionUID = 8766525772371551317L;
	
	private Integer id;
	
	/**
	 * 交易表主键id
	 */
	private String dealId;
	/**
	 * 交易表deal序列号
	 */
	private String dealSerialNum;
	
	/**
	 * 授权商户的ID       2088开头
	 */
	private String userId;
	/**
	 * 授权商户的AppId 应用id
	 */
	private String authAppId;
	
	/**
	 * /商户授权令牌   
	 */
	private String appAuthToken;
	private String subMchId;

	private String subAppId;

	/**
	 * 店铺编号
	 */
	private String storeId;
	
	private Date createDate;
	
	private String openId;
	private String openKey;
	
	private String tradeAccount;
	
	
	
//	public static DealAccount getDealAccount(Deal deal,AppAuth auth){
//		
//		DealAccount da = new DealAccount();
//		
//		da.setAppAuthToken(auth.getAppAuthToken());
//		da.setAuthAppId(auth.getAuthAppId());
//		da.setCreateDate(new Date());
//		da.setDealId(deal.getId()+"");
//		da.setDealSerialNum(deal.getSerialNum());
//		da.setStoreId(auth.getStoreId());
//		da.setUserId(auth.getUserId());
//	
//		return da;
//	}
//	
	public static DealAccount getDealAccount(Deal deal,AppAuth auth,WxSub wxSub){
		
		DealAccount da = new DealAccount();
		da.setCreateDate(new Date());
		da.setDealId(deal.getId()+"");
		da.setDealSerialNum(deal.getSerialNum());
		
		if(wxSub!=null){
			da.setStoreId(wxSub.getStoreId());
			da.setSubAppId(wxSub.getSubAppId());
			da.setSubMchId(wxSub.getSubMchId());
		}
		if(auth!=null){
			da.setAppAuthToken(auth.getAppAuthToken());
			da.setAuthAppId(auth.getAuthAppId());
			da.setStoreId(auth.getStoreId());
			da.setUserId(auth.getUserId());
		}

		return da;
	}
	
	
	public static DealAccount getDealAccount(Deal deal,Order order){
		
		DealAccount da = new DealAccount();
		da.setCreateDate(new Date());
		da.setDealId(deal.getId()+"");
		da.setDealSerialNum(deal.getSerialNum());
		da.setStoreId(deal.getStoreId());
		if(order!=null){
			da.setOpenId(order.getOpenId());
			da.setOpenKey(order.getOpenKey());
			da.setTradeAccount(order.getTradeAccount());
		}
		

		return da;
	}
	
	


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String getOpenKey() {
		return openKey;
	}


	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}


	public String getTradeAccount() {
		return tradeAccount;
	}


	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}


	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealSerialNum() {
		return dealSerialNum;
	}

	public void setDealSerialNum(String dealSerialNum) {
		this.dealSerialNum = dealSerialNum;
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

	public String getAppAuthToken() {
		return appAuthToken;
	}

	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
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
	

	
	
}
