package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: IncomeValidator
 * @Description: 收入校验器
 * @author 小飞
 * @date 2016年12月29日 下午4:33:33
 *
 */
public class IncomeValidator extends AbsValidator {

    public Result checkListByType(HttpServletRequest req) {
        String id = req.getParameter("storeId");
        if (StringUtils.isBlank(id)) {
            return Result.build(400, "storeId值为空");
        }
        String type = req.getParameter("type");
        if (StringUtils.isBlank(type)) {
            return Result.build(400, "type值为空");
        }
        String currentNum = req.getParameter("currentNum");
        if (StringUtils.isBlank(currentNum)) {
            return Result.build(400, "currentNum值为空");
        }
        String pageSize = req.getParameter("pageSize");
        if (StringUtils.isBlank(pageSize)) {
            return Result.build(400, "pageSize值为空");
        }
        return Result.success();
    }

    public Result checkRefundList(HttpServletRequest req) {
        String id = req.getParameter("storeId");
        if (StringUtils.isBlank(id)) {
            return Result.build(400, "storeId值为空");
        }
        String currentNum = req.getParameter("currentNum");
        if (StringUtils.isBlank(currentNum)) {
            return Result.build(400, "currentNum值为空");
        }
        String pageSize = req.getParameter("pageSize");
        if (StringUtils.isBlank(pageSize)) {
            return Result.build(400, "pageSize值为空");
        }
        return Result.success();
    }

}
