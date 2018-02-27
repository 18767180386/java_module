package com.aiiju.pojo;

import java.util.Date;

/** 
 * @ClassName ReputationShopImages
 * @Description 口碑店铺图片
 * @author zong
 * @CreateDate 2017年5月31日 上午11:06:44
 */
public class ReputationShopImages {

	
	private int id;
	private String field_remark;
	private String field_name;
	private String image_name;
	private String image_type;
	private String image_url;
	private String image_pid;
	private String store_id;
	private String reputation_image_id;
	private String reputation_image_url;
	private String status;
	private String remark;
	private Date create_date;
	
	private Date modify_date;
	
	
	
	public String getField_remark() {
		return field_remark;
	}
	public void setField_remark(String field_remark) {
		this.field_remark = field_remark;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_pid() {
		return image_pid;
	}
	public void setImage_pid(String image_pid) {
		this.image_pid = image_pid;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getReputation_image_id() {
		return reputation_image_id;
	}
	public void setReputation_image_id(String reputation_image_id) {
		this.reputation_image_id = reputation_image_id;
	}
	public String getReputation_image_url() {
		return reputation_image_url;
	}
	public void setReputation_image_url(String reputation_image_url) {
		this.reputation_image_url = reputation_image_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
