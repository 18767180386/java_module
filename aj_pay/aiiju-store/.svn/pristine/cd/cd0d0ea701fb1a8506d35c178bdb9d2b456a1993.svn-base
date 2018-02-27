package com.aiiju.store.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.HttpClientUtil;
import com.aiiju.common.util.signTool;
import com.aiiju.pojo.Goods;
import com.aiiju.store.service.ShopService;

/**
 * @Description:  这是aop 切面执行的程序。
 * 当订单发生增加修改的时候，如果和ERP系统进行了绑定。需要通知ERP系统
 * @Author:	天明
 * @CreateDate:	2017年7月27日下午2:52:22
 */
public class AjsyNotify {
	
	// 地址写到 properties 文件中
	@Value("${ERP_AJSYNOTIFY_URL}")
    private String erp_url;
	
	@Autowired
	private ShopService shopService;
	
	private static Logger logger = Logger.getLogger(AjsyNotify.class);
	
	
	
	/**
	 * 当商品的信息，出现修改，增加的时候。
	 * 需要通知ERP系统
	 * spring切面的方法
	 * @param point 
	 */
	public void goodsERP(JoinPoint point,Result result ) {
		/*System.err.println("执行了切面的方法");
		
		System.out.println("@AfterReturning：目标方法为：" + 
                point.getSignature().getDeclaringTypeName() + 
                "." + point.getSignature().getName());
		
		
		System.out.println("@AfterReturning：参数为：" + 
                Arrays.toString(point.getArgs()));*/
		// 只有 结果是成功的时候才 通知ERP系统
		if (result == null || result.getStatus() != 200) {
			return;
		}
		
		Object[] args = point.getArgs();
		Goods goods = (Goods) args[0];
		
		// 通知 erp 系统
		String storeId = goods.getStoreId();
		if (storeId != null && !StringUtils.isBlank(storeId)) {
			this.sendAjsyNotify(storeId, "item");
			logger.info("需要通知ERP系统信息的更新");
		}
		
		
	}
	

	/**
	 * 向ERP系统中发送消息，这边有商品 
	 * @param storeId 商品 或者订单等 店铺的id
	 * @param whichSync 他的值只有三个 item 同步商品，order订单，refund退款
	 */
	public void sendAjsyNotify(String storeId,String whichSync) {
		
		//logger.info("执行了通知消息");
		
		Map<String, String> notifyIDMap = new HashMap<>();
		notifyIDMap.put("storeId", storeId);
		notifyIDMap.put("whichSync", whichSync);
		
		String signResult = signTool.sign(notifyIDMap);
		notifyIDMap.put("sign", signResult);
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost(erp_url, notifyIDMap);
	//	String doPostStr = this.doPost("http://121.199.182.2/dsb/auther_ajsy/ajsyNotify", notifyIDMap);
		System.out.println(doPostStr);
	}

}
