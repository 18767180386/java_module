package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.DealRate;
/**
 * 
 * @ClassName: DealRateMapper 
 * @Description: 交易费率Mapper
 * @author 小飞 
 * @date 2016年11月14日 下午4:30:41 
 *
 */
public interface DealRateMapper {
	
	public void add(DealRate dealRate);
	
	public void update(DealRate dealRate);

	public List<DealRate> getAll();
}
