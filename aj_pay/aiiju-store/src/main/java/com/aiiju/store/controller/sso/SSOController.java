package com.aiiju.store.controller.sso;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.User;
import com.aiiju.pojo.UserLoginRecord;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.BusinessSwitchService;
import com.aiiju.store.service.UserService;

/**
 * 
 * @ClassName: SSOController
 * @Description: 登录注册相关Controller
 * @author 小飞
 * @date 2016年11月16日 下午1:20:06
 *
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

    private static Logger logger = Logger.getLogger(SSOController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private BusinessSwitchService businessSwitchService;
    

    @RequestMapping("/isExist")
    @ResponseBody
    public Object isExist(String param, Integer type) {
        Result result = null;
        // 参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = Result.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = Result.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = Result.build(400, "校验内容类型错误");
        }
        // 校验出错
        if (null != result) {
            return result;
        }
        // 调用服务
        try {
            result = this.userService.checkUser(param, type);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = Result.build(500, WebConstant.SYS_ERROR);
        }
        return result;
    }

    // 创建用户
    @RequestMapping(value = "/register")
    @ResponseBody
    public Result registerUser(User user) {
        try {
            Result result = null;
            if (user.getStoreId() == null) {
                result = this.userService.save(user);
            } else {
                result = this.userService.saveSeller(user);
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    // 创建用户
    @RequestMapping(value = "/createAdmin")
    @ResponseBody
    public Result createAdmin(User user) {
        try {
        	Result isOpen = businessSwitchService.getCreateAdminSwitch();
        	
        	if(isOpen.getStatus()==200){
        		  Result result = this.userService.createAdmin(user);
        		  return result;
        	}else{
        		return Result.build(500, isOpen.getMsg());
        	}
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    // 用户登录
    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(String userName, String password, String equipmentCode,String phoneType,String OS,String phoneId) {
        try {
        	
        	if(equipmentCode==null||"".equals(equipmentCode)){
        		 return Result.build(500, "登录时参数异常，设备号为空");
        	}
        	
            Result result = this.userService.login(userName, password, equipmentCode,phoneType,OS,phoneId);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout(String token, String equipmentCode,Long id) {
        try {
            return this.userService.logout(token, equipmentCode,id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    // 修改密码
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Result updatePassword(String userName, String oldPassword, String newPassword) {
        try {
            Result result = this.userService.updatePWD(userName, oldPassword, newPassword);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    /**
     * 忘记密码
     * 
     * @param userName
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/newPassword")
    @ResponseBody
    public Result newPassword(String userName, String newPassword, String code) {
        try {
            Result rt = this.userService.checkCode(userName, code);
            if (rt.getStatus() != 200) {
                return rt;
            }
            return this.userService.updatePWD(userName, newPassword);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping(value = "/checkToken")
    @ResponseBody
    public Result checkToken(String token) {
        try {
            return this.userService.checkToken(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    @RequestMapping(value = "/updateUserOnlineStatus")
    @ResponseBody
    public Result saveOrUpdateUserLoginRecord(UserLoginRecord userLoginRecord) {
        try {
            return this.userService.saveOrUpdateUserLoginRecord(userLoginRecord);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
}