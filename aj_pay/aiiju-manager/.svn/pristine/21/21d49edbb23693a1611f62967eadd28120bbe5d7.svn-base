package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.JPush;

/**
 * 
 * @ClassName: JPushMapper 
 * @Description: 消息推送Mapper
 * @author 小飞 
 * @date 2016年12月20日 上午11:59:13 
 *
 */
public interface JPushMapper {
	
	/**
	 * 添加
	 * @param jpush
	 */
	public void add(JPush jpush);
	/**
	 * 修改
	 * @param jpush
	 */
	public void update(JPush jpush);
	
	
	/**
	 * 修改
	 * @param jpush
	 */
	public void updateStatus(JPush jpush);
	
	/**
	 * 修改
	 * @param jpush
	 */
	public void updateByEquipmentCode(JPush jpush);
	
	/**
	 *   查询
	 * @param operatorId  操作员编号
	 * @param equipmentCode  设备编号
	 * @return
	 */
	public List<JPush> getByOperatorId(String operatorId);
	
	/**
	 *   查询
	 * @param operatorId  操作员编号
	 * @param equipmentCode  设备编号
	 * @return
	 */
	public List<JPush> getListByOperatorId(String JPush);
	
	/**
	 *  查询
	 * @param storeId 店铺编号
	 * @return
	 */
	public List<JPush> getByStoreId(String storeId);
	
	/**
	 *   查询
	 *
	 * @param equipmentCode  设备编号
	 * @return
	 */
	public String  getByEquipmentCode(String equipmentCode);
	
	
	/**
	 *   查询
	 *
	 * @param equipmentCode  设备编号
	 * @return
	 */
	public JPush  getJpushByEquipmentCode(String equipmentCode);
	
	
	/**
	 *   查询
	 *
	 * @param equipmentCode  设备编号
	 * @return
	 */
	public List<JPush>  getAllEquipmentCode();
	
	
}
