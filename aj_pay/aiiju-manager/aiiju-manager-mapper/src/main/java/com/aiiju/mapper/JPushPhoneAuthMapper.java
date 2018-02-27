package com.aiiju.mapper;

import com.aiiju.pojo.JPushPhoneAuth;

public interface JPushPhoneAuthMapper {
	
    JPushPhoneAuth selectByPhone(String phone);
	
    int deleteByPrimaryKey(Integer id);

    int insert(JPushPhoneAuth record);

    int insertSelective(JPushPhoneAuth record);

    JPushPhoneAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JPushPhoneAuth record);


}