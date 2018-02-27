package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Item;

/**
 * 
 * @ClassName: ItemService
 * @Description: 账目Service
 * @author 小飞
 * @date 2017年3月22日 下午1:39:34
 */
public interface ItemService {

    public Result save(Item item);

    public Result update(Item item);

    public Result deleteById(String operatorId, Long id);

    public Result getById(Long id);

    /**
     * 通过账本id获取 账目列表
     * 
     * @param accountId
     * @return
     */
    public Result getAllByAccountId(Long accountId, int currentNum, int pageSize);

    /**
     * 通过日期查询账目
     * @param accountId
     * @param date
     * @param currentNum
     * @param pageSize
     * @return
     */
    public Result getPageByDate(Long accountId,String date, int currentNum, int pageSize);
}
