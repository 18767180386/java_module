package com.aiiju.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.AppAuthMapper;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pay.business.zfb.util.AlipayUtil;
import com.aiiju.pay.service.AppAuthService;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.User;
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
	private UserMapper userMapper;
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

	@Override
	public Result checkUser(String userName) {
		User user = this.userMapper.checkByUserNameWithoutShop(userName);
		if (user == null) {
			return Result.build(400, "用户名未注册");
		}
		AppAuth appAuth = this.appAuthMapper.getByStoreId(user.getStoreId());
		if (appAuth != null) {
			long t1 = appAuth.getModifyDate().getTime()+ Long.parseLong(appAuth.getExpiresIn())*1000;//授权记录时间+token有效时间
			long t2 = System.currentTimeMillis();//当前毫秒值
			if( t1 - t2  > 86400) {
				return Result.build(400, "用户已授权");
			}
		}
		return Result.success("https://openauth.alipay.com/oauth2/appToAppAuth.htm?type=app&storeId="+user.getStoreId()+
				"&app_id="+AlipayUtil.APP_ID+"&redirect_uri="+AlipayUtil.AUTH_CALL_URL);//https://trade.ecbao.cn/zfbauth/getToken
	}

}
