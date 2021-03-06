package com.aiiju.pay.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JpushClientUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.mapper.JPushMapper;
import com.aiiju.mapper.MessageMapper;
import com.aiiju.pay.service.JPushService;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.Message;

@Service("jpushService")
public class JPushServiceImpl implements JPushService {
    
	private static Logger logger = Logger.getLogger(JPushServiceImpl.class);
	
    private  Message msgObj = new Message();

    @Autowired
    private JPushMapper jpushMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void sendPayNotice(Deal deal) {
        if (deal.getReceTypeDesc().indexOf("台卡") > 0) {
            // 发送给该店铺所有人
            this.sendAll(deal);
        } else {
            // 发送给操作员和店长
            this.sendNotice(deal);
        }
    }

    @Override
    public void sendRefundNotice(Deal deal) {
    	logger.info("--------------------------->发送推送消息");
    	
        // 保存消息
        Message message = (Message) msgObj.clone();
        message.setStoreId(deal.getStoreId());
        message.setOperatorId(deal.getOperatorId());
        message.setMoney(deal.getPrice().toString());
        message.setMsgType(Byte.valueOf(deal.getRefundType()));
        message.setSubject(deal.getReceTypeDesc());
        message.setContent("￥" + deal.getPrice().abs().toString() + "元,退款成功");
        message.setExt(deal.getSerialNum());
        this.messageMapper.add(message);

        
 
        List<JPush> jpushList = this.jpushMapper.getByOperatorId(deal.getOperatorId());
        
        for (JPush jPush2 : jpushList) {
         	String code = jPush2.getEquipmentCode();  
            String msgType = JpushClientUtil.REFUND + "_" + deal.getRefundType();
            
         	String phoneType = jPush2.getPhoneType();
         	
         	if("androidT1".equals(phoneType)){
         		String preMsg ="成功退款"+deal.getPrice().abs().toString().toString()+"元";
         		JpushClientUtil.sendMsgT1(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, preMsg, msgType)),jPush2.getEnvType());
         	}else{
         	   JpushClientUtil.sendMsg(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, message.getContent(), msgType)),jPush2.getEnvType(),jPush2.getFromApp());
         	}

		}
        
 
    }

    private void sendNotice(Deal deal) {
    	
    	logger.info("--------------------------->发送推送消息");
    	
        // 保存消息
        Message message = (Message) msgObj.clone();
        
        message.setStoreId(deal.getStoreId());
        message.setOperatorId(deal.getOperatorId());
        message.setMoney(deal.getPrice().toString());
        message.setMsgType(Byte.valueOf(deal.getPayType()));
        String msgType = null;
        if (deal.getReceTypeDesc().indexOf("扫码") > 0) {
            message.setSubject("扫码收款");
            msgType = JpushClientUtil.PAYMENT + "_3";
        } else if (deal.getReceTypeDesc().indexOf("条码") > 0) {
            message.setSubject("条码收款");
            msgType = JpushClientUtil.PAYMENT + "_2";
        } else {
            message.setSubject("现金收款");
            msgType = JpushClientUtil.PAYMENT + "_0";
        }
        message.setContent("￥" + deal.getPrice().toString() + "元,顾客付款成功");
      
        message.setExt(deal.getSerialNum());
        this.messageMapper.add(message);

        List<JPush> jpushList = this.jpushMapper.getByOperatorId(deal.getOperatorId());
        
        for (JPush jPush2 : jpushList) {
         	String code = jPush2.getEquipmentCode();
         	String phoneType = jPush2.getPhoneType();
         	
         	if("androidT1".equals(phoneType)){
         		
         		String payType = deal.getPayType();
         		
         		String preMsg = "";
         		
         		if("1".equals(payType)||"4".equals(payType)){
         			preMsg= "支付宝成功收款"+deal.getPrice().toString()+"元";
         		}else if("2".equals(payType)||"5".equals(payType)){
                	preMsg= "微信成功收款"+deal.getPrice().toString()+"元";
         		}else if("0".equals(payType)){
                	preMsg= "现金成功收款"+deal.getPrice().toString()+"元";
         		}else{
         			preMsg= "成功收款"+deal.getPrice().toString()+"元";
         		}
         		  JpushClientUtil.sendMsgT1(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, preMsg, msgType, deal.getOperatorId(),deal.getSerialNum())),jPush2.getEnvType());
         		  
         	}else{
         		  JpushClientUtil.sendMsg(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, message.getContent(), msgType, deal.getOperatorId(),deal.getSerialNum())),jPush2.getEnvType(),jPush2.getFromApp());
         	}
         	
//         	logger.info("------------------------------收款推送消息内容----------------------------------");
//         	
//        	logger.info("code="+code);
//        	logger.info("message.getContent()="+message.getContent());
//        	logger.info("msgType="+msgType);
//        	logger.info("deal.getOperatorId()="+deal.getOperatorId());
//        	logger.info("deal.getSerialNum()="+deal.getSerialNum());
//        	
//        	logger.info("-------------------------------------------------------------------------------");
//
//            JpushClientUtil.sendMsg(Arrays.asList(code), message.getContent(),JsonUtils.objectToJson(Result.build(200, message.getContent(), msgType, deal.getOperatorId(),deal.getSerialNum())));
//            
		}

    }

    private void sendAll(Deal deal) {
    	logger.info("--------------------------->发送推送消息");
        // 保存消息
        Message message = (Message) msgObj.clone();
        message.setStoreId(deal.getStoreId());
        message.setMoney(deal.getPrice().toString());
        message.setMsgType(Byte.valueOf("3"));
        message.setSubject("台卡收款");
        message.setContent("￥" + deal.getPrice().toString() + "元,顾客付款成功");
        message.setExt(deal.getSerialNum());
        this.messageMapper.add(message);

//        List<String> codes = new ArrayList<String>();
        List<JPush> list = this.jpushMapper.getByStoreId(deal.getStoreId());
        
        
        for (JPush jPush2 : list) {
         	String code = jPush2.getEquipmentCode();
         	String phoneType = jPush2.getPhoneType();
         	
         	if("androidT1".equals(phoneType)){
         		
         		
 		     String payType = deal.getPayType();
         		
         		String preMsg = "";
         		
        		if("1".equals(payType)||"4".equals(payType)){
         			preMsg= "支付宝成功收款"+deal.getPrice().toString()+"元";
         		}else if("2".equals(payType)||"5".equals(payType)){
                	preMsg= "微信成功收款"+deal.getPrice().toString()+"元";
         		}else if("0".equals(payType)){
                	preMsg= "现金成功收款"+deal.getPrice().toString()+"元";
         		}else{
         			preMsg= "成功收款"+deal.getPrice().toString()+"元";
         		}
         		  JpushClientUtil.sendMsgT1(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, preMsg, JpushClientUtil.PAYMENT + "_1", null,deal.getSerialNum())),jPush2.getEnvType());
         		  
         	}else{
         		  JpushClientUtil.sendMsg(code, message.getContent(),JsonUtils.objectToJson(Result.build(200, message.getContent(), JpushClientUtil.PAYMENT + "_1", null,deal.getSerialNum())),jPush2.getEnvType(),jPush2.getFromApp());
         	}
         	
  
		}
        
        
    
    }
    
}
