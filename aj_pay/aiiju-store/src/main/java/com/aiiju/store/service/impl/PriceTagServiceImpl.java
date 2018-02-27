package com.aiiju.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.GoodsMapper;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pojo.Goods;
import com.aiiju.pojo.User;
import com.aiiju.store.service.PriceTagService;

/** 
 * @ClassName PriceTagServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年8月18日 下午2:37:54
 */

@Service("priceTagService")
public class PriceTagServiceImpl implements PriceTagService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    
	 

	@Override
	public List<Goods> getAllGoodsByPhone(String phone) {
		
		User user = userMapper.checkByPhone(phone);

		String storeId = user.getStoreId();
		
		List<Goods> list= goodsMapper.getAllGoodsByStoreId(storeId);
		
		
		return list;
	}

}
