package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.GoodsType;

/**
 * 
 * @ClassName: GoodTypeMapper 
 * @Description: 商品类型 Mapper
 * @author 小飞 
 * @date 2016年11月10日 上午9:08:48 
 *
 */
public interface GoodsTypeMapper {
	/**
	 * 添加
	 * @param goodsType
	 */
	public void add(GoodsType goodsType);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id);
	/**
	 * 修改
	 * @param goodsType
	 */
	public void update(GoodsType goodsType);
	
	
	/**
	 * 默认分类关联关系
	 * @param goodsType
	 */
	public void updateDefaultGoodsTypeRelation(Map map);
	
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
	public GoodsType getById(Long id);
	/**
	 * 通过storeId获取所有对象
	 * @param storeId
	 * @return
	 */
	public List<GoodsType> getAllByStoreId(String storeId);
	
	public List<GoodsType> getAllWithGoodsByStoreId(String storeId);
	
	public List<GoodsType> getParentGoodsList(String parentStoreId);
	
	
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
	public GoodsType getByRelationGoodsTypeId(String goodsType,String storeId);
	
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
	public GoodsType getDefaultGoodsType(String storeId,String defalutName);
	
	/**
	 * 获取修改的商品类型的id 的集合
	 * 三个条件，指定店铺的id的goods_type， 有关联关系，名字和关联的goods 不同，
	 * @param storeId
	 * @return 
	 */
	public List<GoodsType> getModifyGoodTypes(String storeId);
	/**
	 * 查询所有关联商品
	 * 通过关联商品的 id 进行查询
	 * @param relationGoodsTypeId  关联商品的id 
	 * @return 关联商品的列表
	 */
	public List<GoodsType> getRelationGoods(String relationGoodsTypeId);
	/**
	 * 总店没有的商品类型 而分店没有
	 * @param storeId
	 * @return
	 */
	public List<Long> getNeedDeleteGoodsType(String storeId);
	/**
	 * 通过总店铺 GoodsTypeID 获取相关联的 id
	 * @param storeId   分店铺的storeID
	 * @param goodsTypeId  总店铺的 goodsTypeId
	 * @return
	 */
	public Long getSonGoodsTypeId(String storeId, String goodsTypeId);
	/**
	 * 总店有的类型，分店没有需要新增
	 * @param pstoreId 总店铺的id
	 * @param sstoreId 分店铺的id
	 * @return
	 */
	public List<Long> getNeedAddGoodsType(String pstoreId,String sstoreId);
	/**
	 * 通过 relativeId 获取所有相关关联的商品
	 * @param id 关联商品类型的id
	 * @return 关联商品类型的 集合
	 */
	public List<GoodsType> getGoodsByRelativeGoodId(String id);
	
	
	
	
}
