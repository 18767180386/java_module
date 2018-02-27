package com.aiiju.mapper;

import com.aiiju.pojo.QqSub;
/**
 * 
 * @ClassName: QqSubMapper 
 * @Description: qq钱包子商户 Mapper
 * @author 小飞 
 * @date 2017年2月7日 上午11:08:55 
 *
 */
public interface QqSubMapper {

	public QqSub getByStoreId(String storeId);

	public void add(QqSub qqSub);

}
