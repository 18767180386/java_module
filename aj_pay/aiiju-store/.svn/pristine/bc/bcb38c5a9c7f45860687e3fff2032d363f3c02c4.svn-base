package com.aiiju.store.service;

import java.util.List;

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
	
	public Boolean saveDeal(Deal deal);
	
	public Boolean updateDeal(Deal deal);

	public Result getById(Long id);
	
	public Result getDetailBySerialNum(String serialNum);

	public Result getAllByStoreId(String storeId, String operatorId,Integer currentNum,Integer pageSize,String serialNum);
	
	public Result queryDealStatistics(String storeId, String role,String queryStoreId, String operatorId,String date,String payType,String tradeType, Integer currentNum,Integer pageSize,String serialNum);
	
	
	/**
	 * 收入
	 * @param storeId
	 * @return
	 */
	public Result summarize(String storeId);
	/**
	 * 收入详情（流水）
	 * @param storeId
	 * @param type
	 * @param currentNum
	 * @param pageSize
	 * @return
	 */
	public Result getIncomeDetail(String storeId, String type, Integer currentNum, Integer pageSize);

	public Result getRefundList(String storeId, Integer currentNum, Integer pageSize);
	
	   /**
     * 获取打印列表（）
     * @return
     */
    public List<Deal> getPrintedStatusList();
    
    /**
     * 批量更新
     * @param taskList
     */
    public void updateprintBatch(List<Deal> taskList);


    /**
     * 给定的店铺，在某个时间后的全部流水
     * @param storeId 店铺的id
     * @param startDate 开始的日期
     * @param endDate  结束的日期
     * @param page   现实的第几页
     * @param pageSize 每页现实的条数
     * @return
     */
	public Result getDealByGiveDateAndStoreID(String storeId, String startDate, String endDate, String page,
			String pageSize);

}