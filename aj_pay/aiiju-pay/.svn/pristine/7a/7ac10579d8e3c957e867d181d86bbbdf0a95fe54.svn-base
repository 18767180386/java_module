package com.aiiju.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.mapper.QqSubMapper;
import com.aiiju.pay.service.QqSubService;
import com.aiiju.pojo.QqSub;
@Service("qqSubService")
public class QqSubServiceImpl implements QqSubService {

	@Autowired
	private QqSubMapper qqSubMapper;
	
	@Override
	public QqSub getQqSubByStoreId(String storeId) {
		return this.qqSubMapper.getByStoreId(storeId);
	}

	@Override
	public Boolean saveQqSub(QqSub qqSub) {
		try {
			this.qqSubMapper.add(qqSub);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
