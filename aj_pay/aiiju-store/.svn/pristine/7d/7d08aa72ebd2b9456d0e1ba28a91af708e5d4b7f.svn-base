package com.aiiju.store.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pa_utils.platform.ContractManagerUtil;
import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.PAContractMapper;
import com.aiiju.pojo.PAContract;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.store.service.ContractService;

import net.sf.json.JSONObject;

/** 
 * @ClassName ContractServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年8月2日 下午1:47:02
 */
@Service("contractService")
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	private PAContractMapper paContractMapper;
	
	
	

	
	
	
	@Override
	public Result addOrUpdate(String cttId) {
		
		PAContract contract= paContractMapper.getByCttId(cttId);
		String respObject = ContractManagerUtil.view(cttId);
		
		PAContract paContract = new PAContract();
		
		if(respObject!=null){
			
			JSONObject json = JSONObject.fromObject(respObject);
			
			String mct_no = json.getString("mct_no");
			String ctt_id = json.getString("ctt_id");
			String code = json.getString("code");
			String sdate = json.getString("sdate");
			String edate = json.getString("edate");
			
			String pmt_id = json.getString("pmt_id");
			String pmt_name = json.getString("pmt_name");
			
			String pmt_tag = json.getString("pmt_tag");
			String pmt_internal_name = json.getString("pmt_internal_name");
//			String edate = json.getString("edate");
			

//			    "pmt_id": "46",
//			    "pmt_name": "支付宝",
//			    "pmt_tag": "AlipayHZPA",
//			    "pmt_internal_name": "支付宝（杭州平安）",
//			    "pmt_opt_1": "",
//			    "pmt_opt_2": "",
//			    "pmt_opt_3": "",
//			    "pmt_opt_4": "",
//			    "pmt_opt_5": "",
//			    "pmt_opt_6": "",
//			    "pmt_opt_7": "",
//			    "pmt_opt_8": "",
//			    "pmt_opt_9": "子商户号",
//			    "pmt_opt_10": "",
//			    "pmt_discount_fee": "0",
//			    "pmf_id": "77",
//			    "pmf_name": "线下实体商户",
//			    "fee": "0.600",
//			    "pmf_limit": "0",
//			    "ctt_opt_1": null,
//			    "ctt_opt_2": null,
//			    "ctt_opt_3": null,
//			    "ctt_opt_4": null,
//			    "ctt_opt_5": null,
//			    "ctt_opt_6": null,
//			    "ctt_opt_7": null,
//			    "ctt_opt_8": null,
//			    "ctt_opt_9": "2088721906386903",
//			    "ctt_opt_10": null,
//			    "tra_id": "86000727415032989548292",
//			    "pic1": "201708/599a855a7b0c1.jpg",
//			    "pic2": "",
//			    "agent_no": "860002153",
//			    "agent_name": "大不列颠",
//			    "status": "1",
//			    "remark": null,
//			    "add_time": "2017-08-21 15:03:08",
//			    "upd_time": "2017-08-21 17:43:08",
//			    "sign_name": "天才计划",
//			    "sign_date": "2017-11-21",
//			    "sign_man": "",
//			    "contact": "蔡祖武",
//			    "contact_tel": "18257128736",
//			    "auto_sign": "1",
//			    "shop_count": "1",
//			    "__category_id": "2015062600002758",
//			    "__category_id_select": [
//			        "购物"
//			    ],
//			    "__service_phone": "95188"
//			}
//			
//			
			
			
			
			
			
			Set<String> set = json.keySet();
			
			for (String str : set) {
				
				String fileValue = json.getString(str);
				PAContract.setAllValueByReflect(paContract, str, fileValue);
				
			}
			
		    String mctNo = 	paContract.getMctNo();
			
		   PAMerchant pamerchant =  paContractMapper.getMerchantByMctNo(mctNo);

			if(pamerchant==null){
				   paContract.setStoreId("");
				   paContract.setParentStoreId(""); 
			}else{
				   paContract.setStoreId(pamerchant.getStoreId());
				   paContract.setParentStoreId(pamerchant.getParentStoreId());
				
			}

				if(contract==null){
					// 新增
					paContractMapper.add(paContract);
				}else{
					// 更新
					
					paContract.setId(contract.getId());
					paContractMapper.update(paContract);
				}
		   
		   
		  
		}else{
			Result.build(500, "接口查询合同信息时失败");
		}
		
	
		return Result.success("notify_success");
	}


	@Override
	public Result add(PAContract contract) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result update(PAContract contract) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result getContractInfo(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
