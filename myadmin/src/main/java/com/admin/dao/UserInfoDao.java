package com.admin.dao;

import com.admin.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {
    /**
     *
     * 检测用户是否存在
     * @param name
     * @return
     */
    UserInfo checkUserName(@Param("name") String name);


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

}
