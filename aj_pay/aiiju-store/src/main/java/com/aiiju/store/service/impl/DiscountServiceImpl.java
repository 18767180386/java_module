package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.DiscountMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.Discount;
import com.aiiju.pojo.DiscountShopLink;
import com.aiiju.pojo.Shop;
import com.aiiju.store.constant.DiscountTypeSwitch;
import com.aiiju.store.service.DiscountService;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName: DiscountServiceImpl 
 * @Description: 优惠折扣ServiceImpl
 * @author 小飞  
 * @date 2016年11月15日 下午3:01:32 
 *
 */
@Service("discountService")
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountMapper discountMapper;
	@Autowired
	private ShopMapper shopMapper;
	
	@Override
	public Result save(String storeId, Discount discount) {
		discount.setStatus(Byte.valueOf("2"));
		this.discountMapper.add(discount);
		//绑定店铺与优惠折扣关系
		DiscountShopLink link = new DiscountShopLink();
		link.setDiscountId(discount.getId());
		link.setStoreId(storeId);	
		this.discountMapper.addLink(link);
		return Result.success(discount.getId());
	}

	@Override
	public Result getAllByStoreId(String storeId, String operatorId) {
		
		
		JSONObject json = this.shopMapper.getDiscountStatusByStoreId(storeId);
		Object s =  json.get("is_discount");
		
		Map<String,String> map =new HashMap<String,String>();

        if(s instanceof JSONObject){
        	JSONObject jsons = (JSONObject)s;
        	
        	Set<String> set = jsons.keySet();
        	
        	for (String str : set) {
        	//	System.out.println("str="+str+"");
        		if((Integer)jsons.get(str)==1){
        			
        			//System.out.println("type为"+str+"的开关为开");
        			
        			map.put(str, str);
        		}
			}
		      
		}
		
		if(s instanceof String){
			String jsons = (String)s;
		    
			if(jsons.equals("1")){
    			map.put("1", "1");
    			map.put("2", "1");
    		}
			
		}
		
		
		
		
		List<Discount> list = this.discountMapper.getAllByStoreId(storeId);
		//优惠方式分组
		SortedMap<Byte,List<Discount>> dataMap = new TreeMap<>();
		List<Discount> temp = null;
		for (Discount discount : list) {
			Byte type = discount.getType();
			if (dataMap.get(type) != null) {	
				dataMap.get(type).add(discount);
			} else {
				temp = new ArrayList<Discount>();
				temp.add(discount);
				dataMap.put(type, temp);
			}
		}
		//转换格式
		List<Map<String,Object>> rt = new ArrayList<Map<String,Object>>();
		SortedMap<String,Object> rtMap = null;
		for (Map.Entry<Byte,List<Discount>> entry : dataMap.entrySet()) {
			System.out.println("entry.getKey():"+entry.getKey());
            if(map.containsKey(entry.getKey()+"")){
            	
            //	System.out.println("map中包含：key"+entry.getKey());
            	rtMap = new TreeMap<String, Object>();
    			rtMap.put("type", entry.getKey());
    			rtMap.put("list", entry.getValue());
    			rt.add(rtMap);
			}
		
		}
		return Result.success(rt);
	}

	@Override
	public Result delete(String storeId, Integer id) {
		DiscountShopLink link = new DiscountShopLink();
		link.setDiscountId(id);
		link.setStoreId(storeId);
		this.discountMapper.deleteLink(link);
		Discount discount = this.discountMapper.getById(id);
		if ("2".equals(discount.getStatus())) {
			this.discountMapper.delete(id);
		}
		return Result.success(true);
	}

	@Override
	public Result getSingleDiscountByStoreId(String storeId, String type) {
		
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("storeId", storeId);
		 map.put("type",type);
		List<Discount> list = this.discountMapper.getSingleDiscountByStoreId(map);
		JSONObject json = this.shopMapper.getDiscountStatusByStoreId(storeId);
		Object s =  json.get("is_discount");
		Integer status = null;

        if(s instanceof JSONObject){
        	JSONObject jsons = (JSONObject)s;
			if(jsons!=null&&jsons.toString().contains("{")){
				status = (Integer) jsons.get(type);
			}
		}
		
		if(s instanceof String){
			String jsons = (String)s;
			if(jsons!=null&&jsons.toString().contains("{")){
				status = (Integer) JSONObject.fromObject(jsons).get(type);
			}else{
				// 为了兼容，更新1.3.0版本 中的数据
				Shop shop = new Shop();
				shop.setStoreId(storeId);
				shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
				shopMapper.updateByStoreId(shop);
				status = 1;
			}
		}
		
		
		
		return Result.build(200, "成功", list, status+"");
	}

	@Override
	public Result updateSwitch(String storeId, String type) {
		
		JSONObject json = this.shopMapper.getDiscountStatusByStoreId(storeId);
		JSONObject s = (JSONObject) json.get("is_discount");
		Integer status = (Integer) s.get(type);
		
		if(status==1){
			s.put(type, 2);
		}else{
			s.put(type, 1);
		}
		
		
			Shop shop = new Shop();
			shop.setStoreId(storeId);
			shop.setIsDiscount(s.toString());
			shopMapper.updateByStoreId(shop);

		return Result.build(200, "更新成功", null, status==1?"2":"1");
	}
}
