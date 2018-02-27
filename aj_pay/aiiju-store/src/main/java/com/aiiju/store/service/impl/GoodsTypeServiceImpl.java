package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.GoodsMapper;
import com.aiiju.mapper.GoodsTypeMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.Goods;
import com.aiiju.pojo.GoodsType;
import com.aiiju.pojo.Shop;
import com.aiiju.store.service.GoodsTypeService;
import com.mysql.fabric.xmlrpc.base.Data;
/**
 * 
 * @ClassName: GoodsTypeServiceImpl 
 * @Description: 商品类型 ServiceImpl
 * @author 小飞 
 * @date 2016年11月10日 下午4:15:18 
 *
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
	
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Override
	public Result getById(Long id) {
		GoodsType goodsType = this.goodsTypeMapper.getById(id);
		return Result.success(goodsType);
	}

	@Override
	public Result getAllByStoreId(String storeId) {
		List<GoodsType> list = this.goodsTypeMapper.getAllByStoreId(storeId);
		return Result.success(list);
	}

	@Override
	public Result save(GoodsType goodsType) {
		goodsType.setCreateDate(new Date());
		this.goodsTypeMapper.add(goodsType);
		return Result.success(goodsType.getId());
	}

	@Override
	public Result update(GoodsType goodsType) {
		goodsType.setModifyDate(new Date());
		this.goodsTypeMapper.update(goodsType);
		
		/**
		 * 更新关联的goodsType
		 */
		// 获取需要进行更新集合
		List<GoodsType> needSynchronizeGoodsTypeList = this.getSynchronizeGoodsType(goodsType.getId());
		if(needSynchronizeGoodsTypeList != null && needSynchronizeGoodsTypeList.size() > 0){
			for (GoodsType sGoodsType : needSynchronizeGoodsTypeList) {
				sGoodsType.setName(goodsType.getName());
				sGoodsType.setModifyDate(new Date());
				
				// 更新字段
        		this.goodsTypeMapper.update(sGoodsType);
			}
		}
        
		return Result.success(true);
	}

	/**
     * 输入一个商品类型的ID
     * 需要进行更新的商品的GoodsType 集合
     * 判断条件：1，商品类型所在的店铺是主店的商品 ；2，查询所有有关联关系的商品3，主店开启了同步功能 ,同步所有的商品；主店没有开启，分店开启，只同步分店的；
	 * @param id 商品的Id
	 * @return
	 */
	private List<GoodsType> getSynchronizeGoodsType(Long id) {
		
		GoodsType pgoodsType = goodsTypeMapper.getById(id);
		Shop pStore = shopMapper.getByStoreId(pgoodsType.getStoreId());
		
		// 判断是不是总店的商品  0 表示总店
		if(!"0".equals(pStore.getShopType())){
			// 不是总店商品，是不需要进行同步的
			return null;
		}
		
		// 查询所有有关联关系的商品 类型
		List<GoodsType> relativeGoodsType = goodsTypeMapper.getGoodsByRelativeGoodId(String.valueOf(id));
		
		// 判断总店是否开启了同步，总店开启了同步；全部需要进行同步，总店没有进行同步。然后看分店
		String isOpenSynchronize = pStore.getIsOpenSynchronize();
		// 总店进行了同步 1 表示同步 0 不同步
		if("1".equals(isOpenSynchronize)){
			return relativeGoodsType;
		}else{
			for (GoodsType goodsType : relativeGoodsType) {
				Shop sShop = shopMapper.getByStoreId(goodsType.getStoreId());
				String sIsOpen = sShop.getIsOpenSynchronize();
				// 分店没有开启同步，将不进行同步
				if(!"1".equals(sIsOpen)){
					relativeGoodsType.remove(goodsType);
				}
			}
			return relativeGoodsType;
		}
	}

	/**
	 * 添加 级联删除的功能
	 * 如果店铺主店开启了同步的功能，将会级联删除分店的功能
	 */
	@Override
	public Result deleteById(Long id) {
		int count = this.goodsMapper.getGoodsCount(id);
		if (count == 0) {
			
			/**
			 * 删除关联的goodsType
			 */
			int countGoodsType = 0;
			List<GoodsType> relationGoodsType = this.goodsTypeMapper.getRelationGoods(String.valueOf(id));
			if(relationGoodsType != null && relationGoodsType.size() > 0){
				for (GoodsType goodsType : relationGoodsType) {
					// 判断goodsType 中 是否有商品 （分店中没有商品才能删除）
					int scount = goodsMapper.getGoodsCount(goodsType.getId());
					countGoodsType += scount;
				}
			}
			// 说明没有任何 有商品 可以放心删除
			if(countGoodsType == 0){
				if(relationGoodsType != null && relationGoodsType.size() > 0){
					for (GoodsType sGoodsType : relationGoodsType) {
						this.goodsTypeMapper.deleteById(sGoodsType.getId());
					}
				}
				// 删除删除指定 的 goodstype 如果有关联 将无法删除
				this.goodsTypeMapper.deleteById(id);
				
				return Result.success(true);
			}else{
				return Result.build(401, "选中的类目下存在关联信息,无法删除");
			}
			
		} else {
			return Result.build(410, "选中的类目下存在商品信息,无法删除");
		}
	}



	@Override
	public Result getAllWithGoodsByStoreId(String storeId) {
		List<GoodsType> list = this.goodsTypeMapper.getAllWithGoodsByStoreId(storeId);
		return Result.success(list);
	}


	@Override
	public Result getRelationParentGoodsList(String parentStoreId,String storeId,String keyWord,String goodsTypeId,String pageNum,String pageSize) {
		
		// 获取本商店所有关联（主营店）的商品
	     List<String> listGoods = this.goodsMapper.getRelationGoodsId(storeId);
	     List<GoodsType> list = this.goodsTypeMapper.getAllWithGoodsByStoreId(parentStoreId);
	     
	     List<GoodsType> returnGoodsTypeList = new ArrayList<GoodsType>();
	     
	     for (GoodsType goodsType : list) {
 
	    	 List<Goods> parentGoodsList = goodsType.getGoodsList();

	    	 List<Goods> returnGoodsList = new ArrayList<Goods>();
	    	 
		      for (Goods goods : parentGoodsList) {
					
			    	 if(listGoods.contains(goods.getId()+"")){
			    		 goods.setIsRelation("1"); 
			    	 }else{
			    		 goods.setIsRelation("0");
			    	 }
			    	 returnGoodsList.add(goods);
				 }
		      goodsType.setGoodsList(returnGoodsList);
		      
		      returnGoodsTypeList.add(goodsType);
		      
		}
	
		return Result.success(returnGoodsTypeList);

	}


	@Override
	public Result saveOrDeleteRelationGoods(String storeId, String saveGoodsIds, String deleteGoodsIds) {
		
		// 新增关联 商品；
		if((saveGoodsIds==null||"".equals(saveGoodsIds))&&(deleteGoodsIds==null||"".equals(deleteGoodsIds))){
			System.out.println("未关联或删除任何商品，直接返回");
			return Result.success();
		}
		//334:1215,334:1222,334:1224
//		Map<String,String> savaGoodsType = new HashMap<String,String>();
//		Map<String,String> savaGoodsId   = new HashMap<String,String>();
//		
//		Map<String,String> deleteGoodsType = new HashMap<String,String>();
//		Map<String,String> deleteGoodsId   = new HashMap<String,String>();
		
		
            if(saveGoodsIds!=null&&saveGoodsIds.contains(":")){
            	if(saveGoodsIds.contains(",")){
            		String[] savatypeids = saveGoodsIds.split(",");
            		for (String typeids : savatypeids) {
            			 String goodsType =  typeids.split(":")[0];
            			 String goodsid =  typeids.split(":")[1];
            			 // 查询该商品种类是否已添加
            		
            			 save(goodsType, goodsid, storeId);
            	
            			// savaGoodsId.put(goodsId, goodsId); 
					}
            	}else{
            		 String goodsType =  saveGoodsIds.split(":")[0];
        			// savaGoodsType.put(goodsType, goodsType);
        			 String goodsId =  saveGoodsIds.split(":")[1];
        			// savaGoodsId.put(goodsId, goodsId);
        			 save(goodsType, goodsId, storeId);
            	}	
            }
            
            
            
            /**
             * 在goods表中删除 relation_goods_id 关联数据
             */
            
            
            
            
       //     goodsMapper.deleteByRelationGoodsId(id);
        
            
            if(deleteGoodsIds!=null&&!"".equals(deleteGoodsIds)){
                StringBuffer sb = new StringBuffer();
                
                
                if(deleteGoodsIds!=null&&deleteGoodsIds.contains(":")){
                	if(deleteGoodsIds.contains(",")){
                		String[] deletetypeids = deleteGoodsIds.split(",");
                		for (String typeids : deletetypeids) {
                			 String goodsType =  typeids.split(":")[0];
                			// deleteGoodsType.put(goodsType, goodsType);
                			 String goodsId =  typeids.split(":")[1];
                			 
                			 sb.append(goodsId+",");
                			//  goodsMapper.deleteByRelationGoodsId(goodsId);
                			 //deleteGoodsId.put(goodsId, goodsId); 
    					}
                	}else{
                		 String goodsType =  deleteGoodsIds.split(":")[0];
                	//	 deleteGoodsType.put(goodsType, goodsType);
            			 String goodsId =  deleteGoodsIds.split(":")[1];
            			 sb.append(goodsId+",");
            			//  goodsMapper.deleteByRelationGoodsId(goodsId);
            			// deleteGoodsId.put(goodsId, goodsId);
                	}
                }
                
                
                
               String[] ids =  sb.toString().split(",");
               
               goodsMapper.deleteByRelationGoodsId(ids);
            }
            
            return Result.success();
	}
	
	public void save(String goodsType,String goodsId,String storeId){
		 /**
		  * select * from goods_type where relation_goods_type_id = #{goodsType}  返回  商品类别对象
		  * 
		  * 如果为空， 则新增 ，返回新增对象   ， 获取其id
		  *  savaGoodsType.put(goodsType, goodsType/id);
		  * 
		  * 查询 该商品信息 返回对象， 插入新对象， 更新其storeid 且  relation_goods_id 赋值
		  * 
		  */
		
		
		 GoodsType   gt2 =  goodsTypeMapper.getByRelationGoodsTypeId(goodsType,storeId);
		 String goodTypeid = "";
		 if(gt2==null){
			 // 没有添加过, 新增 此 GoodsType
			  GoodsType  gt =  goodsTypeMapper.getById(Long.parseLong(goodsType));
			  
			  if(!gt.getName().equals("默认分类")){
				  
				  GoodsType gtnew = new GoodsType();
				  gtnew.setCreateDate(new Date());
				  gtnew.setName(gt.getName());
				  gtnew.setStoreId(storeId);
				  gtnew.setRelationGoodsTypeId(goodsType);
				  goodsTypeMapper.add(gtnew);
				  goodTypeid = gtnew.getId()+"";
			  }else{
				  
				  GoodsType   gt3 =  goodsTypeMapper.getDefaultGoodsType(storeId,"默认分类");
				  goodTypeid = gt3.getId()+"";
				  
				  Map map = new HashMap();
				  map.put("goodsType", goodsType);
				  map.put("storeId", storeId);
				  
				  goodsTypeMapper.updateDefaultGoodsTypeRelation(map);
	
			  }
		//	  savaGoodsType.put(goodsType, gtnew.getId()+"");
			
		 }else{
		//	 savaGoodsType.put(goodsType, gt2.getId()+"");
			  goodTypeid =gt2.getId()+"";
		 }
		 
		//  String goodsId =  typeids.split(":")[1];
		  Goods goodsDB = 	 goodsMapper.getById(Long.parseLong(goodsId));
		  goodsDB.setGoodsTypeId(Long.parseLong(goodTypeid));
		  goodsDB.setRelationGoodsId(goodsId);
		  goodsDB.setCreateDate(new Date());
		  goodsDB.setStoreId(storeId);
		  goodsMapper.add(goodsDB);
		
	}
	
	
	
}
