package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: Discount 
 * @Description: 优惠折扣
 * @author 小飞 
 * @date 2016年11月14日 下午1:11:12 
 *
 */
public class Discount implements Serializable{

	private static final long serialVersionUID = 5540003777136303047L;

	private Integer id;
	/**
	 * 优惠值
	 */
	private BigDecimal value;
	/**
	 * 优惠方式 1:整单折扣  2:整单减价，3：满减，4满折
	 */
	private Byte type;
	
	/**
	 * 状态 1：不可删除   2：可删除
	 */
	private Byte status;


	
	/**
	 * 满折或满减的 条件
	 */
	private BigDecimal condition;
	

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getCondition() {
		return condition;
	}

	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
