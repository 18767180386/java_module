package com.aiiju.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.CouponMapper;
import com.aiiju.pojo.Coupon;
import com.aiiju.store.service.CouponService;
/**
 * 
 * @ClassName: CouponServiceImpl 
 * @Description: 优惠券 ServiceImpl
 * @author 小飞 
 * @date 2016年11月11日 下午1:49:53 
 *
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapper couponMapper;
	
	@Override
	public Result save(Coupon coupon) {
		long useDate = coupon.getUseDate().getTime();
		long now = System.currentTimeMillis();
		if (useDate < now) {
			coupon.setTimeStatus(Byte.parseByte("1")); //1:未开始 2：进行中 3：已结束
		} else {
			coupon.setTimeStatus(Byte.parseByte("2"));
		}
		coupon.setUseStatus(Byte.parseByte("1"));//1：可使用  2：禁用
		this.couponMapper.add(coupon);
		return Result.success(true);
	}

	@Override
	public Result update(Coupon coupon) {
		this.couponMapper.update(coupon);
		return Result.success(true);
	}

	@Override
	public Result getById(Long id) {
		Coupon coupon = this.couponMapper.getById(id);
		return Result.success(coupon);
	}

	@Override
	public Result getAllByStoreId(String storeId,Byte timeStatus) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("storeId",storeId);
		if (timeStatus != null) {
			map.put("timeStatus", String.valueOf(timeStatus));
		}
		List<Coupon> list = this.couponMapper.getAllByStoreId(map);
		return Result.success(list);
	}
}
