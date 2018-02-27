package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.PAContract;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.ReputationShopInfo;
import com.aiiju.pojo.Shop;
import com.aiiju.pojo.SignPay;

/** 
 * @ClassName SignMapper
 * @Description
 * @author zong
 * @CreateDate 2017年8月8日 下午3:23:55
 */
public interface SignPayMapper {
	
	public SignPay querySignPay(SignPay signPay);
	
	public void add(SignPay signPay);
	
	public void update(SignPay signPay);
	
	public SignPay querySignPayStatus(Map<String,String> param);
	
	
	public Shop queryShopSignStatus(SignPay signPay);
	public ReputationShopInfo queryReputationSignStatus(SignPay signPay);
	public List<PAContract> queryPAContractStatus(SignPay signPay);
	
    public PAMerchant queryPAMerchant(String storeId);

}
