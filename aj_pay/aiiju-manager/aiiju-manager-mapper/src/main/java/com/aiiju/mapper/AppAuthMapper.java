package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.AppAuth;
/**
 * 
 * @ClassName: AppAuthMapper 
 * @Description: 第三方应用授权Mapper
 * @author 小飞 
 * @date 2016年11月29日 上午11:29:29 
 *
 */
public interface AppAuthMapper {

	public void add(AppAuth auth);
	
	public void update(AppAuth auth);
	
	public AppAuth getByStoreId(String storeId);
	

	public List<AppAuth> getAppAuthList();
	
}
