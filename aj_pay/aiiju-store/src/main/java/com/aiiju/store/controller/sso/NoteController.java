package com.aiiju.store.controller.sso;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pojo.User;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.UserService;

/**
 * 
 * @ClassName: NoteController
 * @Description: 短信验证码
 * @author 小飞
 * @date 2016年11月22日 下午2:15:38
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    private static Logger logger = Logger.getLogger(NoteController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 添加用户/修改密码 获取验证码
     * @param request
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Result getCode(HttpServletRequest request) {
        try {
            String phone = request.getParameter("phone");
            Result rt = this.userService.checkUser(phone, 2);
            String type = request.getParameter("type");// 1:注册 2:修改密码
            if ("1".equals(type)) {
                if (Boolean.parseBoolean(rt.getData().toString())) {
                    return Result.build(201, "该手机号已注册", false);
                } else {
                    String codeResult = this.userService.sendVerificationCode(phone);
                    if ("0".equals(codeResult)) {
                        return Result.build(200, "获取成功", true);
                    } else {
                        return Result.build(500, "获取验证码失败", true);
                    }
                }
            } else if ("2".equals(type)) {
                if (Boolean.parseBoolean(rt.getData().toString())) {
                    String codeResult = this.userService.sendVerificationCode(phone);
                    if ("0".equals(codeResult)) {
                        return Result.build(200, "获取成功", true);
                    } else {
                        return Result.build(500, "获取验证码失败", true);
                    }
                } else {
                    return Result.build(202, "该手机号未注册", false);
                }
            }
            return Result.build(400, "type参数类型错误");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    /**
     * 邀请店员时，新用户在h5页面修改密码 获取验证码
     * @param request
     * @return
     */
    @RequestMapping("/getClerkCode")
    @ResponseBody
    public Result getClerkCode(HttpServletRequest request) {
        try {
            String phone = request.getParameter("phone");
            String storeId = request.getParameter("storeId");
            
            System.out.println(phone);
            System.out.println(storeId);
            
            User user = new User();
            user.setPhone(phone);
            user.setStoreId(storeId);
            User userDB = userMapper.getUserByPhoneAndStoreId(user);
    		
    		if(userDB!=null){
    			
    			if(!"".equals(userDB.getPassword())&&userDB.getPassword()!=null){
    				
    				return Result.build(501, "您已经设置过密码了，如若修改密码请在App内进行");
    			}else{
    			      String codeResult = this.userService.sendVerificationCode(phone);
                      if ("0".equals(codeResult)) {
                          return Result.build(200, "获取成功", true);
                      } else {
                          return Result.build(500, "获取验证码失败", true);
                      }
    			}
    			
    		}else{
    			return Result.build(505, "未收到店长邀请，不能成为店员");
    		}  

        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
     * 顾客领取会员卡获取验证码
     * @param request
     * @return
     */
    @RequestMapping("/getByConsumer")
    @ResponseBody
    public Result getCodeByConsumer(HttpServletRequest request) {
        try {
            String phone = request.getParameter("phone");
            String codeResult = this.userService.sendVerificationCode(phone);
            if ("0".equals(codeResult)) {
                return Result.build(200, "获取成功", true);
            } else {
                return Result.build(500, "获取验证码失败", true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/check")
    @ResponseBody
    public Result checkCode(HttpServletRequest request) {
        try {
            String phone = request.getParameter("phone");
            String code = request.getParameter("code");
            return this.userService.checkCode(phone, code);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
