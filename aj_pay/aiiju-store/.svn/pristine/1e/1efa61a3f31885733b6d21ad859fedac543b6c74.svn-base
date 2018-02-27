package com.aiiju.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.MPointsMapper;
import com.aiiju.pojo.MPoints;
import com.aiiju.store.service.MPointsService;

/**
 * 
 * @ClassName: MPointsServiceImpl
 * @Description: 会员积分 ServiceImpl
 * @author 宗介
 * @date 2017年5月6日 
 *
 */
@Service("mpointsService")
public class MPointsServiceImpl implements MPointsService {

    @Autowired
    private MPointsMapper mPointsMapper;



    @Override
    public Result update(MPoints mpoints) {
    	
    	// 查询数据库中是否已存在；
    	
    	 MPoints points = this.mPointsMapper.getByStoreId(mpoints.getStoreId());
    	 
	       if(points==null){
	    	  
	    	   this.mPointsMapper.add(mpoints);
	       }else{
	    	   
	    	   this.mPointsMapper.update(mpoints);
	       }
    	
    	
        return Result.success(mpoints.getId());
    }



	@Override
	public Result getByStoreId(String storeId) {
	       MPoints points = this.mPointsMapper.getByStoreId(storeId);
	       if(points==null){
	    	  
	    	   return Result.success(true);
	       }
	       
	        return Result.success(points);
	
	}




 
}
