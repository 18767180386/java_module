package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Deal;

/**
 * 
 * @ClassName: MPointsService 
 * @Description: 会员积分详情接口
 * @author 宗介 
 * @date 2017年5月15日 下午3:58:19 
 *
 */
public interface MPointsDetailService {
	/**
	 * 积分(获取)列表
	 * @param memberPhone 对象（memberPhone会员卡号，storeId商品编号）
	 * @return
	 */

	 public Result getGetPointslist(String memberPhone,String storeId, int currentNum, int pageSize);
	 
	 
	/**
	 * 积分支出（使用）列表
	 * @param memberPhone 对象（memberPhone会员卡号，storeId商品编号）
	 * @return
	 */

	 public Result getUsedPointslist(String memberPhone,String storeId, int currentNum, int pageSize);
	
	 
	 
		/**
		 * 有效积分（总获取-总支出）
		 * @param memberPhone 对象（memberPhone会员卡号，storeId商品编号）
		 * @return
		 */

	 public Result getValidPoints(String memberPhone,String storeId);
	 
	 
}
