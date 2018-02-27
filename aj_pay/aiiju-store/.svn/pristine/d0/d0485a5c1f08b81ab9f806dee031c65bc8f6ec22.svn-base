package com.aiiju.store.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.User;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.UserService;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户修改相关
 * @author 小飞
 * @date 2016年11月16日 下午7:25:51
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public Result getUser(Long id) {
        try {
            return this.userService.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result getUsers(String storeId) {
        try {
            return this.userService.getByStoreId(storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    
    /**
	 * 获取店员列表
	 * @param storeId
	 * @return
	 */
    @RequestMapping("/clerkList")
    @ResponseBody
    public Result clerkList(String storeId) {
        try {
            return this.userService.clerkList(storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/queryClerkList")
    @ResponseBody
    public Result clerkListIsActivate(String role,String storeId,String queryStoreId) {
        try {
            return this.userService.clerkListIsActivate(role,storeId,queryStoreId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
     * 增加店员
     * @param user
     * @param shopName
     * @param fromPhone
     * @return
     */
    @RequestMapping("/addClerk")
    @ResponseBody
    public Result addClerk(User user,String shopName,String fromPhone) {
        try {
            return this.userService.addClerk( user, shopName, fromPhone);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/deleteClerk")
    @ResponseBody
    public Result deleteClerk(User user) {
        try {
            return this.userService.deleteClerk(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    
    
    @RequestMapping("/update")
    @ResponseBody
    public Result update(User user) {
        try {
            return this.userService.update(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    
    @RequestMapping("/updateClerk")
    @ResponseBody
    public Result updateClerk(User user) {
        try {
            return this.userService.updateClerk(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String operatorId) {
        try {
            return this.userService.updateByOperatorId(operatorId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/inviteByNote")
    @ResponseBody
    public Result inviteByNote(String shopName,String fromPhone ,String toPhone, String storeId,byte role) {
        try {
           String codeResult = this.userService.sendInviteURL(shopName,fromPhone,toPhone, storeId,role);
     	   JSONObject json = 	JSONObject.fromObject(codeResult);
		   Integer code =  (Integer) json.get("code");
		   String data =  (String) json.get("data");
		 //  System.out.println("短信工具包返回："+data);
		   if(code==0){
			
			   return Result.build(200, "发送成功", true);
			   
		   }else{
		
			   return Result.build(500, "发送失败,"+data, true);
			   
		   }		

        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/inviteUI")
    public String inviteUI(HttpServletRequest request) {
        request.setAttribute("storeId", request.getParameter("storeId"));
        return "user/inviteUI";
    }

    @RequestMapping("/inviteUser")
    @ResponseBody
    public Result inviteUser(User user) {
        try {
         //   return this.userService.saveSeller(user);  单店铺的逻辑，邀请店员直接注册，而多店铺无注册的，所以改为一下逻辑
        	
        	return this.userService.updateClerkPassword(user); // 只修改密码
        	
        	
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/inviteResult")
    public String inviteResult(HttpServletRequest request) {
    	
    	String shopName = request.getParameter("shopName");
    	System.out.println("传入页面的shopName="+shopName);
    	
        request.setAttribute("shopName", shopName);
        return "user/inviteResult";
    }

    
    @RequestMapping("/whoIsOnline")
    @ResponseBody
    public Result whoIsOnline() {
        try {
            return this.userService.whoIsOnline();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/sendIsOnlineMsg")
    @ResponseBody
    public Result sendIsOnlineMsg() {
        try {
            return this.userService.sendIsOnlineMsg();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/heartBeat")
    @ResponseBody
    public Result heartBeat(User user) {
        try {
        	
            return this.userService.heartBeat(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    /**
     * 获取授权的信息
     * @param rId  ERP生成的随机码，唯一标识
     * @param pStoreId 父店铺的id
     * @param storeIds  多个子店铺的id ，多个店铺使用逗号隔开
     * @return
     */
    @RequestMapping("/grantERP")
    @ResponseBody
    public Result grantERP(String rId,String pStoreId, String storeIds) {
        try {
            return this.userService.grantERP(rId, pStoreId, storeIds);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
     * 获取授权的结果
     * @param rId
     * @return
     */
    @RequestMapping("/getGrantStatus")
    @ResponseBody
    public Result getGrantStatus(String rId) {
        try {
            return this.userService.getGrantStatus(rId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    
}
