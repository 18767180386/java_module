package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.JpushMessage;

public interface JpushMessageMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(JpushMessage record);

    JpushMessage selectByPrimaryKey(Integer id);
    
    List<JpushMessage> selectNotReadByEquipmentCode(String equipmentCode);
    
    void deleteAllByEquipmentCode(String equipmentCode);
    

    int update(JpushMessage record);

    
     void saveJpushMessage(JpushMessage record);
   
}