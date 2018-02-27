package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.ShopAccount;
import com.aiiju.pojo.ShopAccountUserLink;
/**
 * 
* @ClassName: ShopAccountMapper 
* @Description: 自记账Mapper
* @author 小飞 
* @date 2017年3月20日 上午11:41:17
 */
public interface ShopAccountMapper {

    public void add(ShopAccount shopAccount);
    
    public void update(ShopAccount shopAccount);
    
    public void deleteById(Long id);
    
    
     
    
    /**
     * 通过id获取
     * @param id
     * @return
     */
    public ShopAccount getById(Long id);
    
    public List<ShopAccount> getAllByStoreId(String storeId);

    public ShopAccount getShopAccountByName(ShopAccount shopaccount);
    /**
     * 账本关联员工
     * @param link
     */
    public void addLink(ShopAccountUserLink link);
    /**
     * 员工查看共享的账本
     * @param operatorId
     * @return
     */
    public List<ShopAccount> getShareAccounts(String operatorId);
    /**
     * 取消账本共享
     * @param link
     */
    public void deleteLink(ShopAccountUserLink link);
    /**
     * 取消该账本下的所有共享员工
     * @param id
     */
    public void deleteAllLink(Long id);
    /**
     * 修改预算
     * @param shopAccount
     */
    public void updateBudget(ShopAccount shopAccount);
    
  
    public void updateDefalutByShopId(ShopAccount shopAccount);

    public void setDefalutById(ShopAccount shopAccount);
 
    /**
     * 更新预算余额
     * @param shopAccount
     */
    public void updateSurplus(ShopAccount shopAccount);
    
	public ShopAccount getIdByStoreIdAndIsDefault(String storeId);
    
}
