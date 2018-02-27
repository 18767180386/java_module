package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Item;

public interface ItemMapper {

    public void add(Item item);
    
    public void update(Item item);
    
    public void deleteById(Long id);
    
    public Item getById(Long id);
    
    public List<Item> getPageByAccountId(Map<String, Object> params);

    public List<Item> getPageByDate(Map<String, Object> params);
    
    /**
     * 获取某账本每天收入和支出
     * @param params
     * @return
     */
    public List<Item> getAllItemDaySum(Long accountId);
    
    
    /**
     * 获取账本当月收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemSum(Map<String, Object> params);
    
    
    
    /**
     * 获取账本当日收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemDaySum(Map<String, Object> params);
    
    //--------20170404开始-----------------------
    
    /**
     * 获取账本当天收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemSumGroupByDay(Map<String, Object> params);
    public Item getSurplusGroupByDay(Map<String, Object> params);
    /**
     * 获取账本当月收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemSumGroupByMonth(Map<String, Object> params);
    public Item getSurplusGroupByMonth(Map<String, Object> params);
    /**
     * 获取账本当季度天收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemSumGroupByQuarter(Map<String, Object> params);
    public Item getSurplusGroupByQuarter(Map<String, Object> params);
    /**
     * 获取账本当年收入和支出
     * @param params
     * @return
     */
    public List<Item> getItemSumGroupByYear(Map<String, Object> params);
    public Item getSurplusGroupByYear(Map<String, Object> params);
    
    //--------20170404结束-----------------------
}
