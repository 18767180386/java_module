package com.aiiju.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.NoteUtil;
import com.aiiju.mapper.PATagPhoneMapper;
import com.aiiju.pojo.PATagPhone;
import com.aiiju.store.service.PATagPhoneService;

/** 
 * @ClassName ContractServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年8月2日 下午1:47:02
 */
@Service("paTipPhoneService")
public class PATagPhoneServiceImpl implements PATagPhoneService {
	
	@Autowired
	private PATagPhoneMapper PATipPhoneMapper;


	@Override
	public Result sendTag() {
		
	List<PATagPhone> list = PATipPhoneMapper.getPATagPhone();
		
	for (PATagPhone paTipPhone : list) {
		
		String phone = paTipPhone.getPhone();
		String message = paTipPhone.getMessage();
		 
		NoteUtil.sendNote(phone, message);
			
	}

		return null;
	}
	
	
	

	
	
}
