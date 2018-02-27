package com.aiiju.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.BusinessSwitchMapper;
import com.aiiju.pojo.BusinessSwitch;
import com.aiiju.store.service.BusinessSwitchService;

/** 
 * @ClassName BusinessManageServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年7月5日 下午4:14:57
 */
@Service("businessSwitchService")
public class BusinessSwitchServiceImpl implements BusinessSwitchService {

    @Autowired
    private BusinessSwitchMapper businessSwitchMapper;
	
	
	@Override
	public Result getCreateAdminSwitch() {
		
		
		BusinessSwitch businessSwitch = businessSwitchMapper.selectBySwtichName("create_admin");
		
	
		
		if(businessSwitch==null){
			
			return Result.build(500, "无法创建，因为创建通道已关闭");
			
		}else{
			String isOpne = businessSwitch.getSwithStatus();
			
			//开关状态 swithStatus： 0.关，1.开
			if("1".equals(isOpne)){
				
				return Result.success();
			}else{
				return Result.build(500, "无法创建，因为创建通道已关闭");
			}
		}
		

		

	}

}
