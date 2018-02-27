package com.aiiju.pay.service;

import java.util.Map;

import com.aiiju.pojo.WxSub;
/**
 * 
 * @ClassName: WxSubService 
 * @Description: 微信子商户Service
 * @author 小飞 
 * @date 2016年12月21日 上午11:36:22 
 *
 */
public interface WxSubService {

	public WxSub getWxSubByStoreId(String storeId);
	
	public Boolean saveWxSub(WxSub wxSub);
	
	/**
	 * 	判断是否使用scrm优惠，如使用，通知scrm
	 * @param wxSub
	 */
	public void noticeSCRM(Map<String,Object> map);
}
