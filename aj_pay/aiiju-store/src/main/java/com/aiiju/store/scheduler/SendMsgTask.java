package com.aiiju.store.scheduler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JpushClientUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.pojo.BookingManage;
import com.aiiju.pojo.BookingManageSwitch;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.User;
import com.aiiju.store.service.BookingManageService;

/**
 * @ClassName: SendMsgTask
 * @Description: 预约提醒时间到了,给纹身师推送msg;开始时间到了给纹身师和客服推送msg
 * @author 乔巴
 * @date 2017年8月30日
 */
public class SendMsgTask {
    
	 private static Logger logger = Logger.getLogger(SendMsgTask.class);
	
    @Autowired
	private BookingManageService bookingManageService;
    
  	String bookingReminderMsg = "一个预约即将开始！";
	String staticMsg = "一个预约已经开始!";
    
    /**
     * 预约订单管理推送
     */
    public void sendMsgOnTime() throws ParseException {
    	
    	
    	logger.info("调用sendMsgOnTime方法-----------------------");

        // 获取预约中的没有被删除的没推送完成的；
    	List<BookingManage>  bmList=  bookingManageService.findAllBookingManage();
    	
    	
    	/**
    	 * 1, 当前时间与预约提醒设置时间，接近1分钟时，推送；之后更新预约提醒推送状态 remind_status 0->1
    	 * 
    	 * 
    	 * 2, 当前时间与预约的开始时间比较，接近1分钟时，推送；之后更新预约推送状态 pushs 0 ->1
    	 */
    	
    	if(bmList.size()<1){
    		return;
    	}
    	
    	Date currentDate = new Date();
    	for (BookingManage bookingManage : bmList) {
			
    		
    		BookingManageSwitch  bms=   bookingManageService.getRemindSwitch(bookingManage);
    		
     		if(bms==null||(bms!=null&&!"1".equals(bms.getRemindStatus()))){
   			    logger.info("预约提醒开关 关闭，所以不在进行预约提醒");
   		      }
     		
    		if(bms!=null&&"1".equals(bms.getRemindStatus())&&!"1".equals(bookingManage.getSendStatus())&&bookingManage.getSendTime()!=null){
    			

                long interval =	(bookingManage.getSendTime().getTime()-currentDate.getTime())/1000 ;
                logger.info("当前时间与预约提醒时间的 时间间隔为："+interval);
                
                
                if(interval<0){
             	   logger.info("预约提醒时间已失效，更新send_status=1");
             	   bookingManageService.updateSendStatusById(bookingManage.getId());
                }
                
                if(interval>0&&interval<=60){
             	   
             	   logger.info("预约提醒时间到了，可以推送了-----");
             	   sendJPushMsg(bookingManage,"纹身师",bookingReminderMsg);
             	   
             	   logger.info("提醒完毕，更新send_status=1");
             	   bookingManageService.updateSendStatusById(bookingManage.getId());
                }
    		}

    	
       
               long begin_interval =	(bookingManage.getBeginTime().getTime()-currentDate.getTime())/1000 ;
               
               if(begin_interval<0){
            	   logger.info("开始预约的时间已失效，更新pushs=1");
            	   bookingManageService.updatePushsStatusById(bookingManage.getId());
               }
               
                if(begin_interval>=0&&begin_interval<=60){
            	   
            	   logger.info("开始预约的时间到了，可以推送了-----");
            	   
            	   sendJPushMsg(bookingManage,"纹身师",staticMsg);
            	   sendJPushMsgToCustomer(bookingManage);
            	   
            	   logger.info("提醒完毕，更新pushs=1");
            	   bookingManageService.updatePushsStatusById(bookingManage.getId());
               }
                	
		}

    }
    
    public  void sendJPushMsg(BookingManage bookingManage,String whoRole,String msg){
    	
    	   List<JPush>  listJPush =  bookingManageService.getJPushListByPhone(bookingManage.getPhone());
    	   
    	   logger.info("查询到"+whoRole+"的设备号信息，共有："+listJPush.size()+"条");
    	   
    	   for (JPush jPush : listJPush) {

    		   logger.info("向->"+whoRole+bookingManage.getPhone()+"["+jPush.getEquipmentCode()+"]"+"发送推送消息:"+msg);
    			if ("androidT1".equals(jPush.getPhoneType())) {
    				JpushClientUtil.sendMsgT1(jPush.getEquipmentCode(),msg,JsonUtils.objectToJson(Result.build(200, msg, JpushClientUtil.ISBOOKING,bookingManage.getId()+"")),jPush.getEnvType());
				} else {
					JpushClientUtil.sendMsg(jPush.getEquipmentCode(),msg,JsonUtils.objectToJson(Result.build(200, msg,JpushClientUtil.ISBOOKING,bookingManage.getId()+"")),jPush.getEnvType(),jPush.getFromApp());
				}

		    }

    }
    
    public  void sendJPushMsgToCustomer(BookingManage bookingManage){
    	
    	
      List<User> customerList = bookingManageService.getCustomerFromUserByStoreId(bookingManage.getStoreId());
    
    	for (User user : customerList) {
			
    		logger.info("客服电话："+user.getPhone());
    		bookingManage.setPhone(user.getPhone());
    		sendJPushMsg(bookingManage,"客服",staticMsg);
    		
		}
    	
    	
    	
   

 }
    
    
}
