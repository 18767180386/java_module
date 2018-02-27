package com.aiiju.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.AppAuthMapper;
import com.aiiju.pojo.AppAuth;
import com.aiiju.store.service.AppAuthService;
/**
 * 
 * @ClassName: AppAuthServiceImpl 
 * @Description: 第三方应用授权ServiceImpl
 * @author 小飞 
 * @date 2016年11月29日 上午11:52:09 
 *
 */
@Service("appAuthService")
public class AppAuthServiceImpl implements AppAuthService {

	@Autowired
	private AppAuthMapper appAuthMapper;
	
	@Override
	public Boolean saveAppAuth(AppAuth auth) {
		try {
			this.appAuthMapper.add(auth);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean updateAppAuth(AppAuth auth) {
		try {
			this.appAuthMapper.update(auth);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public AppAuth getAppAuthByStoreId(String storeId) {
		AppAuth auth = this.appAuthMapper.getByStoreId(storeId);
		return auth;
	}

	/* (non-Javadoc)
	 * @see com.aiiju.store.service.AppAuthService#getAppAuthList()
	 */
	@Override
	public List<AppAuth> getAppAuthList() {
		
		List<AppAuth> list = appAuthMapper.getAppAuthList();
		return list;
	}



}
