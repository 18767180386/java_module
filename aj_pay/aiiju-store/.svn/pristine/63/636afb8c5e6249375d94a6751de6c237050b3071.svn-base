package com.aiiju.store.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.FtpUtil;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.AppAuthMapper;
import com.aiiju.mapper.DiscountMapper;
import com.aiiju.mapper.GoodsTypeMapper;
import com.aiiju.mapper.ImageResourceMapper;
import com.aiiju.mapper.ReputationAreaCodeMapper;
import com.aiiju.mapper.ReputationCategoryMapper;
import com.aiiju.mapper.ReputationShopImagesMapper;
import com.aiiju.mapper.ReputationShopInfoMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.DiscountShopLink;
import com.aiiju.pojo.GoodsType;
import com.aiiju.pojo.ImageResource;
import com.aiiju.pojo.ReputationAreaCode;
import com.aiiju.pojo.ReputationCategory;
import com.aiiju.pojo.ReputationShopImages;
import com.aiiju.pojo.ReputationShopInfo;
import com.aiiju.pojo.Shop;
import com.aiiju.store.constant.DiscountTypeSwitch;
import com.aiiju.store.constant.ImageFieldMap;
import com.aiiju.store.service.ReputationService;
import com.aiiju.store.util.AlipayUtil;
import com.aiiju.store.util.GsonUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.request.AlipayOfflineMarketShopCreateRequest;
import com.alipay.api.request.AlipayOfflineMaterialImageUploadRequest;
import com.alipay.api.response.AlipayOfflineMarketShopCreateResponse;
import com.alipay.api.response.AlipayOfflineMaterialImageUploadResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @ClassName ReputationServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午4:08:15
 */
@Service("reputationService")
public class ReputationServiceImpl implements ReputationService {

	static AlipayClient alipayClient =AlipayUtil.getAlipayClient();
	
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
    
	@Autowired
	private ReputationAreaCodeMapper reputationAreaCodeMapper;
	
	@Autowired
	private ReputationCategoryMapper reputationCategoryMapper;
	
	
	@Autowired
	private ReputationShopInfoMapper reputationShopInfoMapper;
	
	@Autowired
	private ReputationShopImagesMapper reputationShopImagesMapper;
	
	@Autowired
	private ImageResourceMapper imageResourceMapper;
	
	@Autowired
	private AppAuthMapper appAuthMapper;
	
    @Autowired
    private JedisClient jedisClient;
	
    @Autowired
    private ShopMapper shopMapper;
    
	
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private DiscountMapper discountMapper;
    
    
    public String getNotifyUrl(String storeId){
    	System.out.println(IMAGE_BASE_URL);
    	return this.IMAGE_BASE_URL+"/reputation/operateNotifyUrl?store_id="+storeId;
    }
	
	
	@Override
	public Result getCategoryId() {
		
		long start =System.currentTimeMillis();
		List<ReputationCategory> list = reputationCategoryMapper.getFirstLevel();
		JSONObject json = new JSONObject();
		for (ReputationCategory reputationCategory2 : list) {
			
			 String first_level = reputationCategory2.getFirst_level() ;   
	         String category_id1 = reputationCategory2.getCategory_id();  
			
	         List<ReputationCategory> list2 =   reputationCategoryMapper.getSecondLevel(first_level);
	         
	         JSONArray jsa2 = new JSONArray();
	     	for (ReputationCategory reputationCategory3 : list2) {
	     		 JSONObject json2 = new JSONObject();
				 String second_level = reputationCategory3.getSecond_level();   
		         String category_id2 = reputationCategory3.getCategory_id();  
				
		         
		         ReputationCategory rc =   new ReputationCategory();
		         rc.setFirst_level(first_level);
		         rc.setSecond_level(second_level);
		         
		         List<ReputationCategory> list3 =   reputationCategoryMapper.getThirdLevel(rc);
		         JSONArray jsa3 = new JSONArray();
		     	for (ReputationCategory reputationCategory4 : list3) {
					
					 String third_level = reputationCategory4.getThird_level();
			         String category_id3 = reputationCategory4.getCategory_id();  
					
			         jsa3.add(third_level+"_"+category_id3);

				}
		     	 json2.put(second_level+"_"+category_id2, jsa3);
				 jsa2.add(json2);
		         
			}
	         
	     	 json.put(first_level+"_"+category_id1, jsa2); 
		}
		
		long end =System.currentTimeMillis();
		System.out.println(end-start+"ms");
		return Result.success(json);
	}

	
	@Override
	public Result getAreaCode() {

		long start =System.currentTimeMillis();
		
		List<ReputationAreaCode> list = reputationAreaCodeMapper.getProvenceCode();
		JSONObject json = new JSONObject();
		for (ReputationAreaCode reputationAreaCode2 : list) {
			
			 String area_code1 = reputationAreaCode2.getArea_code();   
	         String area_name1 = reputationAreaCode2.getArea_name();  
	        
	         List<ReputationAreaCode> list2 =   reputationAreaCodeMapper.getCityCodeByPro(area_code1);
	         
	         JSONArray jsa2 = new JSONArray();
	     	for (ReputationAreaCode reputationAreaCod3 : list2) {
	     		
	     		 JSONObject json2 = new JSONObject();
				 String area_code2 = reputationAreaCod3.getArea_code();
		         String area_name2 = reputationAreaCod3.getArea_name();
					// 市
		         List<ReputationAreaCode> list3 =   reputationAreaCodeMapper.getDistrictCodeByCity(area_code2);
		         JSONArray jsa3 = new JSONArray();
		     	for (ReputationAreaCode reputationAreaCode4 : list3) {
					
					 String area_code3 = reputationAreaCode4.getArea_code() ;   
			         String area_name3 = reputationAreaCode4.getArea_name();  
			         jsa3.add(area_name3+"_"+area_code3);

				}
		     	 json2.put(area_name2+"_"+area_code2, jsa3);
				 jsa2.add(json2);
		         
			}
	         
	     	 json.put(area_name1+"_"+area_code1, jsa2); 
		}
		long end =System.currentTimeMillis();
		
		System.out.println(end-start+"ms");
		return Result.success(json);
		
	}

	
	@Override
	public ReputationShopInfo addOrUpdateShop(ReputationShopInfo reputationShopInfo,AlipayOfflineMarketShopCreateRequest request) {

	//	Integer id = reputationShopInfo.getId();
	
	
		//ReputationShopInfo DBreputationShopInfo  = reputationShopInfoMapper.getShopInfoByStoreId(reputationShopInfo.getStore_id());
		
		if(reputationShopInfo.getId()==null||reputationShopInfo.getId()<1){

		    String storeId =	this.jedisClient.incr(STORE_ID)+"";
		    reputationShopInfo.setStore_id(storeId);
			reputationShopInfoMapper.add(reputationShopInfo);
			String notifyUrl = getNotifyUrl(reputationShopInfo.getStore_id());
			reputationShopInfo.setOperate_notify_url(notifyUrl+"&id="+reputationShopInfo.getId());
		    // 绑定店铺
		    Shop shop = new Shop();
		    String mainImageId = reputationShopInfo.getMain_image();
		    ReputationShopImages reputationShopImages =  reputationShopImagesMapper.getByImageId(mainImageId);
		    shop.setImageUrl(reputationShopImages.getImage_url());
		    shop.setParentStoreId(shop.getParentStoreId());
		    shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
		    shop.setAlipay(Byte.valueOf("2"));
		    shop.setWx(Byte.valueOf("2"));
		    shop.setCreateDate(reputationShopInfo.getCreate_date());
		    shop.setModifyDate(reputationShopInfo.getCreate_date());
		    shop.setParentStoreId(reputationShopInfo.getParent_store_id());
		    shop.setShopName(reputationShopInfo.getMain_shop_name());
		    shop.setProvinceCode(reputationShopInfo.getProvince_code());
		    shop.setCityCode(reputationShopInfo.getCity_code());
		    shop.setDistrictCode(reputationShopInfo.getDistrict_code());
		    shop.setCodeName(reputationShopInfo.getCode_name());
		    shop.setAddressDetail(reputationShopInfo.getAddress());
		    shop.setLongitude(reputationShopInfo.getLongitude());
		    shop.setLatitude(reputationShopInfo.getLatitude());
		    shop.setContactNumber(reputationShopInfo.getContact_number());
		    shop.setShopType(reputationShopInfo.getShop_type());
		    shop.setIsReputationShop("1");
		    shop.setReputationShopId(reputationShopInfo.getId());		   
		    shop.setStoreId(storeId);

	        shop.setModifyRelationGoods("0");
	        shop.setDeleteRelationGoods("0");
	        shop.setManageOwnGoods("0");
	  		
		
	        // 商品管理默认分类
	        GoodsType gt = new GoodsType();
	        gt.setName("默认分类");
	        gt.setStoreId(shop.getStoreId());
	        gt.setCreateDate(new Date());
	        this.goodsTypeMapper.add(gt);
	        // 优惠折扣,默认8种
	        List<DiscountShopLink> links = new ArrayList<>(8);
	        DiscountShopLink link = null;
	        for (int i = 1; i < 9; i++) {
	            link = new DiscountShopLink();
	            link.setStoreId(shop.getStoreId());
	            link.setDiscountId(i);
	            links.add(link);
	        }
	        this.discountMapper.insertBatch(links);
		    this.shopMapper.add(shop);

			
		}else{
			String notifyUrl = getNotifyUrl(reputationShopInfo.getStore_id());
			reputationShopInfo.setOperate_notify_url(notifyUrl+"&id="+reputationShopInfo.getId());
			reputationShopInfoMapper.update(reputationShopInfo);
		    Shop shop = new Shop();
//		    String mainImageId = reputationShopInfo.getMain_image();
//		    ReputationShopImages reputationShopImages =  reputationShopImagesMapper.getByImageId(mainImageId);
//		    shop.setImageUrl(reputationShopImages.getImage_url());
//		    shop.setParentStoreId(reputationShopInfo.getParent_store_id());
//		    shop.setIsDiscount(DiscountTypeSwitch.DiscountTypeSwitchOpen);
//		    shop.setAlipay(Byte.valueOf("2"));
//		    shop.setWx(Byte.valueOf("2"));
//		    shop.setCreateDate(reputationShopInfo.getCreate_date());
		    shop.setModifyDate(reputationShopInfo.getCreate_date());
		    shop.setParentStoreId(reputationShopInfo.getParent_store_id());
		    shop.setShopName(reputationShopInfo.getMain_shop_name());
		    shop.setProvinceCode(reputationShopInfo.getProvince_code());
		    shop.setCityCode(reputationShopInfo.getCity_code());
		    shop.setDistrictCode(reputationShopInfo.getDistrict_code());
		    shop.setCodeName(reputationShopInfo.getCode_name());
		    shop.setAddressDetail(reputationShopInfo.getAddress());
		    shop.setLongitude(reputationShopInfo.getLongitude());
		    shop.setLatitude(reputationShopInfo.getLatitude());
		    shop.setContactNumber(reputationShopInfo.getContact_number());
		    shop.setShopType(reputationShopInfo.getShop_type());
		    shop.setReputationShopId(reputationShopInfo.getId());		   
		    shop.setStoreId(reputationShopInfo.getStore_id());
			
			shopMapper.updateByStoreIdNew(shop);
			
			
			
		}
		
		
		String bizContent = GsonUtil.getGson().toJson(reputationShopInfo);
		
		request.setBizContent(bizContent);
	
		System.out.println("发送参数：\r\n"+bizContent);
		AppAuth auth = appAuthMapper.getByStoreId(reputationShopInfo.getStore_id());
		
		 
	        if (auth == null) {
	        	System.out.println("商户未授权");
	         
	        }else{
	        	System.out.println("商户已授权："+auth.getAppAuthToken());
	        	 request.putOtherTextParam("app_auth_token",auth.getAppAuthToken());
	        }
		
		 request.setNotifyUrl(reputationShopInfo.getOperate_notify_url());
	
		 
		try{
		
			AlipayOfflineMarketShopCreateResponse response = alipayClient.execute(request);
		//	AlipayOfflineMarketShopCreateResponse response = alipayClient.execute(request,"app_auth_token:"+auth.getAppAuthToken());

			reputationShopInfo.setCode(response.getCode());
			reputationShopInfo.setMsg(response.getMsg());
			reputationShopInfo.setSub_code(response.getSubCode());
			reputationShopInfo.setSub_msg(response.getSubMsg());
			reputationShopInfo.setAudit_status(response.getAuditStatus());
			reputationShopInfo.setResult_code(response.getResultCode());
			reputationShopInfo.setApply_id(response.getApplyId());
			
		//	reputationShopInfo.setId(id);
			reputationShopInfo.setCreate_date(new Date());
			reputationShopInfo.setModify_date(new Date());
			System.out.println("返回结果"+response.getBody());

			if(response.isSuccess()){
				System.out.println("调用成功");
				reputationShopInfo.setReview_status("2");
				this.updateCreateShopResultMessage(reputationShopInfo);
			//	return 	Result.build(200, "创建口碑成功", reputationShopInfo);

				} else {
					System.out.println("调用失败");
					reputationShopInfo.setReview_status("0");
					this.updateCreateShopResultMessage(reputationShopInfo);
				//	return 	Result.build(404, "创建口碑失败", reputationShopInfo);
			
			 }
			
		}catch(AlipayApiException ae){
			
			reputationShopInfo.setSub_msg("请求超时，请稍后再试");
			reputationShopInfo.setReview_status("0");
			this.updateCreateShopResultMessage(reputationShopInfo);
			System.out.println("超时了"+ae.getMessage());
			//return 	Result.build(500, "创建口碑超时了", reputationShopInfo);
		}
		
		
		
//	String audit_images = reputationShopInfo.getAudit_images();
//		
//		StringBuffer sb = new StringBuffer();
//		
//		if(audit_images!=null){
//			
//		   String [] images = 	audit_images.split("\\,");
//		   
//		   
//		   for (String string : images) {
//			
//			   if(!string.equals("")){
//				   ReputationShopImages r1 =  reputationShopImagesMapper.getByImageId(string);
//				   
//				   sb.append(string+"分割线"+r1.getImage_url()+",");
//			   }
//				
//		   }
//			
//		   reputationShopInfo.setAudit_images(sb.substring(0, sb.length()-1));
//		}
//		
//		
//		if(reputationShopInfo.getAuth_letter()!=null){
//			ReputationShopImages r2 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getAuth_letter());
//			reputationShopInfo.setAuth_letter(reputationShopInfo.getAuth_letter()+"分割线"+r2.getImage_url());
//			
//		}
//	
//		
//		if(reputationShopInfo.getBrand_logo()!=null){
//			ReputationShopImages r3 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBrand_logo());
//			reputationShopInfo.setBrand_logo(reputationShopInfo.getBrand_logo()+"分割线"+r3.getImage_url());
//			
//			
//		}
//		
//	
//		if(reputationShopInfo.getBusiness_certificate()!=null){
//			ReputationShopImages r4 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBusiness_certificate());
//			reputationShopInfo.setBusiness_certificate(reputationShopInfo.getBusiness_certificate()+"分割线"+r4.getImage_url());	
//		}
//
//		if(reputationShopInfo.getLicence()!=null){
//			ReputationShopImages r5 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getLicence());
//			reputationShopInfo.setLicence(reputationShopInfo.getLicence()+"分割线"+r5.getImage_url());
//		}
//
//	
//		if(reputationShopInfo.getMain_image()!=null){
//		ReputationShopImages r6 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getMain_image());
//		reputationShopInfo.setMain_image(reputationShopInfo.getMain_image()+"分割线"+r6.getImage_url());
//		}
//		if(reputationShopInfo.getOther_authorization()!=null){
//		ReputationShopImages r7 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getOther_authorization());
//		reputationShopInfo.setOther_authorization(reputationShopInfo.getOther_authorization()+"分割线"+r7.getImage_url());
//		}
		
		
	//	reputationShopInfo.setId(id);
		return reputationShopInfo;

	}


	@Override
	public Result upload(MultipartFile uploadFile,String type,String storeId,int id) {
	
	    String path = this.savePicture(uploadFile,type,storeId);
        String originalFilename = uploadFile.getOriginalFilename();
	    String image_type  = originalFilename.substring(originalFilename.lastIndexOf(".")).replaceAll("\\.", "");
		
	    ImageResource imageResource = new ImageResource(ImageFieldMap.getFieldRemarkByName(type),originalFilename, image_type, IMAGE_BASE_URL+path, storeId, "1", "口碑门店相关", new Date());
	    imageResourceMapper.add(imageResource);

        ReputationShopImages reputationShopImages = new ReputationShopImages();
	    
	    
        reputationShopImages.setField_remark(ImageFieldMap.getFieldRemarkByName(type));
	    reputationShopImages.setField_name(type);
	    reputationShopImages.setCreate_date(new Date());
	    reputationShopImages.setImage_name(originalFilename);
	    reputationShopImages.setImage_pid("");
	    reputationShopImages.setImage_type(image_type);
	    reputationShopImages.setImage_url(IMAGE_BASE_URL+path);


		AlipayOfflineMaterialImageUploadRequest request = new AlipayOfflineMaterialImageUploadRequest();
		request.setImageType(image_type);
		request.setImageName(originalFilename);
		FileItem ImageContent;
		try {
			ImageContent = new FileItem(originalFilename, uploadFile.getBytes());
			
			request.setImageContent(ImageContent);
			AlipayOfflineMaterialImageUploadResponse response = alipayClient.execute(request);

			System.out.println("response.getBody="+response.getBody());
			
			if(response.isSuccess()){
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
		    
		    
		    reputationShopImages.setReputation_image_id(response.getImageId());
		    reputationShopImages.setReputation_image_url(response.getImageUrl());
		    reputationShopImages.setRemark(response.getMsg());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    reputationShopImages.setStatus("1");
	    reputationShopImages.setStore_id(storeId);
	    
	    
		if(id<1||"".equals(id)){
			  reputationShopImagesMapper.add(reputationShopImages);
		}else{
			// 更新
			reputationShopImages.setId(id);
			reputationShopImages.setModify_date(new Date());
			reputationShopImagesMapper.update(reputationShopImages);
		}

	 //   returnMap.put("id", reputationShopImages.getId());
//	    returnMap.put("image_id", reputationShopImages.getReputation_image_id());
	//	returnMap.put("image_url", reputationShopImages.getImage_url());
	    
		return Result.success(reputationShopImages.getReputation_image_id());
     
		
	}
	
	
	
	  private String savePicture(MultipartFile uploadFile,String type,String storeId) {
	        String result = null;
	        try {
	            // 判断文件是否为空
	            if (uploadFile == null || uploadFile.isEmpty()) {
	                return result;
	            }
	     
	            // 上传文件以店铺为单位分开存放
	            String filePath = "/" + storeId;
	            // 取原始文件名
	            String originalFilename = uploadFile.getOriginalFilename();
	            // 新文件名
	            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
	            // 转存文件，上传到ftp服务器
	            boolean flag = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD, FILI_UPLOAD_PATH, filePath,
	                    newFileName, uploadFile.getInputStream());
	            if (!flag) {
	                return result;
	            }
	            result = filePath + "/" + newFileName;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }


	@Override
	public Result updateCreateShopResultMessage(ReputationShopInfo reputationShopInfo) {
		
		reputationShopInfoMapper.updateCreateShopResultMessage(reputationShopInfo);
		
		
		return Result.success();
	}

	@Override
	public Result getShopInfoByStoreId(String storeId) {

		ReputationShopInfo reputationShopInfo = reputationShopInfoMapper.getShopInfoByStoreId(storeId);
		
		if(reputationShopInfo==null){
			System.out.println("数据库中无此店铺信息");
			return Result.success();
		}
		
//		String audit_images = reputationShopInfo.getAudit_images();
//		
//		StringBuffer sb = new StringBuffer();
//		
//		if(audit_images!=null){
//			
//		   String [] images = 	audit_images.split("\\,");
//		   
//		   
//		   for (String string : images) {
//			
//			   if(!string.equals("")){
//				   ReputationShopImages r1 =  reputationShopImagesMapper.getByImageId(string);
//				   
//				   sb.append(string+"分割线"+r1.getImage_url()+",");
//			   }
//				
//		   }
//			
//		   reputationShopInfo.setAudit_images(sb.substring(0, sb.length()-1));
//		}
//		
//		if(reputationShopInfo.getAuth_letter()!=null){
//		ReputationShopImages r2 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getAuth_letter());
//		reputationShopInfo.setAuth_letter(reputationShopInfo.getAuth_letter()+"分割线"+r2.getImage_url());
//		}
//		
//		if(reputationShopInfo.getBrand_logo()!=null){
//		ReputationShopImages r3 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBrand_logo());
//		reputationShopInfo.setBrand_logo(reputationShopInfo.getBrand_logo()+"分割线"+r3.getImage_url());
//		}
//		if(reputationShopInfo.getBusiness_certificate()!=null){
//		ReputationShopImages r4 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBusiness_certificate());
//		reputationShopInfo.setBusiness_certificate(reputationShopInfo.getBusiness_certificate()+"分割线"+r4.getImage_url());
//		}
//		if(reputationShopInfo.getLicence()!=null){
//		ReputationShopImages r5 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getLicence());
//		reputationShopInfo.setLicence(reputationShopInfo.getLicence()+"分割线"+r5.getImage_url());
//		}
//		if(reputationShopInfo.getMain_image()!=null){
//		ReputationShopImages r6 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getMain_image());
//		reputationShopInfo.setMain_image(reputationShopInfo.getMain_image()+"分割线"+r6.getImage_url());
//		}
//		if(reputationShopInfo.getOther_authorization()!=null){
//		ReputationShopImages r7 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getOther_authorization());
//		reputationShopInfo.setOther_authorization(reputationShopInfo.getOther_authorization()+"分割线"+r7.getImage_url());
//		}
		
		
	
		
		return Result.success(reputationShopInfo);
	}

//	@Override
//	public Result getShopInfoByStoreId(String storeId) {
//
//		ReputationShopInfo reputationShopInfo = reputationShopInfoMapper.getShopInfoByStoreId(storeId);
//		
//		if(reputationShopInfo==null){
//			System.out.println("数据库中无此店铺信息");
//			return Result.success();
//		}
//		
//		String audit_images = reputationShopInfo.getAudit_images();
//		
//		StringBuffer sb = new StringBuffer();
//		
//		if(audit_images!=null){
//			
//		   String [] images = 	audit_images.split("\\,");
//		   
//		   
//		   for (String string : images) {
//			
//			   if(!string.equals("")){
//				   ReputationShopImages r1 =  reputationShopImagesMapper.getByImageId(string);
//				   
//				   sb.append(string+"分割线"+r1.getImage_url()+",");
//			   }
//				
//		   }
//			
//		   reputationShopInfo.setAudit_images(sb.substring(0, sb.length()-1));
//		}
//		
//		if(reputationShopInfo.getAuth_letter()!=null){
//		ReputationShopImages r2 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getAuth_letter());
//		reputationShopInfo.setAuth_letter(reputationShopInfo.getAuth_letter()+"分割线"+r2.getImage_url());
//		}
//		
//		if(reputationShopInfo.getBrand_logo()!=null){
//		ReputationShopImages r3 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBrand_logo());
//		reputationShopInfo.setBrand_logo(reputationShopInfo.getBrand_logo()+"分割线"+r3.getImage_url());
//		}
//		if(reputationShopInfo.getBusiness_certificate()!=null){
//		ReputationShopImages r4 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getBusiness_certificate());
//		reputationShopInfo.setBusiness_certificate(reputationShopInfo.getBusiness_certificate()+"分割线"+r4.getImage_url());
//		}
//		if(reputationShopInfo.getLicence()!=null){
//		ReputationShopImages r5 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getLicence());
//		reputationShopInfo.setLicence(reputationShopInfo.getLicence()+"分割线"+r5.getImage_url());
//		}
//		if(reputationShopInfo.getMain_image()!=null){
//		ReputationShopImages r6 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getMain_image());
//		reputationShopInfo.setMain_image(reputationShopInfo.getMain_image()+"分割线"+r6.getImage_url());
//		}
//		if(reputationShopInfo.getOther_authorization()!=null){
//		ReputationShopImages r7 =  reputationShopImagesMapper.getByImageId(reputationShopInfo.getOther_authorization());
//		reputationShopInfo.setOther_authorization(reputationShopInfo.getOther_authorization()+"分割线"+r7.getImage_url());
//		}
//		
//		
//	
//		
//		return Result.success(reputationShopInfo);
//	}

	@Override
	public Result getImageInfo(String reputation_image_id) {
	
		StringBuffer sb = new StringBuffer();
		
		
		
		if(reputation_image_id.contains(",")){
			
			String[] reputation_image_ids= reputation_image_id.split("\\,");
			
			for (String string : reputation_image_ids) {
				
				ReputationShopImages reputationShopImages =  reputationShopImagesMapper.getByImageId(string);
				
				sb.append(reputationShopImages.getReputation_image_id()+"分割线"+reputationShopImages.getImage_url()+",");
			}
			
		}else{
			
			ReputationShopImages reputationShopImages =  reputationShopImagesMapper.getByImageId(reputation_image_id);
			sb.append(reputationShopImages.getReputation_image_id()+"分割线"+reputationShopImages.getImage_url()+",");
		}
		
		String returnImageIds = null;
		
		if(sb.toString().endsWith(",")){
			
			returnImageIds = sb.substring(0, sb.toString().length()-1);
		}
		
		return Result.success(returnImageIds);
	}

	@Override
	public Result deleteImageInfo(String reputation_image_id) {
		reputationShopImagesMapper.deleteImageInfo(reputation_image_id);
		return Result.success();
	}
}
