package com.aiiju.pay.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.AppAuth;

/**
 * 
 * @ClassName: AppAuthService 
 * @Description: 第三方应用Service
 * @author 小飞 
 * @date 2016年11月29日 上午11:49:41 
 *
 */
public interface AppAuthService {

	public Boolean saveAppAuth(AppAuth auth);
	
	public Boolean updateAppAuth(AppAuth auth);
	
	public AppAuth getAppAuthByStoreId(String storeId);

	public Result checkUser(String userName);
}
