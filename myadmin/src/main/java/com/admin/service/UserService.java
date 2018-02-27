package com.admin.service;

import com.admin.entity.PermissionsList;
import com.admin.entity.Role;
import com.admin.entity.User;
import com.admin.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */
public interface UserService extends  IBaseService {
     List<User> getUserLists(int offset, int limit);


     /**
      *
      * 单个添加用户
      * @param user
      * @return
      */
     boolean addUser(User user);


     /**
      *
      * 删除用户
      * @param userid
      */
     void delUser(String userid);


     /**
      *
      * 登录
      * @param user
      * @return
      */
     int   login(User user) throws Exception;


     /**
      *
      * 批量添加用户
      * @param item
      */
     void addUserList(List<User> item);


     /**
      *
      * 获取用户列表
      * @param user
      * @param keyword
      * @param offset
      * @param limit
      * @return
      */
     List<User>  getUserList(List<User> user,String keyword,int offset,int limit);

     /**
      *
      * 批量删除
      * @param checkedId
      */
     void falseDelete(String[] checkedId);


     /**
      *
      * 搜索
      * @param keyword
      * @return
      */
     int getUserCount(@Param("keyword") String keyword);


     /**
      *
      * 修改登录信息
      * @param user
      * @return
      */
    // int  updateLoginInfo(User user);

     /**
      *
      * 批量添加用户角色
      * @param item
      */
     void addUserRoleList(List<UserRole> item);


     /**
      *
      *
      * @param userid
      * @return
      */
     User  getUserById(@Param("userid") int userid);


     /**
      *
      *
      * @param userid
      * @return
      */
     List<UserRole>  getUserRoleById(@Param("userid") int userid);


     /**
      *
      * 更改用户信息
      * @param user
      */
     void updateUserInfoByUser(User user);


     /**
      *
      *
      * @param userid
      */
     void delUserRoleByUserId(@Param("userid") int userid);


     /**
      *
      *
      * @param userName
      * @return
      */
     User  getUserByName(@Param("userName") String  userName);


     /**
      *
      *
      * @param userid
      */
     void updateUserStatus(@Param("status") int status,@Param("userid") int userid);


     /**
      *
      *
      * @param userid
      * @return
      */
     Role getUserRoleByUserId(@Param("userid") int userid);


     /**
      *
      *
      * @param userid
      * @return
      */
     List<PermissionsList> getPermissionsListByUserId(@Param("userid") int userid);
}


