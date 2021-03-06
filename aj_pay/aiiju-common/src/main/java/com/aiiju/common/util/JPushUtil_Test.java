package com.aiiju.common.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.aiiju.common.pojo.Result;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 * @ClassName: PushUtil
 * @Description: 极光推送工具类
 * @author 小飞
 * @date 2016年12月20日 下午1:46:44
 *
 */
public class JPushUtil_Test {
    private static String appKey = "3e000b01554ab8a86763ed94";

    private static String masterSecret = "72ae0edc92d18814193e3fad";

    private static JPushClient jpushClient = null;

    public static final String INVITE = "invite";

    public static final String PAYMENT = "payment";

    public static final String REFUND = "refund";

    public static final String UNBIND = "unbind";

    public static final String KICK = "kick";
    
    
    public static void main(String[] args) {


    	SimpleDateFormat sd = new SimpleDateFormat("yyyy年M月dd日 HH:mm");
    	String time = sd.format(new Date());
    	String msg = "最新消息,您的账户成功收款1亿\n\r收款时间"+time;
    //	String msgJson = "{\"aa\":\"aac\"}";
    	
    
    	String msgJson = "{1:1,1:1}";
    		
    
    	//	JPushUtil_Test.sendMsg(Arrays.asList("140fe1da9e982058310"), msg, msgJson);
    	
    	//	JPushUtil_Test.sendMsg(Arrays.asList("120c83f760280d00ad5"), msg, msgJson);
    	
    	    
    	//	JPushUtil_Test.sendMsg(Arrays.asList("1517bfd3f7f5fbab338"), msg, msgJson);
    	//	JPushUtil_Test.sendMsg(Arrays.asList("121c83f760176de29fe"), msg, msgJson);
    		
    	//	JPushUtil_Test.sendMsg(Arrays.asList("13165ffa4e3b3e37d35"), msg, msgJson);
    		
    	
    //	JPushUtil_Test.sendMsg(Arrays.asList("101d85590975473b69a"), msg, msgJson);
    //	JPushUtil_Test.sendMsg(Arrays.asList("1a1018970a90f2f7c67"), msg, msgJson);
    //	JPushUtil_Test.sendMsg(Arrays.asList("141fe1da9e99133252f"), msg, msgJson);
    //	JPushUtil_Test.sendMsg(Arrays.asList("1104a89792a673b6dad"), msg, msgJson);
    	
    	
//        Message message = (Message) msgObj.clone();
//        message.setStoreId(deal.getStoreId());
//        message.setMoney(deal.getPrice().toString());
//        message.setMsgType(Byte.valueOf("3"));
//        message.setSubject("台卡收款");
//        message.setContent("￥" + deal.getPrice().toString() + "元,顾客付款成功");
//        message.setExt(deal.getSerialNum());
    	
    	 //JPushUtil.sendMsg(Arrays.asList("1a1018970a90f2f7c67"),"￥6元,顾客付款成功",JsonUtils.objectToJson(Result.build(200, "￥6元,顾客付款成功", "3", "","")));	
    	
        //JPushUtil.sendMsg(Arrays.asList("1a1018970a90f2f7c67"), "吹鼓手34543543",
                //  JsonUtils.objectToJson(Result.build(200, message.getContent(), msgType, deal.getOperatorId()+","+deal.getSerialNum())));
                  //JsonUtils.objectToJson(Result.build(200, "吹鼓手", "1", "13165ffa4e3e09de8831","12312312321")));
    		//  A用户 设备号 A1   B用户 设备号B1
    		 
    		 // A用户登录（设备号A1）
    		// b用户用A账号登录（设备号A1），此时A用户下线
    		//A用户用B用户的账号登录（设备号）
    		
//		JPushUtil.sendMsg(Arrays.asList(jPush2.getEquipmentCode()),
//				"检测到您的账号在另一设备上登录,此设备已退出,请确保是本人操作",
//				JsonUtils.objectToJson(Result.success(JPushUtil.KICK)));
    	 
    	
    	
    	//System.out.println(b);
	}

    private JPushUtil_Test() {
    }

    /**
     * 发送消息
     * 
     * @param registrationID
     * @return
     */
    public static Boolean sendMsg(List<String> registrationID, String msg, String msgJson) {
        boolean flag = false;
        try {
            jpushClient = new JPushClient(masterSecret, appKey);// 消息默认保留一天
            PushResult result = jpushClient
                    .sendPush(buildPushObject_all_all_regesterIds(registrationID, msg, msgJson));
            
            System.out.println(result);
//            System.out.println(result.getOriginalContent());
//            System.out.println(result.getRateLimitRemaining());
//     
//            System.out.println(result.getRateLimitReset());
//            System.out.println(result.getRateLimitQuota());
   
            if (result != null) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    private static PushPayload buildPushObject_all_all_regesterIds(List<String> registrationIDs, String msg,
            String msgJson) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(registrationIDs))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(msg).setTitle("")
                                .addExtra("json", msgJson).build())
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(msg).incrBadge(1)
                                .addExtra("json", msgJson).build())
                        .build())
                .build();

    }
}
