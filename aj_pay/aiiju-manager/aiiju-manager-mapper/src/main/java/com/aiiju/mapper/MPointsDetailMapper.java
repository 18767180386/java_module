package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.MPointsDetail;

/**
 * 
 * @ClassName: MPointsDetailMapper
 * @Description: 会员积分详情Mapping
 * @author 宗介
 * @date 2017年5月11日 下午5:15:49
 *
 */
public interface MPointsDetailMapper {
	
	    public void add(MPointsDetail mPointsDetail);
	
	
	    public List<MPointsDetail> getPointsValueList(Map<String, Object> params);
	    public List<MPointsDetail> usedPointsValueList(Map<String, Object> params);
	    
	    public Integer getPointsValue(Map<String, Object> params);
	    
	    public Integer usedPointsValue(Map<String, Object> params);
	    public Integer getValidPoints(Map<String, Object> params);
	    
	    
	    public void updateMPointsDetailBySerialnum(Map map);
	    
	    
	    /**
	     * 删除积分详情
	     * @param map	
	     * @param map
	     * @param map
	     * @return
	     */
	    public int delete(Map<String,Object> map );
}