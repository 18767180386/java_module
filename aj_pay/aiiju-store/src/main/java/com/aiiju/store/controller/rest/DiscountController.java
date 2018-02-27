package com.aiiju.store.controller.rest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Discount;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.DiscountService;
/**
 * 
 * @ClassName: DiscountController 
 * @Description: 优惠折扣控制器
 * @author 小飞 
 * @date 2016年11月17日 上午11:02:44 
 *
 */
@Controller
@RequestMapping("/discount")
public class DiscountController {

	private static Logger logger = Logger.getLogger(DiscountController.class);
	
	@Autowired
	private DiscountService discountService;

//	@RequestMapping("/list")
//	@ResponseBody
//	public Result gets(String storeId, String operatorId) {
//		try {
//			return this.discountService.getAllByStoreId(storeId, operatorId);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return Result.build(500,WebConstant.SYS_ERROR);
//		}
//	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Result gets(String storeId, String operatorId) {
		try {
			//参数type为优惠方式
			return this.discountService.getAllByStoreId(storeId, operatorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/singleDiscount")
	@ResponseBody
	public Result getSingleDiscount(String storeId,String type) {
		try {
			//operatorId 目前没用到
			return this.discountService.getSingleDiscountByStoreId(storeId, type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/updateSwitch")
	@ResponseBody
	public Result updateSwitch(String storeId,String type,String Value) {
		try {
			//operatorId 目前没用到
			return this.discountService.updateSwitch(storeId, type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(String storeId,Discount discount) {
		try {
			return this.discountService.save(storeId, discount);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String storeId, Integer id) {
		try {
			return this.discountService.delete(storeId, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
}
