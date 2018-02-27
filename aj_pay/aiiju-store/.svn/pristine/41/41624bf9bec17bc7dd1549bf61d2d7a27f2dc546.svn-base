package com.aiiju.store.service;

import java.util.List;
import java.util.Set;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.User;
import com.aiiju.pojo.UserLoginRecord;
/**
 * 
 * @ClassName: UserService 
 * @Description: 用户Service
 * @author 小飞 
 * @date 2016年11月8日 下午4:40:29 
 *
 */
public interface UserService {
	//===========SSO==========
	public Result checkUser(String content, Integer type);
	
	public Result save(User user);
	
	/**
	 * 创建总店用户
	 */
	public Result createAdmin(User user);
	
	public Result heartBeat(User user);
	/**
	 * 增加店员
	 * @param user
	 * @param shopName
	 * @param fromPhone
	 * @return
	 */
	
	public Result addClerk(User user,String shopName,String fromPhone);
	
	
	/**
	 * 获取店员列表
	 * @param storeId
	 * @return
	 */
	public Result clerkList(String storeId);
	public Result clerkListIsActivate(String role,String storeId,String queryStoreId);
	
	public Result deleteClerk(User user);
	
	public Result saveSeller(User user);
	
	public Result login(String username, String password, String equipmentCode,String phoneType,String OS,String phoneId);
	
	public Result logout(String token, String equipmentCode,Long id);
	
	public Result checkToken(String token);
	/**
	 * 发送验证码
	 * @param phone 手机号
	 * @return
	 */
	public String sendVerificationCode(String phone);
	/**
	 * 发送邀请店员连接
	 * @param phone   手机号
	 * @param storeId 店铺编号
	 * @return
	 */
	public String sendInviteURL(String shopName,String fromPhone ,String toPhone, String storeId,byte role);
	/**
	 * 校验验证码
	 * @param phone 手机号
	 * @param code    验证码
	 * @return
	 */
	public Result checkCode(String phone, String code);
	
	//============rest=========
	
	/**
	 * 解除用户和店铺关系
	 * @param map
	 */
	public Result updateByOperatorId(String operatorId);
	
	public Result update(User user);
	
	/**
	 * 修改员工信息
	 * @param map
	 */
	public Result updateClerk(User user);
	public Result updateClerkPassword(User user);
	
	
	public Result getById(Long id);
	
	public Result getByStoreId(String storeId);
	/**
	 * 修改密码
	 * @param userName
	 * @param password
	 * @return
	 */
	public Result updatePWD(String userName, String oldPassword,
			String newPassword);
	/**
	 *  设置新密码（忘记密码）
	 * @param userName
	 * @param newPassword
	 * @return
	 */
	public Result updatePWD(String userName, String newPassword);
	
	
	public Result saveOrUpdateUserLoginRecord(UserLoginRecord userLoginRecord);
	
	public Result updateStatusBatch(List<UserLoginRecord> userLoginRecord);
	 /**
	  * 获取在线列表（）
	  * @return
	  */
	 public List<UserLoginRecord> getOnlineUserList();

	 /**
	  * 授权给ERP 用户
	  * @param erpId  用户的erp 的id
	  * @param userName   授权用户的手机号码
	  * @param shopIds  需要授权的店铺的ID ， 多个店铺使用逗号隔开
	  * @return
	  */
	public Result grantERP(String rId,String pStoreId,String storeIds);

	/**
	 * 获取授权的token
	 * @param rId ERP系统生成随机数字
	 * @return
	 */
	public Result getGrant(String rId);

	/**
	 * 移动端扫描二维码后，需要再次发送请求，查看授权的结果。
	 * @param rId 随机 rId ，由ERP系统 生成显示在二维码中
	 * @return
	 */
	public Result getGrantStatus(String rId);

	
	/**
	 * 更新授权的店铺；
	 * @param storeIdSet 
	 * @return 如果这些店铺是同一家店铺，可以进行更新，返回的是TRUE， 如果不是同一家店铺，无法进行更新
	 * 注意：如果这个店铺的id 在数据库中不存在，不会对结果产生影响；
	 */
	public boolean updateErpGrantStores(Set<String> storeIdSet);
	
	/**
	 * 
	 * @Description   发送检查用户是否在线的推送，当app接收此推送后，调用心跳接口
	 * @return      
	 * @return Result
	 */
	public Result sendIsOnlineMsg();
	
	/**
	 * 
	 * @Description   当前在线用户
	 * @return      
	 * @return Result
	 */
	public Result whoIsOnline();
	

	public User getUserByPhone(String phone);
	

}
