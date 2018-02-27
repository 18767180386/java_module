package com.aiiju.mapper;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.ReputationShopImages;

/** 
 * @ClassName ReputationCategoryMapper
 * @Description
 * @author zong
 * @CreateDate 2017年5月25日 下午3:14:13
 */
public interface ReputationShopImagesMapper {

	
	public void  add(ReputationShopImages reputationShopImages);

	public void  update(ReputationShopImages reputationShopImages);
	
	public void  deleteImageInfo(String reputation_image_id);
	public ReputationShopImages  getByImageId(String reputation_image_id);
	//public ReputationShopInfo getReputationShopInfo(String storeId);
	
    public ReputationShopImages getImageInfo(String reputation_image_id);
}
