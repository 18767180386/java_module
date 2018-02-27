package com.aiiju.pojo;

import java.util.Date;

/** 
 * @ClassName ImagesResource
 * @Description 图片资源
 * @author zong
 * @CreateDate 2017年5月31日 上午11:06:44
 */
public class ImageResource {

	
	private int id;
	private String used_detail;
	private String image_name;
	private String image_type;
	private String image_url;
	private String store_id;
	private String status;
	private String remark;
	private Date create_date;
	
	
	
	
	

	/**
	 * @param used_detail
	 * @param image_name
	 * @param image_type
	 * @param image_url
	 * @param store_id
	 * @param status
	 * @param remark
	 * @param create_date
	 */
	public ImageResource(String used_detail, String image_name, String image_type, String image_url, String store_id,
			String status, String remark, Date create_date) {
		super();
		this.used_detail = used_detail;
		this.image_name = image_name;
		this.image_type = image_type;
		this.image_url = image_url;
		this.store_id = store_id;
		this.status = status;
		this.remark = remark;
		this.create_date = create_date;
	}
	public String getUsed_detail() {
		return used_detail;
	}
	public void setUsed_detail(String used_detail) {
		this.used_detail = used_detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	

	


	
}
