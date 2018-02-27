package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.DealDetail;
import com.aiiju.pojo.Goods;
import com.aiiju.pojo.GoodsType;

/** 
 * @ClassName InventoryMapper
 * @Description
 * @author zong
 * @CreateDate 2017年6月30日 下午5:48:06
 */
public interface InventoryMapper {

	public List<GoodsType> getAllWithGoodsByStoreId(String storeId);
	
	public GoodsType getAllWithGoodsByGoodsTypeId(String storeId);
	
	
	public List<GoodsType> findAllWithGoodsByStoreId(String storeId);
	
	public List<Goods> getByIdWithoutGoodsTypeId(String goodsTypeId);
	

	
	public List<DealDetail> getAllGoodsXS(String storeId);
	
	public List<DealDetail> getYesterDayGoodsXS(String storeId);
	
	public void updateInventory(Goods goods);
	
	public String getAllGoodsXSSurplus(Map map);
	
	public String getYesterDayGoodsXSYesterday(Map map);
	    
	public List<Goods> getByGoodsTypeId(Long goodsTypeId);
	public List<Goods> getPageByGoodsTypeId(Map<String,Object> params);
	
	
	
	public List<Goods> getGoodsByStoreAndKeyword(Map<String,String> map);
	
	
}
