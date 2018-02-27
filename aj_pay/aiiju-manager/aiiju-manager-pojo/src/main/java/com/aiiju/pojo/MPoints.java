package com.aiiju.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: MPoints
 * @Description: 会员积分
 * @author 宗介
 * @date 2017年5月6日 上午11:17:21
 *
 */
public class MPoints implements Serializable {

    private static final long serialVersionUID = 2264899787642344255L;

    private Long id;

    /**
     * 获取方式
     * '顾客每消费1元换取1积分'
     */
    private String getType;
    
    /**
     * 使用限制
     * '当次累积，下次可用'
     */
    private String useLimit;
    
    /**
     * 积分价值
     */
    private Integer pointsWorth;
    
    /**
     * 使用方式
     */
    private Integer useType;
    
    /**
     *商铺 编号
     */
    private String storeId;  
    
    /**
     * 开关
     */
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType;
	}

	public String getUseLimit() {
		return useLimit;
	}

	public void setUseLimit(String useLimit) {
		this.useLimit = useLimit;
	}

	public Integer getPointsWorth() {
		return pointsWorth;
	}

	public void setPointsWorth(Integer pointsWorth) {
		this.pointsWorth = pointsWorth;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}



	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}  
    
    
    
}
