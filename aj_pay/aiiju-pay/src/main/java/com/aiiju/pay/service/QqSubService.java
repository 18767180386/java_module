package com.aiiju.pay.service;

import com.aiiju.pojo.QqSub;

/**
 * 
 * @ClassName: QqSubService 
 * @Description: qq钱包子商户
 * @author 小飞 
 * @date 2017年2月7日 上午11:05:13 
 *
 */
public interface QqSubService {

	public QqSub getQqSubByStoreId(String storeId);
	
	public Boolean saveQqSub(QqSub qqSub);
}
