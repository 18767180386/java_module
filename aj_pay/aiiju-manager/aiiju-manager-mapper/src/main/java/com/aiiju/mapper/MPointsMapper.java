package com.aiiju.mapper;

import com.aiiju.pojo.MPoints;

/**
 * 
 * @ClassName: MPointsMapper
 * @Description: 会员积分设置Mapping
 * @author 宗介
 * @date 2017年5月11日 下午5:15:49
 *
 */
public interface MPointsMapper {
	
	    public void add(MPoints mPoints);
	
	    public void update(MPoints mPoints);

	    public MPoints getByStoreId(String storeId);
	    
	    public MPoints getById(String id);
}
