package com.aiiju.store.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.BookingManage;
import com.aiiju.pojo.BookingManageSwitch;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.User;

/** 
 * @ClassName BookingManageService
 * @Description 预约管理service
 * @author 乔巴
 * @CreateDate 2017年8月23日
 */
public interface BookingManageService {

	/**
	 * 获取预约管理列表
	 */
	List<Map<String, Object>> getBoookingManageList(String status,String startDate,String endDate,
			String phone,String keyWord,Integer pageNum, Integer pageSize,String storeId,String drawer);
	

	/**
	 * 获取预约管理列表总记录数
	 */
	List<Map<String,Object>> getCount(String status,String startDate,String endDate,
			String phone,String keyWord,Integer pageNum, Integer pageSize,String storeId,String drawer);
	
	/**
	 * 根据id查询预约单详情
	 */
	List<Map<String,Object>> getBookingManageById(Long id);
	
	/**
	 * 根据id编辑预约单
	 */
	int updateById(BookingManage bookingManage);
	
	/**
	 * 根据id删除预约单
	 */
	int deleteById(Long id);
	
	/**
	 * //获取每个店铺每个纹身师所有的预约单开始和结束时间
	 */
	List<BookingManage> getRecentTime(String storeId,String phone,Long id);
	
	/**
	 * 创建预约单
	 * @param bookingManage
	 * @return
	 */
	int createOrder(BookingManage bookingManage);
	/**
	 * 获取总计金额
	 */
	Map<String,String> getTotalAmount(String status,String storeId,String startDate,String endDate,
			String phone,String keyWord,String drawer);
	/**
	 * 获取所有的预约时间和预约提醒时间
	 */
	List<Map<String,String>> getAllBeginAndReminderTime(String storeId); 
	/**
	 *批量添加预约提醒时间 
	 */
	int bacthAddRemindTime(List<BookingManage> list);
	/**
	 * 获取所有纹身师的设备型号和手机类型
	 */
	List<Map<String,String>>getDrawerEquipmentCode();
	/**
	 * 获取所有的纹身师和客户的设备型号和手机类型
	 */
	List<Map<String,String>>getDrawerAndCustomerCode();
	/**
	 * 获取当前所有预约单中需要提醒的订单的订单状态
	 */
//	List<Map<String,String>>getRemindStatus(String storeId);
	/**
	 * 根据预约提醒时间批量设置推送状态
	 */
	int bacthSetByRemindTime(List<BookingManage> list);
	/**
	 * 根据开始时间批量设置推送状态
	 */
	int bacthSetByBeginTime(List<BookingManage> list);
	/**
	 * 获取预约提醒推送订单的id
	 */
	Map<String,Object> getIdBySendTime(String sendTime);
	/**
	 *  获取预约开始推送订单的id
	 */
	Map<String,Object> getIdByBeginTime(String beginTime);
	/**
	 *获取所有用户的对应权限 
	 */
	Map<String,String> getAllRole(Integer userId);
	/**
	 * 查询用户姓名
	 */
	Map<String,String> getUserName(String phone);
	
	List<BookingManage> findAllBookingManage();
	
	void updatePushsStatusById(long id);
	void updateSendStatusById(long id);
	
	List<JPush> getJPushListByPhone(String phone);
	
     
	List<User> getCustomerFromUserByStoreId(String storeId);
	
	public Result updateRemindSwitch(BookingManageSwitch bookingManageSwitch);
	
	public BookingManageSwitch getRemindSwitch(BookingManage bookingManage);
	
	public Result getList(BookingManage bookingManage,String startDate,String endTime,String searchDate,String keyWord, Integer currentNum,Integer pageSize);
	
}
