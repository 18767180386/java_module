package com.aiiju.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.BusinessSwitchMapper;
import com.aiiju.pay.service.BusinessSwitchService;
import com.aiiju.pojo.BusinessSwitch;

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
	public Result getPACodeSwitch() {
		
		BusinessSwitch businessSwitch = businessSwitchMapper.selectBySwtichName("user_qrcode");
		
		if(businessSwitch==null){
			
			return Result.build(500, "无法使用银行台卡，开关已关闭");
			
		}else{
			String isOpne = businessSwitch.getSwithStatus();
			
			if("1".equals(isOpne)){
				
				return Result.success();
			}else{
				return Result.build(500, "无法使用银行台卡，开关已关闭");
			}
		}
		

		

	}

}
