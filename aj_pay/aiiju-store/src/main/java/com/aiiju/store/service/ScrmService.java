package com.aiiju.store.service;

import java.util.Map;

import com.aiiju.common.pojo.Result;
/**
 * 
 * @ClassName: ScrmService 
 * @Description: scrm对接接口
 * @author qiyu 
 * @date 2016年11月28日 下午3:58:19 
 *
 */
public interface ScrmService {
	
	/**
	 * 获取scrm会员卡信息
     * @param  query 			手机号或者会员卡号
     * @param  shopId   			爱聚收银店铺的storeId
     * @return
	 */
	public Result getScrmMemberInfo(Map<String,Object> map);

	/**
	 * 店铺是否关联scrm公司
	 * @param  storeId   店铺的id
	 * @return 
	 */
	
	public Result getRelativeScrm(Map<String, Object> map);
	
	/**
	 * 解除 scrm
	 * @param  storeIds   剩下绑定的店铺storeIds,多个逗号隔开
	 * @param  scrmId    	SCRM公司
	 * @return 
	 */
	public Result updateUnwrapScrm(Map<String, Object> map);
	
	
	/**
	 * 解除 scrm
	 * @param  storeIds   店铺的id,多个逗号隔开
	 * @param  type   1.解绑 ,2.绑定
	 * @param  scrmId    	SCRM公司；当type为2时，必传
	 * @return 
	 */
	public Result updateBindScrm(Map<String, Object> map);

	
	/**
     * 店铺列表和scrm绑定状态
     * @param pStoreId    操作店铺的父店铺storeId
     * @param scrmId    SCRM公司id
     * @param role    店铺角色  
     * @return
     */
	public Result getRelativeScrmShopList(Map<String, Object> map);

	public Result updateScrmGrant(Map<String, Object> map);
}