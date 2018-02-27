package com.aiiju.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName PAContract
 * @Description
 * @author zong
 * @CreateDate 2017年8月2日 上午10:32:23
 */
public class PAContract {

	private Integer id;
	private String storeId;
	private String parentStoreId;
	private String cttId; // 合同主键编号
	private String code;// 合同编号
	private String sdate;// 生效日期
	private String edate;// 过期日期
	private String pmtId;// 支付方式编号
	private String pmtType;// 交易类型（多个以小写逗号分开，0 现金，1 刷卡，2主扫，3 被扫，4JSPAY，5 预授权）
	private String pmtTag;// 支付标签（唯一性）
	private String pmtName;// 付款方式名称
	private String pmtInternalName;// 付款方式名称（内部名称）
	private String pmfId;// 支付方式行业编号
	private String pmfName;// 支付方式行业名称
	private String fee; // 商户签约扣率
	private String pmfLimit;// 商户签约扣率封顶值（分为单位）
	private String addTime;// 添加时间（yyyy-mm-dd hh:ii:ss）
	private String updTime;// 更新时间（yyyy-mm-dd hh:ii:ss）
	private String agentNo;// 结构编号
	private String agentName;// 机构名称
	private String signMan;// 签约人
	private String signDate;// 签约日期
	private String shopCount;// 合同关联的门店数量
	private String autoSign;// 是否自动续签（1 是，0 否）
	private String status;// 合同状态（0 未审核，1 已审核，2 审核未通过，3待审核，4 已删除，5 初审通过）
	private String contractPic1;// 合同照片【私密区】
	private String contractPic2;// 合同照片补充【私密区】
	private String remark;// 审核备注
	private String mctNo;// 商户编号

	
	
	
	
	public String getMctNo() {
		return mctNo;
	}

	public void setMctNo(String mctNo) {
		this.mctNo = mctNo;
	}

	public static void main(String[] args) {

		PAContract pc = new PAContract();

		setAllValueByReflect(pc, "auto_sign", "123asdf");

		System.out.println(pc.getAutoSign());

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
						m.invoke(model, fileValue);
					}
					if (type.equals("class java.lang.Integer")) {

						Method m = model.getClass().getMethod("set" + name, Integer.class);
						m.invoke(model, Integer.parseInt(fileValue));
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCttId() {
		return cttId;
	}

	public void setCttId(String cttId) {
		this.cttId = cttId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getPmtId() {
		return pmtId;
	}

	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}

	public String getPmtType() {
		return pmtType;
	}

	public void setPmtType(String pmtType) {
		this.pmtType = pmtType;
	}

	public String getPmtTag() {
		return pmtTag;
	}

	public void setPmtTag(String pmtTag) {
		this.pmtTag = pmtTag;
	}

	public String getPmtName() {
		return pmtName;
	}

	public void setPmtName(String pmtName) {
		this.pmtName = pmtName;
	}

	public String getPmtInternalName() {
		return pmtInternalName;
	}

	public void setPmtInternalName(String pmtInternalName) {
		this.pmtInternalName = pmtInternalName;
	}

	public String getPmfId() {
		return pmfId;
	}

	public void setPmfId(String pmfId) {
		this.pmfId = pmfId;
	}

	public String getPmfName() {
		return pmfName;
	}

	public void setPmfName(String pmfName) {
		this.pmfName = pmfName;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPmfLimit() {
		return pmfLimit;
	}

	public void setPmfLimit(String pmfLimit) {
		this.pmfLimit = pmfLimit;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getSignMan() {
		return signMan;
	}

	public void setSignMan(String signMan) {
		this.signMan = signMan;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getShopCount() {
		return shopCount;
	}

	public void setShopCount(String shopCount) {
		this.shopCount = shopCount;
	}

	public String getAutoSign() {
		return autoSign;
	}

	public void setAutoSign(String autoSign) {
		this.autoSign = autoSign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractPic1() {
		return contractPic1;
	}

	public void setContractPic1(String contractPic1) {
		this.contractPic1 = contractPic1;
	}

	public String getContractPic2() {
		return contractPic2;
	}

	public void setContractPic2(String contractPic2) {
		this.contractPic2 = contractPic2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
