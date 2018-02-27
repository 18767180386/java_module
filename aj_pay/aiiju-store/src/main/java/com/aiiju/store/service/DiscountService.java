package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Discount;
/**
 * 
 * @ClassName: DiscountService 
 * @Description: 优惠折扣Service
 * @author 小飞 
 * @date 2016年11月15日 下午3:00:31 
 *
 */
public interface DiscountService {

	public Result save(String storeId, Discount discount);
	
	public Result getAllByStoreId(String storeId, String operatorId);
	
	
	public Result getSingleDiscountByStoreId(String storeId,String type);
	
	public Result updateSwitch(String storeId,String type);
	
	/**
	 * 删除优惠折扣与店铺的关系
	 * @param map
	 * @throws Exception
	 */
	public Result delete(String storeId,Integer id);
	
	
	
}
