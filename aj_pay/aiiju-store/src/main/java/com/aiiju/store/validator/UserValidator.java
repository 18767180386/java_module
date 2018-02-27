package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: UserValidator
 * @Description: 用户校验器
 * @author 小飞
 * @date 2016年12月29日 下午4:04:50
 *
 */
public class UserValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (StringUtils.isBlank(userName)) {
            return Result.build(400, "userName值为空");
        }
        if (StringUtils.isBlank(password)) {
            return Result.build(400, "password值为空");
        }
        return Result.success();
    }

    @Override
    public Result checkDelete(HttpServletRequest req) {
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        return Result.success();
    }
    public Result checkInviteByNote(HttpServletRequest req) {
        return Result.success();
    }
    public Result checkHeartBeat(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkClerkList(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkAddClerk(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkDeleteClerk(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkUpdateClerk(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkQueryClerkList(HttpServletRequest req) {
         return Result.success();
     }
    public Result checkGrantERP(HttpServletRequest req) {
        return Result.success();
    }
    public Result checkGetGrantStatus(HttpServletRequest req) {
        return Result.success();
    }
    public Result checkWhoIsOnline(HttpServletRequest req) {
        return Result.success();
    }
    public Result checkSendIsOnlineMsg(HttpServletRequest req) {
        return Result.success();
    }
}
