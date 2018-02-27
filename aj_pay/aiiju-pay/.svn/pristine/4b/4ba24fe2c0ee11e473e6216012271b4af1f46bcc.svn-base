package com.aiiju.pay.aop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.chainsaw.Main;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.NoteUtil;
import com.aiiju.common.util.PropertiesUtil;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.MPointsDetailMapper;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.ShopService;
import com.aiiju.pojo.Deal;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.google.gson.JsonObject;

/**
 * scrm支付aop通知
 * @author qiyu
 *
 */
@Aspect
@Component
public class ScrmPayNotify {
	
	
	@Autowired
	private MPointsDetailMapper mPonitsDeailMapper;
	
	@Autowired
    private JedisClient jedisClient;
	
	@Autowired
	private DealService dealService;
	
    @Autowired
    private ShopService shopService;
	
	
	private static Logger logger = LoggerFactory.getLogger(ScrmPayNotify.class);
	/**
	 * 支付成功后通知scrm
	 * @param pjp
	 * @return
	 */
	@Around("execution(* com.aiiju.pay.controller.cash.CashController.pay(..))"
			+ " || execution(* com.aiiju.pay.controller.pa.zfb.PAZFBPayController.barCodePay(..))"
			+ " || execution(* com.aiiju.pay.controller.pa.wx.PAWXPayController.barCodePay(..))"
			+ " || execution(* com.aiiju.pay.controller.zfb.ZFBPayController.barCodePay(..))"
			+ " || execution(* com.aiiju.pay.controller.wx.WXPayController.barCodePay(..))"
			)
			
	public Result scrmTradeAop(ProceedingJoinPoint pjp){
		
		Result result = null;
		try {
			String scrmMark = null;		//是否绑定scrm会员
			String scrmCode = null;		//scrm会员卡
			HttpServletRequest rq = null;		
			//获取调用参数
			Object[] args = pjp.getArgs();
			if(args!=null&&args.length>0){
				 rq = (HttpServletRequest)args[0];   //第一个参数
				if(rq!=null){
					scrmCode =	rq.getParameter("scrmCode"); 
					String storeId  = rq.getParameter("storeId");   
					if(!StringUtils.isEmpty(storeId)){
						Map<String,Object> map = new HashMap<>();
						map.put("storeId", storeId);
						scrmMark = shopService.relativeSCRM(map);
					}
				}
			}
			try{
				result = (Result) pjp.proceed();			//执行被加强方法
			}catch (Exception e) {
				e.printStackTrace();
				scrmMark =null;
			}
			
			//支付成功 
			if(!StringUtils.isEmpty(scrmMark)&&result!=null&&result.getStatus()!=null&&(result.getStatus()==200||result.getStatus()==10000)){
				
				//1.绑定scrm会员，不在aiju收银做积分计算，部分支付接口支付成功有增加积分逻辑，
				//当绑定scrm会员，删除该积分记录
				Map<String,Object> map = new HashMap<>();
				Object dealSerialNum =null;
				if(result.getStatus()==200){							//现金支付返回的流水号在data,其余在other	
					dealSerialNum= result.getData();
				}else{
					dealSerialNum=result.getOther();
				}
				map.put("dealSerialNum", dealSerialNum);
				mPonitsDeailMapper.delete(map);
				
				
				//2.当使用scrm会员卡才通知scrm
				if(!StringUtils.isEmpty(scrmCode)){
					logger.info("----------------------使用scrm会员aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
					
					Map<String,Object> scrmMap = new HashMap<>();
					scrmMap.put("trade_id", dealSerialNum);
					scrmMap.put("code", scrmCode);
					scrmMap.put("payment", rq.getParameter("price"));
					scrmMap.put("shop_id", rq.getParameter("storeId"));
					String desc = "用户"+ rq.getParameter("memberPhone")+"在"+
							DateUtil.currentTime("yyyy-MM-dd")+"时，实际支付 "+rq.getParameter("price")+" 元。";
					scrmMap.put("desc",desc);
					try{
						logger.info("--------------------通知scrmk开始---------------------");	
						NoteUtil.getScrm(PropertiesUtil.getPropertyByKey("scrm_membercard_trade"), scrmMap, 1);
					}catch (Exception e) {
						e.printStackTrace();
						logger.error("---------------通知scrm失败-----------"+scrmMap);
					}
					
					logger.info("----------------------使用scrm会员aop结束:"+new Date(System.currentTimeMillis())+"--------------------------------------");	
				}
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			return Result.build(500, "系统内部错误");
		}
		
		return result;
		
	}
	
	
	
	
	/**
	 * scrm打折预下单AOP
	 * @param pjp
	 * @return
	 */
	@Around(	"execution(* com.aiiju.pay.controller.pa.zfb.PAZFBPayController.qrCodePay(..))"
			+ " || execution(* com.aiiju.pay.controller.pa.wx.PAWXPayController.qrCodePay(..))"
			+ " || execution(* com.aiiju.pay.controller.zfb.ZFBPayController.tradePrecreate(..))"
			+ " || execution(* com.aiiju.pay.controller.wx.WXPayController.tradePrecreate(..))"
			)
			
	public Result scrmPreOrderAop(ProceedingJoinPoint pjp){
		
		Result result = null;
		try {
			String scrmCode = null;		//scrm会员卡号
			String storeId = null;		//店铺storeId
			String scrmMark = null;		//是否绑定scrm会员
			HttpServletRequest rq = null;
			//获取调用参数
			Object[] args = pjp.getArgs();
			if(args!=null&&args.length>0){
				 rq = (HttpServletRequest)args[0];   //第一个参数
				if(rq!=null){
					scrmCode =	rq.getParameter("scrmCode"); 
					 storeId  = rq.getParameter("storeId");   
					if(!StringUtils.isEmpty(storeId)){
						Map<String,Object> map = new HashMap<>();
						map.put("storeId", storeId);
						scrmMark = shopService.relativeSCRM(map);
					}
				}
			}
			
			try{
				result = (Result) pjp.proceed();			//执行被加强方法
			}catch (Exception e) {
				e.printStackTrace();
				scrmMark =null;
			}
			//支付成功 
			if(StringUtils.isEmpty(scrmMark)&&result!=null&&result.getStatus()!=null&&(result.getStatus()==200||result.getStatus()==10000)){
				
				//扫码的付款需要回调确认 ，先确保预下单时，吧订单后存入缓存中
				//当回调后，去缓存认后，回调scrm(在回调方法中加强)
				String serialNum  = result.getOther().toString();
				//删除支付积分
				Map<String,Object> map = new HashMap<>();
				map.put("dealSerialNum", serialNum);
				mPonitsDeailMapper.delete(map);

				if(!StringUtils.isEmpty(scrmCode)){
					logger.info("----------------------使用scrm会员预下单aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
					
					jedisClient.setex("SCRMCALLBACKCODE:"+serialNum,1200,scrmCode);  	//回调会员卡号
					jedisClient.setex("SCRMCALLBACKSTOREID:"+serialNum,1200,storeId);	//回调店铺storeId
					
					logger.info("----------------------使用scrm会员预下单aop结束:"+new Date(System.currentTimeMillis())+"--------------------------------------");	
				}
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			return Result.build(500, "系统内部错误");
		}
		
		return result;
		
	}
	
	/**
	 * scrm打折回调成功支付，再通知scrm(微信)// （停止使用）
	 * 注意：因微信支付回调成功后是用IO流的形式，在AOP用流解析后在，业务中无法在使用
	 * 处理方式： 现吧通知内置在回调的接口逻辑中，不做AOP加强
	 * @param pjp
	 * @return
	 */
	public void scrmCallBackWXAop(ProceedingJoinPoint pjp){
		
			String scrmCode = null;		//scrm会员卡号
			String storeId = null;		//scrm店铺storeID
			HttpServletRequest rq = null;
			InputStream inputStream = null;
			Map<String, String> map = new HashMap<String, String>();//xml 解析后的键值队
			//获取调用参数
			Object[] args = pjp.getArgs();
			if(args!=null&&args.length>0){
				 rq = (HttpServletRequest)args[0];   //第一个参数
				if(rq!=null){
			            // 解析结果存储在HashMap
					try {
			            // 读取输入流
			            SAXReader reader = new SAXReader();
			            inputStream = rq.getInputStream();
			            
			            BufferedInputStream  bf = new BufferedInputStream(inputStream);
			            bf.mark(10000);
			            Document document = reader.read(bf);
			            bf.reset();
			            // 得到xml根元素
			            Element root = document.getRootElement();
			            // 得到根元素的所有子节点
			            List<Element> elementList = root.elements();

			            // 遍历所有子节点
			            for (Element e : elementList) {
			                map.put(e.getName(), e.getText());
			            }
			            String returnCode = map.get("return_code");
			            if ("SUCCESS".equals(returnCode)) {
			               String  outTradeNo = map.get("out_trade_no");
			               scrmCode = jedisClient.getAndDel("SCRMCALLBACKCODE:"+outTradeNo);
			               storeId = jedisClient.getAndDel("SCRMCALLBACKSTOREID:"+outTradeNo);
			            }
			            
			            
		            }catch (Exception e) {
		            	logger.error("---------------无法解析xml-----------"+inputStream);
					} finally {
			            if (inputStream != null) {
			                try {
			                    inputStream.close();
			                    inputStream = null;
			                } catch (IOException e) {
			                	
			                    e.printStackTrace();
			                }
			            }
			        }
				}
			}
			
			
			try {
				pjp.proceed();
			} catch (Throwable e) {
				scrmCode=null;
				logger.error("---------------pjp 执行失败-----------");
				e.printStackTrace();
			}
						
			
			//支付成功 通知scrm
			if(!StringUtils.isEmpty(scrmCode)){
				
				logger.info("----------------------使用scrm会员回调支付aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
				
				//通知scrm
				Map<String,Object> scrmMap = new HashMap<>();
				String totalFee =map.get("total_fee")==null?"0":map.get("total_fee");//total_fee 整数，分为单位
				String price =Integer.valueOf(totalFee)/100+"";
				
				scrmMap.put("trade_id", map.get("out_trade_no"));
				scrmMap.put("code", scrmCode);
				scrmMap.put("payment", price);    
				scrmMap.put("shop_id", storeId);
				
				try{
					String date = DateUtil.format(map.get("time_start"), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
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
	
	
	
	/**
	 * scrm打折回调成功支付，再通知scrm(支付宝)
	 * @param pjp
	 * @return
	 */
	@Around("execution(* com.aiiju.pay.controller.zfb.ZFBPayController.notifyResult(..))")
	public void scrmCallBackZFBAop(ProceedingJoinPoint pjp){
		
			String scrmCode = null;		//scrm会员卡号
			String storeId = null;		//scrm店铺storeID
			HttpServletRequest rq = null;
			//获取调用参数
			Object[] args = pjp.getArgs();
			if(args!=null&&args.length>0){
				 rq = (HttpServletRequest)args[0];   //第一个参数
				if(rq!=null){
			            String  status = rq.getParameter("trade_status");
			            if ("TRADE_SUCCESS".equals(status)||"TRADE_FINISHED".equals(status)) {
			            	String  outTradeNo = rq.getParameter("out_trade_no");
			            	scrmCode = jedisClient.getAndDel("SCRMCALLBACKCODE:"+outTradeNo);
			            	storeId = jedisClient.getAndDel("SCRMCALLBACKSTOREID:"+outTradeNo);
			            }
					}
				}
			try {
				pjp.proceed();
			} catch (Throwable e) {
				scrmCode=null;
				e.printStackTrace();
			}			
			
			//支付成功 通知scrm
			if(!StringUtils.isEmpty(scrmCode)){
				
				logger.info("----------------------使用scrm会员回调支付aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
				Map<String,Object> scrmMap = new HashMap<>();
				scrmMap.put("trade_id", rq.getParameter("out_trade_no"));
				scrmMap.put("code", scrmCode);
				scrmMap.put("payment", rq.getParameter("total_amount"));    
				scrmMap.put("shop_id", storeId);
				
				String desc = "用户在"+rq.getParameter("notify_time")+"时，实际支付 "+rq.getParameter("total_amount")+" 元。";
				scrmMap.put("desc",desc);
				
				try{
					NoteUtil.getScrm(PropertiesUtil.getPropertyByKey("scrm_membercard_trade"), scrmMap, 1);
				}catch (Exception e) {
					e.printStackTrace();
					logger.error("---------------通知scrm失败-----------"+scrmMap);
				}
				
				logger.info("----------------------使用scrm会员回调支付aop结束:"+new Date(System.currentTimeMillis())+"--------------------------------------");	
			}
			
		
		
	}
	
	/**
	 * scrm打折回调成功支付，再通知scrm(平安银行)
	 * @param pjp
	 * @return
	 */
	@Around("execution(* com.aiiju.pay.controller.pa.zfb.PAZFBPayController.notifyResult(..))"
			+ " || execution(* com.aiiju.pay.controller.pa.wx.PAWXPayController.notifyResult(..))"
			)
	public void scrmCallBackPAAop(ProceedingJoinPoint pjp){
		
			String scrmCode = null;		//scrm会员卡号
			String storeId = null;		//scrm店铺storeID
			String price = null;		//订单价格
			HttpServletRequest rq = null;
			//获取调用参数
			Object[] args = pjp.getArgs();
			if(args!=null&&args.length>0){
				 rq = (HttpServletRequest)args[0];   //第一个参数
				if(rq!=null){
		            if ("1".equals(rq.getParameter("status"))) {
		            	String  outTradeNo = rq.getParameter("out_no");
		            	Deal deal = dealService.getDealBySerialNum(outTradeNo);
		            	if(deal!=null){
		            		price = deal.getPrice().toString();
		            		scrmCode = jedisClient.getAndDel("SCRMCALLBACKCODE:"+outTradeNo);
		            		storeId = jedisClient.getAndDel("SCRMCALLBACKSTOREID:"+outTradeNo);
		            	}
		            }
				}
			}
			try {
				pjp.proceed();
			} catch (Throwable e) {
				scrmCode=null;
				e.printStackTrace();
			}			
			
			//支付成功 通知scrm
			if(!StringUtils.isEmpty(scrmCode)){
				
				logger.info("----------------------使用scrm会员回调支付aop开始:"+new Date(System.currentTimeMillis())+"--------------------------------------");
				Map<String,Object> scrmMap = new HashMap<>();
				scrmMap.put("trade_id", rq.getParameter("out_no"));
				scrmMap.put("code", scrmCode);
				scrmMap.put("payment", price);    
				scrmMap.put("shop_id", storeId);
				
				String desc = "用户在"+DateUtil.currentTime(null)+"时，实际支付 "+price+" 元。";
				scrmMap.put("desc",desc);
				
				try{
					NoteUtil.getScrm(PropertiesUtil.getPropertyByKey("scrm_membercard_trade"), scrmMap, 1);
				}catch (Exception e) {
					e.printStackTrace();
					logger.error("---------------通知scrm失败-----------"+scrmMap);
				}
				
				logger.info("----------------------使用scrm会员回调支付aop结束:"+new Date(System.currentTimeMillis())+"--------------------------------------");	
			}
	}	
	
	
	public static void main(String[] args) throws Exception {
         InputStream in = new FileInputStream("C://Users//R//Desktop//123.txt");
         BufferedInputStream  bf = new BufferedInputStream(in);
         bf.mark(10000);
//         SAXReader reader = new SAXReader();
//         Document document = reader.read(bf);
//         System.out.println( document);
//	      SAXReader reader2 = new SAXReader();
//	         Document document2 = reader2.read(bf);
//	         System.out.println( document2);
         byte [] b = new  byte[1024];
         byte [] x = new  byte[1024];
	         bf.read(b);
	         bf.read(x);
	         System.out.println(new String(b));
	         System.out.println(new String(x));
	         bf.reset();
	         in = bf;
	         SAXReader reader = new SAXReader();
	         Document document = reader.read(in);
	         System.out.println( document);
		
	}

}
