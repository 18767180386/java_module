package com.aiiju.store.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.store.service.ContractService;
import com.aiiju.store.service.MerchantService;
import com.aiiju.store.service.OrderService;

import net.sf.json.JSONObject;

/**
 * @ClassName PACallbackController
 * @Description
 * @author zong
 * @CreateDate 2017年8月2日 上午11:49:15
 */
@Controller
@RequestMapping("/paCallback")
public class PACallbackController {

	private static Logger logger = Logger.getLogger(PACallbackController.class);
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * Method testPayCallback Description 说明：
	 *
	 * @param request
	 *            说明：
	 *
	 * @return 返回值说明：
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	@ResponseBody
	public String callback(HttpServletRequest request) throws UnsupportedEncodingException {

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String paramValue = request.getParameter(paramName);
			
			String str;
			try {
				
				str = new String(paramValue.getBytes("ISO-8859-1"),"UTF-8");
				logger.info(paramName + ":"+ str);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
		}


		Map<String, String> params = new TreeMap<String, String>();

		// 取出所有参数是为了验证签名
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();

			params.put(parameterName, new String(request.getParameter(parameterName).getBytes("ISO-8859-1"),"UTF-8"));
		}

		JSONObject paramsObject = JSONObject.fromObject(params);

		if (TLinx2Util.verifySign(paramsObject, StaticConfig.publickey)) { // 验签
//			logger.info("===========验签成功==");
			logger.info("->银行回调请求，携带全部数据的：" + paramsObject.toString());

			String trade_type = paramsObject.getString("trade_type");

			switch (trade_type) {
			case "shop_status_notify":
				
				String shop_no = paramsObject.getString("shop_no");
				logger.info("->该条回调请求属于【门店信息变更】，门店编号：" + shop_no);

				break;

			case "merchant_status_notify":
				String mct_no = paramsObject.getString("mct_no");

				logger.info("->该条回调请求属于【商户信息变更】，商户编号" + mct_no);
				merchantService.loadMerchantInfo(mct_no);
				break;

			case "contract_status_notify":
				String ctt_id = paramsObject.getString("ctt_id");
				logger.info("->该条回调请求属于【合同信息变更】，合同号："+ctt_id);

				contractService.addOrUpdate(ctt_id);
				
				
				break;
			case "order_status_notify":
				String ord_no = paramsObject.getString("ord_no");
				logger.info("->该条回调请求属于【订单信息变更】，订单号："+ord_no);
				break;				
				
				

			default:
				
				logger.info("警告：接收一条银行返回的回调请求，但类型未知，请结合日志，觉得是否处理此回调");
				
				
				break;
			}

			return "notify_success";
		} else {
			logger.info("警告：===========验签失败===========");
			return "fail";
		}

	}
}