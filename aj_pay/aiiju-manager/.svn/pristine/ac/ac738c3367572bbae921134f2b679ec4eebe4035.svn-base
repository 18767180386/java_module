package com.aiiju.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Deal;
import com.aiiju.pojo.MCard;
/**
 * 
 * @ClassName: DealMapper 
 * @Description: 交易 Mapper
 * @author 小飞 
 * @date 2016年11月14日 下午1:24:42 
 *
 */
public interface DealMapper {

	public void add(Deal deal);
	
	public void update(Deal deal);
	
	public void updateOldDeal(Deal deal);
	
	public void updateprint(Deal deal);
	
	public void updateprintBatch(List<Deal> dealList);
	
	public Integer getCurrentPrintNumBySerialNum(Deal deal);
	
	public Deal getById(Long id);
	
	public Deal getDetailBySerialNum(String serialNum);
	
	public Deal getBySerialNum(String serialNum);

	public List<Deal> getPrintedStatus();
	
    public List<Map<String,Object>> getPageByStoreId(Map<String, Object> params);	
    
    public String getPageByStoreId_dayTotal(Map<String, Object> params);	
    public String getRefundByPage_dayTotal(Map<String, Object> params);	
    
    public String queryDealStatistics_dayTotal(Map<String, Object> params);	
    
	/**
	 * 总收入
	 * @param storeId
	 * @return
	 */
	public BigDecimal getTotalIncome(String storeId);
	/**
	 *  通过时间区间统计收入
	 * @param  storeId  start  end 三个参数
	 * @return
	 */
	public BigDecimal getSummarize(Map<String, String> params);
    /**
     *  统计今天收入
     * @param  storeId  today 2个参数
     * @return
     */
	public BigDecimal getSummarizeByToday(Map<String, String> params);

	public List<Deal> getSummarizeByType(Map<String, String> params);
	
	/**
	 * 获取 收入流水
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getPageByToday(Map<String, Object> params);

	public List<Map<String, Object>> getPageByDay(Map<String, Object> params);

	public List<Map<String, Object>> getPageByType(Map<String, Object> params);
	
	public List<Map<String, Object>> getPAPageByType(Map<String, Object> params);
	
	public String getPageByType_dayTotal(Map<String, Object> params);
	

	public void addRefund(Deal deal);
	/**
	 * 退款统计
	 * @param params
	 * @return
	 */
	public BigDecimal getRefundSummarize(Map<String, String> params);

	public List<Map<String, Object>> getRefundByPage(Map<String, Object> params);
    public List<Map<String,Object>> queryDealStatistics(Map<String,Object> map);
    
    
    public String queryDealTotalMoney(Map<String,Object> map);
    public String queryDealPayCount(Map<String,Object> map);
    public String queryDealRefundCount(Map<String,Object> map);

	/**
	 * 通过给定的条件 查询信息 （getPageByType进行修改的）
	 * 
	 * @param params
	 * @return
	 */
	public List<Deal> getDealByGiveDateAndStoreID(Map<String, Object> params);
	
	
	
    public List<Map<String,Object>> queryDealStatisticsBySerialNum(Map<String,Object> map);
    
    public String queryDealTotalMoneyBySerialNum(Map<String,Object> map);
    public String queryDealPayCountBySerialNum(Map<String,Object> map);
    public String queryDealRefundCountBySerialNum(Map<String,Object> map);

    
    
	/**
	 * 商铺中个人时间段总收入
	 * 
	 * map 中 有String storeId,String operatorId ,String startTime,String endTime,
	 * String tradeType  交易类型（1，收入，2退出） ,String payType 支付类型
	 *  如果不传时间，则查询该人所有营业额（收入减支出）
	 * @param storeId
	 * @return
	 */
	public String getOnePersonTotalIncome(Map<String,String> map);

}
