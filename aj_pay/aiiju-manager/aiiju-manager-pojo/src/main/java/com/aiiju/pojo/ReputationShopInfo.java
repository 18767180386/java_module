package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/** 
 * @ClassName ReputationShopInfo
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:03:20
 */
public class ReputationShopInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer id;
	private String store_id;
	private String category_id;
	private String brand_name;
	private String brand_logo;
	private String main_shop_name;
	private String branch_shop_name;
	private String province_code;
	private String city_code;
	private String district_code;
	
	private String code_name;
	private String category_name;
	
	private String address;
	private String longitude;
	private String latitude;
	private String contact_number;
	private String notify_mobile;
	private String main_image;
	private String audit_images;
	private String business_time;
	private String wifi;
	private String parking;
	private String value_added;
	private String avg_price;
	private String isv_uid ="2088801006306668";
	private String licence;
	private String licence_code;
	private String licence_name;
	private String business_certificate;
	private String auth_letter;
	private String is_operating_online;
	private String online_url;
	private String operate_notify_url;
	private String implement_id;
	private String no_smoking;
	private String box;
	private String request_id =UUID.randomUUID().toString().replaceAll("-", "");
	private String other_authorization;
	private String licence_expires;
	private String op_role ="ISV";
	private String biz_version ="2.0";
	private String code;
	private String msg;
	private String sub_code;
	private String sub_msg;
	private String business_certificate_expires;
	
	private String audit_status;
	private String result_code;
	private String result_desc;
	
	private String apply_id;
	private Date create_date = new Date();
	private Date modify_date =new Date();
	private Date notify_time =new Date();
	
	private String review_status;
	private String app_auth_token;
	
	
	private String parent_store_id;
	private String shop_type;
	
	
	
	
	
	public String getShop_type() {
		return shop_type;
	}
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}

	
	
	public String getParent_store_id() {
		return parent_store_id;
	}
	public void setParent_store_id(String parent_store_id) {
		this.parent_store_id = parent_store_id;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Date getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}
	public String getBusiness_certificate_expires() {
		return business_certificate_expires;
	}
	public void setBusiness_certificate_expires(String business_certificate_expires) {
		this.business_certificate_expires = business_certificate_expires;
	}
	public String getResult_desc() {
		return result_desc;
	}
	public void setResult_desc(String result_desc) {
		this.result_desc = result_desc;
	}
	public String getApp_auth_token() {
		return app_auth_token;
	}
	public void setApp_auth_token(String app_auth_token) {
		this.app_auth_token = app_auth_token;
	}
	public String getReview_status() {
		return review_status;
	}
	public void setReview_status(String review_status) {
		this.review_status = review_status;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public String getSub_msg() {
		return sub_msg;
	}
	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBrand_logo() {
		return brand_logo;
	}
	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}
	public String getMain_shop_name() {
		return main_shop_name;
	}
	public void setMain_shop_name(String main_shop_name) {
		this.main_shop_name = main_shop_name;
	}
	public String getBranch_shop_name() {
		return branch_shop_name;
	}
	public void setBranch_shop_name(String branch_shop_name) {
		this.branch_shop_name = branch_shop_name;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getNotify_mobile() {
		return notify_mobile;
	}
	public void setNotify_mobile(String notify_mobile) {
		this.notify_mobile = notify_mobile;
	}
	public String getMain_image() {
		return main_image;
	}
	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}
	public String getAudit_images() {
		return audit_images;
	}
	public void setAudit_images(String audit_images) {
		this.audit_images = audit_images;
	}
	public String getBusiness_time() {
		return business_time;
	}
	public void setBusiness_time(String business_time) {
		this.business_time = business_time;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getValue_added() {
		return value_added;
	}
	public void setValue_added(String value_added) {
		this.value_added = value_added;
	}
	public String getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(String avg_price) {
		this.avg_price = avg_price;
	}
	public String getIsv_uid() {
		return isv_uid;
	}
	public void setIsv_uid(String isv_uid) {
		this.isv_uid = isv_uid;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getLicence_code() {
		return licence_code;
	}
	public void setLicence_code(String licence_code) {
		this.licence_code = licence_code;
	}
	public String getLicence_name() {
		return licence_name;
	}
	public void setLicence_name(String licence_name) {
		this.licence_name = licence_name;
	}
	public String getBusiness_certificate() {
		return business_certificate;
	}
	public void setBusiness_certificate(String business_certificate) {
		this.business_certificate = business_certificate;
	}
	public String getAuth_letter() {
		return auth_letter;
	}
	public void setAuth_letter(String auth_letter) {
		this.auth_letter = auth_letter;
	}
	public String getIs_operating_online() {
		return is_operating_online;
	}
	public void setIs_operating_online(String is_operating_online) {
		this.is_operating_online = is_operating_online;
	}
	public String getOnline_url() {
		return online_url;
	}
	public void setOnline_url(String online_url) {
		this.online_url = online_url;
	}
	public String getOperate_notify_url() {
		return operate_notify_url;
	}
	public void setOperate_notify_url(String operate_notify_url) {
		this.operate_notify_url = operate_notify_url;
	}
	public String getImplement_id() {
		return implement_id;
	}
	public void setImplement_id(String implement_id) {
		this.implement_id = implement_id;
	}
	public String getNo_smoking() {
		return no_smoking;
	}
	public void setNo_smoking(String no_smoking) {
		this.no_smoking = no_smoking;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {

		
		this.request_id = request_id;
	}
	public String getOther_authorization() {
		return other_authorization;
	}
	public void setOther_authorization(String other_authorization) {
		this.other_authorization = other_authorization;
	}
	public String getLicence_expires() {
		return licence_expires;
	}
	public void setLicence_expires(String licence_expires) {
		this.licence_expires = licence_expires;
	}
	public String getOp_role() {
		return op_role;
	}
	public void setOp_role(String op_role) {
		this.op_role = op_role;
	}
	public String getBiz_version() {
		return biz_version;
	}
	public void setBiz_version(String biz_version) {
		this.biz_version = biz_version;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getApply_id() {
		return apply_id;
	}
	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}


	
	
}
