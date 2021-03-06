package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.GoodsType;

/**
 * 
 * @ClassName: GoodsTypeService 
 * @Description: 商品类型 Service
 * @author 小飞 
 * @date 2016年11月10日 下午4:07:05 
 *
 */
public interface GoodsTypeService {
	
public Result getById(Long id);
	
	public Result getAllByStoreId(String storeId);
	
	public Result save(GoodsType goodsType);
	
	public Result update(GoodsType goodsType);
	
	public Result deleteById(Long id);

	public Result getAllWithGoodsByStoreId(String storeId);
	
	public Result getRelationParentGoodsList(String parentStoreId,String storeId,String keyWord,String goodsTypeId,String pageNum,String pageSize);

	public Result saveOrDeleteRelationGoods(String storeId,String savaGoodsIds,String deleteGoodsIds);
	
	

}
