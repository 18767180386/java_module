package com.aiiju.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @ClassName: Message 
 * @Description: 消息通知
 * @author 小飞 
 * @date 2016年11月7日 下午7:29:06 
 *
 */
public class Message implements Serializable, Cloneable {

	private static final long serialVersionUID = -926039458533289986L;
	
	private Long id;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 如果是收款消息，保存流水号
	 */
	private String ext;
	/**
	 * 金额
	 */
	private String money;
	/**
	 * 消息类型  0现金  1支付宝 2微信  3台卡  9消息
	 */
	private Byte msgType;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 店铺编号
	 */
	private String storeId;
	/**
	 * 收银员编号
	 */
	private String operatorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Byte getMsgType() {
		return msgType;
	}

	public void setMsgType(Byte msgType) {
		this.msgType = msgType;
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
