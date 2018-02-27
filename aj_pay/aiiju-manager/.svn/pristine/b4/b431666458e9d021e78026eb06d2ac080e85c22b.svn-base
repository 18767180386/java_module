package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Coupon;

/**
 * 
 * @ClassName: CouponMapper 
 * @Description: 优惠券 Mapper
 * @author 小飞 
 * @date 2016年11月11日 下午1:23:15 
 *
 */
public interface CouponMapper {

	/**
	 * 保存
	 * @param coupon
	 */
	public void add(Coupon coupon);
	/**
	 * 修改
	 * @param coupon
	 */
	public void update(Coupon coupon);
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
	public Coupon getById(Long id);
	/**
	 * 通过shopId获取对象列表
	 * @param shopId
	 * @return
	 */
	public List<Coupon> getAllByStoreId(Map<String, String> map);
	
	/**
	 * 通过会员id获取优惠券列表
	 * @param memberId
	 * @return
	 */
	public List<Coupon> getByMemberId(Long memberId);
}
