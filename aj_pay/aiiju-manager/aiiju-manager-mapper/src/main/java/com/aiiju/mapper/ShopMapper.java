package com.aiiju.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aiiju.pojo.Shop;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName: ShopMapper 
 * @Description: 店铺 Mapper
 * @author 小飞 
 * @date 2016年11月8日 下午8:18:21 
 *
 */
public interface ShopMapper {

	public void add(Shop shop);
	
	public void update(Shop shop);
	public void updatePayAuth(Shop shop);
	
	public void updateByStoreId(Shop shop);
	public void updateShopGoodsAuth(Shop shop);
	
	
	
	public List getShopByParentStoreIdAndName(Map params);
	
	public List<Shop> isExist(Shop shop);
	
	
	public Shop getById(Integer id);
	
	public Shop getByStoreId(String storeId);
	
	public void deleteById(Integer id);

	public String getShopNameById(Integer id);

	public String getShopNameByStoreId(String storeId);
	
	public Shop getShopListByStoreId(String storeId);
	public List<Shop> getShopListByParentStoreId(String storeId);
	public List<Shop> getSwitchListByParentStoreId(String storeId);
	
	
	public JSONObject getDiscountStatusByStoreId(String storeId);
	
	public void updateByStoreIdNew(Shop shop);

	/**
	 * 修改同步状态
	 * @param shop
	 */
	public void updateSynchronize(Shop shop);
	/**
	 * 判读是否需要进行同步
	 * 判断的标准是 1，shop的isOpenSynchronize 为1， 
	 * 
	 * @param storeId  店铺的id
	 * @return  String null表示 不需要同步， 有值（storeId）表示需要进行同步
	 */
	public String getSynchronizeStatue(String storeId);

	/**
	 * 通过店铺的id 获取 shop
	 * @param storeId 店铺的id
	 * @return
	 */
	public Shop getshopByStoreId(String storeId);
	
	/**
	 *  查询店铺的父店铺的集合
	 * @param params map集合   （使用举例）
	 * Map<String, Object> params = new HashMap<String, Object>(2); 
	 * params.put("ids", list);
	 * 必须是key 时ids 值为 list set 都可以
	 * @return
	 */
	public Set<String> getParentStoreIdSet(Map<String, Object> params);

	/**
	 * 保存授权给ERP店铺的
	 * @param params 授权的店铺 ，参数同上
	 * @return  
	 */
	public void savaGrantStatus(Map<String, Object> params);
	
	/**
	 * 取消总店下所有店铺的 ERP授权
	 * @param pstoreId
	 */
	public void cancelErpGrant(String pstoreId);

	/**
	 * 查询制定的店铺是否关联到ERP系统
	 * @param storeId 店铺的id
	 * @return 如果得到的值是1 说明是已经关联的，如果是 (不是‘1’)0 或者null 说明 没有关联到ERP系统
	 */
	public String getShopRelativeErpStatus(String storeId);

	/**
	 * 店铺是否关联scrm公司
	 * @param storeId 店铺的id
	 * @return 
	 */
	public String getRelativeScrm(Map<String, Object> map);
	
	
	/**
	 * 解除scrm绑定
	 * @param  storeIds   店铺的id,多个逗号隔开
	 * @param map 
	 * @return 
	 */
	public int updateShopListSCRM(Map<String, Object> map);

	public List<Map<String,Object>> getRelativeScrmShopList(Map<String, Object> map);

	public List<Map<String,Object>> getScrmList(Map<String, Object> map);
	
	/**
	 * 获取多余的scrm绑定店铺storeId
	 * @param  storeIds   店铺的id,多个逗号隔开
	 * @param map 
	 * @return 
	 */
	public String getUnnecessaryScrm(Map<String, Object> map);
	
	
	

	
}
