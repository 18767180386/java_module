package com.admin.service;

import com.admin.dto.Results;
import com.admin.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoService  extends IBaseService{
    /**
     *
     * 检测用户是否存在
     * @param name
     * @return
     */
    UserInfo checkUserName(String name);


    /**
     *
     * 用户注册
     * @param userInfo
     * @return
     */
    long insertUser(UserInfo userInfo);


    /**
     *
     * 登录
     * @param userName
     * @param userPassword
     * @return
     */
    UserInfo login(@Param("userName") String userName,@Param("userPassword") String userPassword);


    /**
     *
     *
     * @param userName
     * @param userPassword
     * @return
     */
    Results loginTest(@Param("userName") String userName,@Param("userPassword") String userPassword);

}
