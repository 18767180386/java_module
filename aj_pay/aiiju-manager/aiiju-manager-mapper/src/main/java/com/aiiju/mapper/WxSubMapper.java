package com.aiiju.mapper;

import com.aiiju.pojo.WxSub;
/**
 * 
 * @ClassName: WxSubMapper 
 * @Description: 微信子商户Mapper
 * @author 小飞 
 * @date 2016年12月21日 上午11:23:26 
 *
 */
public interface WxSubMapper {

	public void add(WxSub wxSub);
	
	public void delete(Integer id);
	
	public void deleteByStoreId(String storeId);
	
	public WxSub getByStoreId(String storeId);
}
