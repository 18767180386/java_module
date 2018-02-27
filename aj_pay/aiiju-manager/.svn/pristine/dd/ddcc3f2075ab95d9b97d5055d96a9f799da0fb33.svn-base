package com.aiiju.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JpushMessage {
	
    private Integer id;

    private String phone;

    private String equipmentCode;

    private String msgType;

    private String msgInfo;

    private String isRead;
    
    private String createDate;

    
    
   public  static JpushMessage formatJpush(JPush jpush){
    	
    	JpushMessage jm = new JpushMessage();
    	
    	jm.setEquipmentCode(jpush.getEquipmentCode());
    	jm.setIsRead("0");
    	jm.setMsgInfo(jpush.getMsg());
    	jm.setMsgType(jpush.getMsgType());
    	jm.setPhone(jpush.getPhone());
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	jm.setCreateDate(sdf.format(new Date()));
    	
    	
    	return jm;
    	
    	
    	
    }
    
    
    
    
    public String getCreateDate() {
		return createDate;
	}




	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}




	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode == null ? null : equipmentCode.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo == null ? null : msgInfo.trim();
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }
}