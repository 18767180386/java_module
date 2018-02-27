package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;

/** 
 * @ClassName BusinessManageService
 * @Description
 * @author zong
 * @CreateDate 2017年7月5日 下午4:12:23
 */
public interface BusinessSwitchService {

	/**
	 * 获取创建总店设置是否开启
	 * 200 开启
	 * 
	 */
	 public Result getCreateAdminSwitch();
	
	
}
