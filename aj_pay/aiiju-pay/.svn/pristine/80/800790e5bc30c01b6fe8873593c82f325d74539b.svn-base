package com.aiiju.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.DealAccountMapper;
import com.aiiju.pay.service.DealAccountService;
import com.aiiju.pojo.DealAccount;

/** 
 * @ClassName DealAccountServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年6月17日 上午11:50:04
 */
@Service("dealAccountService")
public class DealAccountServiceImpl implements DealAccountService {

	@Autowired
	private DealAccountMapper dealAccountMapper;
	
	@Override
	public Boolean saveDealAccount(DealAccount dealAccount) {
		
		
		if(dealAccount!=null){
			
			if(dealAccount.getSubAppId()!=null){
				dealAccountMapper.addWeChat(dealAccount);
			}
			
			else if(dealAccount.getAuthAppId()!=null){
				dealAccountMapper.addApliay(dealAccount);
				
			}else if(dealAccount.getOpenId()!=null){
				dealAccountMapper.addPa(dealAccount);
				
			}else{
				
				System.out.println("警告，保存交易与账户对应关系时出错，dealAccount="+dealAccount);
			}
			
		}else{
			System.out.println("警告，保存交易与账户对应关系时出错，dealAccount="+dealAccount);
		}
		
		
		
		return true;
	}

}
