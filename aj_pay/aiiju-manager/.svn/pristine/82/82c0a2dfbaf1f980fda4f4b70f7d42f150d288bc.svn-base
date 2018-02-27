package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.PAMerchant;

public interface PAMerchantMapper {
    
	public void getMerchantList(); 
	
	public PAMerchant get(String storeId); 
	
	public PAMerchant getByMctNo(String mctNo); 
	
	
	public void add(PAMerchant merchant);
	
	public void update(PAMerchant merchant);

	public void deleteMerchantByIdFromDB(Integer id);
	
	public void  updateFeedbackMsg(PAMerchant merchant);
	
	/**
	 * 批量添加
	 * @param links
	 */
	public void insertBatchMerClass(List<Map> list); 
	
	public List getMctClass();
	
	public PAMerchant findPAMerchantByParStoreId(String storeId);
	public PAMerchant findPAMerchantByStoreId(String storeId);
	

	
	
	
}