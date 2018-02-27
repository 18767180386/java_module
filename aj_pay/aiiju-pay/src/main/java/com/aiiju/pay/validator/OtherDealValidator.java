package com.aiiju.pay.validator;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.pojo.DealDetail;

/**
 * 
 * @ClassName: OtherDealValidator
 * @Description: 条码支付、扫卡支付 订单校验器
 * @author 小飞
 * @date 2017年1月3日 上午9:09:11
 *
 */
public class OtherDealValidator implements DealValidator {

    private static Logger logger = Logger.getLogger(OtherDealValidator.class);

    @Override
    public Result checkParameter(HttpServletRequest request) {
        String storeId = request.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }

        String priceStr = request.getParameter("price");
        if (StringUtils.isBlank(priceStr)) {
            return Result.build(400, "price值为空");
        }
        BigDecimal price = null;
        try {
            price = new BigDecimal(priceStr);
        } catch (Exception e) {
            return Result.build(400, "price值格式错误");
        }

        String sumPriceStr = request.getParameter("sumPrice");
        if (StringUtils.isBlank(sumPriceStr)) {
            return Result.build(400, "sumPrice值为空");
        }
        BigDecimal sumPrice = null;
        try {
            sumPrice = new BigDecimal(sumPriceStr);
        } catch (Exception e) {
            return Result.build(400, "sumPrice值格式错误");
        }

        String privType = request.getParameter("privType");
        if (StringUtils.isBlank(privType)) {
            return Result.build(400, "privType值为空");
        }

        String privPriceStr = request.getParameter("privPrice");
        if (StringUtils.isBlank(privPriceStr)) {
            return Result.build(400, "privPrice值为空");
        }
        BigDecimal privPrice = null;
        try {
            privPrice = new BigDecimal(privPriceStr);
        } catch (Exception e) {
            return Result.build(400, "privPrice值格式错误");
        }

        String shouldPriceStr = request.getParameter("shouldPrice");
        if (StringUtils.isBlank(shouldPriceStr)) {
            return Result.build(400, "shouldPrice值为空");
        }
        BigDecimal shouldPrice = null;
        try {
            shouldPrice = new BigDecimal(shouldPriceStr);
        } catch (Exception e) {
            return Result.build(400, "shouldPrice值格式错误");
        }

        String actualPriceStr = request.getParameter("actualPrice");
        if (StringUtils.isBlank(actualPriceStr)) {
            return Result.build(400, "actualPrice值为空");
        }
        BigDecimal actualPrice = null;
        try {
            actualPrice = new BigDecimal(actualPriceStr);
        } catch (Exception e) {
            return Result.build(400, "actualPrice值格式错误");
        }

        String changePriceStr = request.getParameter("changePrice");
        if (StringUtils.isBlank(changePriceStr)) {
            return Result.build(400, "changePrice值为空");
        }
        BigDecimal changePrice = null;
        try {
            changePrice = new BigDecimal(changePriceStr);
        } catch (Exception e) {
            return Result.build(400, "changePrice值格式错误");
        }

        String roundPriceStr = request.getParameter("roundPrice");
        if (StringUtils.isBlank(roundPriceStr)) {
            return Result.build(400, "roundPrice值为空");
        }
        BigDecimal roundPrice = null;
        try {
            roundPrice = new BigDecimal(roundPriceStr);
        } catch (Exception e) {
            return Result.build(400, "roundPrice值格式错误");
        }

        String operatorId = request.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }

        String operatorName = request.getParameter("operatorName");
        if (StringUtils.isBlank(operatorName)) {
            return Result.build(400, "operatorName值为空");
        }

//        String detail = request.getParameter("detail");
//        if (StringUtils.isBlank(detail)) {
//            return Result.build(400, "detail值为空");
//        }
//        try {
//            JsonUtils.jsonToList(detail, DealDetail.class);
//        } catch (Exception e) {
//            return Result.build(400, "detail值格式错误");
//        }

        // 金额校验
        if (price.doubleValue() != sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue()) {
            logger.error("price:" + price.doubleValue());
            logger.error("sumPrice:" + sumPrice.doubleValue());
            logger.error("privPrice:" + privPrice.doubleValue());
            logger.error("roundPrice:" + roundPrice.doubleValue());
            logger.error("sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue():"
                    + (sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue()));
            return Result.build(400, "金额校验错误");
        }
        if (changePrice.doubleValue() != actualPrice.subtract(shouldPrice).doubleValue()) {
            logger.error("changePrice:" + changePrice.doubleValue());
            logger.error("actualPrice:" + actualPrice.doubleValue());
            logger.error("shouldPrice:" + shouldPrice.doubleValue());
            logger.error("actualPrice.subtract(shouldPrice).doubleValue():" + (actualPrice.subtract(shouldPrice).doubleValue()));
            return Result.build(400, "金额校验错误");
        }

        return Result.success();
    }
    
    
     public static void main(String[] args) {
    	  BigDecimal roundPrice = new BigDecimal("0.00");
    	  BigDecimal sumPrice = new BigDecimal("19.73");
    	  BigDecimal privPrice = new BigDecimal("9.86");
    	  BigDecimal price = new BigDecimal("9.86");
    	  
    	     if (price.doubleValue() != sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue()) {
    	            logger.error("price:" + price.doubleValue());
    	            logger.error("sumPrice:" + sumPrice.doubleValue());
    	            logger.error("privPrice:" + privPrice.doubleValue());
    	            logger.error("roundPrice:" + roundPrice.doubleValue());
    	            logger.error("sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue():"
    	                    + (sumPrice.subtract(privPrice).subtract(roundPrice).doubleValue()));
    	            System.out.println("金额校验错误");
    	        }else{
    	        	 System.out.println("金额正确");
    	        }
    	  
    	     System.out.println(roundPrice.doubleValue());
	}
}
