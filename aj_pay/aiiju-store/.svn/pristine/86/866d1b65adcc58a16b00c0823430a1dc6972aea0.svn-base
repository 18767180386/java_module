package com.aiiju.store.service;

import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.PAMerchant;

/** 
 * @ClassName MerchantService
 * @Description
 * @author zong
 * @CreateDate 2017年7月19日 上午11:40:35
 */
public interface MerchantService {

	public Result loadMerchantInfo(String mctNo);
	
	public Result add(PAMerchant merchant);
	
	public Result update(PAMerchant merchant,String updateType);

	public Result getMerchantInfo(String storeId);
	
	public Result deleteMerchantFromDB(Integer id);

	Result updateFeedbackMsg(PAMerchant merchant);
	
	public Result createMctClass();
	
	public Result getMctClass();
	
	public Result upload(MultipartFile uploadFile,String type,String storeId,int id);
	
	public Result getImageInfo(String imageId);
}
