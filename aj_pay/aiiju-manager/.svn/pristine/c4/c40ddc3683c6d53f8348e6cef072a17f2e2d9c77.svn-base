package com.aiiju.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.BookingManage;
import com.aiiju.pojo.BookingManageSwitch;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.User;


/**
 * 
 * @ClassName: BookingManageMapper 
 * @Description: 预约管理 Mapper
 * @author 乔巴
 * @date 2017年08月23日 
 *
 */
public interface BookingManageMapper {
	/**
	 * 创建预约单
	 * @param bookingManage
	 * @return
	 */
	int createOrder(BookingManage bookingManage);
	
	/**
	 * 编辑预约单
	 * @param bookingManage
	 * @return
	 */
	int updateById(BookingManage bookingManage);
	
	/**
	 * 获取预约管理列表
	 */
	List<Map<String, Object>> getBoookingManageList(@Param("status")String status,@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("phone")String phone,@Param("keyWord")String keyWord,@Param("storeId")String storeId,@Param("drawer")String drawer);
	
	/**
	 * 获取预约管理列表总记录数
	 */
	List<Map<String,Object>> getCount(@Param("status")String status,@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("phone")String phone,@Param("keyWord")String keyWord,@Param("storeId")String storeId,@Param("drawer")String drawer);
	
	/**
	 * 根据id查询预约单详情
	 */
	List<Map<String,Object>> getBookingManageById(@Param("id")Long id);
	
	/**
	 * 根据id逻辑删除预约单
	 */
	int deleteById(@Param("id")Long id);
	
	/**
	 * //获取每个店铺每个纹身师所有的预约单开始和结束时间
	 */
	List<BookingManage> getRecentTime(@Param("storeId")String storeId,@Param("phone")String phone,@Param("id")Long id);
	
	/**
	 * 获取总计金额
	 */
	Map<String,String> getTotalAmount(@Param("status")String status,@Param("storeId")String storeId,@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("phone")String phone,@Param("keyWord")String keyWord,@Param("drawer")String drawer);
	/**
	 * 获取所有的预约时间和预约提醒时间
	 */
	List<Map<String,String>> getAllBeginAndReminderTime(@Param("storeId")String storeId);
	/**
	 * 批量添加预约提醒时间 
	 */
	int bacthAddRemindTime(@Param("list") List<BookingManage> list);
	/**
	 * 获取所有纹身师的设备型号和手机类型
	 */
	List<Map<String,String>>getDrawerEquipmentCode();
	/**
	 * 获取所有的纹身师和客服的设备型号和手机类型
	 */
	List<Map<String,String>> getDrawerAndCustomerCode();
	/**
	 * 获取当前所有预约单中需要提醒的订单的订单状态
	 */
//	List<Map<String,String>> getRemindStatus(@Param("storeId")String storeId);
	/**
	 * 根据预约提醒时间批量设置推送状态
	 */
	int bacthSetByRemindTime(@Param("list") List<BookingManage> list);
	/**
	 * 根据开始时间批量设置推送状态
	 */
	int bacthSetByBeginTime(@Param("list") List<BookingManage> list);
	/**
	 * 获取预约提醒推送订单的id
	 */
	Map<String,Object> getIdBySendTime(@Param("sendTime")String sendTime);
	/**
	 *  获取预约开始推送订单的id
	 */
	Map<String,Object> getIdByBeginTime(@Param("beginTime")String beginTime);
	/**
	 *获取所有用户的对应权限 
	 */
	Map<String,String> getAllRole(@Param("userId")Integer userId);
	
	
	List<BookingManage> findAllBookingManage ();
	
	
     void updatePushsStatusById(long id) ;
		
	 void updateSendStatusById(long id); 
	 
	 
	  List<JPush> getJPushListByPhone(String phone);
		
	
	List<User> getCustomerFromUserByStoreId(String storeId);
	
	String getBookingManageSwitchByStoreId(String storeId);
	void saveBookingManageSwitch(BookingManageSwitch bookingManageSwitch);
	void updateBookingManageSwitch(BookingManageSwitch bookingManageSwitch);
	
	void updateRemindStatus(BookingManage bookingManage);
	/**
	 * 查询用户姓名
	 */
	Map<String,String> getUserName(@Param("phone")String phone);
	
	List<Map<String, Object>> getList(Map<String,Object> map);
	String getTotalAmountNew(Map<String,Object> map);
	
	String getDayTotal(Map<String,Object> map);
	
	User getUserByPhone(String phone);
}
