package com.aiiju.store.validator;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: DiscountValidator 
 * @Description: 优惠折扣校验器
 * @author 小飞 
 * @date 2016年12月29日 下午4:55:58 
 *
 */
public class DiscountValidator extends AbsValidator {

	@Override
	public Result checkList(HttpServletRequest req) {
		String storeId = req.getParameter("storeId");
		if (StringUtils.isBlank(storeId)) {
			return Result.build(400, "storeId值为空");
		}
		String operatorId = req.getParameter("operatorId");
		if (StringUtils.isBlank(operatorId)) {
			return Result.build(400, "operatorId值为空");
		}
		return Result.success();
	}
	
	@Override
	public Result checkDelete(HttpServletRequest req) {
		String id = req.getParameter("id");
		if (StringUtils.isBlank(id)) {
			return Result.build(400, "id值为空");
		}
		String storeId = req.getParameter("storeId");
		if (StringUtils.isBlank(storeId)) {
			return Result.build(400, "storeId值为空");
		}
		return Result.success();
	}
	
	@Override
	public Result checkSave(HttpServletRequest req) {
		String storeId = req.getParameter("storeId");
		if (StringUtils.isBlank(storeId)) {
			return Result.build(400, "storeId值为空");
		}
		String type = req.getParameter("type");
		if (StringUtils.isBlank(type)) {
			return Result.build(400, "type值为空");
		}
		String value = req.getParameter("value");
		if (StringUtils.isBlank(value)) {
			return Result.build(400, "value值为空");
		}
		try {
			new BigDecimal(value);
		} catch (Exception e) {
			return Result.build(400, "value值格式错误");
		}
		return Result.success();
	}
	
	
	public Result checkSingleDiscount(HttpServletRequest req){
		return Result.success();
	}
	public Result checkUpdateSwitch(HttpServletRequest req){
		return Result.success();
	}
	
}
