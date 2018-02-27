package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
* @ClassName: Item 
* @Description: 账目
* @author 小飞 
* @date 2017年3月22日 下午12:00:24
 */
public class Item implements Serializable{

    private static final long serialVersionUID = -6746798375007862423L;
    
    private Long id;
    /**
     * 账本id
     */
    private Long accountId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 账目类型 1：收入 2：支出
     */
    private Byte type;
    /**
     * 账户类型 0：现金 1：支付宝 2：微信
     */
    private Byte accountType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 记录者（操作员名称）
     */
    private String operatorName;
    /**
     * 操作员编号
     */
    private String operatorId;
    
    private Byte imageType;
    /**
     * 用于日期查询
     */
    private Date date;
    
    private Date createDate;

    private Date modifyDate;
    
    private String storeId;
    
    
    
    
    public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public Byte getImageType() {
		return imageType;
	}

	public void setImageType(Byte imageType) {
		this.imageType = imageType;
	}

 
    
}
