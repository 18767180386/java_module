package com.aiiju.pojo;

import java.util.List;

/** 
 * @ClassName UserShop
 * @Description
 * @author zong
 * @CreateDate 2017年6月11日 上午11:40:30
 */
public class UserShop {
	
   /**
     * id
     */
    private int id;

    /**
     * userId(user主键)
     */
    private int userId;

    /**
     * shopId(shop主键)
     */
    private int shopId;
   
    /**
     * 旗下的所有普通商铺
     */
    private List<Shop> shopList;
   
    /**
     * reputationShopInfo主键id
     */
    private int reputationShopInfoId;
    
    /**
     * 旗下的所有口碑商铺
     */
    private List<ReputationShopInfo> reputationShopInfoList;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getShopId() {
		return shopId;
	}


	public void setShopId(int shopId) {
		this.shopId = shopId;
	}


	public List<Shop> getShopList() {
		return shopList;
	}


	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}


	public int getReputationShopInfoId() {
		return reputationShopInfoId;
	}


	public void setReputationShopInfoId(int reputationShopInfoId) {
		this.reputationShopInfoId = reputationShopInfoId;
	}


	public List<ReputationShopInfo> getReputationShopInfoList() {
		return reputationShopInfoList;
	}


	public void setReputationShopInfoList(List<ReputationShopInfo> reputationShopInfoList) {
		this.reputationShopInfoList = reputationShopInfoList;
	}
    
    
    
    
    
    
    
}
