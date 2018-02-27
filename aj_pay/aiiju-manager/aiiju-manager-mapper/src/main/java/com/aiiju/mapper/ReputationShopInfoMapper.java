package com.aiiju.mapper;

import com.aiiju.pojo.ReputationShopInfo;

/** 
 * @ClassName ReputationCategoryMapper
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:14:13
 */
public interface ReputationShopInfoMapper {

	/**
	 * 创建店铺（本地数据库+支付宝）
	 * @param 
	 * @return
	 */
	public void  add(ReputationShopInfo reputationShopInfo);
	
	/**
	 * 更新店铺（本地数据库+支付宝）
	 * @param 
	 * @return
	 */
	public void  update(ReputationShopInfo reputationShopInfo);
	
	/**
	 * 更新（支付宝创店返回的结果信息）
	 * @param 
	 * @return
	 */
	public void  updateCreateShopResultMessage(ReputationShopInfo reputationShopInfo);
	
//	public ReputationShopInfo getReputationShopInfo(String storeId);
	
	public ReputationShopInfo getShopInfoByStoreId(String storeId);
}
