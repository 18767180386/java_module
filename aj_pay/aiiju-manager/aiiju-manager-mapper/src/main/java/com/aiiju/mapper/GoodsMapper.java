package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Goods;
/**
 * 
 * @ClassName: GoodsMapper 
 * @Description: 商品 Mapper
 * @author 小飞 
 * @date 2016年11月10日 上午9:22:03 
 *
 */
public interface GoodsMapper {

	/**
	 * 添加
	 * @param goods
	 */
	public void add(Goods goods);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id);
	/**
	 * 修改
	 * @param goods
	 */
	public void update(Goods goods);
	
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Goods getById(Long id);
	
	public Goods getByIdWithoutGoodsType(Long id);
	/**
	 * 通过goodsTypeId获取
	 * @param id
	 * @return
	 */
	public List<Goods> getByGoodsTypeId(Long goodsTypeId);
	
	public List<Goods> getRelationGoods(String storeId);
	public List<Goods> getParentGoods(String storeId);
	public List getRelationGoodsId(String storeId);
	public List getParentGoodsId(String storeId);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteByIds(String[] ids);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteByRelationGoodsId(String[] ids);
	
	
	/**
	 * 分页获取列表
	 * @param params  goodsTypeId 、 index、pageSize
	 * @return
	 */
	public List<Goods> getPageByGoodsTypeId(Map<String,Object> params);
	/**
	 * 获取该商品类型下商品个数
	 * @param id
	 * @return
	 */
	public int getGoodsCount(Long id);
	
	public Goods getGoodsByName(Goods goods);
	
	public Goods getGoodsByCode(Goods goods);
	/**
	 * 获取修改的商品的id 的集合
	 * 两个个条件， 有关联关系，制定店铺的商品
	 * @param storeId
	 * @return
	 */
	public List<Goods> getModifyGoods(String storeId);

	/**
	 * 查询 分店有而总店没有 的商品的id
	 * @param storeId
	 * @return
	 */
	public List<Long> getNeedDeleteGoods(String storeId);


	public String getAllGoodsXSSurplus(Map map);
	
	public String getYesterDayGoodsXSYesterday(Map map);
	/**
	 * 通过 relativeId 获取所有相关关联的商品
	 * @param id 关联商品的id
	 * @return 关联商品的 集合
	 */
	public List<Goods> getGoodsByRelativeGoodId(String id);
	/**
	 * 查询店铺的商品的信息。（）指定的时间以后的
	 * @param params
	 * @return
	 */
	public List<Goods> getGoodsByGiveDateAndStoreID(Map<String, Object> params);
	    
	
	public List<Goods> getAllGoodsByStoreId(String storeId);
	
}
