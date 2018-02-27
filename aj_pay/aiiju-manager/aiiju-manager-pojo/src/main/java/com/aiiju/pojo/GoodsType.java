package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: GoodsType 
 * @Description: 商品类型
 * @author 小飞 
 * @date 2016年11月8日 上午10:38:20 
 *
 */
public class GoodsType implements Serializable{

	private static final long serialVersionUID = -3513193932822681995L;
	
	private Long id;
	/**
	 * 商品类型名称
	 */
	private String name;
	/**
	 * 店铺编号
	 */
	private String storeId;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date modifyDate;
	
	private List<Goods> goodsList;
	
	
	
	private String relationGoodsTypeId ;//关联id
	
	private String relationTotal;  //关联数量
	
	
	private String goodsNum;//该分类下的商品数量
	
	

	
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getRelationTotal() {
		return relationTotal;
	}

	public void setRelationTotal(String relationTotal) {
		this.relationTotal = relationTotal;
	}

	public String getRelationGoodsTypeId() {
		return relationGoodsTypeId;
	}

	public void setRelationGoodsTypeId(String relationGoodsTypeId) {
		this.relationGoodsTypeId = relationGoodsTypeId;
	}

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

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
