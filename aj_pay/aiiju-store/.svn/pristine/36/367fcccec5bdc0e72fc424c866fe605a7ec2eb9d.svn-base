package com.aiiju.store.validator;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: GoodsValidator 
 * @Description: 商品校验器
 * @author 小飞 
 * @date 2016年12月29日 下午4:44:50 
 *
 */
public class GoodsValidator extends AbsValidator {

	@Override
	public Result checkList(HttpServletRequest req) {
		String goodsTypeId = req.getParameter("goodsTypeId");
		if (StringUtils.isBlank(goodsTypeId)) {
			return Result.build(400, "goodsTypeId值为空");
		}
		return Result.success();
	}
	
	@Override
	public Result checkDelete(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if (StringUtils.isBlank(ids)) {
			return Result.build(400, "ids值为空");
		}
		return Result.success();
	}
	
	@Override
	public Result checkSave(HttpServletRequest req) {
		String name = req.getParameter("name");
		if (StringUtils.isBlank(name)) {
			return Result.build(400, "name值为空");
		}
		String goodsTypeId = req.getParameter("goodsTypeId");
		if (StringUtils.isBlank(goodsTypeId)) {
			return Result.build(400, "goodsTypeId值为空");
		}
		String price = req.getParameter("price");
		if (StringUtils.isBlank(price)) {
			return Result.build(400, "price值为空");
		}
		String unit = req.getParameter("unit");
		if (StringUtils.isBlank(unit)) {
			return Result.build(400, "unit为空");
		}
		try {
			new BigDecimal(price);
		} catch (Exception e) {
			return Result.build(400, "price值格式错误");
		}
		return Result.success();
	}
	
	
	
	public Result checkUpload(HttpServletRequest req) {
		
		return Result.success();
	}
	
	
	@Override
	public Result checkSynchronize(HttpServletRequest req) {
		String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        return Result.success();
	}
	
}
