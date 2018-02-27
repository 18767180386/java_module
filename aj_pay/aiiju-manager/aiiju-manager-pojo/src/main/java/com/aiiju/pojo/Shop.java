package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Shop 
 * @Description: 店铺实体类
 * @author zong
 * @date 2017年6月7日 下午4:04:21 
 *
 */
public class Shop implements Serializable{

	private static final long serialVersionUID = 4139548288443858414L;
	
	private Integer id;
	
	/**
	 * 主店铺商铺编号
	 */
	private String parentStoreId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 省份编码
	 */
	private String provinceCode;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 区县编码
	 */
	private String districtCode;
	/**
	 * code对应地区名称
	 */
	private String codeName;
	/**
	 * 详细地址
	 */
	private String  addressDetail;
	/**
	 * 经度；最长15位字符（包括小数点）
	 */
	private String longitude;
	/**
	 * 纬度；最长15位字符（包括小数点）
	 */
	private String latitude;
	/**
	 * 门店电话
	 */
	private String contactNumber;
	
	/**
	 * 门店类型（0，主店铺，1直营店，2加盟店）
	 */
	private String shopType;

	/**
	 * 店铺头像
	 */
	private String imageUrl;
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
	/**
	 * 各种优惠折扣开关 1：是  2：否
	 */
	private String isDiscount;
	/**
	 * 支付宝是否签约 1:签约  2:未签约
	 */
	private Byte alipay;
	/**
	 * 微信是否签约 1:签约  2:未签约
	 */
	private Byte wx;
	/**
	 * qq钱包是否签约 1:签约  2:未签约
	 */
	private Byte qq;
	
	
	/**
	 * 账本对象
	 */
	private ShopAccount shopAccount;
	
	
	/**
	 * 积分设置对象
	 */
	private MPoints mPoints;

	private String isReputationShop;
	private int reputationShopId;
	private String modifyRelationGoods;
	private String deleteRelationGoods;
	private String manageOwnGoods;

//	private String auditStatus;
	 
	private String reviewStatus;
	private String subMsg;
	private String  resultDesc;
	
	/**
     * 0 不同步1 同步
     */
    private String isOpenSynchronize;
	

	public String getIsOpenSynchronize() {
		return isOpenSynchronize;
	}

	public void setIsOpenSynchronize(String isOpenSynchronize) {
		this.isOpenSynchronize = isOpenSynchronize;
	}


	public String getReviewStatus() {
		return reviewStatus;
	}


	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}


	public String getSubMsg() {
		return subMsg;
	}


	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}


	public String getResultDesc() {
		return resultDesc;
	}


	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}


	public String getModifyRelationGoods() {
		return modifyRelationGoods;
	}


	public void setModifyRelationGoods(String modifyRelationGoods) {
		this.modifyRelationGoods = modifyRelationGoods;
	}


	public String getDeleteRelationGoods() {
		return deleteRelationGoods;
	}


	public void setDeleteRelationGoods(String deleteRelationGoods) {
		this.deleteRelationGoods = deleteRelationGoods;
	}


	public String getManageOwnGoods() {
		return manageOwnGoods;
	}


	public void setManageOwnGoods(String manageOwnGoods) {
		this.manageOwnGoods = manageOwnGoods;
	}




	public String getIsReputationShop() {
		return isReputationShop;
	}


	public void setIsReputationShop(String isReputationShop) {
		this.isReputationShop = isReputationShop;
	}


	public int getReputationShopId() {
		return reputationShopId;
	}


	public void setReputationShopId(int reputationShopId) {
		this.reputationShopId = reputationShopId;
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


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public String getProvinceCode() {
		return provinceCode;
	}


	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}


	public String getCityCode() {
		return cityCode;
	}


	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getDistrictCode() {
		return districtCode;
	}


	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}


	public String getCodeName() {
		return codeName;
	}


	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}


	public String getAddressDetail() {
		return addressDetail;
	}


	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getShopType() {
		return shopType;
	}


	public void setShopType(String shopType) {
		this.shopType = shopType;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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


	public String getIsDiscount() {
		return isDiscount;
	}


	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}


	public Byte getAlipay() {
		return alipay;
	}


	public void setAlipay(Byte alipay) {
		this.alipay = alipay;
	}


	public Byte getWx() {
		return wx;
	}


	public void setWx(Byte wx) {
		this.wx = wx;
	}


	public Byte getQq() {
		return qq;
	}


	public void setQq(Byte qq) {
		this.qq = qq;
	}


	public ShopAccount getShopAccount() {
		return shopAccount;
	}


	public void setShopAccount(ShopAccount shopAccount) {
		this.shopAccount = shopAccount;
	}


	public MPoints getmPoints() {
		return mPoints;
	}


	public void setmPoints(MPoints mPoints) {
		this.mPoints = mPoints;
	}
	
	

	
	
	
	
}
