package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;
/**
 * 
* @ClassName: MCardValidator 
* @Description: 会员卡校验器
* @author 小飞 
* @date 2017年2月21日 上午10:40:53
 */
public class MCardValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String name = req.getParameter("name");
        if (StringUtils.isBlank(name)) {
            return Result.build(400, "name值为空");
        }

        String discount = req.getParameter("discount");
        if (StringUtils.isBlank(discount)) {
            return Result.build(400, "discount值为空");
        }

        String limitTime = req.getParameter("limitTime");
        if (StringUtils.isBlank(limitTime)) {
            return Result.build(400, "limitTime值为空");
        }
        
        if ("2".equals(limitTime)) {
            String startDate = req.getParameter("startDate");
            if (StringUtils.isBlank(startDate)) {
                return Result.build(400, "有期限的会员卡,startDate值为空");
            }
            String endDate = req.getParameter("endDate");
            if (StringUtils.isBlank(endDate)) {
                return Result.build(400, "有期限的会员卡,endDate值为空");
            }
        }
        

        String status = req.getParameter("status");
        if (StringUtils.isBlank(status)) {
            return Result.build(400, "status值为空");
        }
        
        String type = req.getParameter("type");
        if (StringUtils.isBlank(type)) {
            return Result.build(400, "type值为空");
        }
        
        return Result.success();
    }
}
