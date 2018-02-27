package com.aiiju.pojo;

import java.util.Date;

/** 
 * @ClassName PAShop
 * @Description
 * @author zong
 * @CreateDate 2017年7月25日 上午10:40:03
 */
public class PAShop {

	
	private Integer id;
	private String parentStoreId;
	
	private String storeId;
	private Integer merId;
	
	private String mctNo;
    /**
     * Description 门店编号
     */
    private String shopNo;

    /**
     * Description 机构门店主键（系统有唯一性校验），建议使用门店表的主键ID，防止重复添加门店
     */
    private String traId;
    private String shopName;
    private String shopFullName;
    private String cityid;
    private String address;
    private String tel;
    private String pic1;
    private String pic2;
    private String pic3;
    private String lng;
    private String lat;
    private String openHours;

    private Date createDate;
    private Date modifyDate;
    private Integer errcode;
    private String errcodeMsg;
    private String step;
    private String stepStatus;
    
    
    private String openId;
    
    private String openKey;
    
    
    
    
	public String getMctNo() {
		return mctNo;
	}
	public void setMctNo(String mctNo) {
		this.mctNo = mctNo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenKey() {
		return openKey;
	}
	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentStoreId() {
		return parentStoreId;
	}
	public void setParentStoreId(String parentStoreId) {
		this.parentStoreId = parentStoreId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
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
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrcodeMsg() {
		return errcodeMsg;
	}
	public void setErrcodeMsg(String errcodeMsg) {
		this.errcodeMsg = errcodeMsg;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getStepStatus() {
		return stepStatus;
	}
	public void setStepStatus(String stepStatus) {
		this.stepStatus = stepStatus;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getTraId() {
		return traId;
	}
	public void setTraId(String traId) {
		this.traId = traId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopFullName() {
		return shopFullName;
	}
	public void setShopFullName(String shopFullName) {
		this.shopFullName = shopFullName;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getOpenHours() {
		return openHours;
	}
	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	
}
