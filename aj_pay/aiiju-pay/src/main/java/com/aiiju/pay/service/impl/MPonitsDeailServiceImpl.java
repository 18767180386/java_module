package com.aiiju.pay.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.MPointsDetailMapper;
import com.aiiju.mapper.MPointsMapper;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.MPoints;
import com.aiiju.pojo.MPointsDetail;

@Service("mPonitsDeailService")
public class MPonitsDeailServiceImpl implements MPointsDetailService {

    @Autowired
    private MPointsDetailMapper mPonitsDeailMapper;
    
    @Autowired
    private MPointsMapper mPointsMapper;
    
	
	@Override
	public Result saveMPointsDetail(Deal deal) {

		
		if(deal!=null&&deal.getMemberPhone()!=null&&!("").equals(deal.getMemberPhone())){
			
			  MPoints points = this.mPointsMapper.getByStoreId(deal.getStoreId()); 
			  if(points==null){
				  System.out.println("用户还没有开启会员积分功能！"); 
					return Result.success();
				  
			  }else{
				  
				  String flag =  points.getStatus(); //积分设置开关
				  if("1".equals(flag)){

						MPointsDetail mPointsDetail = new MPointsDetail();
						mPointsDetail.setDealSerialNum(deal.getSerialNum());
						mPointsDetail.setDealTime(deal.getCreateDate());
						mPointsDetail.setGetPointsValue(deal.getShouldPrice().intValue());
						mPointsDetail.setPhone(deal.getMemberPhone());
						mPointsDetail.setStoreId(deal.getStoreId());
						mPointsDetail.setStatus(deal.getStatus()==1?"1":"0");
						String type = deal.getPrivType();
						System.out.println("type"+type);
						if(type!=null&&type.contains("会员积分抵扣")){
							String pointsValue= type.trim().replaceAll("会员积分抵扣", "").replaceAll("元", "");
							int usedpoints = new BigDecimal(pointsValue).multiply(new BigDecimal(100)).intValue();  
							System.out.println("使用积分"+usedpoints);
							mPointsDetail.setUsedPointsValue(usedpoints);
							
						}else{
							mPointsDetail.setUsedPointsValue(0);
						}

						mPonitsDeailMapper.add(mPointsDetail);			
					  
				  }else{
					  
					  System.out.println("会员积分开关已关闭，不做积分操作");
						return Result.success();
				  }
			  }

			
		}else{
			System.out.println("没有选择会员卡，无积分积累");	
			
			return Result.success();
		}

		return Result.success();
	}


	@Override
	public Result updateMPointsDetailBySerialnum(String serialnum,String status) {

		System.out.println("完成付款后回调函数"+serialnum+"---"+status);
		
		
		Map map =new HashMap();
		map.put("serialnum", serialnum);
		map.put("status", status);
		
		mPonitsDeailMapper.updateMPointsDetailBySerialnum(map);
		
		return Result.success();
	}

}
