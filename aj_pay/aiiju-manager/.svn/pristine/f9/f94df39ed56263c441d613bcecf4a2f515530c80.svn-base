package com.aiiju.pojo;

public class JPushPhoneAuth {
    private Integer id;

    private String phone;
    
   /*1共享账号（该账号可同时登录设备，不会挤掉，不受任何限制就能登录），
    2一般账号（登录互斥），
    3部分可登录（在允许的设备中共享账号）
    4，部分不可登录（在不允许的设备中禁止登录），
    5仅自己登录（一旦登录，别的登录者将无法登录，退出后，在其他设备才可登录，此类型慎用,非特殊情况不要用哈，哈哈哈）
    */
    private String phoneAuthType;

    private String allowEquipmentType;

    private String notAllowEquipmentType;

    private String isEquipmentAuthType;

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

    public String getPhoneAuthType() {
        return phoneAuthType;
    }

    public void setPhoneAuthType(String phoneAuthType) {
        this.phoneAuthType = phoneAuthType == null ? null : phoneAuthType.trim();
    }

    public String getAllowEquipmentType() {
        return allowEquipmentType;
    }

    public void setAllowEquipmentType(String allowEquipmentType) {
        this.allowEquipmentType = allowEquipmentType == null ? null : allowEquipmentType.trim();
    }

    public String getNotAllowEquipmentType() {
        return notAllowEquipmentType;
    }

    public void setNotAllowEquipmentType(String notAllowEquipmentType) {
        this.notAllowEquipmentType = notAllowEquipmentType == null ? null : notAllowEquipmentType.trim();
    }

    public String getIsEquipmentAuthType() {
        return isEquipmentAuthType;
    }

    public void setIsEquipmentAuthType(String isEquipmentAuthType) {
        this.isEquipmentAuthType = isEquipmentAuthType == null ? null : isEquipmentAuthType.trim();
    }
}