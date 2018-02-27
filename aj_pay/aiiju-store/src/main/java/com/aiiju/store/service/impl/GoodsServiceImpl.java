package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.FtpUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.mapper.GoodsMapper;
import com.aiiju.mapper.GoodsTypeMapper;
import com.aiiju.mapper.ImageResourceMapper;
import com.aiiju.mapper.InventoryMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.Goods;
import com.aiiju.pojo.GoodsType;
import com.aiiju.pojo.ImageResource;
import com.aiiju.pojo.Shop;
import com.aiiju.store.service.GoodsService;
import com.aiiju.store.util.StringUtil;
import com.mchange.v2.c3p0.impl.NewProxyDatabaseMetaData;

/**
 * 
 * @ClassName: GoodsServiceImpl
 * @Description: 商品 ServiceImpl
 * @author 小飞
 * @date 2016年11月10日 下午5:31:37
 *
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Value("${BASE_URL}")
    private String IMAGE_BASE_URL;

    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;

    @Value("${FTP_IP}")
    private String FTP_SERVER_IP;

    @Value("${FTP_PORT}")
    private Integer FTP_SERVER_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_SERVER_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_SERVER_PASSWORD;

    @Autowired
    private GoodsMapper goodsMapper;
    
	@Autowired
	private ImageResourceMapper imageResourceMapper;
	
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	
	@Autowired
	private InventoryMapper inventoryMapper;

    @Override
    public Result save(Goods goods) {
//       Goods g1 = this.goodsMapper.getGoodsByName(goods);
//        if (g1 != null) {
//            return Result.build(400, "商品名称已存在");
//        }
    	 Goods g1 = null;
        if (!StringUtils.isBlank(goods.getCode())) {
        	goods.setIsDel("0");
            g1 = this.goodsMapper.getGoodsByCode(goods);
            if (g1 != null) {
                return Result.build(400, "商品条码已存在");
            }else{
            	return Result.build(400, "请输入条形码");
            }
        }
        if (StringUtils.isBlank(goods.getUnit())) {//版本替换时为了预防老版本报错添加的判断
            goods.setUnit("件");
        }
        goods.setCreateDate(new Date());
        this.goodsMapper.add(goods);
        return Result.success(goods.getId());
    }

    /**
     * 更新了逻辑
     * 如果店铺  有同步的需求，那么在更新的时候，需要进行关联更新
     */
    @Override
    public Result update(Goods goods) {
//    	Goods dbGoods = this.goodsMapper.getGoodsByName(goods);
//        if (dbGoods != null) {
//            if (!String.valueOf(dbGoods.getId()).equals(String.valueOf(goods.getId()))) {
//                return Result.build(400, "商品名称已存在");
//            }
//        }
        Goods dbGoods ;
        if (!StringUtils.isBlank(goods.getCode())) {
        	goods.setIsDel("0");
            dbGoods = this.goodsMapper.getGoodsByCode(goods);
            if (dbGoods != null) {
                if (!String.valueOf(dbGoods.getId()).equals(String.valueOf(goods.getId()))) {
                    return Result.build(400, "商品条码已存在");
                }
            }
        }else{
        	 return Result.build(400, "请输入条形码");
        }
        goods.setModifyDate(new Date());
        this.goodsMapper.update(goods);
        
                // 查询需要更新的商品的列表
        List<Goods> needSynchronizeGoodsList = this.getSynchronizeGoods(goods.getId());
        if(needSynchronizeGoodsList != null && needSynchronizeGoodsList.size() > 0){
        	for (Goods sGoods : needSynchronizeGoodsList) {
        		sGoods.setName(goods.getName());
        		sGoods.setCode(goods.getCode());
				//relativeGoods.setGoodsTypeId(goods.getGoodsTypeId());
				
        		// 因为只更新需要更改的字段。如果没有更改，是无法查到
        		if(goods.getGoodsTypeId() != null){
        			Long sonId = goodsTypeMapper.getSonGoodsTypeId(sGoods.getStoreId(),String.valueOf(goods.getGoodsTypeId()));
        			if(sonId == null){
        				// 说明没有找到有关联关系的goodsType 这样需要进行创建一个
        				GoodsType pGoodsType = goodsTypeMapper.getById(goods.getGoodsTypeId());
        				// 根据父类的goodstype 创建一个分店的 goodstype
        				sonId = this.createRelateGoodsType(pGoodsType,sGoods.getStoreId());
        			}
        			sGoods.setGoodsTypeId(sonId);
        		}
				
				sGoods.setPrice(goods.getPrice());
				sGoods.setUnit(goods.getUnit());
				sGoods.setImageUrl(goods.getImageUrl());
				sGoods.setModifyDate(new Date());
				
				
				
				// 更新关联的商品
				goodsMapper.update(sGoods);
			}
        }

        
        return Result.success(true);
    }


	/**
	 * 为分店创建一个分类
	 * @param pGoodsType 总店的 GoodsType
	 * @param storeId 分店的店铺的id
	 * @return
	 */
	private Long createRelateGoodsType(GoodsType pGoodsType, String storeId) {
		GoodsType newGoodsType = new GoodsType();
		
		// 进行赋 初始值
		newGoodsType.setName(pGoodsType.getName());
		newGoodsType.setStoreId(storeId);
		newGoodsType.setCreateDate(new Date());
		newGoodsType.setModifyDate(new Date());
		newGoodsType.setRelationGoodsTypeId(String.valueOf(pGoodsType.getId()));
		
		// 进行更新
		goodsTypeMapper.add(newGoodsType);
		
		// 查询更新的id
		Long sonId = goodsTypeMapper.getSonGoodsTypeId(storeId,String.valueOf(pGoodsType.getId()));
		
		return sonId;
	}

	/**
     * 输入一个商品的ID
     * 需要进行更新的商品的Goods 集合
     * 判断条件：1，商品所在的店铺是主店的商品 ；2，查询所有有关联关系的商品3，主店开启了同步功能 ,同步所有的商品；主店没有开启，分店开启，只同步分店的；
	 * @param id 商品的Id
	 * @return
	 */
	private List<Goods> getSynchronizeGoods(Long id) {
		
		Goods pGoods = goodsMapper.getById(id);
		Shop pStore = shopMapper.getByStoreId(pGoods.getStoreId());
		
		// 判断是不是总店的商品  0 表示总店
		if(!"0".equals(pStore.getShopType())){
			// 不是总店商品，是不需要进行同步的
			return null;
		}
		
		// 查询所有有关联关系的商品
		List<Goods> relativeGoods = goodsMapper.getGoodsByRelativeGoodId(String.valueOf(id));
		
		// 判断总店是否开启了同步，总店开启了同步；全部需要进行同步，总店没有进行同步。然后看分店
		String isOpenSynchronize = pStore.getIsOpenSynchronize();
		// 总店进行了同步 1 表示同步 0 不同步
		if("1".equals(isOpenSynchronize)){
			return relativeGoods;
		}else{
			/*for (Goods goods : relativeGoods) {
				Shop sShop = shopMapper.getByStoreId(goods.getStoreId());
				String sIsOpen = sShop.getIsOpenSynchronize();
				// 分店没有开启同步，将不进行同步
				if(!"1".equals(sIsOpen)){
					relativeGoods.remove(goods);
				}
			}*/
			
			/* 阿里技术手册中：不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator
			 方式，如果并发操作，需要对 Iterator 对象加锁*/
			// 所有修改成迭代器的方式 
			Iterator<Goods> it = relativeGoods.iterator();
			while (it.hasNext()) {
				Goods goods = it.next();
				Shop sShop = shopMapper.getByStoreId(goods.getStoreId());
				String sIsOpen = sShop.getIsOpenSynchronize();
				// 分店没有开启同步，将不进行同步
				if(!"1".equals(sIsOpen)){
					it.remove();
				}
			}
			
			return relativeGoods;
		}
		
	}

	/**
     * 改变业务逻辑
     * 如果同步关系，先删除同步到商品再进行删除；
     */
    @Override
    public Result deleteById(Long id) {
        
    	// 查询需要删除的商品的列表
    	Goods goods = goodsMapper.getById(id);
    	if(goods == null){
    		return Result.success(true);
    	}
    	
        List<Goods> needSynchronizeGoodsList = this.getSynchronizeGoods(goods.getId());
        if(needSynchronizeGoodsList != null && needSynchronizeGoodsList.size() > 0){
        	for (Goods sGoods : needSynchronizeGoodsList) {
				// 更新关联的商品
        		sGoods.setIsDel("1");
				this.goodsMapper.update(sGoods);
			}
        }
    	
        // 删除父店铺的商品
        Goods good = new Goods();
        good.setId(id);
        good.setIsDel("1");
        this.goodsMapper.update(good);
        
        return Result.success(true);
    }

    @Override
    public Result getById(Long id) {
        Goods goods = this.goodsMapper.getById(id);
        return Result.success(goods);
    }

    @Override
    public Result getAllByGoodsTypeId(Long goodsTypeId, Integer currentNum, Integer pageSize) {
        List<Goods> list = null;
        if (currentNum != null && pageSize != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("goodsTypeId", goodsTypeId);
            params.put("index", (currentNum - 1) * pageSize);
            params.put("pageSize", pageSize);
            list = this.goodsMapper.getPageByGoodsTypeId(params);
        } else {
            list = this.goodsMapper.getByGoodsTypeId(goodsTypeId);
        }
        return Result.success(list);
    }

    @Override
    public Result deleteByIds(String[] ids, String storeId) {
    	
    	// 每次删除一个
    	for (String id : ids) {
			this.deleteById(Long.parseLong(id));
		}
//    	// 先删除相关的商品，再删除 需要删除的商品
//        this.goodsMapper.deleteByIds(ids);
        
        return Result.success(true);
    }

    @Override
    public Result upload(MultipartFile uploadFile,String storeId) {
    	
        String path = this.savePicture(uploadFile, storeId);
        if (path != null) {
            return Result.success(IMAGE_BASE_URL + path);
        } else {
            return Result.build(401, "文件为空或文件保存失败,请重试");
        }
    }

    private String savePicture(MultipartFile uploadFile,String storeId) {
        String result = null;
        try {
            // 判断文件是否为空
            if (uploadFile == null || uploadFile.isEmpty()) {
            	System.out.println("----------------------1111--------------------------");
                return result;
            }
            Date now = new Date();
            // 上传文件以日期为单位分开存放，可以提高图片的查询速度
            String filePath = "/" + DateUtil.formatDate(now, "yyyy") + "/" + DateUtil.formatDate(now, "MM") + "/" + DateUtil.formatDate(now, "dd");
            // 取原始文件名
            String originalFilename = uploadFile.getOriginalFilename();
            // 新文件名
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 转存文件，上传到ftp服务器
            boolean flag = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD, FILI_UPLOAD_PATH, filePath,
                    newFileName, uploadFile.getInputStream());
            if (!flag) {
            	System.out.println("----------------------222--------------------------");
                return result;
            }
            result = filePath + "/" + newFileName;
            
	       String image_type  = originalFilename.substring(originalFilename.lastIndexOf(".")).replaceAll("\\.", "");
			
		    ImageResource imageResource = new ImageResource("商品图片",originalFilename, image_type, IMAGE_BASE_URL+result, storeId, "1", "商品图片设置相关", new Date());
		    imageResourceMapper.add(imageResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	/**
	 * 分成两种情况：总店和分店
	 * 主店：更新主店下所有分店 :商品的类型和商品
	 * 分店：只是更分店的  :商品的类型和商品
	 */
	@Override
	public Result updateSynchronize(String storeId) {

		// 查询所有的分店 
		Shop getshop = shopMapper.getByStoreId(storeId);
		// 如果是主店   0表示是主店
		if("0".equals(getshop.getShopType())){ 
			List<Shop> sonShopList = shopMapper.getShopListByParentStoreId(storeId);
			for (Shop shop : sonShopList) {
				// 遍历所有的分店进行更新
				if(!"0".equals(shop.getShopType())){
					synchronizeOneShop(shop.getStoreId());
				}
			}
		}else{ 
			// 表明是一个分店的id
			synchronizeOneShop(storeId);
		}
		
		// 同步完成返回成功
		return Result.success();
	}

	/**
	 * 同步一个分店的所有有关联关系的 商品和商品类型
	 * @param storeId 必须是分店的storeId
	 */
	private void synchronizeOneShop(String storeId) {
		/**
		 * 商品类型的同步 update
		 */
		// 获取需要更新的商品的id
		List<GoodsType> goodtypeList = goodsTypeMapper.getModifyGoodTypes(storeId);
		if(goodtypeList != null && goodtypeList.size() > 0){
			for (GoodsType sGoodsType : goodtypeList) {
				 // 找到 父节点
				 GoodsType pGoodsType = goodsTypeMapper.getById(Long.parseLong(sGoodsType.getRelationGoodsTypeId()));
				 // 进行赋值
				 sGoodsType.setName(pGoodsType.getName());
				// 更新数据库
				 goodsTypeMapper.update(sGoodsType);
			}
		}
		/**
		 * 商品的同步  update
		 */
		// 查询需要进行店铺的所有的关联商品
		List<Goods> goodsList = goodsMapper.getModifyGoods(storeId);
		if(goodsList != null && goodsList.size() > 0){
			for (Goods sgood : goodsList) {
				 // 找到 父节点
				Goods pGoods = goodsMapper.getById(Long.parseLong(sgood.getRelationGoodsId()));
				 // 进行赋值
				sgood.setName(pGoods.getName());
				sgood.setCode(pGoods.getCode());
				
				// 更新商品的类型 ，是更新 相关联的 子类
				Long sonId = goodsTypeMapper.getSonGoodsTypeId(storeId,String.valueOf(pGoods.getGoodsTypeId()));
				sgood.setGoodsTypeId(sonId);
				
				sgood.setPrice(pGoods.getPrice());
				sgood.setUnit(pGoods.getUnit());
				sgood.setImageUrl(pGoods.getImageUrl());
				sgood.setIsDel(pGoods.getIsDel());
				// 更新数据库
				goodsMapper.update(sgood);
				//goodsMapper.updateGood(sgood);
			}
		}
		
		/**
		 * 总店没有 而分店有的商品，应该删除
		 */
		List<Long> goodsIdList = goodsMapper.getNeedDeleteGoods(storeId);
		if(goodsIdList != null && goodsIdList.size() > 0){
			for (Long id : goodsIdList) {
				Goods delGoods = new  Goods();
				delGoods.setId(id);
				delGoods.setIsDel("1");
				goodsMapper.update(delGoods);
			}
		}
		
		/**
		 * 总店没有 而分店有的商品类型，应该删除
		 */
		List<Long> goodstypeIdList = goodsTypeMapper.getNeedDeleteGoodsType(storeId);
		if(goodstypeIdList != null && goodstypeIdList.size() > 0){
			for (Long id : goodsIdList) {
				goodsTypeMapper.deleteById(id);
			}
		}
		
		/**
		 * 总店有 而分店没有的商品类型，应该添加
		 */
		//Shop byId = shopMapper.getById(Integer.parseInt(storeId));
		Shop byId = shopMapper.getshopByStoreId(storeId);
		if(byId != null){
			List<Long> goodstypeAdIds = goodsTypeMapper.getNeedAddGoodsType(byId.getParentStoreId(),storeId);
			if(goodstypeAdIds != null && goodstypeAdIds.size() > 0){
				for (Long id : goodstypeAdIds) {
					GoodsType pGoodsType = goodsTypeMapper.getById(id);
					
					GoodsType sGoodsType = new GoodsType();
					sGoodsType.setName(pGoodsType.getName());
					sGoodsType.setRelationGoodsTypeId(String.valueOf(id));
					sGoodsType.setCreateDate(new Date());
					sGoodsType.setCreateDate(new Date());
					sGoodsType.setStoreId(storeId);
					
					goodsTypeMapper.add(sGoodsType);
				}
			}
		}
		
	}

	
	@Override
	public Result getGoodsByGiveDateAndStoreID(String storeId, String startDate, String endDate,Integer page ,Integer pageSize) {
		// 判断日期的输入
		if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
			return Result.build(401, "请输入日期");
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean matches = pattern.matcher(startDate).matches() && pattern.matcher(endDate).matches();
		if(!matches){
			return Result.build(401, "输入的日期格式有误");
		}
		
		// 如果没有给数值；默认是 1000条数据
		if (pageSize == null) {
			pageSize = 1000;
		}
		if (page == null) {
			page = 0;
		}
		
		Date start = new Date(Long.valueOf(startDate));
		Date end = new Date(Long.valueOf(endDate));
		
		List<Goods> source = null;
        Map<String, Object> params = new HashMap<>();
		params.put("storeId", storeId);
        params.put("start", start);
        params.put("end", end);
        params.put("page", page);
        params.put("pageSize", pageSize);

        source =  this.goodsMapper.getGoodsByGiveDateAndStoreID(params);
       
         // 增加商品的类型的名称
         List<Goods> result = new ArrayList<Goods>();
         if(source != null || source.size() > 0){
        	 for (Goods goods : source) {
        		 GoodsType goodsType = this.goodsTypeMapper.getById(goods.getGoodsTypeId());
        		 if(goodsType!= null){
        			 goods.setGoodsTypeName(goodsType.getName());
        		 }
        		 result.add(goods);
        	 }
         }
         String json = JsonUtils.objectToJsonNONull(result);
         return Result.success(json);
	}
	
	

	
  public static void main(String[] args) {
	BigDecimal b = new BigDecimal("0.0");
}

}
