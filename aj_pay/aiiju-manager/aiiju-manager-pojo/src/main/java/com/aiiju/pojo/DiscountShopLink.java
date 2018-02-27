package com.aiiju.pojo;
/**
 * 
 * @ClassName: DiscountShopLink 
 * @Description: 优惠折扣 店铺 中间表对象
 * @author 小飞 
 * @date 2016年11月15日 下午3:30:20 
 *
 */
public class DiscountShopLink {
	/**
	 * 优惠折扣id
	 */
	private Integer discountId;
	/**
	 * 店铺编号
	 */
	private String storeId;

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
