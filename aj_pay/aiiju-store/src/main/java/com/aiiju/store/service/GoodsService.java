package com.aiiju.store.service;

import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Goods;

/**
 * 
 * @ClassName: GoodsService 
 * @Description: 商品 Service
 * @author 小飞 
 * @date 2016年11月10日 下午5:27:51 
 *
 */
public interface GoodsService {

	public Result deleteById(Long id);
	
	public Result getById(Long id);
	/**
	 * 通过goodsTypeID获取商品分页列表
	 * @param goodsTypeId
	 * @param currentNum
	 * @return
	 */
	public Result getAllByGoodsTypeId(Long goodsTypeId, Integer currentNum,Integer pageSize);

	public Result deleteByIds(String[] ids,String storeId);

	public Result save(Goods goods);
	
	public Result update(Goods goods);

	public Result upload(MultipartFile uploadFile,String storeId);

	/**
	 * 同步总店下所有分店的  
	 * 有关联关系的 商品类型和 商品
	 * @param storeId
	 * @return
	 */
	public Result updateSynchronize(String storeId);

	/**
	 * 查询更新的商品的信息 ； (条件是给定的时间以后)
	 * @param storeId 店铺的id
	 * @param date 给定的时间
	 * @return
	 */
	public Result getGoodsByGiveDateAndStoreID(String storeId, String startDate, String endDate,Integer page ,Integer pageSize);
	
}
