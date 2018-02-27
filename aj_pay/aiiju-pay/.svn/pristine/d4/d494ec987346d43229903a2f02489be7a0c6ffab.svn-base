package com.aiiju.pay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.PAMerchantMapper;
import com.aiiju.mapper.PAShopMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pay.service.PAService;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.PAShop;
import com.aiiju.pojo.Shop;

/** 
 * @ClassName PAServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年8月3日 下午7:02:13
 */
@Service("paService")
public class PAServiceImpl implements PAService {

	@Autowired
	private PAShopMapper paShopMapper;
	
	@Autowired
	private PAMerchantMapper merchantMapper;
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Override
	public PAShop getPAShop(String storeId, String parentStoreId, String shopType) {
		
		PAShop pashop = null;
		if(!"2".equals(shopType)){
			
			if(StringUtils.isEmpty(parentStoreId)){
				Shop shop = shopMapper.getByStoreId(storeId);
				parentStoreId=shop.getParentStoreId();
			}
			
			
			
			 pashop=	paShopMapper.findPAShopByParStoreId(parentStoreId);
			
			//查询  storeId ={storeId} and storeId = parentStoreId 的 openid 和 openkey 信息
			
		}else {
			 pashop=	paShopMapper.findPAShopByStoreId(storeId);
			//查询  storeId ={storeId} 的 openid 和 openkey 信息
		}
		
		return pashop;
		
	}


	@Override
	public PAMerchant getPAMerchant(String storeId, String parentStoreId, String shopType) {
		
		PAMerchant merchant = null;
		if(!"2".equals(shopType)){
			
			if(StringUtils.isEmpty(parentStoreId)){
				Shop shop = shopMapper.getByStoreId(storeId);
				parentStoreId=shop.getParentStoreId();
			}
			
			merchant=	merchantMapper.findPAMerchantByParStoreId(parentStoreId);
			
			//查询  storeId ={storeId} and storeId = parentStoreId 的 openid 和 openkey 信息
			
		}else {
			merchant=	merchantMapper.findPAMerchantByStoreId(storeId);
			//查询  storeId ={storeId} 的 openid 和 openkey 信息
		}
		

		return merchant;
	}

}
