package com.aiiju.pojo;
/**
 * 
* @ClassName: ShopAccountUserLink 
* @Description: 账本和员工共享关系
* @author 小飞 
* @date 2017年3月20日 下午1:17:28
 */
public class ShopAccountUserLink {
    /**
     * 账本id
     */
    private Long shopAccountId;
    /**
     * 员工操作员编号
     */
    private String operatorId;

    public Long getShopAccountId() {
        return shopAccountId;
    }

    public void setShopAccountId(Long shopAccountId) {
        this.shopAccountId = shopAccountId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

}
