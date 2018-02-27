package com.aiiju.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.MPointsDetailMapper;

import com.aiiju.pojo.Deal;
import com.aiiju.pojo.MPointsDetail;
import com.aiiju.store.service.MPointsDetailService;

@Service("mPonitsDeailService")
public class MPonitsDeailServiceImpl implements MPointsDetailService {

    @Autowired
    private MPointsDetailMapper mPonitsDeailMapper;
	






	@Override
	public Result getGetPointslist(String memberPhone, String storeId, int currentNum, int pageSize) {
		Map params = new HashMap();
	      if(currentNum<1){
	        	currentNum=1;
	        }
	    params.put("index", (currentNum - 1) * pageSize);
		params.put("phone", memberPhone);
		params.put("storeId", storeId);
		params.put("pageSize", pageSize);
		

		List list = mPonitsDeailMapper.getPointsValueList(params);
		Integer sum  = mPonitsDeailMapper.getPointsValue(params);
		Map map = new HashMap();

		map.put("sum", sum);
		map.put("list", list);

		return Result.success(map);
	}







	@Override
	public Result getUsedPointslist(String memberPhone, String storeId, int currentNum, int pageSize) {
		Map params = new HashMap();
	      if(currentNum<1){
	        	currentNum=1;
	        }
	    params.put("index", (currentNum - 1) * pageSize);
		params.put("phone", memberPhone);
		params.put("storeId", storeId);
		params.put("pageSize", pageSize);
		

		List list = mPonitsDeailMapper.usedPointsValueList(params);
		Integer sum  = mPonitsDeailMapper.usedPointsValue(params);
		Map map = new HashMap();

		map.put("sum", sum);
		map.put("list", list);

		return Result.success(map);
	}







	@Override
	public Result getValidPoints(String memberPhone, String storeId) {
		Map params = new HashMap();
		params.put("phone", memberPhone);
		params.put("storeId", storeId);
		Integer sum  = mPonitsDeailMapper.getValidPoints(params);
		return Result.success(sum);
	}

}
