package com.aiiju.pay.validator;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
/**
 * 
 * @ClassName: RefundDealValidator 
 * @Description: 退款订单校验器
 * @author 小飞 
 * @date 2017年2月10日 下午2:33:30 
 *
 */
public class RefundDealValidator implements DealValidator {

	@Override
	public Result checkParameter(HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		if (StringUtils.isBlank(storeId)) {
			return Result.build(400, "storeId值为空");
		}
		String originalOutTradeNo = request.getParameter("originalOutTradeNo");
		if (StringUtils.isBlank(originalOutTradeNo)) {
			return Result.build(400, "originalOutTradeNo值为空");
		}
//		String refundType = request.getParameter("refundType");
//		if (StringUtils.isBlank(refundType)) {
//			return Result.build(400, "refundType值为空");
//		}
		String totalFeeStr = request.getParameter("totalFee");
		if (StringUtils.isBlank(totalFeeStr)) {
			return Result.build(400, "totalFee值为空");
		}
		BigDecimal totalFee = null;
		try {
			totalFee = new BigDecimal(totalFeeStr);
		} catch (Exception e) {
			return Result.build(400, "totalFee值格式错误");
		}
		String refundFeeStr = request.getParameter("refundFee");
		if (StringUtils.isBlank(refundFeeStr)) {
			return Result.build(400, "refundFee值为空");
		}
		BigDecimal refundFee = null;
		try {
			refundFee = new BigDecimal(refundFeeStr);
		} catch (Exception e) {
			return Result.build(400, "refundFee值格式错误");
		}
		
		if (refundFee.subtract(totalFee).doubleValue() > 0) {
			return Result.build(400, "refundFee值大于totalFee值");
		}
		
		String operatorId = request.getParameter("operatorId");
		if (StringUtils.isBlank(operatorId)) {
			return Result.build(400, "operatorId值为空");
		}
		String operatorName = request.getParameter("operatorName");
		if (StringUtils.isBlank(operatorName)) {
			return Result.build(400, "operatorName值为空");
		}
		return Result.success();
	}

}
