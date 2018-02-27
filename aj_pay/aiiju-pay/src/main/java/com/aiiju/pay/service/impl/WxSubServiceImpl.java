package com.aiiju.pay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.NoteUtil;
import com.aiiju.common.util.PropertiesUtil;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.WxSubMapper;
import com.aiiju.pay.controller.wx.WXPayController;
import com.aiiju.pay.service.WxSubService;
import com.aiiju.pojo.WxSub;
@Service("wxSubService")
public class WxSubServiceImpl implements WxSubService {
	
	private static Logger logger = Logger.getLogger(WxSubServiceImpl.class);

	@Autowired
	private WxSubMapper wxSubMapper;
	
	@Autowired
    private JedisClient jedisClient;
	
	@Override
	public WxSub getWxSubByStoreId(String storeId) {
		return this.wxSubMapper.getByStoreId(storeId);
	}

	@Override
	public Boolean saveWxSub(WxSub wxSub) {
		try {
			this.wxSubMapper.add(wxSub);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void noticeSCRM(Map<String,Object> map) {
		 String  outTradeNo = map.get("outTradeNo")==null?null:map.get("outTradeNo").toString();
		 if(outTradeNo==null){
			 return;
		 }
		 String scrmCode = jedisClient.getAndDel("SCRMCALLBACKCODE:"+outTradeNo);
         String storeId = jedisClient.getAndDel("SCRMCALLBACKSTOREID:"+outTradeNo);
         
       //支付成功 通知scrm
			if(!StringUtils.isEmpty(scrmCode)){
				
				logger.info("----------------------使用scrm会员回调支付aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
				
				//通知scrm
				Map<String,Object> scrmMap = new HashMap<>();
				String totalFee =map.get("totalFee")==null?"0":map.get("totalFee").toString();//totalFee 整数，分为单位
				String price =Integer.valueOf(totalFee)/100+"";
				
				scrmMap.put("trade_id", map.get("out_trade_no"));
				scrmMap.put("code", scrmCode);
				scrmMap.put("payment", price);    
				scrmMap.put("shop_id", storeId);
				
				try{
					String date = DateUtil.format(map.get("time_start").toString(), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
					String desc = "用户在"+date+"时，实际支付 "+price+" 元。";
					scrmMap.put("desc",desc);
					
				}catch (Exception e) {
					logger.error("---------------日期转换失败-----------"+map.get("time_start"));
					String desc = "用户在"+DateUtil.currentTime(null)+"时，实际支付 "+price+" 元。";
					scrmMap.put("desc",desc);
				}
				try{
					NoteUtil.getScrm(PropertiesUtil.getPropertyByKey("scrm_membercard_trade"), scrmMap, 1);
				}catch (Exception e) {
					e.printStackTrace();
					logger.error("---------------通知scrm失败-----------"+scrmMap);
				}
				
				logger.info("----------------------使用scrm会员回调支付aop结束:"+new Date(System.currentTimeMillis())+"--------------------------------------");	
			}
         
		
	}

}
