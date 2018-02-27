package com.aiiju.pay.service;

import java.util.Map;

import com.aiiju.pojo.Shop;

/**
 * 
 * @ClassName: ShopService 
 * @Description: 店铺 Service
 * @author 小飞 
 * @date 2016年11月9日 下午5:02:50 
 *
 */
public interface ShopService {

	public String getShopName(String storeId);

	public Shop getShopByStoreId(String storeId);
	
	/**
	 * 判断这个店铺是否需要 把新增的商品信息发送给ERP系统
	 * @param storeId 店铺的id
	 * @return TRUE 店铺关联了ERP系统，FALSE店铺没有关联ERP系统
	 */
	public boolean relativeERP(String storeId);
	
	
	/**
	 * 判断时否绑定scrm
	 * @param storeId 店铺的id
	 * @return 
	 */
	String relativeSCRM(Map<String, Object> map);
	
}
