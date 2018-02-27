package com.aiiju.store.controller.rest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Coupon;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.CouponService;
/**
 * 
 * @ClassName: CouponController 
 * @Description: 优惠券控制器
 * @author 小飞 
 * @date 2016年11月17日 上午11:37:37 
 *
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

	private static Logger logger = Logger.getLogger(CouponController.class);
	
	@Autowired
	private CouponService couponService;

	@RequestMapping("/get")
	@ResponseBody
	public Result get(Long id) {
		try {
			return this.couponService.getById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/list")
	@ResponseBody
	public Result gets(String storeId,Byte timeStatus) {
		try {
			return this.couponService.getAllByStoreId(storeId,timeStatus);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(Coupon coupon) {
		try {
			return this.couponService.save(coupon);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(Coupon coupon) {
		try {
			return this.couponService.update(coupon);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
}
