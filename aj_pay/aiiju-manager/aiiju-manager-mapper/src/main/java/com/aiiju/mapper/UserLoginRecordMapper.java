package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.UserLoginRecord;

/**
 * 
 * @ClassName: UserLoginRecordMapper
 * @Description: 用户 状态Mapper
 * @author 宗介
 * @date 2017年5月22日 下午4:36:43
 *
 */
public interface UserLoginRecordMapper {
    /**
     * 添加用户
     * 
     * @param user
     * @return
     */
    public void add(UserLoginRecord userLoginRecord);

    /**
     * 更新用户
     * 
     * @param user
     */
    public void update(UserLoginRecord userLoginRecord);
    
    
    public void  updateStatusBatch(List<UserLoginRecord> userLoginRecord) ;

    /**
     * 获取用户
     * 
     * @param id
     * @return
     */
    public UserLoginRecord getByPhoneStoreId(UserLoginRecord userLoginRecord);

    /**
     * 获取在线用户
     * 
     * @param 
     * @return
     */
    public List<UserLoginRecord> getOnlineUser();

 
    /**
     * 更新用户在线状态
     * 
     * @param user
     */
    public void updateStatus(UserLoginRecord userLoginRecord);
    
    
    
    public UserLoginRecord selectByEquipAndPhone(UserLoginRecord userLoginRecord);
    
    

}
