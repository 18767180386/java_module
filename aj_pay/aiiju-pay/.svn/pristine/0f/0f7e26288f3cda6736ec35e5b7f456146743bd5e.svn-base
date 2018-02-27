package com.aiiju.pay.validator;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
/**
 * 
 * @ClassName: DecometerDealValidator 
 * @Description: 台卡收款 订单校验器
 * @author 小飞 
 * @date 2017年1月3日 上午9:08:52 
 *
 */
public class DecometerDealValidator implements DealValidator{

	public Result checkParameter(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		if (StringUtils.isBlank(storeId)) {
			return Result.build(400, "storeId值为空");
		}
		String shopName = request.getParameter("shopName");
		if (StringUtils.isBlank(shopName)) {
			return Result.build(400, "shopName值为空");
		}
		String price = request.getParameter("price");
		if (StringUtils.isBlank(price)) {
			return Result.build(400, "price值为空");
		}
		try {
			new BigDecimal(price);
		} catch (Exception e) {
			return Result.build(400, "price值格式错误");
		}
		return Result.success();
	}
}
