package com.aiiju.pay.service;

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
	 * 保存积分
	 * @param deal 对象（memberPhone会员卡号，storeId商品编号，serialnum订单号，dealtime交易时间;getpointsvalue和usedpointsvalue 是计算得出）
	 * @return
	 */
	public Result saveMPointsDetail(Deal deal); 
	
	
	
	
	/**
	 * 更改积分状态（支付完成后，更新积分状态；status：1 可用；0否）
	 * @param serialnum订单号
	 * @param  status 状态值
	 * @return
	 */
	public Result updateMPointsDetailBySerialnum(String serialnum,String status); 
	
}
