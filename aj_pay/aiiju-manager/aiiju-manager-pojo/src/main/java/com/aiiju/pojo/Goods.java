package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: Goods
 * @Description: 商品
 * @author 小飞
 * @date 2016年11月8日 上午10:42:31
 *
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 4181356010854334957L;

    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品条码
     */
    private String code;

    /**
     * 商品分类
     */
    private Long goodsTypeId;
    
    private String goodsTypeName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 单位 件，500克，千克，两，斤
     */
    private String unit;

    /**
     * 图片url
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private String storeId;

    private GoodsType goodsType;

    private String isDel;  //删除状态 0。未删；1.已删

    private String relationGoodsId;
    
    private String inventory="0";
    
    private Date modifyInventoryDate; //更新库存时间
    
    private String isRelation ="0";  //是否关联， 只作传递参数，数据库无此字段
    
    private String surplus ="0";  //库存剩余， 只作传递参数，数据库无此字段
    
    private String yesterdayNum ="0";  //昨日销售数量 只作传递参数，数据库无此字段,默认为0
    
    
    
    
    

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getModifyInventoryDate() {
		return modifyInventoryDate;
	}

	public void setModifyInventoryDate(Date modifyInventoryDate) {
		this.modifyInventoryDate = modifyInventoryDate;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	public String getYesterdayNum() {
		return yesterdayNum;
	}

	public void setYesterdayNum(String yesterdayNum) {
		this.yesterdayNum = yesterdayNum;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(String isRelation) {
		this.isRelation = isRelation;
	}

	public String getRelationGoodsId() {
		return relationGoodsId;
	}

	public void setRelationGoodsId(String relationGoodsId) {
		this.relationGoodsId = relationGoodsId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
