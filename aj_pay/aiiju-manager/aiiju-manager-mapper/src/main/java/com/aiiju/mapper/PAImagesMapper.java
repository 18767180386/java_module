package com.aiiju.mapper;

import com.aiiju.pojo.PAImages;

/** 
 * @ClassName PAImagesMapper
 * @Description
 * @author zong
 * @CreateDate 2017年7月26日 下午2:12:24
 */
public interface PAImagesMapper {

	public void  add(PAImages paImages);

	public void  update(PAImages paImages);
	
	public void  deleteImageInfo(String filePath);
	
	public PAImages  getByFilePath(String filePath);
	
 

}
