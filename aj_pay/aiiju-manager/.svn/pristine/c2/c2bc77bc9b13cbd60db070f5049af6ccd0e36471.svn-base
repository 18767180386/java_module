package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.ReputationCategory;

/** 
 * @ClassName ReputationCategoryMapper
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:14:13
 */
public interface ReputationCategoryMapper {

	/**
	 * 获取一级类别
	 * @param 
	 * @return
	 */
	public List<ReputationCategory> getFirstLevel();

	/**
	 * 获取二级类别
	 * @param proCode
	 * @return
	 */
	public List<ReputationCategory> getSecondLevel(String first_level);
	/**
	 * 获取三级类别
	 * @param cityCode
	 * @return
	 */
	public List<ReputationCategory> getThirdLevel(ReputationCategory reputationCategory);
	
}
