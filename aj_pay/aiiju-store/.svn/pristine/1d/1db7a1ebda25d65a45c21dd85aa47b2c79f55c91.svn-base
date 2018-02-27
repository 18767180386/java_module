package com.aiiju.store.service;

import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.ReputationShopInfo;
import com.alipay.api.request.AlipayOfflineMarketShopCreateRequest;

/** 
 * @ClassName ReputationService
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午4:07:47
 */
public interface ReputationService {

    public Result getCategoryId();

    public Result getAreaCode();
    
    public Result getShopInfoByStoreId(String storeId);
    
    public Result getImageInfo(String reputation_image_id);
    public Result deleteImageInfo(String reputation_image_id);
    
    public ReputationShopInfo addOrUpdateShop(ReputationShopInfo reputationShopInfo,AlipayOfflineMarketShopCreateRequest request);
    
    public Result updateCreateShopResultMessage(ReputationShopInfo reputationShopInfo);
    
    public Result upload(MultipartFile uploadFile,String type,String storeId,int id);
	
}
