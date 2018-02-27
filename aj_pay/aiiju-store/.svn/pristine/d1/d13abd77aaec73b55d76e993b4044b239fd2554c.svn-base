package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.mapper.ItemMapper;
import com.aiiju.mapper.ShopAccountMapper;
import com.aiiju.mapper.UserMapper;
import com.aiiju.pojo.Item;
import com.aiiju.pojo.ShopAccount;
import com.aiiju.pojo.ShopAccountUserLink;
import com.aiiju.pojo.User;
import com.aiiju.store.service.ShopAccountService;
import com.github.pagehelper.PageHelper;

import cn.jpush.api.utils.StringUtils;

/**
 * 
 * @ClassName: ShopAccountServiceImpl
 * @Description: 自记账ServiceImpl
 * @author 小飞
 * @date 2017年3月20日 上午11:44:08
 */
@Service("shopAccountService")
public class ShopAccountServiceImpl implements ShopAccountService {

    private static Logger logger = Logger.getLogger(ShopAccountServiceImpl.class);
    @Autowired
    private ShopAccountMapper shopAccountMapper;

    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result deleteById(Long id) {
        this.shopAccountMapper.deleteById(id);
        this.shopAccountMapper.deleteAllLink(id);
        return Result.success(true);
    }

    @Override 
    public Result getById(Long id) {
        ShopAccount shopAccount = this.shopAccountMapper.getById(id);
        return Result.success(shopAccount);
    }

    @Override
    public Result save(ShopAccount shopAccount) {
        ShopAccount db = this.shopAccountMapper.getShopAccountByName(shopAccount);
        if (db != null) {
            if (!String.valueOf(db.getId()).equals(String.valueOf(shopAccount.getId()))) {
                return Result.build(400, "账本名称已存在");
            }
        }
        shopAccount.setIsDefault(Byte.valueOf("2"));
        shopAccount.setStatus(Byte.valueOf("1"));// 个人
        shopAccount.setCreateDate(new Date());
        shopAccount.setModifyDate(shopAccount.getCreateDate());
        this.shopAccountMapper.add(shopAccount);
        return Result.success(shopAccount.getId());
    }

    @Override
    public Result update(ShopAccount shopAccount) {
    	
        ShopAccount db = this.shopAccountMapper.getShopAccountByName(shopAccount);
        if (db != null) {
            if (!String.valueOf(db.getId()).equals(String.valueOf(shopAccount.getId()))) {
                return Result.build(400, "账本名称已存在");
            }
        }
        shopAccount.setModifyDate(new Date());
        this.shopAccountMapper.update(shopAccount);
        return Result.success(true);
    }

    @Override
    public Result getAllByStoreId(String storeId, String operatorId, Integer currentNum, Integer pageSize) {
        List<ShopAccount> list = null;
        String temp = operatorId.substring(operatorId.length() - 3);
        PageHelper.startPage(currentNum, pageSize);
        // 店主获取该店铺所有账本
        if (temp.equals("001")) {
            list = this.shopAccountMapper.getAllByStoreId(storeId);
        } else {
            list = this.shopAccountMapper.getShareAccounts(operatorId);
        }
        return Result.success(list);
    }

    @Override
    public Result getAccountUserList(String storeId, Long shopAccountId) {
        List<User> userList = new ArrayList<User>();
        List<User> storeUserList = this.userMapper.getAllByStoreId(storeId);
        List<User> shareUserList = this.userMapper.getShareUserList(shopAccountId);
        for (User user : storeUserList) {
            if ("1".equals(user.getRole().toString())) {
                continue;
            }
            if (shareUserList == null || shareUserList.size() == 0) {
                user.setShareStatus("2");//未共享
            } else {
                for (User u : shareUserList) {
                    if (String.valueOf(user.getId()).equals(String.valueOf(u.getId()))) {
                        user.setShareStatus("1");//共享
                    } else {
                        user.setShareStatus("2");//未共享
                    }
                }
            }
            userList.add(user);
        }
        return Result.success(userList);
    }

    @Override
    public Result saveLink(Long id, String operatorId) {
        List<User> shareUserList = this.userMapper.getShareUserList(id);
        if (shareUserList != null && shareUserList.size() > 0) {
            for (User user : shareUserList) {
                if (user.getOperatorId().equals(operatorId)) {
                    return Result.build(401, "该账本已对该员工开放共享");
                }
            }
        }
        ShopAccountUserLink link = new ShopAccountUserLink();
        link.setShopAccountId(id);
        link.setOperatorId(operatorId);
        this.shopAccountMapper.addLink(link);
        return Result.success(true);
    }

    @Override
    public Result deleteLink(Long id, String operatorId) {
        List<User> shareUserList = this.userMapper.getShareUserList(id);
        if (shareUserList != null && shareUserList.size() > 0) {
            boolean flag = false;
            for (User user : shareUserList) {
                if (user.getOperatorId().equals(operatorId)) {
                    flag = true;
                }
            }
            if (flag) {
                ShopAccountUserLink link = new ShopAccountUserLink();
                link.setShopAccountId(id);
                link.setOperatorId(operatorId);
                this.shopAccountMapper.deleteLink(link);
            }else {
                return Result.build(401, "该账本未对该员工共享,无需此操作");
            }
        }
        return Result.success(true);
    }

    @Override
    public Result updateBudget(Long id, Byte budgetType, String budgetMoney) {
        ShopAccount account = new ShopAccount();
        account.setId(id);
        account.setBudgetType(budgetType);
        account.setBudgetMoney(new BigDecimal(budgetMoney));
        account.setSurplusMoney(account.getBudgetMoney());
        this.shopAccountMapper.updateBudget(account);
        return Result.success(true);
    }



	@Override
	public Result setDefault(ShopAccount shopAccount) {

		 this.shopAccountMapper.updateDefalutByShopId(shopAccount);
		 this.shopAccountMapper.setDefalutById(shopAccount);
	    return Result.success(shopAccount);
	}

	@Override
	public Result setSurplusById(Long id) {
		logger.info("设置及更新预算剩余金额方法");
		
		BigDecimal returnSurplus = null;
		/**
		 * 1,获取账目信息；预算金额类型、预算总金额、根据预算金额的类型进行统计（默认为月）
		 */
   	 ShopAccount shopAccount = this.shopAccountMapper.getById(id);
   	 Byte budgetTypeDB =  shopAccount.getBudgetType();  //1:年 2：季 3：月 4：日

   	 if(budgetTypeDB==null||StringUtils.isEmpty(String.valueOf(budgetTypeDB))){
   		 budgetTypeDB=3;
   	 }
   	 
   	 Long accountId = shopAccount.getId();
   	 
   	 BigDecimal budgetDB =  shopAccount.getBudgetMoney();//用户设置的预算金额,  用户可能未设置
   	 
   	 Map<String, Object> params = new HashMap<>();
   	 
   	 Item item = new  Item();
   	 // 按日统计剩余预算金额
   	 if(4==budgetTypeDB){
   		logger.info("按日统计剩余预算金额");
   		 // 当前日期
   	    String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        params.put("accountId", accountId);
        params.put("date", date);
        params.put("type", (byte)2);
         item = this.itemMapper.getSurplusGroupByDay(params);

   	 }
   	 
   	 
  	 // 按月统计剩余预算金额
   	 if(3==budgetTypeDB){
   		logger.info("按月统计剩余预算金额");
		 // 当前月份
    	 String date = DateUtil.formatDate(new Date(), "yyyy-MM");
         params.put("accountId", accountId);
         params.put("date", date);
         params.put("type", (byte)2);
         item = this.itemMapper.getSurplusGroupByMonth(params);
 
   	 }
   	 
	 // 按季度统计剩余预算金额
   	 if(2==budgetTypeDB){
   		logger.info("按季度统计剩余预算金额");
		 // 当前月份
    	 String date = DateUtil.formatDate(new Date(), "M");
    	 
    	String quarter =  ""+(Integer.valueOf(date)+2)/3;
    	 
         params.put("accountId", accountId);
         params.put("date", quarter);
         params.put("type", (byte)2);
         
          item = this.itemMapper.getSurplusGroupByQuarter(params);

   	 }
	 // 按年统计剩余预算金额
   	 if(1==budgetTypeDB){
   		logger.info("按年统计剩余预算金额");
		 // 当前月份
    	 String date = DateUtil.formatDate(new Date(), "yyyy");
         params.put("accountId", accountId);
         params.put("date", date);
         params.put("type", (byte)2);
          item = this.itemMapper.getSurplusGroupByYear(params);

   	 }
   	 
     if(budgetDB!=null){
     	
     	if(item!=null){
     		returnSurplus =  budgetDB.subtract(item.getMoney());
     	}else{
     		returnSurplus= budgetDB;
     	}
    // 	System.out.println("returnSurplus="+returnSurplus);
    	shopAccount.setSurplusMoney(returnSurplus);
     
     }
     if(returnSurplus==null){
    	 
     }else{
    	    updateSurplusById(shopAccount);
     }
 
     
   	 // 查询item中的 accountId是accountId的支出account_type=2 金额 （按budgetTypeDB的类型统计）
     return Result.success(shopAccount);
	
	}

	@Override
	public Result updateSurplusById(ShopAccount account) {
		shopAccountMapper.updateSurplus(account);
		   return Result.success(true);
	}

}
