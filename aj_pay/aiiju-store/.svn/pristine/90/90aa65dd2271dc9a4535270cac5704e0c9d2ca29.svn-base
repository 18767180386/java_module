package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;
/**
 * 
* @ClassName: MemberValidator 
* @Description: 会员校验器
* @author 小飞 
* @date 2017年2月21日 上午10:31:30
 */
public class MemberValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String phone = req.getParameter("phone");
        if (StringUtils.isBlank(phone)) {
            return Result.build(400, "phone值为空");
        }

        String mCardId = req.getParameter("mCardId");
        if (StringUtils.isBlank(mCardId)) {
            return Result.build(400, "mCardId值为空");
        }

        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        
        return Result.success();
    }
}
