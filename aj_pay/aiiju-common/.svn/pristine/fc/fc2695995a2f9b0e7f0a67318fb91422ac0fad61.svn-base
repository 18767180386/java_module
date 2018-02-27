package com.aiiju.common.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;

/**
 * 
 * @ClassName: AbsValidator
 * @Description: 校验抽象类
 * @author 小飞
 * @date 2016年12月29日 下午4:04:38
 *
 */
public abstract class AbsValidator implements Validator {

    @Override
    public Result checkGet(HttpServletRequest req) {
        return checkId(req);
    }

    @Override
    public Result checkList(HttpServletRequest req) {
        return checkStoreId(req);
    }

    @Override
    public Result checkUpdate(HttpServletRequest req) {
        return checkId(req);
    }

    @Override
    public Result checkDelete(HttpServletRequest req) {
        return checkId(req);
    }

    @Override
    public Result checkSave(HttpServletRequest req) {
        return Result.success();
    }

//    @Override
//    public Result checkListByOther(HttpServletRequest req) {
//        return Result.success();
//    }

    protected Result checkId(HttpServletRequest req) {
        String id = req.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return Result.build(400, "id值为空");
        }
        return Result.success();
    }

    protected Result checkStoreId(HttpServletRequest req) {
        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        return Result.success();
    }

	public  Result checkSynchronize(HttpServletRequest req) {
		String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        return Result.success();
	}
}
