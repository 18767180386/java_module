package com.aiiju.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.WxSubMapper;
import com.aiiju.pojo.WxSub;
import com.aiiju.store.service.WxSubService;
@Service("wxSubService")
public class WxSubServiceImpl implements WxSubService {

	@Autowired
	private WxSubMapper wxSubMapper;
	
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

}
