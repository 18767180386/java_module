package com.admin.dao;

import com.admin.entity.PermissionsList;
import com.admin.entity.Role;
import com.admin.entity.User;
import com.admin.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public interface UserDao {
    /**
     * 根据手机号查询用户对象
     *
     * @param userPhone
     * @return
     */
  //  User queryByPhone(long userPhone);


    /**
     * 根据偏移量查询用户列表
     *
     * @param offset
     * @param limit
     * @return
     */
   List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 增加积分
     */
    //void addScore(@Param("add")int add);


    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

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
    int   login(User user);



    /**
     *
     * 批量插入数据库
     * @param item
     */
    void addUserList(List<User> item);


    /**
     *
     *
     * 获取所有用户
     * @return
     */
    List<User>  getUserList(@Param("keyword") String keyword,@Param("offset") int offset, @Param("limit") int limit);


 /***
  *
  * 所选中的id,逻辑删除
  * @param checkedId
  */
    void falseDelete(String[] checkedId);


 /**
  *
  * 获取管理员数量
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
    int updateLoginInfo(User user);


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
