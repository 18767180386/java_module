package com.aiiju.pay.controller.zfb;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.HttpClientUtil;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.pay.bo.DealBuilder;
import com.aiiju.pay.business.zfb.ExtendParams;
import com.aiiju.pay.business.zfb.builder.AlipayTradeCreateRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradePayRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradePrecreateRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradeRefundRequestBuilder;
import com.aiiju.pay.business.zfb.result.AlipayF2FCreateResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FPayResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FPrecreateResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FRefundResult;
import com.aiiju.pay.business.zfb.util.AlipayUtil;
import com.aiiju.pay.service.AlipayTradeService;
import com.aiiju.pay.service.AppAuthService;
import com.aiiju.pay.service.DealAccountService;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealAccount;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradePrecreateResponse;

/**
 * 
 * @ClassName: PayController
 * @Description: 支付宝支付控制器
 * @author 小飞
 * @date 2016年11月17日 下午5:32:24
 *
 */
@Controller
@RequestMapping("/zfbpay")
public class ZFBPayController {

	private static Logger logger = Logger.getLogger(ZFBPayController.class);

	@Autowired
	private AlipayTradeService tradeService;

	@Autowired
	private AppAuthService appAuthService;

	@Autowired
	private DealService dealService;

	@Autowired
	private DealAccountService dealAccountService;

	@Autowired
	private MPointsDetailService mPointsDetailService;

	/**
	 * 台卡 支付宝支付 ok
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/pay")
	@ResponseBody
	public Result sendToZhiFuBao(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("555555555555---token =" + req.getParameter("token"));
		// 1.获取授权方信息

		String storeId = req.getParameter("storeId");
		AppAuth auth = this.appAuthService.getAppAuthByStoreId(storeId);
		if (auth == null) {
			return Result.build(500, "商户未授权");
		}
		// 2.保存订单（交易流水）
		String outTradeNo = storeId + RandomUtil.generateCode();
		String tradeType = "1";
		Map createMap = this.createDecometerOrder(outTradeNo, req, auth, tradeType);
		String price = String.format("%.2f", Double.parseDouble(req.getParameter("price")));
		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
		}

		Deal deal = (Deal) createMap.get("deal");

		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, auth,null));

		// 3.预支付请求
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId(AlipayUtil.PID);
		AlipayTradeCreateRequestBuilder builder = new AlipayTradeCreateRequestBuilder()
				.setAppAuthToken(auth.getAppAuthToken()).setNotifyUrl(AlipayUtil.NOTICE_URL)
				.setSellerId(auth.getUserId()).setBuyerId(req.getParameter("user_id")).setOutTradeNo(outTradeNo)
				.setTotalAmount(req.getParameter("price")).setSubject(req.getParameter("shopName") + "消费")
				.setStoreId(storeId).setExtendParams(extendParams);

		AlipayF2FCreateResult alipayResult = this.tradeService.tradeCreate(builder);
		Result result = null;
		switch (alipayResult.getTradeStatus()) {
		case SUCCESS:
			logger.info("支付宝统一收款交易创建成功,商户订单号:" + outTradeNo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("total_fee", price);
			data.put("trade_time", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			data.put("out_trade_no", outTradeNo);
			data.put("trade_no", alipayResult.getResponse().getTradeNo());
			data.put("finish_url", "https://trade.ecbao.cn/zfbpay/finish");
			// 返回结果
			mPointsDetailService.saveMPointsDetail(deal);
			result = Result.success(data);
			break;

		case FAILED:
			logger.error("支付宝统一收款交易创建失败!!!");
			result = Result.build(40004, "支付宝统一收款交易创建失败!");
			break;

		case UNKNOWN:
			logger.error("系统异常，订单状态未知!!!");
			result = Result.build(20000, "系统异常，订单状态未知!");
			break;

		default:
			logger.error("不支持的交易状态，交易返回异常!!!");
			result = Result.build(40004, "不支持的交易状态，交易返回异常!");
			break;
		}
		return result;
	}

	/**
	 * 条码支付（商家扫描买家付款码） ok
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/barCodePay")
	@ResponseBody
	public Result barCodePay(HttpServletRequest req) {
		// 1.获取授权信息
		String storeId = req.getParameter("storeId");
		AppAuth auth = this.appAuthService.getAppAuthByStoreId(storeId);
		if (auth == null) {
			return Result.build(500, "商户未授权");
		}
		

		// 2.保存订单
		String outTradeNo = storeId + RandomUtil.generateCode();
		String tradeType = "1";
		Map createMap = this.createF2FOrder(req, "12", outTradeNo, auth, tradeType);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
		}

		Deal deal = (Deal) createMap.get("deal");
		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, auth,null));

		// 3.支付请求
		String authCode = req.getParameter("authCode");
		String subject = req.getParameter("subject");
		String operatorId = req.getParameter("operatorId");
		String timeoutExpress = "2m";
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId(AlipayUtil.PID);
		// 创建请求对象
		AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
				.setAppAuthToken(auth.getAppAuthToken()).setNotifyUrl(AlipayUtil.NOTICE_URL)
				.setSellerId(auth.getUserId()).setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
				.setTotalAmount(req.getParameter("price")).setStoreId(storeId).setOperatorId(operatorId)
				.setExtendParams(extendParams).setTimeoutExpress(timeoutExpress);

		// 调用tradePay方法获取当面付应答
		AlipayF2FPayResult alipayResult = this.tradeService.tradePay(builder);
		Result result = null;
		switch (alipayResult.getTradeStatus()) {
		case SUCCESS:
			logger.info("支付宝支付成功,订单号为:" + outTradeNo);
			// result = Result.build(10000, "支付宝支付成功!");

              deal.setStatus(Byte.parseByte("1"));// 订单完成
              deal.setTradeStatus("已支付");
              Boolean updateRT = this.dealService.updateDeal(deal);
             mPointsDetailService.saveMPointsDetail(deal);

			result = Result.build(10000, "支付宝支付成功!", null, null, deal.getSerialNum());
			mPointsDetailService.saveMPointsDetail(deal);
			break;

		case FAILED:
			logger.error("支付宝支付失败!!!");
			result = Result.build(40004, "支付宝支付失败!");
			break;

		case UNKNOWN:
			logger.error("系统异常，订单状态未知!!!");
			result = Result.build(20000, "系统异常，订单状态未知!");
			break;

		default:
			logger.error("不支持的交易状态，交易返回异常!!!");
			result = Result.build(40004, "不支持的交易状态，交易返回异常!");
			break;
		}
		return result;
	}

	/**
	 * 扫码支付（让买家扫描订单二维码） ok
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/qrCodePay")
	@ResponseBody
	public Result tradePrecreate(HttpServletRequest req) {
		// 1.获取授权信息
		String storeId = req.getParameter("storeId");

		AppAuth auth = this.appAuthService.getAppAuthByStoreId(storeId);
		if (auth == null) {
			return Result.build(500, "商户未授权");
		}
		// 2.保存订单
		String outTradeNo = storeId + RandomUtil.generateCode();

		String tradeType = "1";
		Map createMap = this.createF2FOrder(req, "13", outTradeNo, auth, tradeType);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
		}

		Deal deal = (Deal) createMap.get("deal");

		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, auth,null));

		// 3.发起获取带价格的二维码请求
		String subject = req.getParameter("subject");
		String operatorId = req.getParameter("operatorId");
		String timeoutExpress = "2m";
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId(AlipayUtil.PID);

		System.out.println("AppAuthToken---->" + auth.getAppAuthToken());
		System.out.println("auth.getUserId()---->" + auth.getUserId());
		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
				.setAppAuthToken(auth.getAppAuthToken()).setSubject(subject).setTotalAmount(req.getParameter("price"))
				.setOutTradeNo(outTradeNo).setSellerId(auth.getUserId()).setOperatorId(operatorId).setStoreId(storeId)
				.setExtendParams(extendParams).setTimeoutExpress(timeoutExpress).setNotifyUrl(AlipayUtil.NOTICE_URL);

		
		Result result = null;
		AlipayF2FPrecreateResult aliPayResult = tradeService.tradePrecreate(builder);

		switch (aliPayResult.getTradeStatus()) {
		case SUCCESS:
			logger.info("支付宝预下单成功: )");
			AlipayTradePrecreateResponse response = aliPayResult.getResponse();
			result = Result.build(200, "预下单成功", response.getQrCode(), null, deal.getSerialNum());

			mPointsDetailService.saveMPointsDetail(deal);

			break;
		case FAILED:
			logger.error("支付宝预下单失败!!!");
			result = Result.build(40004, "支付宝预下单失败!");
			break;

		case UNKNOWN:
			logger.error("系统异常，预下单状态未知!!!");
			result = Result.build(20000, "系统异常，预下单状态未知!");
			break;

		default:
			logger.error("不支持的交易状态，交易返回异常!!!");
			result = Result.build(40004, "不支持的交易状态，交易返回异常!");
			break;
		}
		return result;
	}

	@RequestMapping("/refund")
	@ResponseBody
	public Result refund(HttpServletRequest req) {
		Result result = null;
		// 1.判断授权

		String storeId = req.getParameter("storeId");
		AppAuth auth = this.appAuthService.getAppAuthByStoreId(storeId);
		if (auth == null) {
			return Result.build(500, "商户未授权");
		}
		// 2.获取订单
		String originalOutTradeNo = req.getParameter("originalOutTradeNo");
		// String refundType = req.getParameter("refundType");
		String outRequestNo = req.getParameter("outRefundNo");
		if (StringUtils.isBlank(outRequestNo)) {
			outRequestNo = UUID.randomUUID().toString();
		}
		// 3.保存退款订单
		String newOutTradeNo = storeId + RandomUtil.generateRefundCode();
		String tradeType = "2";
		Map createMap = this.createRefundOrder(req, "1", originalOutTradeNo, newOutTradeNo, auth, tradeType,outRequestNo);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建退款订单失败!请重新尝试扫描条码");
		}

		Deal deal = (Deal) createMap.get("deal");

		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, auth,null));

		String appAuthToken = auth.getAppAuthToken();

		System.out.println("------appAuthToken=" + appAuthToken);

		// 4.调用接口
		AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
				.setOutTradeNo(originalOutTradeNo).setRefundAmount(req.getParameter("refundFee"))
				.setRefundReason("正常退款").setAppAuthToken(appAuthToken).setOutRequestNo(outRequestNo);

		// AlipayTradeRefundRequestBuilder builder = new
		// AlipayTradeRefundRequestBuilder().setOutTradeNo(originalOutTradeNo)
		// .setRefundAmount(req.getParameter("refundFee")).setRefundReason("正常退款").setStoreId(storeId).setOutRequestNo(outRequestNo);
		try {
			AlipayF2FRefundResult tradeRefund = this.tradeService.tradeRefund(builder);
			switch (tradeRefund.getTradeStatus()) {
			case SUCCESS:
				logger.info("支付宝订单号[" + originalOutTradeNo + "]退款成功");
				// 修改系统中订单状态
				this.dealService.updateRefundDeal(newOutTradeNo, tradeRefund.getResponse().getTradeNo());
				result = Result.build(10000, "支付宝退款成功!", true);
				break;
			case UNKNOWN:
				logger.error("支付宝订单号[" + originalOutTradeNo + "]退款未知错误");
				result = Result.build(20000, "支付宝订单号[" + originalOutTradeNo + "]退款未知错误");
				break;
			case FAILED:
				logger.error("支付宝订单号[" + originalOutTradeNo + "]退款失败");
				// 如果此次退款失败，仍需传入当前设置的outRequestNo进行下次重新退款
				result = Result.build(40004, "支付宝订单号[" + originalOutTradeNo + "]退款失败", outRequestNo);
				break;
			default:
				logger.error("不支持的交易状态，交易返回异常!!!");
				result = Result.build(40004, "不支持的交易状态，交易返回异常!");
				break;
			}
		} catch (Exception e) {
			result = Result.build(500, "修改订单失败");
		}
		return result;
	}

	@RequestMapping("/notifyURL")
	@SuppressWarnings("rawtypes")
	public void notifyResult(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			Map<String, String> notifyIDMap = new HashMap<>();
			notifyIDMap.put("service", "notify_verify");
			notifyIDMap.put("partner", request.getParameter("seller_id"));
			notifyIDMap.put("notify_id", request.getParameter("notify_id"));
			// 验证notify_id
			String verifyRT = HttpClientUtil.doPost(AlipayUtil.NOTIFY_ID_VERIFY_URL, notifyIDMap);
			if (!Boolean.parseBoolean(verifyRT)) {
				out.print("fail");
				return;
			}
			// 验证签名
			if (AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY, AlipayUtil.CHARSET)) {// 验证成功
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
				String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");
				String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String notify_time = new String(request.getParameter("notify_time").getBytes("ISO-8859-1"), "UTF-8");

				if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
					logger.info("流水号:" + out_trade_no + "订单修改状态");
					// 修改系统中订单状态

					Deal deal = this.dealService.getDealBySerialNum(out_trade_no);
	
					if("1".equals(deal.getStatus()+"")){
						logger.info("（支付宝回调）但是----->修改状态时，发现该订单是成功订单，所以不进行更新操作");
					   
					}else{
						if (AlipayUtil.APP_ID.equals(app_id) && deal.getPrice().toString().equals(total_amount)
								&& deal.getSellerId().equals(seller_id)) {
							deal.setTradeNo(trade_no);
							deal.setNotityTime(notify_time);
							deal.setStatus(Byte.parseByte("1"));// 订单完成
							deal.setTradeStatus("已支付");
							Boolean  updateRT = this.dealService.updateDeal(deal);
					         mPointsDetailService.updateMPointsDetailBySerialnum(deal.getSerialNum(), "1");

							if (updateRT) {
								out.print("success"); // 请不要修改或删除
								return;
							}
						}
					}
					

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			out.print("fail");
		}
		out.print("fail");
	}

	@RequestMapping("/finish")
	public String finish(HttpServletRequest request) {
		request.setAttribute("total_fee", request.getParameter("total_fee"));
		request.setAttribute("trade_time", request.getParameter("trade_time"));
		request.setAttribute("out_trade_no", request.getParameter("out_trade_no"));
		return "pay/zfb/finish";
	}

	/**
	 * 扫描台卡 流水订单
	 * 
	 * @param out_trade_no
	 *            流水号
	 * @param price
	 *            金额
	 * @return
	 */
	private Map createDecometerOrder(String outTradeNo, HttpServletRequest req, AppAuth auth, String tradeType) {
		// 创建台卡流水订单
		Deal deal = DealBuilder.buildDecometerOrder("11", outTradeNo, req, auth.getUserId(), tradeType);// 参照
																										// //
																										// com.aiiju.pay.constant.TradeConstant中定义的参数
		// return this.dealService.saveDeal(deal, "interface");
		Result result = this.dealService.saveDeal(deal, "interface");

		Map map = new HashMap();
		map.put("deal", deal);
		if (result.getStatus() == 200) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		return map;
	}

	/**
	 * 创建当面付订单
	 * 
	 * @param request
	 * @param receType
	 * @param outTradeNo
	 * @param auth
	 * @return
	 */
	private Map createF2FOrder(HttpServletRequest request, String receType, String outTradeNo, AppAuth auth,
			String tradeType) {
		// 创建条码支付流水订单

		Deal deal = DealBuilder.buildOrder(request, receType, outTradeNo, auth, tradeType);

		Result result = this.dealService.saveDeal(deal, "interface");

		Map map = new HashMap();
		map.put("deal", deal);
		if (result.getStatus() == 200) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		return map;

	}

	/**
	 * 创建退款订单
	 * 
	 * @param refundType
	 * @param outTradeNo
	 * @param outRequestNo
	 * @param price
	 * @param auth
	 * @return
	 */
	private Map createRefundOrder(HttpServletRequest req, String refundType, String originalOutTradeNo,
			String newOutTradeNo, AppAuth auth, String tradeType,String outRequestNo) {
		// 创建条码支付流水订单
		Map<String, String> params = new HashMap<String, String>();
		params.put("storeId", auth.getStoreId());
		params.put("sellerId", auth.getUserId());
		Deal deal = DealBuilder.buildRefundOrder(req, refundType, originalOutTradeNo, newOutTradeNo, params, tradeType,outRequestNo);

		Result result = this.dealService.saveRefundDeal(deal, "interface");

		Map map = new HashMap();
		map.put("deal", deal);
		if (result.getStatus() == 200) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}

		return map;
	}
}
