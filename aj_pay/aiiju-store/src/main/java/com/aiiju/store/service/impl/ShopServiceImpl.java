package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.FtpUtil;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.DiscountMapper;
import com.aiiju.mapper.GoodsMapper;
import com.aiiju.mapper.GoodsTypeMapper;
import com.aiiju.mapper.ImageResourceMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.DiscountShopLink;
import com.aiiju.pojo.GoodsType;
import com.aiiju.pojo.ImageResource;
import com.aiiju.pojo.Shop;
import com.aiiju.pojo.User;
import com.aiiju.pojo.WxSub;
import com.aiiju.store.constant.DiscountTypeSwitch;
import com.aiiju.store.controller.sso.SSOController;
import com.aiiju.store.service.AppAuthService;
import com.aiiju.store.service.ShopService;
import com.aiiju.store.service.WxSubService;
import com.aiiju.store.util.StringUtil;
@Service("shopService")
public class ShopServiceImpl implements ShopService {
	
	
	private static Logger logger = Logger.getLogger(ShopServiceImpl.class);
	
	
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
	
	@Value("${STORE_ID}")
	private String STORE_ID;
	@Value("${OPERATOR_ID}")
	private String OPERATOR_ID;
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private DiscountMapper discountMapper;
	@Autowired
	private JedisClient jedisClient;
	

	
	@Autowired
	private ImageResourceMapper imageResourceMapper;
	
    @Autowired
	private AppAuthService appAuthService;
    @Autowired
	private WxSubService wxSubService;
	
    @Autowired
	private GoodsMapper goodsMapper;
	
	
	@Override
	public Result save(Shop shop,String name) {
		if (StringUtils.isBlank(shop.getShopName())) {
			shop.setShopName(name);
		}
		shop.setStoreId(String.valueOf(this.jedisClient.incr(STORE_ID)));
		  shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
		shop.setAlipay(Byte.valueOf("2"));
		shop.setWx(Byte.valueOf("2"));
		this.shopMapper.add(shop);
		//商品管理默认分类
		GoodsType gt = new GoodsType();
		gt.setName("默认分类");
		gt.setStoreId(shop.getStoreId());
		this.goodsTypeMapper.add(gt);
		//店主关联店铺
		Integer shopId = shop.getId();
		User user = this.userMapper.checkByUserNameWithoutShop(name);
		if (user == null) {
			return Result.build(400, "用户名未注册");
		}
		user.setRole(Byte.valueOf("1"));
		user.setShopId(shopId);
		user.setStoreId(shop.getStoreId());
		user.setOldStoreId(shop.getStoreId());
		user.setOperatorId(shop.getStoreId() + "001");
		this.userMapper.update(user);
		this.jedisClient.set(OPERATOR_ID + ":" + user.getStoreId(), user.getOperatorId());
		//优惠折扣
		List<DiscountShopLink> links = new ArrayList<>(8);
		DiscountShopLink link = null;
		for (int i=1; i<9; i++) {
			link = new DiscountShopLink();
			link.setStoreId(shop.getStoreId());
			link.setDiscountId(i);
			links.add(link);
		}
		this.discountMapper.insertBatch(links);
		return Result.success(true);
	}
	
	@Override
	public Result saveShop(Shop shop) {

		
		Map params = new HashMap();
		params.put("parentStoreId", shop.getParentStoreId());
		params.put("shopName", shop.getShopName());
		List list = shopMapper.getShopByParentStoreIdAndName(params);
		if(list.size()>0){
			return Result.build(500, "该店铺名称已存在，请换用其他名称");
		}
		
		shop.setStoreId(String.valueOf(this.jedisClient.incr(STORE_ID)));
		shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
		shop.setAlipay(Byte.valueOf("2"));
		shop.setWx(Byte.valueOf("2"));
		shop.setCreateDate(new Date());
		
        shop.setIsReputationShop("0");
      //  shop.setReputationShopId(0);
        shop.setModifyRelationGoods("0");
        shop.setDeleteRelationGoods("0");
        shop.setManageOwnGoods("0");
		
		
		this.shopMapper.add(shop);
		//商品管理默认分类
		GoodsType gt = new GoodsType();
		gt.setName("默认分类");
		gt.setCreateDate(new Date());
		gt.setStoreId(shop.getStoreId());
		this.goodsTypeMapper.add(gt);
//		//店主关联店铺
//		Integer shopId = shop.getId();
//		User user = this.userMapper.checkByUserNameWithoutShop(name);
//		if (user == null) {
//			return Result.build(400, "用户名未注册");
//		}
//		user.setRole(Byte.valueOf("1"));
//		user.setShopId(shopId);
//		user.setStoreId(shop.getStoreId());
//		user.setOldStoreId(shop.getStoreId());
//		user.setOperatorId(shop.getStoreId() + "001");
//		this.userMapper.update(user);
//		this.jedisClient.set(OPERATOR_ID + ":" + user.getStoreId(), user.getOperatorId());
		//优惠折扣
		List<DiscountShopLink> links = new ArrayList<>(8);
		DiscountShopLink link = null;
		for (int i=1; i<9; i++) {
			link = new DiscountShopLink();
			link.setStoreId(shop.getStoreId());
			link.setDiscountId(i);
			links.add(link);
		}
		this.discountMapper.insertBatch(links);
		return Result.success(shop);
	}

	@Override
	public Result update(Shop shop) {
		
		if(shop.getShopName()!=null&&shop.getParentStoreId()!=null){
			List<Shop> shopDB = shopMapper.isExist(shop);
			
			if(shopDB.size()>0){
				return Result.build(500, "该店铺名称已存在，请换用其他名称");
			}
		}
		this.shopMapper.update(shop);
		Shop returnShop = shopMapper.getById(shop.getId());
		return Result.success(returnShop);
	}

	@Override
	public Result getById(Integer id) {
		Shop shop = this.shopMapper.getById(id);
		return Result.success(shop);
	}
	@Override
	public Result getSwitchShop(Integer id) {
		Shop shop = this.shopMapper.getById(id);
		
		   byte aplipay = shop.getAlipay();
	       byte weixin = shop.getWx();
	       
	       if(aplipay==2){
	    	   // 查看 授权表中是否有此店铺编号或者主店铺编号，如果 更新aplipay=1
	    	  AppAuth appAuth1  = appAuthService.getAppAuthByStoreId(shop.getStoreId());
	    	  if(appAuth1!=null){
	    		  shop.setAlipay((byte)1);
	    		  shop.setModifyDate(new Date());
	    		  shopMapper.updatePayAuth(shop);
	    		
	    		  System.out.println("该切换后的店铺有支付宝支付能力");
	    	  }else{
	    		  AppAuth appAuth2  = appAuthService.getAppAuthByStoreId(shop.getParentStoreId());
	    		  if(appAuth2!=null){
	    			  // 主店铺有授权信息
	    			  appAuth2.setStoreId(shop.getStoreId());
	    			  appAuth2.setCreateDate(new Date());
	    			  appAuthService.saveAppAuth(appAuth2);
	    			  shop.setAlipay((byte)1);
	        		  shop.setModifyDate(new Date());
	        		  shopMapper.updatePayAuth(shop);
	        		//  user.setShop(shop);
	        		  System.out.println("该切换后的店铺将使用主店的支付宝支付账号");
	    		  }else{
	    			  System.out.println("该切换后的店铺无支付宝支付能力");
	    		  }
	    	  }   
	       }else{
	    	   System.out.println("该切换后的店铺有支付宝支付能力");
	       }
	       
	       if(weixin==2){
	    	   // 查看 授权表中是否有此店铺编号或者主店铺编号，如果 更新wx=1
	    	  
	    	  WxSub wxSub1  =  wxSubService.getWxSubByStoreId(shop.getStoreId());
	    	  
	    	  if(wxSub1!=null){
	    		  shop.setWx((byte)1);
	    		  shop.setModifyDate(new Date());
	    		  shopMapper.updatePayAuth(shop);
	    		//  user.setShop(shop);
	    		  System.out.println("该切换后的店铺已微信签约");
	    	  }else{
	    		  WxSub wxSub2  =  wxSubService.getWxSubByStoreId(shop.getParentStoreId());
	    		
	    		  if(wxSub2!=null){
	    			  // 主店铺有授权信息
	    			  wxSub2.setStoreId(shop.getStoreId());
	    			  wxSub2.setCreateDate(new Date());
	    			  wxSubService.saveWxSub(wxSub2);
	    			  shop.setWx((byte)1);
	        		  shop.setModifyDate(new Date());
	        		  shopMapper.updatePayAuth(shop);
	        		 
	        		  System.out.println("该切换后的店铺将使用主店的微信签约功能");
	    		  }else{
	    			  System.out.println("该切换后的店铺无微信签约");
	    		  }
	    	  }   
	    	  
	       }else{
	    	   System.out.println("该切换后的店铺已微信签约");
	       }
		
		
		return Result.success(shop);
	}
	
	@Override
	public Result deleteById(Integer id) {
		this.shopMapper.deleteById(id);
		return Result.success(true);
	}

	@Override
	public Result upload(MultipartFile uploadFile,String storeId) {
		String path = this.savePicture(uploadFile, storeId);
		if (path != null) {
			return Result.success(IMAGE_BASE_URL+path);
		} else {
			return Result.build(401, "文件为空或文件保存失败,请重试");
		}
	}
	
	private String savePicture(MultipartFile uploadFile,String storeId) {
		String result = null;
		try {
			// 判断文件是否为空
			if (uploadFile == null || uploadFile.isEmpty()) {
				return result;
			}
			Date now = new Date();
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = "/" + DateUtil.formatDate(now, "yyyy") + "/"
					+ DateUtil.formatDate(now, "MM") + "/"
					+ DateUtil.formatDate(now, "dd");
			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			boolean flag = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			
			
		      
			
			
			if (!flag) {
				return result;
			}
			result = filePath + "/" + newFileName;
			
			
		    String image_type  = originalFilename.substring(originalFilename.lastIndexOf(".")).replaceAll("\\.", "");
			
		    ImageResource imageResource = new ImageResource("店铺头像图片",originalFilename, image_type, IMAGE_BASE_URL+result, storeId, "1", "店铺头像设置相关", new Date());
		    imageResourceMapper.add(imageResource);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
		
		return result;
	}

	@Override
	public String getShopName(Integer shopId) {
		return this.shopMapper.getShopNameById(shopId);
	}

    @Override
    public String getShopName(String storeId) {
        return this.shopMapper.getShopNameByStoreId(storeId);
    }


	@Override
	public Result getShopList(String role,String storeId) {
		
		// 管理员 ，  店长 ,店员 ，    查询 
		
		List<Shop> shopList = new ArrayList<Shop>();

		if("0".equals(role)){

			//超级管理员    查询 Shop 和  ReputationShop 的  parent_store_id  为 storeId的数据
			 shopList = shopMapper.getShopListByParentStoreId(storeId);
		}
	    if("1".equals(role)){
			
	    	Shop shop = shopMapper.getShopListByStoreId(storeId);
	    	shopList.add(shop);
			//店长    查询 Shop 和  ReputationShop 的  store_id  为 storeId的数据
		}
		
		return Result.success(shopList);
	}


	@Override
	public Result switchList(String role, String storeId) {
		List<Shop> shopList = new ArrayList<Shop>();

		if("0".equals(role)){

			//超级管理员    查询 Shop 和  ReputationShop 的  parent_store_id  为 storeId的数据
			 shopList = shopMapper.getSwitchListByParentStoreId(storeId);
			 
			 return Result.success(shopList);
		}else{
			 return Result.success();
		}

	}


	@Override
	public Result updateShopGoodsAuth(Shop shop) {
		shopMapper.updateShopGoodsAuth(shop);
		return Result.success();
	}

	
	@Override
	public Result queryShopList(String role, String storeId) {
		
		List<Shop> shopList = new ArrayList<Shop>();
		if("0".equals(role)){

			//超级管理员    查询 Shop 和  ReputationShop 的  parent_store_id  为 storeId的数据
			 shopList = shopMapper.getSwitchListByParentStoreId(storeId);
			 return Result.success(shopList);
			 
		}else if("1".equals(role)){
			
	    	Shop shop = shopMapper.getShopListByStoreId(storeId);
	    	shopList.add(shop);
			//店长    查询 Shop 和  ReputationShop 的  store_id  为 storeId的数据
	    	 return Result.success(shopList);
		}
		
		else{
			 return Result.success();
		}

	}

	/**
	 * 1，进行一次同步
	 * 2，进行更新，数据库中shop 表中的字段
	 */
	@Override
	public String openSynchronize(String storeId, String status) {
		

		// 更新数据库的字段
		//Shop shop = shopMapper.getByStoreId(Integer.valueOf(storeId));
		Shop shop = shopMapper.getByStoreId(storeId);
		if(!status.equals(shop.getIsOpenSynchronize())){
			shop.setIsOpenSynchronize(status);
			shopMapper.updateSynchronize(shop);
		}
		
		// 进行请求转发到 同步功能，这样就可以进行一次同步
		 return "forward:/goods/synchronize/";
//		return JsonUtils.objectToJson(Result.success());
	}
	
	@Override
	public boolean relativeERP(String storeId){
		String status = this.shopMapper.getShopRelativeErpStatus(storeId);
		if("1".equals(status)){
			return true;
		}
		
		return false;
	}

	
	


}
