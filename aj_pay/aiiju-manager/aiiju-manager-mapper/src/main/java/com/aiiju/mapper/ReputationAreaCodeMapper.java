package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.Goods;
import com.aiiju.pojo.ReputationAreaCode;

/** 
 * @ClassName ReputationAreaCodeMapper
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:14:13
 */
public interface ReputationAreaCodeMapper {

	
	/**
	 * 获取省
	 * @param 
	 * @return
	 */
	public List<ReputationAreaCode> getProvenceCode();

	
	/**
	 * 获取市    通过省code
	 * @param proCode
	 * @return
	 */
	public List<ReputationAreaCode> getCityCodeByPro(String proCode);
	
	
	/**
	 * 获取区县 通过市code
	 * @param cityCode
	 * @return
	 */
	public List<ReputationAreaCode> getDistrictCodeByCity(String cityCode);
	
	
	/**
	 * 获取区县以上的数据
	 * @param cityCode
	 * @return
	 */
	public List<ReputationAreaCode> getValidCode();
	
	
}
