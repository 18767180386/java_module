package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: WxSub 
 * @Description: 微信子商户
 * @author 小飞 
 * @date 2016年12月21日 上午11:20:33 
 *
 */
public class WxSub implements Serializable{

	private static final long serialVersionUID = -1494641454206370614L;

	private Integer id;
	/**
	 * 子商户号
	 */
	private String subMchId;
	/**
	 * 子商户appid
	 */
	private String subAppId;
	/**
	 * 店铺编号
	 */
	private String storeId;
	/**
	 * 创建日期
	 */
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
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
	
}
