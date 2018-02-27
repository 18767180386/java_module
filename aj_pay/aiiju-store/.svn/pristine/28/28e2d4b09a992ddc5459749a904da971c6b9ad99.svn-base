package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.ShopAccount;
/**
 * 
* @ClassName: ShopAccountService 
* @Description: 自记账Service
* @author 小飞 
* @date 2017年3月20日 上午11:42:39
 */
public interface ShopAccountService {

    public Result deleteById(Long id);
    
    public Result getById(Long id);
    
    public Result save(ShopAccount shopAccount);
    
    public Result update(ShopAccount shopAccount);
    
    public Result getAllByStoreId(String storeId,String operatorId,Integer currentNum,Integer pageSize);
    /**
     * 保存账本和员工共享关系
     * @param id
     * @param userId
     * @return
     */
    public Result saveLink(Long id, String operatorId);
    /**
     * 取消账本共享
     * @param id
     * @param operatorId
     * @return
     */
    public Result deleteLink(Long id, String operatorId);
    /**
     * 获取被共享的员工列表
     * @param storeId
     * @param shopAccountId
     * @return
     */
    public Result getAccountUserList(String storeId, Long shopAccountId);
    /**
     * 修改预算
     * @param id
     * @param budgetType
     * @param budgetMoney
     * @return
     */
    public Result updateBudget(Long id, Byte budgetType, String budgetMoney);

    /**
     * 设置默认账本
     * @param id
     * @return
     */
    public Result setDefault(ShopAccount shopAccount);
    
    /**
     * 设置预算余额
     * @param id
     * @return
     */
    public Result setSurplusById(Long id);
    
    
    /**
     * 更新预算余额
     * @param id
     * @return
     */
    public Result updateSurplusById(ShopAccount shopAccount);
}
