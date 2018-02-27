package com.aiiju.pay.controller.pa.zfb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pa_utils.shoppay.ShopPayUtils;
import com.aiiju.common.pa_utils.shoppay.TLinx2Util;
import com.aiiju.common.pa_utils.shoppay.TLinxAESCoder;
import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.pay.bo.PADealBuilder;
import com.aiiju.pay.controller.pa.PAGetPayStatusListener;
import com.aiiju.pay.service.DealAccountService;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pay.service.PAService;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealAccount;
import com.aiiju.pojo.Order;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.PAShop;

import net.sf.json.JSONObject;

/**
 * @ClassName ZFBPayController
 * @Description 平安支付宝收款
 * @author zong
 * @CreateDate 2017年8月3日 下午6:21:32
 */
@Controller
@RequestMapping("/pazfb")
public class PAZFBPayController {

	private static Logger logger = Logger.getLogger(PAZFBPayController.class);
	@Autowired
	private DealService dealService;
	@Autowired
	private MPointsDetailService mPointsDetailService;
	@Autowired
	private PAService paService;

	@Autowired
	private DealAccountService dealAccountService;

	String notifyUrl = "https://trade.ecbao.cn/pazfb/notifyURL";
	// String notifyUrl = "http://16794ui705.iok.la:29890/pazfb/notifyURL";

	/**
	 * 条码支付（商家扫描买家付款码） ok
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/barCodePay")
	@ResponseBody
	public Result barCodePay(HttpServletRequest req) {
		Result result = null;
		/**
		 * 0, 查询openid,openkey,若无，则告知无签约银行 1，创建订单 2，创建成功后 接口提交交易信息
		 * 3，返回客户端成功或失败信息
		 * 
		 */
		String storeId = req.getParameter("storeId");
		String parentStoreId = req.getParameter("parentStoreId");
		String shopType = req.getParameter("shopType");
		String authCode = req.getParameter("authCode");
		PAShop shop = paService.getPAShop(storeId, parentStoreId, shopType);

		PAMerchant merchant = paService.getPAMerchant(storeId, parentStoreId, shopType);

		if (shop == null) {
			return Result.build(500, "尚未签约银行支付功能");
		}
		String outTradeNo = storeId + RandomUtil.generateCode(); // 流水单号

		String tradeType = "1"; // 1收款，2退款
		String payTypeDetail = "43"; // 平安支付宝条码
		Map createMap = this.createF2FOrder(req, payTypeDetail, outTradeNo, tradeType);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
		}

		Deal deal = (Deal) createMap.get("deal");
		Order order = new Order();
		Order.dealToParOrder(deal, order);
		order.setPmtTag("AlipayHZPA");
		order.setPmtName("支付宝");
		order.setOpenId(shop.getOpenId());
		order.setOpenKey(shop.getOpenKey());
		order.setTradeAccount(merchant.getAccountNo());
		order.setNotifyUrl(notifyUrl);
		order.setAuthCode(authCode);

		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, order));

		JSONObject payOrderJson = ShopPayUtils.payOrder(order);
		Integer payOrderErrcode = (Integer) payOrderJson.get("errcode");
		String payOrderMsg = (String) payOrderJson.get("msg");

		if (payOrderErrcode != 0) {
			return Result.build(40004, payOrderMsg); // 抛出异常回滚
		}

		Object payOrderData = payOrderJson.get("data");
		Result payOrderResultJson = getReturnData(payOrderJson, payOrderData, order, payOrderErrcode, payOrderMsg);

		if (payOrderResultJson.getStatus() == 500) {
			// 更新订单失败
			logger.error("平安支付宝支付失败!!!");
			result = Result.build(40004, "平安支付宝支付失败!");
			return payOrderResultJson; // 返回 提审失败的具体信息
		} else {
			// 订单创建成功
			String payStatus = PAGetPayStatusListener.getPayStatus(order);

			if ("1".equals(payStatus)) {
				result = Result.build(10000, "平安支付宝支付成功!", null, null, deal.getSerialNum());
				deal.setStatus((byte) 1);
				deal.setTradeStatus("已支付");
				this.dealService.updateDeal(deal);
				mPointsDetailService.saveMPointsDetail(deal);

			} else {
				if ("0".equals(payStatus)) {
					result = Result.build(40004, "支付宝支付失败,订单已取消");
				} else {
					result = Result.build(40004, "支付失败");
				}
			}
		}
		return result;
	}

	/**
	 * 扫码支付（让买家扫描订单二维码）
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/qrCodePay")
	@ResponseBody
	public Result qrCodePay(HttpServletRequest req) {
		Result result = null;
		/**
		 * 0, 查询openid,openkey,若无，则告知无签约银行 1，创建订单 2，创建成功后 接口提交交易信息
		 * 3，返回客户端成功或失败信息
		 * 
		 */
		String storeId = req.getParameter("storeId");
		String parentStoreId = req.getParameter("parentStoreId");
		String shopType = req.getParameter("shopType");

		PAShop shop = paService.getPAShop(storeId, parentStoreId, shopType);
		PAMerchant merchant = paService.getPAMerchant(storeId, parentStoreId, shopType);

		if (shop == null) {
			return Result.build(500, "尚未签约银行支付功能");
		}
		String outTradeNo = storeId + RandomUtil.generateCode(); // 流水单号

		String tradeType = "1"; // 交易类型：1收款，2退款
		String payTypeDetail = "42"; // 收款方式：平安支付宝扫码
		Map createMap = this.createF2FOrder(req, payTypeDetail, outTradeNo, tradeType);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
		}

		Deal deal = (Deal) createMap.get("deal");

		Order order = new Order();
		Order.dealToParOrder(deal, order);
		order.setPmtTag("AlipayHZPA");
		order.setPmtName("支付宝");
		order.setOpenId(shop.getOpenId());
		order.setOpenKey(shop.getOpenKey());
		order.setTradeAccount(merchant.getAccountNo());
		order.setNotifyUrl(notifyUrl);
		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, order));
		JSONObject payOrderJson = ShopPayUtils.payOrder(order);
		Integer payOrderErrcode = (Integer) payOrderJson.get("errcode");
		String payOrderMsg = (String) payOrderJson.get("msg");

		if (payOrderErrcode != 0) {
			return Result.build(40004, payOrderMsg); // 抛出异常回滚
		}
		Object payOrderData = payOrderJson.get("data");
		Result payOrderResultJson = getReturnData(payOrderJson, payOrderData, order, payOrderErrcode, payOrderMsg);

		if (payOrderResultJson.getStatus() == 500) {
			// 更新订单失败
			logger.error("平安支付宝支付失败!!!");
			result = Result.build(40004, "平安支付宝支付失败!");
			return payOrderResultJson; // 返回 提审失败的具体信息
		} else {
			// 订单创建成功
			JSONObject json = JSONObject.fromObject(payOrderResultJson.getMsg());
			String trade_qrcode = json.getString("trade_qrcode");

			result = Result.build(200, "银行支付宝转二维码成功", trade_qrcode, null, deal.getSerialNum());

			mPointsDetailService.saveMPointsDetail(deal);
		}
		// 3.支付请求
		return result;
	}

	public Result getReturnData(JSONObject respObject, Object dataStr, Order order, Integer errcode, String msg) {

		if (errcode != 0) {
			return Result.build(500, msg);
		}
		if (!respObject.isEmpty() && (dataStr != null)) {
			if (TLinx2Util.verifySign(respObject, order.getOpenKey())) { // 验签成功

				/**
				 * 5 AES解密，并hex2bin
				 */
				String respData;
				try {
					respData = TLinxAESCoder.decrypt(dataStr.toString(), order.getOpenKey());
					logger.info("接口调用成功，返回内容:" + respData);
					return Result.build(200, respData);
				} catch (Exception e) {
					return Result.build(500, e.getMessage());
				}

			} else {

				return Result.build(500, "验签失败");
			}
		} else {
			logger.info("第三方接口调用出错，没有返回data数据，请检查接口传入值是否正确");
			return Result.build(500, "没有返回data数据");
		}

	}

	@RequestMapping("/notifyURL")
	@ResponseBody
	public Result notifyResult(HttpServletRequest request, HttpServletResponse response) {

		String ord_no = request.getParameter("ord_no");
		String out_no = request.getParameter("out_no"); // 我们的流水
		String status = request.getParameter("status");

		Deal deal = this.dealService.getDealBySerialNum(out_no);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// logger.info("---------------------------deal.getStatus()="+deal.getStatus());

		if (deal == null) {
			logger.info("平安支付宝支付后回调（本地无此交易信息，所以不做任何操作）");
		}

		if (deal != null && deal.getStatus() == (byte) 2) {
			// 推送
			deal.setTradeNo(ord_no);
			deal.setNotityTime(sd.format(new Date()));

			if ("1".equals(status)) {

				if (deal.getTradeType().equals("1")) {
					deal.setTradeStatus("已支付");
					deal.setStatus(Byte.parseByte("1"));// 订单完成
					mPointsDetailService.updateMPointsDetailBySerialnum(deal.getSerialNum(), "1"); // 有效积分
				}
			} else {
				if (deal.getTradeType().equals("1")) {
					deal.setTradeStatus("未支付");
					deal.setStatus(Byte.parseByte(status));// 订单完成
				}
			}

			this.dealService.updateDeal(deal);
			logger.info("---------------------------已更新订单信息");
		} else if (deal != null && deal.getStatus() == (byte) 1) {
			// 不推送
		}
		return Result.success("notify_success");

	}

	@RequestMapping("/refund")
	@ResponseBody
	public Result refund(HttpServletRequest req) {

		Result result = null;
		String storeId = req.getParameter("storeId");
		String parentStoreId = req.getParameter("parentStoreId");
		String shopType = req.getParameter("shopType");

		PAShop shop = paService.getPAShop(storeId, parentStoreId, shopType);
		PAMerchant merchant = paService.getPAMerchant(storeId, parentStoreId, shopType);

		if (shop == null) {
			return Result.build(500, "无权限退款");
		}

		// 2.获取订单
		String originalOutTradeNo = req.getParameter("originalOutTradeNo");
		String refundOutNo = UUID.randomUUID().toString();

		// 3.保存退款订单
		String newOutTradeNo = storeId + RandomUtil.generateRefundCode();
		String tradeType = "2"; // 交易类型：1收款，2退款
		String refundType = "4"; // 退款方式：平安支付宝
		Map createMap = this.createRefundOrder(req, refundType, originalOutTradeNo, newOutTradeNo, tradeType,
				refundOutNo);

		boolean flag = (boolean) createMap.get("flag");

		if (!flag) {
			return Result.build(500, "退款时，创建订单失败!");
		}

		Deal deal = (Deal) createMap.get("deal");

		int totalFee = new BigDecimal(req.getParameter("totalFee")).multiply(new BigDecimal("100")).intValue();
		int refundFee = new BigDecimal(req.getParameter("refundFee")).multiply(new BigDecimal("100")).intValue();
		Order order = new Order();

		order.setRefundOriginalOutNo(originalOutTradeNo);
		order.setRefundOutNo(refundOutNo);
		order.setRefundOrdName("平安银行支付宝退款");
		order.setRefundAmount(refundFee + "");
		order.setTradeAccount(merchant.getAccountNo());
		order.setShopPass("123456");
		// datamap.put("trade_no", order.getTradeNo());
		order.setOpenId(shop.getOpenId());
		order.setOpenKey(shop.getOpenKey());
		order.setTradeAccount(merchant.getAccountNo());

		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, order));

		JSONObject payRefundJson = ShopPayUtils.payRefund(order);
		Integer payRefundErrcode = (Integer) payRefundJson.get("errcode");
		String payRefundMsg = (String) payRefundJson.get("msg");

		if (payRefundErrcode != 0) {
			return Result.build(40004, payRefundMsg);
		}
		Object payOrderData = payRefundJson.get("data");
		Result payOrderResultJson = getReturnData(payRefundJson, payOrderData, order, payRefundErrcode, payRefundMsg);

		if (payOrderResultJson.getStatus() == 500) {
			// 更新订单失败
			logger.error("平安支付宝退款失败!!!");
			result = Result.build(40004, "平安支付宝退款失败!");
			return payOrderResultJson; // 返回 提审失败的具体信息
		} else {

			JSONObject json = JSONObject.fromObject(payOrderResultJson.getMsg());
			String status = json.getString("status");
			String ord_no = json.getString("ord_no");

			if ("1".equals(status)) {
				this.dealService.updateRefundDeal(newOutTradeNo, ord_no);

				result = Result.build(10000, "平安支付宝退款成功!", true);
			} else {
				result = Result.build(40004, "平安支付宝退款失败!");
			}
		}
		return result;

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
	private Map createF2FOrder(HttpServletRequest request, String receType, String outTradeNo, String tradeType) {
		// 创建条码支付流水订单

		Deal deal = PADealBuilder.buildOrder(request, receType, outTradeNo, tradeType);
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
			String newOutTradeNo, String tradeType, String outRequestNo) {
		// 创建条码支付流水订单

		Deal deal = PADealBuilder.buildRefundOrder(req, refundType, originalOutTradeNo, newOutTradeNo, tradeType,
				outRequestNo);
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
