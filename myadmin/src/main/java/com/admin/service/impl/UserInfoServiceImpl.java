package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.dao.UserInfoDao;
import com.admin.dto.Results;
import com.admin.entity.RequestCode;
import com.admin.entity.UserInfo;
import com.admin.service.RedisService;
import com.admin.service.UserInfoService;
import com.admin.util.MdUtils;
import com.admin.util.Utils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RedisService redisService;
    /**
     *
     * 检测用户是否存在
     * @param name
     * @return
     */
    public   UserInfo checkUserName(String name)
    {
        return  userInfoDao.checkUserName(name);
    }


    /**
     *
     * 用户注册
     * @param userInfo
     * @return
     */
     public long insertUser(UserInfo userInfo)
     {
         return  userInfoDao.insertUser(userInfo);
     }


    /**
     *
     * 登录
     * @param userName
     * @param userPassword
     * @return
     */
    public UserInfo login(@Param("userName") String userName, @Param("userPassword") String userPassword)
    {
        return userInfoDao.login(userName,userPassword);
    }


    /**
     *
     *
     * @param userName
     * @param userPassword
     * @return
     */
   public  Results loginTest(@Param("userName") String userName, @Param("userPassword") String userPassword)
   {
       if(Utils.isEmpty(userName))
       {
           return Results.build(RequestCode.LoginNoName.getIndex(),RequestCode.LoginNoName.getName());
       }
       if(Utils.isEmpty(userPassword))
       {
           return Results.build(RequestCode.LoginNoPass.getIndex(),RequestCode.LoginNoPass.getName());
       }
       UserInfo user=userInfoDao.checkUserName(userName);
       if(user!=null && user.getStatus()==1)
       {
           UserInfo _user =userInfoDao.login(userName,userPassword);
           if(_user!=null)
           {
               String   sign = MdUtils.md5(_user.getUserId()+System.currentTimeMillis()+"").toLowerCase();
               //  Group group2 = JSON.parseObject(jsonString, Group.class);
               try {
                   redisService.put(_user.getUserId()+"",sign, 6, TimeUnit.HOURS);
               }catch (Exception e)
               {
                   e.printStackTrace();
               }
               _user.setToken(redisService.get(_user.getUserId()+""));
               return Results.build(RequestCode.LoginSucc.getIndex(), RequestCode.LoginSucc.getName(),_user);
           }else{
               return Results.build(RequestCode.LoginPassFail.getIndex(), RequestCode.LoginPassFail.getName());
           }
       }else{
           if(user==null) {
               return Results.build(RequestCode.LoginNameNoExist.getIndex(), RequestCode.LoginNameNoExist.getName());
           }else{
               return Results.build(RequestCode.LoginFail.getIndex(), RequestCode.LoginFail.getName());
           }
       }
   }
}
