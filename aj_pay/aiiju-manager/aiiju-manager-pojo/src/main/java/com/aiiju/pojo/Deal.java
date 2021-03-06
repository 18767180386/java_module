package com.aiiju.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: Deal
 * @Description: 交易流水记录
 * @author 小飞
 * @date 2016年11月14日 上午9:48:20
 *
 */
public class Deal implements Serializable, Cloneable {

    private static final long serialVersionUID = 2724018299218385302L;

    private Long id;

    /**
     * 交易金额
     */
    private BigDecimal price;

    /**
     * 交易时间
     */
    private Date dealDate;

    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 原始流水单号
     */
    private String originalSerialNum;

    /**
     * 销售单号
     */
    private String sellNum;

    /**
     * 支付接口 返回的交易号
     */
    private String tradeNo;

    /**
     * 操作员编号
     */
    private String operatorId;

    /**
     * 支付方式 0：现金 1：支付宝 2：微信 3:qq钱包
     */
    private String payType;
    
    /**
     * 支付方式详情 将0：现金 1：支付宝 2：微信 3:qq钱包 细分如下：
     * 
     * "00", "现金"
     * "11", "二维码台卡-支付宝"
     * "12", "支付宝扫码"
     * "13", "支付宝条码"
     * "21", "二维码台卡-微信"
     * "22", "微信扫码"
     * "23", "微信条码"
     * "31", "二维码台卡-qq钱包"
     * "32", "qq钱包扫码"
     * "33", "qq钱包条码"
     */
    private String payTypeDetail;
    
    
    /**
     *  交易类型 1收款，2，退款
     */
    private String tradeType;

    /**
     * 退款方式 0：现金 1：支付宝 2：微信 3:qq钱包
     */
    private String refundType;

    /**
     * 收款方式说明
     */
    private String receTypeDesc;

    /**
     * 合计金额
     */
    private BigDecimal sumPrice;

    /**
     * 优惠方式
     */
    private String privType;

    /**
     * 优惠金额
     */
    private BigDecimal privPrice;

    /**
     * 应收
     */
    private BigDecimal shouldPrice;

    /**
     * 实收
     */
    private BigDecimal actualPrice;

    /**
     * 找零
     */
    private BigDecimal changePrice;

    /**
     * 抹零
     */
    private BigDecimal roundPrice;

    /**
     * 可退金额
     */
    private BigDecimal canRefundFee;

    /**
     * 备注
     */
    private String remark;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺编号
     */
    private String storeId;

    /**
     * 主店铺编号
     */
    private String parentStoreId;
    
    /**
     * 订单状态 1：完成 2：进行中 3：失败
     */
    private Byte status;

    /**
     * 交易状态 (交易完成,已部分退款,已全额退款;退款成功)
     */
    private String tradeStatus;

    /**
     * 支付宝 通知时间
     */
    private String notityTime;

    /**
     * 支付宝 授权商户pid，notifyURL会用到
     */
    private String sellerId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 操作员姓名
     */
    private String operatorName;
    
    
    /**
     * 操作员电话
     */
    private String operatorPhone;

    /**
     * （收款时）商品详情
     */
    private List<DealDetail> details;
    
    
    /**
     * (退款)商品详情
     */
    private List<DealDetail> refoundDetails;
    

    /**
     * 订单的真实打印状态，1已打印，0未打印;  依据向365小票机查询该打印单为依据
     */
    private String printReceiveStatus;
    
    /**
     * 用户是否打印该订单，0，不打印，1打印，-1，365小票机服务器连接失败
     */
    private String printSendStatus;
    
    /**
     * 该店铺订单打印序列号(小票头的NO号显示）
     */
    private int printNum;
    
    /**
     * 打印时间
     */
    private Date printDate;
    
    
    /**
     * 打印单号(365小票機返回)
     */
    private String printOrder;
    
    /**
     * 小票號 （本系統生成，13位時間戳+5位隨機數）
     */
    private String printSerial;
    
    /**
     * 会员号
     */
    private String memberPhone;
    
    
    /**
     * 商家退款单号（同一笔退款失败后，再次退款，值不变）
     */
    private String outRefundNo;
    
    
    
    
    
    
    

	public List<DealDetail> getRefoundDetails() {
		return refoundDetails;
	}

	public void setRefoundDetails(List<DealDetail> refoundDetails) {
		this.refoundDetails = refoundDetails;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getPayTypeDetail() {
		return payTypeDetail;
	}

	public void setPayTypeDetail(String payTypeDetail) {
		this.payTypeDetail = payTypeDetail;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getParentStoreId() {
		return parentStoreId;
	}

	public void setParentStoreId(String parentStoreId) {
		this.parentStoreId = parentStoreId;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getPrintSerial() {
		return printSerial;
	}

	public void setPrintSerial(String printSerial) {
		this.printSerial = printSerial;
	}

	public String getPrintReceiveStatus() {
		return printReceiveStatus;
	}

	public void setPrintReceiveStatus(String printReceiveStatus) {
		this.printReceiveStatus = printReceiveStatus;
	}

	public String getPrintSendStatus() {
		return printSendStatus;
	}

	public void setPrintSendStatus(String printSendStatus) {
		this.printSendStatus = printSendStatus;
	}

	public String getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(String printOrder) {
		this.printOrder = printOrder;
	}

	public int getPrintNum() {
		return printNum;
	}

	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReceTypeDesc() {
        return receTypeDesc;
    }

    public void setReceTypeDesc(String receTypeDesc) {
        this.receTypeDesc = receTypeDesc;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getPrivType() {
        return privType;
    }

    public void setPrivType(String privType) {
        this.privType = privType;
    }

    public BigDecimal getPrivPrice() {
        return privPrice;
    }

    public void setPrivPrice(BigDecimal privPrice) {
        this.privPrice = privPrice;
    }

    public BigDecimal getShouldPrice() {
        return shouldPrice;
    }

    public void setShouldPrice(BigDecimal shouldPrice) {
        this.shouldPrice = shouldPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    public BigDecimal getRoundPrice() {
        return roundPrice;
    }

    public void setRoundPrice(BigDecimal roundPrice) {
        this.roundPrice = roundPrice;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<DealDetail> getDetails() {
        return details;
    }

    public void setDetails(List<DealDetail> details) {
        this.details = details;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getNotityTime() {
        return notityTime;
    }

    public void setNotityTime(String notityTime) {
        this.notityTime = notityTime;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOriginalSerialNum() {
        return originalSerialNum;
    }

    public void setOriginalSerialNum(String originalSerialNum) {
        this.originalSerialNum = originalSerialNum;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public BigDecimal getCanRefundFee() {
        return canRefundFee;
    }

    public void setCanRefundFee(BigDecimal canRefundFee) {
        this.canRefundFee = canRefundFee;
    }
    
    
    

    public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
