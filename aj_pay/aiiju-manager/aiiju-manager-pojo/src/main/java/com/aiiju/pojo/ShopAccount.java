package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: ShopAccount
 * @Description: 账本实体类
 * @author 小飞
 * @date 2017年3月20日 上午11:07:34
 */
public class ShopAccount implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 账本名称
     */
    private String name;
    /**
     * 店铺编号
     */
    private String storeId;

    /**
     * 是否为默认账本 1:是 2：否（默认值）
     */
    private Byte isDefault;

    /**
     * 状态 1:个人（默认值） 2：共享中
     */
    private Byte status;

    /**
     * 账本颜色
     */
    private Byte color;
    /**
     * 预算类型 1:年 2：季 3：月 4：日
     */
    private Byte budgetType;
    /**
     * 预算金额
     */
    private BigDecimal budgetMoney;
    /**
     * 剩余预算金额
     */
    private BigDecimal surplusMoney;
    
    /**
     * 共享账本的员工
     */
    private List<User> userList;
    
    private Date createDate;
    
    private Date modifyDate;
    
    
    private String operatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getColor() {
        return color;
    }

    public void setColor(Byte color) {
        this.color = color;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Byte getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(Byte budgetType) {
        this.budgetType = budgetType;
    }

    public BigDecimal getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(BigDecimal budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public BigDecimal getSurplusMoney() {
        return surplusMoney;
    }

    public void setSurplusMoney(BigDecimal surplusMoney) {
        this.surplusMoney = surplusMoney;
    }

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
    
    
}
