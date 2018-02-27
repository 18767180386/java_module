package com.aiiju.store.controller.rest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.VerifyUtils;
import com.aiiju.pojo.BookingManage;
import com.aiiju.pojo.BookingManageSwitch;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.BookingManageService;
import com.aiiju.store.util.AppUtil;

/**
 * @ClassName: BookingManageController 
 * @Description: 预约管理功能模块
 * @author 乔巴
 * @date 2017年08月23日  
 *
 */
@Controller
@RequestMapping(value = "/bookingManage")
public class BookingManageController {

	private static Logger logger = Logger.getLogger(BookingManageController.class);
	
	@Autowired
	private BookingManageService bookingManageService;
	
    @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd HH:mm:ss"));
    }
	
    @RequestMapping("/getBoookingManageList")
    @ResponseBody
    public Result getList(BookingManage bookingManage,String startDate,String endTime,String searchDate,String keyWord,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
            return this.bookingManageService.getList(bookingManage,startDate,endTime,searchDate,keyWord,pageNum,pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
	 /** 
	     * 删除list<Map<String,Object>>中所有为空的map元素 
	     * 删除为空的操作方式非常复杂，因为你每一次删除一个map元素之后， 
	     * 当前的list的大小就会变化，但是反过来想，如果只是获取它不为 
	     * 空的元素则不需要考虑它的size大小的变化，而且比较简单。 
	     * @param list 
	     * @return 
	     */ 
	 /*public static List removeEmptyList(List<Map<String, Object>> list,Map<String,Object> map) {  
	        List list1 = new ArrayList();  
	          
	        if(list==null||list.size()<=0)  
	            return null;  
	        //循环第一层  
	        for(int i=0;i<list.size();i++) {  
	            //进入每一个map  
	        	Map<String, Object> map1 = list.get(i);  
	            if(map1!=null && !"0.00".equals(map1.get("paid")))  
	                list1.add(map1);  
	            System.out.println(map1.size());  
	        }  
			map.put("bookingManageList", list1);
	        return list1;  
	    }  */
	
	 /**
	  * 2.根据id查询预约单详情 
	  * @param maps
	  * @return
	  */
	 @RequestMapping(value ="/getBookingManageById" ,method = {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	public Result getBookingManageById(@RequestParam Map<String,String> maps){
		 try {
			Long id = Long.valueOf(VerifyUtils.verifyString(maps.get("id")));
			List<Map<String, Object>> bookingManageDetails = bookingManageService.getBookingManageById(id);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bookingManageDetails", bookingManageDetails);
			if(bookingManageDetails!=null && bookingManageDetails.size()>0){
				return Result.success(map);
			}else{
				return Result.build(401,WebConstant.IS_DEL);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	 } 
	
	/**
	 * 3.根据id编辑预约单
	 */
	 @RequestMapping(value = "/updateById", method = {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public Result updateById(@RequestParam Map<String, String> maps){
		 try {
			 String id = maps.get("id");
			 String storeId = maps.get("storeId");//店铺编号
			 String phone = maps.get("phone");//手机号
			 String customerName = maps.get("customerName");//顾客姓名
			 String customerSex = maps.get("customerSex");//顾客性别
			 String contactWay = maps.get("contactWay");//联系方式
			 String beginTime = maps.get("beginTime");//开始时间
			 String endTime = maps.get("endTime");//结束时间
			 String paidDeposit = maps.get("paid");//已付定金
			 String notPay = maps.get("unpay");//待付尾款
			 String drawer = maps.get("drawer");//纹身师
			 String remark = maps.get("remark");//备注
			 String createUserId = maps.get("createUserId");//创建预约单用户id
			 String bookingStatus = maps.get("bookingStatus");//预约单状态
			 Date beginDate = null;
			 Date endDate = null;
			 if(beginTime!=null && !"".equals(beginTime)){
				  beginDate = DateUtil.parseStr(beginTime,"yyyy-MM-dd HH:mm");
			 }
			 if(endTime!=null && !"".equals(endTime)){
				  endDate = DateUtil.parseStr(endTime, "yyyy-MM-dd HH:mm");
			 }
		
			 List<BookingManage> list =null;
			 List<BookingManage> endDateDB = bookingManageService.getRecentTime(storeId,phone,Long.valueOf(id));//获取每个店铺每个纹身师所有的预约单开始和结束时间
			 
			 boolean flag  =false;
			 
			 for (BookingManage bookingManage : endDateDB) {
				
				 if(endDate.getTime()<=bookingManage.getBeginTime().getTime()||beginDate.getTime()>=bookingManage.getEndTime().getTime()){
				 }else{
					 System.out.println(bookingManage.getId()+" ,有交叉的时间段："+beginDate+"-->"+endDate+" VS "+bookingManage.getBeginTime()+"-->"+bookingManage.getEndTime());
					 flag = true;
				 }
				 
			}
			 if(flag){
				 return Result.build(401,WebConstant.TIME_CREATE_FAIL);
			 }
			 
			 
			 
//			 List<BookingManage> endDateDBOne = bookingManageService.getRecentTime(storeId,phone,null);//获取每个店铺每个纹身师所有的预约单开始和结束时间
//			//创建新预约单的开始时间或者结束时间,不能与上一订单开始结束时间重合
//			 if(endDateDB!=null &&!"".equals(endDateDB)&&endDateDBOne!=null &&!"".equals(endDateDBOne)){
//				 for (int i = 0; i < endDateDB.size(); i++) {
//					 for (int j = 0; j < endDateDBOne.size(); j++) {
//						 if(endDateDBOne.get(j).getBeginTime().equals(endDateDB.get(i).getBeginTime())){
//							 endDateDBOne.remove(j);
//						 }
//						
//					}
//				}
//				 
//			 }
//			//创建新预约单的开始时间或者结束时间,不能与上一订单开始结束时间重合
//			 if(beginTime!=null && !"".equals(beginTime)&& endTime!=null && !"".equals(endTime)){
//				 for (BookingManage bookingManage : endDateDBOne) {
//					 Date beginTimeDate = bookingManage.getBeginTime();
//					 Date endTimeDate = bookingManage.getEndTime();
//					 if(beginDate.getTime()<=endTimeDate.getTime() && beginDate.getTime()>=beginTimeDate.getTime()||endDate.getTime()>=beginTimeDate.getTime()&&endDate.getTime()<=endTimeDate.getTime()||beginDate.getTime()<=beginTimeDate.getTime()&&endDate.getTime()>=endTimeDate.getTime()){
//							return Result.build(401,WebConstant.TIME_CREATE_FAIL);
//					}
//				} 
//			 }
			 
			 BookingManage bookingManage = new BookingManage();
			 bookingManage.setId(Long.valueOf(id));
			 bookingManage.setCustomerName(customerName);
			 if(customerSex!=null && "0".equals(customerSex)){
				 bookingManage.setCustomerSex("男");
			 }
			 if(customerSex!=null && "1".equals(customerSex)){
				 bookingManage.setCustomerSex("女");
			 }
			 bookingManage.setBookingStatus(bookingStatus);
			 bookingManage.setContactWay(contactWay);
			 if(beginTime!=null && !"".equals(beginTime)){
				 bookingManage.setBeginTime(DateUtil.parseStr(beginTime,"yyyy-MM-dd HH:mm"));
			 }
			 if(endTime!=null && !"".equals(endTime)){
				 bookingManage.setEndTime(DateUtil.parseStr(endTime, "yyyy-MM-dd HH:mm"));
			 }
			 bookingManage.setPaid(AppUtil.newBigDecimal(paidDeposit));
			 bookingManage.setUnpay(AppUtil.newBigDecimal(notPay));
			 bookingManage.setOneTotal(AppUtil.add(AppUtil.newBigDecimal(paidDeposit), AppUtil.newBigDecimal(notPay)));
			 bookingManage.setDrawer(drawer);
			 bookingManage.setRemark(remark);
			 if(createUserId!=null && !"".equals(createUserId)){
				 bookingManage.setCreateUserId(Integer.parseInt(createUserId));
			 }
			 int count = bookingManageService.updateById(bookingManage);
			 if(count>0){
				 return Result.success();
			 }else{
				 return Result.build(401,WebConstant.FAIL); 
			 }
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
		 
	 }
	 
	 /**
	  * 4.根据id逻辑删除预约单
	  */
	 @RequestMapping(value = "/deleteById",method = {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public Result deleteById(@RequestParam Map<String, String> maps){
		try {
			String id = VerifyUtils.verifyString(maps.get("id"));
			int count = bookingManageService.deleteById(Long.valueOf(id));
			 if(count > 0){
				 return Result.success();
			 }else{
				 return Result.build(401,WebConstant.FAIL);
			 }
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	 }
	 
	 /**
	  * 5.创建新的预约单
	  */
	 @RequestMapping(value = "/createNewBooking",method = {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public Result createNewBooking(@RequestParam Map<String, String> maps){
		 try {
			 String storeId = maps.get("storeId");//店铺编号
			 String parentStoreId = maps.get("parentStoreId");//父店编号
			 String customerName = maps.get("customerName");//顾客姓名
			 //String customerSex = new String(maps.get("customerSex").getBytes("iso-8859-1"),"UTF-8");//顾客性别
			 String customerSex = maps.get("customerSex");
			 String contactWay = maps.get("contactWay");//联系方式
			 String beginTime = maps.get("beginTime");//开始时间
			 String endTime = maps.get("endTime");//结束时间
			 String paidDeposit = maps.get("paid");//已付定金
			 String notPay = maps.get("unpay");//待付尾款
			 String drawer = maps.get("drawer");//纹身师
			 String remark = maps.get("remark");//备注
			 String createUserId = maps.get("createUserId");//创建预约单用户id
			 String  operatorId = maps.get("operatorId");//操作者id
			 String bookingStatus = maps.get("bookingStatus");//订单状态
			 String phone = maps.get("phone");//手机号
			 
			 
			 Map<String,String> username =bookingManageService.getUserName(phone);//通过手机号到user表查询到username
			 String drawers =null;
			 if(username!=null &&!"".equals(username)){
				  drawers = username.get("username");
			 }
			 
			 Date beginDate = DateUtil.parseStr(beginTime,"yyyy-MM-dd HH:mm");
			 Date endDate = DateUtil.parseStr(endTime, "yyyy-MM-dd HH:mm");
		
			 
			 List<BookingManage> endDateDB = bookingManageService.getRecentTime(storeId,phone,null);//获取每个店铺每个纹身师所有的预约单开始和结束时间
			 
			 boolean flag  =false;
			 
			 for (BookingManage bookingManage : endDateDB) {

				 if(endDate.getTime()<=bookingManage.getBeginTime().getTime()||beginDate.getTime()>=bookingManage.getEndTime().getTime()){
				 }else{
					 System.out.println("有交叉的时间段："+beginDate+"-->"+endDate+" VS "+bookingManage.getBeginTime()+"-->"+bookingManage.getEndTime());
					 flag = true;
				 }
				 
			}
			 if(flag){
				 return Result.build(401,WebConstant.TIME_CREATE_FAIL);
			 }
			 
			 BookingManage bookingManage = new BookingManage();
			 if(parentStoreId!=null && !"".equals(parentStoreId)){
				 bookingManage.setParentStoreId(parentStoreId);
			 }else{
				 bookingManage.setParentStoreId("");
			 }
			 if(storeId!=null && !"".equals(storeId)){
				 bookingManage.setStoreId(storeId);
			 }else{
				 bookingManage.setStoreId("");
			 }
			 bookingManage.setCustomerName(customerName);
			 if(customerSex!=null && "0".equals(customerSex)){
				 bookingManage.setCustomerSex("男");
			 }
			 if(customerSex!=null && "1".equals(customerSex)){
				 bookingManage.setCustomerSex("女");
			 }
			 if(operatorId!=null && !"".equals(operatorId)){
				 bookingManage.setOperatorId(operatorId);
			 }
			 bookingManage.setContactWay(contactWay);
			 bookingManage.setBeginTime(beginDate);
			 bookingManage.setEndTime(endDate);
			 if(paidDeposit!=null && !"".equals(paidDeposit)){
				 bookingManage.setPaid(AppUtil.newBigDecimal(paidDeposit));
			 }else{
				 bookingManage.setPaid(AppUtil.newBigDecimal("0.00"));
			 }
			 if(notPay!=null && !"".equals(notPay)){
				 bookingManage.setUnpay(AppUtil.newBigDecimal(notPay));
			 }else{
				 bookingManage.setUnpay(AppUtil.newBigDecimal("0.00"));
			 }
			 bookingManage.setOneTotal(AppUtil.add(AppUtil.newBigDecimal(paidDeposit), AppUtil.newBigDecimal(notPay)));
			 if(drawers!=null && !"".equals(drawers)){
				 bookingManage.setDrawer(drawers);
			 }else{
				 bookingManage.setDrawer("");
			 }
			 bookingManage.setPhone(phone);
			 if(remark!=null && !"".equals(remark)){
				 bookingManage.setRemark(remark);
			 }else{
				 bookingManage.setRemark(""); 
			 }
			 
			 if(createUserId!=null && !"".equals(createUserId)){
				 bookingManage.setCreateUserId(Integer.parseInt(createUserId));
			 }
			 if(bookingStatus!=null && !"".equals(bookingStatus)){
				 bookingManage.setBookingStatus(bookingStatus);
			 }
			 int count = bookingManageService.createOrder(bookingManage);
			 
			 BookingManageSwitch bookingManageSwitch= bookingManageService.getRemindSwitch(bookingManage);
			 
			 if(bookingManageSwitch!=null&&"1".equals(bookingManageSwitch.getRemindStatus())){
				 
//				 bookingManage.setRemindStatus("1");
//              
//				 bookingManage.setRemindTime(bookingManageSwitch.getRemindTime());
				 bacthAddRemindTime(bookingManageSwitch);
			 }
			 
			 if(count > 0){
				 return Result.success();
			 }else{
				 return Result.build(401,WebConstant.FAIL);
			 }
			 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
		 
	 }
	 /**
	  * 6.预约提醒时间设置的同時,批量添加预约提醒时间
	  */

	 @RequestMapping("/bacthAddRemindTime")
	 @ResponseBody
	 public Result bacthAddRemindTime(BookingManageSwitch bookingManageSwitch){
		try {
			String remindTime =bookingManageSwitch.getRemindTime();
			String remindStatus =bookingManageSwitch.getRemindStatus();
			String storeId = bookingManageSwitch.getStoreId();
			List<Map<String,String>> beginTimeDB =bookingManageService.getAllBeginAndReminderTime(storeId);
			if(beginTimeDB.size()==0){
				 return Result.success();
			}
			List<BookingManage> list = new ArrayList<BookingManage>();
			Date sendTime =null;
			if("1".equals(remindStatus)){
				if(beginTimeDB!=null && beginTimeDB.size()>0){
					for (Map<String, String> map : beginTimeDB) {
						//当预约提醒时间设置为0.5小时时
						
						sendTime = DateUtil.rollMinute(DateUtil.parseStr(map.get("beginTime"),"yyyy-MM-dd HH:mm"),(new BigDecimal(remindTime).multiply(new BigDecimal(60))).intValue());
						BookingManage booking = new BookingManage();
						booking.setBeginTime(DateUtil.parseStr(map.get("beginTime"),"yyyy-MM-dd HH:mm"));
						booking.setSendTime(sendTime);
//						booking.setRemindTime(remindTime);//設置预约提醒时间
//						booking.setRemindStatus("1");;//設置预约提醒狀態
						list.add(booking);


					}
				}
			}else{
				for (Map<String, String> map : beginTimeDB) {
					BookingManage booking = new BookingManage();
					booking.setBeginTime(DateUtil.parseStr(map.get("beginTime"),"yyyy-MM-dd HH:mm"));
					booking.setBookingStatus("0");
					list.add(booking);
				}
			}
			if(list!=null &&list.size()>0){
				 int count = bookingManageService.bacthAddRemindTime(list);
				 if(count>0){
					 return Result.success();
				 }
				 else{
					 return Result.success();
				 }
			}else{
				return Result.success();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	 
		@RequestMapping("/updateRemindSwitch")
		@ResponseBody
		public Result updateRemindSwitch(BookingManageSwitch bookingManageSwitch) {
			try {
				//BookingManage bookingManagenew = bookingManage;
				
//				 BookingManageSwitch bookingManageSwitch = new  BookingManageSwitch();
//				 bookingManageSwitch.setStoreId(bookingManage.getStoreId());
//				 bookingManageSwitch.setRemindStatus(bookingManage.get);
				bacthAddRemindTime(bookingManageSwitch);
				return this.bookingManageService.updateRemindSwitch(bookingManageSwitch);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return Result.build(500,WebConstant.SYS_ERROR);
			}
		}
	 
		@RequestMapping("/getRemindSwitch")
		@ResponseBody
		public Result getRemindSwitch(BookingManage bookingManage) {
			try {
				
				BookingManageSwitch  bms=	this.bookingManageService.getRemindSwitch(bookingManage);
				return Result.success(bms);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return Result.build(500,WebConstant.SYS_ERROR);
			}
		}
	 
}