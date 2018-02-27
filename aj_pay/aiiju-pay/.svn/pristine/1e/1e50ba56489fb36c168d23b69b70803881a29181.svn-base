package com.aiiju.pay.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Deal;
/**
 * 
 * @ClassName: DealService 
 * @Description: 交易流水接口
 * @author 小飞 
 * @date 2016年11月28日 下午3:58:19 
 *
 */
public interface DealService {
	/**
	 * 保存支付订单
	 * @param deal 对象
	 * @param type 类型：cash现金  interface第三方支付接口
	 * @return
	 */
	public Result saveDeal(Deal deal, String type);
	/**
	 * 修改支付订单状态（推送）
	 * @param deal
	 * @return
	 */
	public Boolean updateDeal(Deal deal);

	
	/**
	 * 保存退款订单
	 * @param refundDeal 对象
	 * @param type 类型：cash现金  interface第三方支付接口
	 * @return
	 */
	public Result saveRefundDeal(Deal refundDeal, String type);
	/**
	 * 修改退款订单
	 * @param serialNum  退款订单号
	 * @param tradeNo    第三方支付接口返回的交易号
	 */
	public void updateRefundDeal(String serialNum, String tradeNo);
	
	/**
	 * 通过 流水号查找订单详情
	 * @param serialNum
	 * @return
	 */
	public Deal getDealBySerialNum(String serialNum);

}