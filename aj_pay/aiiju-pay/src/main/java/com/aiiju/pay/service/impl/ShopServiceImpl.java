package com.aiiju.pay.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pay.service.ShopService;
import com.aiiju.pojo.Shop;
@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Override
	public String getShopName(String storeId) {
		return this.shopMapper.getShopNameByStoreId(storeId);
	}

	@Override
	public Shop getShopByStoreId(String storeId) {
		return this.shopMapper.getByStoreId(storeId);
	}

	@Override
	public boolean relativeERP(String storeId){
		String status = this.shopMapper.getShopRelativeErpStatus(storeId);
		if("1".equals(status)){
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public String relativeSCRM( Map<String, Object> map){
		if((map.get("storeId"))==null){
			map.put("storeId","");
		}
		String gelativeScrm = shopMapper.getRelativeScrm(map);
		
		return gelativeScrm;
	}

}
