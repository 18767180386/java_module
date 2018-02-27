package com.aiiju.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: Category
 * @Description: 类目 实体类
 * @author 小飞
 * @date 2017年3月22日 上午9:41:26
 */
public class Category implements Serializable {

    private static final long serialVersionUID = -2354555096804593817L;

    private Long id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 店铺编号
     */
    private String storeId;

    /**
     * 图片类别
     */
    private Byte imageType;

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

    public Byte getImageType() {
        return imageType;
    }

    public void setImageType(Byte imageType) {
        this.imageType = imageType;
    }

}
