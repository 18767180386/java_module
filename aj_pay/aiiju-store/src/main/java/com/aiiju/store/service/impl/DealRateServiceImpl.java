package com.aiiju.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.DealRateMapper;
import com.aiiju.pojo.DealRate;
import com.aiiju.store.service.DealRateService;
@Service("dealRateService")
public class DealRateServiceImpl implements DealRateService {

	@Autowired
	private DealRateMapper dealRateService;
	
	@Override
	public Result getDealRate() {
		return Result.success(this.dealRateService.getAll());
	}

	@Override
	public Result saveDealRate(DealRate dealRate) {
		this.dealRateService.add(dealRate);
		return Result.success(true);
	}

	@Override
	public Result updateDealRate(DealRate dealRate) {
		this.dealRateService.update(dealRate);
		return Result.success(true);
	}

}
