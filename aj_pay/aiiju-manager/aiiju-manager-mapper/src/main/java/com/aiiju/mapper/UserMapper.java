package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.User;

/**
 * 
 * @ClassName: UserMapper
 * @Description: 用户 Mapper
 * @author 小飞
 * @date 2016年11月8日 下午4:36:43
 *
 */
public interface UserMapper {
    /**
     * 添加用户（超级管理员）
     * 
     * @param user
     * @return
     */
    public void add(User user);
    
    
    /**
     * 添加店员（包括店员及店长）
     * 
     * @param 
     * @return
     */
    public void addClerk(User user);
    
    /**
     * 
     * 
     * @param 
     * @return
     */
    public void deleteClerk(User user);
    
    /**
     * 统计该店铺的店长人数
     * 
     * @param 
     * @return
     */
    
    public Integer queryIsExistRoleIs1(User user);
    
    
    
    
    
    
    /**
     * 更新用户
     * 
     * @param user
     */
    public void update(User user);
    
    
    public void updateIsActivate(User user);

    /**
     * 更新店员
     * 
     * @param user
     */
    public void updateClerk(User user);
  
    public void updateClerkPassword(User user);
    
    
    public User getUserByPhoneAndStoreId(User user);
    /**
     * 获取用户
     * 
     * @param id
     * @return
     */
    public User getById(Long id);

    /**
     * 通过storeId获取用户
     * 
     * @param storeId
     * @return
     */
    public List<User> getAllByStoreId(String storeId);

    /**
     * 用户和店铺解除关系
     * 
     * @param id
     */
    public void updateOnShop(User user);

    /**
     * 通过id获取用户（只封装了用户名）
     * 
     * @param id
     * @return
     */
    public User getUserName(Long id);
    
    /**
     * 统计该店铺的店长人数（排除自己）
     * 
     * @param 
     * @return
     */
    
    public Integer selectHave(User user);
    

    /**
     * 检测用户是否存在
     * 
     * @param user
     * @return
     */
    public User checkByUserName(String userName);

    public User checkByUserNameWithoutShop(String userName);
    
    /**
     * 检测手机是否存在；存在返回 user ，否则 null
     * 
     * @param user
     * @return
     */
    public User checkByPhone(String phone);

    public User checkByEmail(String email);

    public User checkByNickName(String nickName);

    public String getMaxOperatorIdByStoreId(String storeId);

    public User getUserByOperatorId(String operatorId);

    public void updatePWD(User user);

    public List<User> getShareUserList(Long shopAccountId);
    
    public List<User> getClerkListByParentStoreId(String storeId);
    
    
    /**
     * 通过店铺id获得旗下的员工
     * @param storeId
     * @return
     */
    public List<User> getByStoreId(String storeId);
    
    public List<User> clerkListIsActivateByParentStoreId(String storeId);
    
    public List<User> clerkListIsActivateByStoreId(String storeId);
}
