package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Coupon;
/**
 * 
 * @ClassName: CouponService 
 * @Description: 优惠券 Service
 * @author 小飞 
 * @date 2016年11月11日 下午1:48:14 
 *
 */
public interface CouponService {

	public Result save(Coupon coupon);
	
	public Result update(Coupon coupon);
	
	public Result getById(Long id);
	
	public Result getAllByStoreId(String storeId,Byte timeStatus);
}
