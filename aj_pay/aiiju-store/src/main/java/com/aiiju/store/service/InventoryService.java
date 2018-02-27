package com.aiiju.store.service;

import java.util.Map;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Goods;

/** 
 * @ClassName InventoryService
 * @Description
 * @author zong
 * @CreateDate 2017年6月30日 下午4:33:34
 */
public interface InventoryService {

	
	public Result getInventoryList(String storeId);
	
	public Result getInventoryListByGoodsTypeId(Long goodsTypeId,Integer currentNum,Integer pageSize);
	
    public Result updateInventory(Goods goods) ;
	 
    public Result selectInventoryByKeyword(String storeId,String keyword  ) ;
    
    
    
    /**
     * 修改库存（erp同步）;
     *id:商品id,
     *inventory:同步数量,
     * @return
     */
	public Result updateInventoryErp(Map<String,Object> map);
	  
}
