package com.aiiju.store.service;

import java.util.List;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.MPoints;

/**
 * 
 * @ClassName: MPointsService
 * @Description: 会员积分 Service
 * @author 宗介
 * @date 2017年5月6日 上午10:42:35
 *
 */
public interface MPointsService {

  

    public Result update(MPoints card);
    
    public Result getByStoreId(String storeId);

    

}
