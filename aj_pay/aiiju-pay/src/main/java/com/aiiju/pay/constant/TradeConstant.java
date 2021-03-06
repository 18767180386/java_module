package com.aiiju.pay.constant;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: Constant 
 * @Description: 支付方式常量
 * @author 小飞 
 * @date 2016年12月19日 下午7:20:10 
 *
 */
public class TradeConstant {

	private  static Map<String,String> receTypeDescMap = new HashMap<>();
	private  static Map<String,String> refundTypeDescMap = new HashMap<>();
	
	static {
		
		receTypeDescMap.put("00", "现金");
		
		receTypeDescMap.put("11", "二维码台卡-支付宝");
		receTypeDescMap.put("12", "支付宝扫码");
		receTypeDescMap.put("13", "支付宝条码");
		
		receTypeDescMap.put("21", "二维码台卡-微信");
		receTypeDescMap.put("22", "微信扫码");
		receTypeDescMap.put("23", "微信条码");
		
		receTypeDescMap.put("31", "二维码台卡-qq钱包");
		receTypeDescMap.put("32", "qq钱包扫码");
		receTypeDescMap.put("33", "qq钱包条码");
		
		
		receTypeDescMap.put("41", "二维码台卡-银行");
		receTypeDescMap.put("42", "银行支付宝扫码");
		receTypeDescMap.put("43", "银行支付宝条码");
		
		receTypeDescMap.put("51", "银行微信扫码");
		receTypeDescMap.put("52", "银行微信条码");
		
		receTypeDescMap.put("61", "会员余额");
		
		//==========================================
		refundTypeDescMap.put("0", "现金退款");
		refundTypeDescMap.put("1", "支付宝退款");
		refundTypeDescMap.put("2", "微信退款");
		refundTypeDescMap.put("3", "qq钱包退款");
		
		refundTypeDescMap.put("4", "银行支付宝退款");
		refundTypeDescMap.put("5", "银行微信退款");
		refundTypeDescMap.put("6", "会员余额退款");
		
		
		
		
	}

	public static Map<String, String> getReceTypeDescMap() {
		return receTypeDescMap;
	}
	
	public static Map<String, String> getRefundTypeDescMap() {
		return refundTypeDescMap;
	}
}
