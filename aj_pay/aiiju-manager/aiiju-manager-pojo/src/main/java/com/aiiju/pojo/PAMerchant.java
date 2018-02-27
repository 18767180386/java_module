package com.aiiju.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/** 
 * @ClassName Merchant
 * @Description 商户实体类（平安银行）
 * @author zong
 * @CreateDate 2017年7月14日 下午1:31:24
 */
public class PAMerchant {

	/**
     * @Field mctNo
     * @Description 主键id
     */
	 private Integer id;
	
	    
	    /**
	     * @Description 商铺编号（备注：我们自己的商铺编号）
	     */
	    private String storeId;
	    
	    /**
	     * @Description 主店商铺编号（备注：我们自己的商铺编号）
	     */
	    private String parentStoreId;
	 
    /**
     * @Field mctNo
     * @Description 商户编号   （备注：新增后，由平安银行返回，反写数据）
     */
    private String mctNo;

    /**
     * @Field traId
     * @Description 机构商户主键（系统有唯一性校验），建议使用商户表的主键ID，防止重复添加商户  （备注：即本店的storeId）
     */
    private String traId;

    /**
     * @Field mctName
     * @Description 商户名称 （备注：需同营业执照名称一致）
     */
    private String mctName;

    /**
     * @Field brandName
     * @Description 招牌名称
     */
    private String brandName;

    /**
     * @Field cityId
     * @Description 城市编号
     */
    private String cityid;

    /**
     * @Field address
     * @Description 详细地址，不含省市区县名称
     */
    private String address;

    /**
     * @Field tel
     * @Description 联系电话
     */
    private String tel;

    /**
     * @Field openHours
     * @Description 营业时间，多个以小写逗号分开  (9:00-12:00,13:00-18:00)
     */
    private String openHours;

    /**
     * @Field clsId
     * @Description 行业分类编号列表（第一个分类编号为主分类，后面的为二级分类）
     */
    private String clsid;
    
    
    /**
     * @Field clsidName
     * @Description 行业分类编号对应名称
     */
    private String clsidName;
    
    
    
    /**
     * @Field clsidList
     * @Description  与银行接口传此参数
     */
    private List<String> clsidList;
    
    

    
    
    /**
     * @Field bossName
     * @Description 法人姓名
     */
    private String bossName;

    /**
     * @Field bossSex
     * @Description 法人性别（1男，2女）
     */
    private String bossSex;

    /**
     * @Field bossIdCountry
     * @Description 法人证件国别/地区（中国CHN，香港HKG，澳门MAC，台湾CTN）
     */
    private String bossIdCountry;

    /**
     * @Field bossIdType
     * @Description 法人证件类型（1居民身份证,2临时居民身份证,3居民户口簿,4护照,5港澳居民来往内地通行证,6回乡证,7军人证,8武警身份证,9其他法定文件）
     */
    private String bossIdType;

    /**
     * @Field bossSdate
     * @Description 法人证件生效时间（yyyy-mm-dd）
     */
    private String bossSdate;

    /**
     * @Field bossEdate
     * @Description 法人证件过期时间（yyyy-mm-dd）
     */
    private String bossEdate;

    /**
     * @Field bossIdNo
     * @Description 法人证件号码
     */
    private String bossIdNo;

    /**
     * @Field bossTel
     * @Description 法人电话
     */
    private String bossTel;

    /**
     * @Field bossEmail
     * @Description 法人邮箱
     */
    private String bossEmail;

    /**
     * @Field bossJob
     * @Description 法人职业
     */
    private String bossJob;

    /**
     * @Field bossAddress
     * @Description 法人住址
     */
    private String bossAddress;

    /**
     * @Field bossPositive
     * @Description 法人身份证正面【私密区】（系统返回的图片路径）
     */
    private String bossPositive;

    /**
     * @Field bossBack
     * @Description 法人身份证背面【私密区】（系统返回的图片路径）
     */
    private String bossBack;

    /**
     * @Field blType
     * @Description 营业执照类型（1三证合一，2非三证合一）
     */
    private String blType;

    /**
     * @Field blSdate
     * @Description 营业执照生效时间（yyyy-mm-dd）
     */
    private String blSdate;

    /**
     * @Field blEdate
     * @Description 营业执照过期时间（yyyy-mm-dd）
     */
    private String blEdate;

    /**
     * @Field blNo
     * @Description 营业执照编号（系统有唯一性校验）
     */
    private String blNo;

    /**
     * @Field blPic
     * @Description 营业执照图片【私密区】（系统返回的图片路径）
     */
    private String blPic;

    /**
     * @Field bankName
     * @Description 开户行名称
     */
    private String bankName;

    /**
     * @Field accountName
     * @Description 银行户名
     */
    private String accountName;

    /**
     * @Field accountNo
     * @Description 银行账号
     */
    private String accountNo;

    /**
     * @Field accountType
     * @Description 结算账户类型（2对私，1对公）
     */
    private String accountType;

    /**
     * @Field bankNo
     * @Description 清算联行号，开户行行号
     */
    private String bankNo;

    /**
     * @Field accountBoss
     * @Description 结算账户人身份（1法人，2法人亲属），结算帐户为对私时必填
     */
    private String accountBoss;

    /**
     * @Field accountIdType
     * @Description 法人亲属证件类型（1居民身份证,2临时居民身份证,3居民户口簿,4护照,5港澳居民来往内地通行证,6回乡证,7军人证,8武警身份证,9其他法定文件）结算账户人身份为法人亲属时必填
     */
    private String accountIdType;

    /**
     * @Field accountIdNo
     * @Description 法人亲属证件号码
     */
    private String accountIdNo;

    /**
     * @Field intro
     * @Description 商户简介
     */
    private String intro;

    /**
     * @Field admName
     * @Description 客户经理姓名，必须为系统后台的管理员真实姓名
     */
    private String admName;

    
    private String admId;

    
    /**
     * @Field occNo
     * @Description 组织机构代码证号
     */
    private String occNo;

    /**
     * @Field occSdate
     * @Description 组织机构代码证生效时间（yyyy-mm-dd）
     */
    private String occSdate;

    /**
     * @Field occEdate
     * @Description 组织机构代码证过期时间（yyyy-mm-dd）
     */
    private String occEdate;

    /**
     * @Field occPic 组织机构代码证图片【私密区】
     * @Description
     */
    private String occPic;

    /**
     * @Field trcNo
     * @Description 税务登记证号
     */
    private String trcNo;

    /**
     * @Field trcSdate
     * @Description 税务登记证生效时间（yyyy-mm-dd）
     */
    private String trcSdate;

    /**
     * @Field trcEdate
     * @Description 税务登记证过期时间（yyyy-mm-dd）
     */
    private String trcEdate;

    /**
     * @Field trcPic
     * @Description 税务登记证图片【私密区】
     */
    private String trcPic;

    /**
     * @Field tag
     * @Description 商户标记，自定义参数
     */
    private String tag;

    /**
     * @Field financialContact
     * @Description 财务联系人
     */
    private String financialContact;

    /**
     * @Field financialTel
     * @Description 财务联系人电话
     */
    private String financialTel;

    /**
     * @Field logo
     * @Description 商户logo【公共区】
     */
    private String logo;

    /**
     * @Field licencePic
     * @Description 许可证图片【私密区】
     */
    private String licencePic;

    /**
     * @Field licencePic2
     * @Description 授权文件【私密区】
     */
    private String licencePic2;

    /**
     * @Field otherPic1
     * @Description 其他资料1
     */
    private String otherPic1;

    /**
     * @Field otherPic2
     * @Description 其他资料2
     */
    private String otherPic2;

    /**
     * @Field otherPic3
     * @Description 其他资料3
     */
    private String otherPic3;

    /**
     * @Field otherPic4
     * @Description 其他资料4
     */
    private String otherPic4;

    /**
     * @Description 创建时间
     */
    private Date createDate;
    
    /**
     * @Description 修改时间
     */
    private Date modifyDate;
    
    /**
     * @Description 签约返回错误码（0为成功，其他不成功）
     */
    private Integer errcode;
    
    /**
     * @Description 签约返回信息（也是步骤的错误信息）
     */
    private String errcodeMsg;
    
    /**
     * @Description 签约已完成步骤（0：默认值，1提交签约信息；2提审签约信息；3审核签约信息）
     */
    private String step;
    
    /**
     * @Description 步骤的状态（1，成功；0失败）
     */
    private String stepStatus;
    
    /**
     * @Description 地图经度
     */
    private String lng;
    /**
     * @Description 地图纬度
     */
    private String lat;
    /**
     * @Description 整体门面（含招牌）图片【公共区】
     */
    private String  pic1 ;
    /**
     * @Description 收银台图片【公共区】
     */
    private String  pic2;
    /**
     * @Description 店内环境图片【公共区】
     */
    private String pic3;


    
    
    
    
	public String getClsidName() {
		return clsidName;
	}

	public void setClsidName(String clsidName) {
		this.clsidName = clsidName;
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





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getParentStoreId() {
		return parentStoreId;
	}

	public void setParentStoreId(String parentStoreId) {
		this.parentStoreId = parentStoreId;
	}

	public String getMctNo() {
		return mctNo;
	}

	public void setMctNo(String mctNo) {
		this.mctNo = mctNo;
	}

	public String getTraId() {
		return traId;
	}

	public void setTraId(String traId) {
		this.traId = traId;
	}

	public String getMctName() {
		return mctName;
	}

	public void setMctName(String mctName) {
		this.mctName = mctName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}



	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}


	public String getClsid() {
		return clsid;
	}

	public void setClsid(String clsid) {
		this.clsid = clsid;
	}

	public List<String> getClsidList() {
		return clsidList;
	}

	public void setClsidList(List<String> clsidList) {
		this.clsidList = clsidList;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossSex() {
		return bossSex;
	}

	public void setBossSex(String bossSex) {
		this.bossSex = bossSex;
	}

	public String getBossIdCountry() {
		return bossIdCountry;
	}

	public void setBossIdCountry(String bossIdCountry) {
		this.bossIdCountry = bossIdCountry;
	}

	public String getBossIdType() {
		return bossIdType;
	}

	public void setBossIdType(String bossIdType) {
		this.bossIdType = bossIdType;
	}

	public String getBossSdate() {
		return bossSdate;
	}

	public void setBossSdate(String bossSdate) {
		this.bossSdate = bossSdate;
	}

	public String getBossEdate() {
		return bossEdate;
	}

	public void setBossEdate(String bossEdate) {
		this.bossEdate = bossEdate;
	}

	public String getBossIdNo() {
		return bossIdNo;
	}

	public void setBossIdNo(String bossIdNo) {
		this.bossIdNo = bossIdNo;
	}

	public String getBossTel() {
		return bossTel;
	}

	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}

	public String getBossEmail() {
		return bossEmail;
	}

	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}

	public String getBossJob() {
		return bossJob;
	}

	public void setBossJob(String bossJob) {
		this.bossJob = bossJob;
	}

	public String getBossAddress() {
		return bossAddress;
	}

	public void setBossAddress(String bossAddress) {
		this.bossAddress = bossAddress;
	}

	

	public String getBossPositive() {
		return bossPositive;
	}

	public void setBossPositive(String bossPositive) {
		this.bossPositive = bossPositive;
	}

	public String getBossBack() {
		return bossBack;
	}

	public void setBossBack(String bossBack) {
		this.bossBack = bossBack;
	}

	public String getBlType() {
		return blType;
	}

	public void setBlType(String blType) {
		this.blType = blType;
	}

	public String getBlSdate() {
		return blSdate;
	}

	public void setBlSdate(String blSdate) {
		this.blSdate = blSdate;
	}

	public String getBlEdate() {
		return blEdate;
	}

	public void setBlEdate(String blEdate) {
		this.blEdate = blEdate;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getBlPic() {
		return blPic;
	}

	public void setBlPic(String blPic) {
		this.blPic = blPic;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getAccountBoss() {
		return accountBoss;
	}

	public void setAccountBoss(String accountBoss) {
		this.accountBoss = accountBoss;
	}

	public String getAccountIdType() {
		return accountIdType;
	}

	public void setAccountIdType(String accountIdType) {
		this.accountIdType = accountIdType;
	}

	public String getAccountIdNo() {
		return accountIdNo;
	}

	public void setAccountIdNo(String accountIdNo) {
		this.accountIdNo = accountIdNo;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getAdmId() {
		return admId;
	}

	public void setAdmId(String admId) {
		this.admId = admId;
	}

	public String getOccNo() {
		return occNo;
	}

	public void setOccNo(String occNo) {
		this.occNo = occNo;
	}

	public String getOccSdate() {
		return occSdate;
	}

	public void setOccSdate(String occSdate) {
		this.occSdate = occSdate;
	}

	public String getOccEdate() {
		return occEdate;
	}

	public void setOccEdate(String occEdate) {
		this.occEdate = occEdate;
	}

	public String getOccPic() {
		return occPic;
	}

	public void setOccPic(String occPic) {
		this.occPic = occPic;
	}

	public String getTrcNo() {
		return trcNo;
	}

	public void setTrcNo(String trcNo) {
		this.trcNo = trcNo;
	}

	public String getTrcSdate() {
		return trcSdate;
	}

	public void setTrcSdate(String trcSdate) {
		this.trcSdate = trcSdate;
	}

	public String getTrcEdate() {
		return trcEdate;
	}

	public void setTrcEdate(String trcEdate) {
		this.trcEdate = trcEdate;
	}

	public String getTrcPic() {
		return trcPic;
	}

	public void setTrcPic(String trcPic) {
		this.trcPic = trcPic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFinancialContact() {
		return financialContact;
	}

	public void setFinancialContact(String financialContact) {
		this.financialContact = financialContact;
	}

	public String getFinancialTel() {
		return financialTel;
	}

	public void setFinancialTel(String financialTel) {
		this.financialTel = financialTel;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLicencePic() {
		return licencePic;
	}

	public void setLicencePic(String licencePic) {
		this.licencePic = licencePic;
	}

	public String getLicencePic2() {
		return licencePic2;
	}

	public void setLicencePic2(String licencePic2) {
		this.licencePic2 = licencePic2;
	}

	public String getOtherPic1() {
		return otherPic1;
	}

	public void setOtherPic1(String otherPic1) {
		this.otherPic1 = otherPic1;
	}

	public String getOtherPic2() {
		return otherPic2;
	}

	public void setOtherPic2(String otherPic2) {
		this.otherPic2 = otherPic2;
	}

	public String getOtherPic3() {
		return otherPic3;
	}

	public void setOtherPic3(String otherPic3) {
		this.otherPic3 = otherPic3;
	}

	public String getOtherPic4() {
		return otherPic4;
	}

	public void setOtherPic4(String otherPic4) {
		this.otherPic4 = otherPic4;
	}
    
	public static void setAllValueByReflect(Object model, String fileName, String fileValue) {

		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字

				if (fileName.contains("_")) {
					fileName = fileName.replaceAll("_", "");
				}

				if (fileName.toLowerCase().contains(name.toLowerCase())&&!"id".equals(name)) {

					name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
					String type = field[j].getGenericType().toString(); // 获取属性的类型

					if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																	// "，后面跟类名
						Method m = model.getClass().getMethod("set" + name, String.class);
						
						if(fileValue==null||"null".equals(fileValue)){
							
						}else{
							m.invoke(model, fileValue);
						}
						
					
					}
					if (type.equals("class java.lang.Integer")) {

						Method m = model.getClass().getMethod("set" + name, Integer.class);
						m.invoke(model, Integer.parseInt(fileValue));
					}
					if (type.equals("class java.util.Date")) {
                         System.out.println("日期类型");
						Method m = model.getClass().getMethod("set" + name, Date.class);
						SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
						if(fileValue==null||"null".equals(fileValue)){
							m.invoke(model, sd.format(new Date()));
						}else{
						
							try {
								m.invoke(model, sd.parse(fileValue));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				}

			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	

}
