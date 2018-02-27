package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.GoodsMapper;
import com.aiiju.mapper.InventoryMapper;
import com.aiiju.pojo.DealDetail;
import com.aiiju.pojo.Goods;
import com.aiiju.pojo.GoodsType;
import com.aiiju.store.service.InventoryService;

/** 
 * @ClassName InventoryServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年6月30日 下午4:35:22
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	

	

	
	@Override
	public Result getInventoryList(String storeId) {
		
		long start = System.currentTimeMillis();
		List<GoodsType> list = this.inventoryMapper.findAllWithGoodsByStoreId(storeId);
		long end = System.currentTimeMillis();
		
		System.out.println("按类型查询有所商品，耗时："+(end-start));
		
//		List<DealDetail>  detailList =  inventoryMapper.getAllGoodsXS(storeId);
//		List<DealDetail>  yesdetailList =  inventoryMapper.getYesterDayGoodsXS( storeId);
	     
//	     for (GoodsType goodsType : list) {
////	    	 
//	    	 List<Goods> parentGoodsList = goodsType.getGoodsList();
//	    	 for (Goods goods : parentGoodsList) { 
//	    		 
//	    		 Map map = new HashMap();
//	    		 
//	    		 if(goods.getInventory()==null){
//	    			 goods.setInventory(0);
//	    		 }
//
//	    		// map.put("inventory", goods.getInventory());
//	    		 map.put("goodsId", goods.getId());
//	    		 map.put("modifyInventoryDate", goods.getModifyInventoryDate());
//	    		 String surplus =  inventoryMapper.getAllGoodsXSSurplus(map);
//	    		 if(surplus==null||"".equals(surplus)){
//	    			 surplus= "0";
//		    		}
//	    		 
//	    		 BigDecimal bd = new BigDecimal(goods.getInventory()); 
//	    		 BigDecimal bd_surplus = new BigDecimal(surplus); 
//
//    			 bd = bd.subtract(bd_surplus);
//	    		 goods.setSurplus( formatData(bd+""));
//
//	    		String yesterday =  inventoryMapper.getYesterDayGoodsXSYesterday(map);
//	    		if(yesterday==null||"".equals(yesterday)){
//	    			yesterday= "0";
//	    		}
//
//	    		goods.setYesterdayNum(formatData(yesterday));
//	    		
//	    	 }
//	    	 
//	     }  	 
	    	 

		return Result.success(list);

	}
	
	 public static boolean isContain(List<DealDetail> list,String id){
		 
		 boolean flag = false;
		 
		 for (DealDetail dealDetail : list) {
			
			 if(dealDetail.getGoodsId().equals(id)){
				 flag = true;
			 }
			 
		}
		 return flag;
	 }
	
	 
	 public static String formatData(String str){
		 
		 if(str!=null&&str.contains(".")){
			 
			 if((str.endsWith(".")||str.endsWith("0"))&&str.length()>1){
				 str=str.substring(0, str.length()-1);
				 str= formatData(str);
			 }else{
				 return str; 
			 }
		 }
		 return str;
	 }


	@Override
	public Result updateInventory(Goods goods) {
		goods.setModifyInventoryDate(new Date());
		inventoryMapper.updateInventory(goods);
		return Result.success();
	}


	@Override
	public Result getInventoryListByGoodsTypeId(Long goodsTypeId,Integer currentNum,Integer pageSize) {
		
	     List<Goods> list = null;
	        if (currentNum != null && pageSize != null) {
	            Map<String, Object> params = new HashMap<>();
	            params.put("goodsTypeId", goodsTypeId);
	            params.put("index", (currentNum - 1) * pageSize);
	            params.put("pageSize", pageSize);
	            list = this.inventoryMapper.getPageByGoodsTypeId(params);
	        } else {
	            list = this.inventoryMapper.getByGoodsTypeId(goodsTypeId);
	        }
	      

   	 for (Goods goods : list) { 
   		 
   		 Map map = new HashMap();
   		 
   		 if(goods.getInventory()==null){
   			 goods.setInventory("0");
   		 }

   		// map.put("inventory", goods.getInventory());
   		 map.put("goodsId", goods.getId());
   		 map.put("modifyInventoryDate", goods.getModifyInventoryDate());
   		 String surplus =  inventoryMapper.getAllGoodsXSSurplus(map);
   		 if(surplus==null||"".equals(surplus)){
   			 surplus= "0";
	    		}
   		 
   		 BigDecimal bd = new BigDecimal(goods.getInventory()); 
   		 BigDecimal bd_surplus = new BigDecimal(surplus); 

			 bd = bd.subtract(bd_surplus);
   		 goods.setSurplus( formatData(bd+""));

   		String yesterday =  inventoryMapper.getYesterDayGoodsXSYesterday(map);
   		if(yesterday==null||"".equals(yesterday)){
   			yesterday= "0";
   		}

   		goods.setYesterdayNum(formatData(yesterday));
   		
   	 }
		

		return Result.success(list);
	}


	@Override
	public Result selectInventoryByKeyword(String storeId, String keyword) {
		
		// 判断一下keyword是否全数字，如果包含非数字，则查询商品名称，否则查询条码和名称
		
		if(keyword==null||"".equals(keyword)){
			return Result.success();
		}else{
			
		    boolean flag=isInteger(keyword);
		    
		   
		    Map<String,String> mapKeyword = new HashMap<String,String>();
		    mapKeyword.put("storeId", storeId);
		    if(flag){
		    	mapKeyword.put("name", keyword);
		    	mapKeyword.put("code", keyword);
		    }else{
		    	mapKeyword.put("name", keyword);
		    	mapKeyword.put("code", "");
		    }
			
		    List<Goods> list = inventoryMapper.getGoodsByStoreAndKeyword(mapKeyword);
		    
		    
		    System.out.println("查询出的数据条数："+list.size());
		    
		    
		  	 for (Goods goods : list) { 
		   		 
		   		 Map map = new HashMap();
		   		 
		   		 if(goods.getInventory()==null){
		   			 goods.setInventory("0");
		   		 }

		   		// map.put("inventory", goods.getInventory());
		   		 map.put("goodsId", goods.getId());
		   		 map.put("modifyInventoryDate", goods.getModifyInventoryDate());
		   		 String surplus =  inventoryMapper.getAllGoodsXSSurplus(map);
		   		 if(surplus==null||"".equals(surplus)){
		   			 surplus= "0";
			    		}
		   		 
		   		 BigDecimal bd = new BigDecimal(goods.getInventory()); 
		   		 BigDecimal bd_surplus = new BigDecimal(surplus); 

					 bd = bd.subtract(bd_surplus);
		   		 goods.setSurplus( formatData(bd+""));

		   		String yesterday =  inventoryMapper.getYesterDayGoodsXSYesterday(map);
		   		if(yesterday==null||"".equals(yesterday)){
		   			yesterday= "0";
		   		}

		   		goods.setYesterdayNum(formatData(yesterday));
		   		
		   	 }
		 	return Result.success(list);
		}

		
	}
	
	
	 public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }

	@Override
	public Result updateInventoryErp(Map<String, Object> map) {
		String id = map.get("id").toString();
		String inventory = map.get("inventory").toString();
		
		if(id==null||inventory==null){
    		return Result.build(500, "更新库存失败，原因：参数不对");
    	}
		
		//因erp同步，单位为斤，收银数据库的单位可能不一致
    	//需要先查询，按单位换算后，在修改
    	 Goods goods = goodsMapper.getById(Long.parseLong(id));
    	if(goods!=null){
    		String unit = goods.getUnit();
    		if("g".equals(unit)||"克".equals(unit)){
    			map.put("inventory",Integer.parseInt(inventory)*500);  
      		  
	      	 }else if("两".equals(unit)){
	      		map.put("inventory",Integer.parseInt(inventory)*10);
	      		  
	      	 }else if("kg".equals(unit)||"千克".equals(unit)||"Kg".equals(unit)||"KG".equals(unit)){
	      		map.put("inventory",Integer.parseInt(inventory)*0.5);
	      		  
	      	 }else if("500g".equals(unit)||"500克".equals(unit)){
	      		map.put("inventory",Integer.parseInt(inventory)*500);
	      	 }
    		
    		
    	}
    	inventoryMapper.updateInventory(goods);
    	
		return Result.success();
	}
	
}
