package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;
/**
 * 
 * @ClassName: ShopValidator 
 * @Description: 店铺校验器
 * @author 小飞 
 * @date 2016年12月29日 下午4:34:03 
 *
 */
public class ShopValidator extends AbsValidator {

	@Override
	public Result checkSave(HttpServletRequest req) {
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickName");
		if (StringUtils.isBlank(name)) {
			return Result.build(400, "name值为空");
		}
		if (StringUtils.isBlank(nickName)) {
			return Result.build(400, "nickName值为空");
		}
		return Result.success();
	}

}
