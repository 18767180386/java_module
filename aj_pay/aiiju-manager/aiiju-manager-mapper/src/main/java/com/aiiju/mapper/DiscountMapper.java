package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Discount;
import com.aiiju.pojo.DiscountShopLink;
/**
 * 
 * @ClassName: DiscountMapper 
 * @Description: 优惠折扣Mapper
 * @author 小飞 
 * @date 2016年11月15日 下午3:00:52 
 *
 */
public interface DiscountMapper {
	
	public Discount getById(Integer id);

	public void add(Discount discount);
	
	public void delete(Integer id);
	
	public List<Discount> getAllByStoreId(String storeId);
	
	public List<Discount> getSingleDiscountByStoreId(Map<String,String> map);
	
	/**
	 * 批量添加
	 * @param links
	 */
	public void insertBatch(List<DiscountShopLink> list);
	/**
	 * 取消优惠折扣和店铺的关联关系
	 * @param link
	 */
	public void deleteLink(DiscountShopLink link);
	/**
	 * 绑定优惠折扣和店铺的关联关系
	 * @param link
	 */
	public void addLink(DiscountShopLink link);

}
