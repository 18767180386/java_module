package com.aiiju.pay.controller.wx;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.pay.bo.DealBuilder;
import com.aiiju.pay.business.wx.WXPay;
import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.common.PaymentUtil;
import com.aiiju.pay.business.wx.common.RandomStringGenerator;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.listener.DefaultRefundBusinessResultListener;
import com.aiiju.pay.business.wx.listener.DefaultScanPayBusinessResultListener;
import com.aiiju.pay.business.wx.protocol.pay_protocol.PayReqData;
import com.aiiju.pay.business.wx.protocol.pay_protocol.ScanPayReqData;
import com.aiiju.pay.business.wx.protocol.refund_protocol.RefundReqData;
import com.aiiju.pay.service.DealAccountService;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pay.service.WxSubService;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealAccount;
import com.aiiju.pojo.WxSub;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: WXPayController
 * @Description: 微信支付控制器
 * @author 小飞
 * @date 2016年12月7日 下午2:29:02
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WXPayController {

    private static Logger logger = Logger.getLogger(WXPayController.class);

    @Autowired
    private DealService dealService;
    @Autowired
    private WxSubService wxSubService;

	@Autowired
	private DealAccountService dealAccountService;
    @Autowired
    private MPointsDetailService mPointsDetailService;
    /**
     * 公众号支付
     *  ok
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/pay")
    @ResponseBody
    public Result sendToWeiXin(HttpServletRequest req, HttpServletResponse resp) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        WxSub wxSub = this.wxSubService.getWxSubByStoreId(storeId);
        if (wxSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType ="1";
        Map createMap = this.createDecometerOrder(outTradeNo, req, wxSub,tradeType);

       boolean flag =    (boolean) createMap.get("flag");
      
      if (!flag) {
          return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
      }
      
      Deal deal =    (Deal) createMap.get("deal");
        
	
		// 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, null,wxSub));
        
        
        // 3.预支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        PayReqData payReqData = new PayReqData(wxSub.getSubMchId(), // 子商户号
                Configure.NOTIFY_URL, // 回调URL
                "JSAPI", // trade_type
                req.getParameter("openId"), // openid
                "", // 商品id
                req.getParameter("shopName") + "商品", // 要支付的商品的描述信息
                storeId, // 附加数据
                outTradeNo, // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                price.multiply(new BigDecimal("100")).intValue(), // 订单总金额，单位为“分”，只能整数
                "", // 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                req.getRemoteAddr(), // 订单生成的机器IP
                DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"), // 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                "", // 订单失效时间，格式同上
                "" // 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        );
        try {
            String prepay_id = WXPay.doGZHPay(payReqData);
            if (prepay_id != null) {
                Map<String, Object> signMap = new HashMap<String, Object>();
                signMap.put("appId", Configure.getAppid());
                signMap.put("timeStamp", Long.toString(new Date().getTime()));
                signMap.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
                signMap.put("package", "prepay_id=" + prepay_id);
                signMap.put("signType", "MD5");
                // 签名
                String paySign = Signature.getSign(signMap);
                // 返回
                signMap.put("prepay_id", signMap.get("package"));
                signMap.put("paySign", paySign);
                // 补充
                signMap.put("total_fee", price.toString());
                signMap.put("trade_time", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                signMap.put("out_trade_no", outTradeNo);
                signMap.put("finish_url", "https://trade.ecbao.cn/wxpay/finish");
                
           	 mPointsDetailService.saveMPointsDetail(deal);
                
                // 返回结果
                return Result.success(signMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",微信一码多付异常信息:" + e.getMessage());
        }
        return Result.build(500, "发起微信预订单请求失败!");
    }

    /**
     * 刷卡支付
     *  ok
     * @param req
     * @return
     */
    @RequestMapping("/barCodePay")
    @ResponseBody
    public Result barCodePay(HttpServletRequest req) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        WxSub wxSub = this.wxSubService.getWxSubByStoreId(storeId);
        if (wxSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType ="1";
        Map createMap = this.createUnifiedOrder(req, "22", outTradeNo, wxSub,tradeType);
        
       boolean flag =    (boolean) createMap.get("flag");
      
      if (!flag) {
          return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
      }
      
        Deal returndeal =    (Deal) createMap.get("deal");
      
     // 保存交易时所用的授权账户
     		dealAccountService.saveDealAccount(DealAccount.getDealAccount(returndeal, null,wxSub));
        // 3.支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        ScanPayReqData scanPayReqData = new ScanPayReqData(wxSub.getSubMchId(), // 子商户号
                req.getParameter("authCode"), // 付款码
                req.getParameter("subject"), // 商品信息
                storeId, // 附加数据，原样返回
                outTradeNo, // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                price.multiply(new BigDecimal("100")).intValue(), // 订单总金额，单位为“分”，只能整数
                "", // 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                req.getRemoteAddr(), // 订单生成的机器IP
                DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"), // 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                "", // 订单失效时间，格式同上
                "" // 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        );
        DefaultScanPayBusinessResultListener resultListener = new DefaultScanPayBusinessResultListener();
        Result result = null;
        try {
            WXPay.doScanPayBusiness(scanPayReqData, resultListener);
            if (resultListener.getResult() != null && "on_success".equals(resultListener.getResult())) {
                Deal deal = this.dealService.getDealBySerialNum(outTradeNo);
                deal.setStatus(Byte.parseByte("1"));// 订单完成
                deal.setTradeStatus("已支付");
                Boolean updateRT = this.dealService.updateDeal(deal);
                if (updateRT) {
                   // result = Result.build(10000, "微信支付成功!");
               	      mPointsDetailService.saveMPointsDetail(deal);
                	  result = Result.build(10000, "微信支付成功!", null, null, deal.getSerialNum());
                } else {
                   // result = Result.build(10000, "微信支付成功!但订单状态修改失败!");
                    result = Result.build(10000, "微信支付成功!但订单状态修改失败!", null, null, deal.getSerialNum());
                }
            } else {
            	
                
                result = Result.build(40004, "微信支付失败!" + resultListener.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",微信刷卡支付异常信息:" + e.getMessage());
            result = Result.build(40004, "微信支付失败!" + e.getMessage());
        }
        return result;
    }

    /**
     * 扫码支付
     *  ok
     * @param req
     * @return
     */
    @RequestMapping("/qrCodePay")
    @ResponseBody
    public Result tradePrecreate(HttpServletRequest req) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        WxSub wxSub = this.wxSubService.getWxSubByStoreId(storeId);
        if (wxSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType = "1";
        Map createMap= this.createUnifiedOrder(req, "23", outTradeNo, wxSub,tradeType);

       boolean flag =    (boolean) createMap.get("flag");
      
      if (!flag) {
          return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
      }
      
      Deal deal =    (Deal) createMap.get("deal");
      
   // 保存交易时所用的授权账户
		dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, null,wxSub));
        
		
        
        // 3.发起支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        PayReqData payReqData = new PayReqData(wxSub.getSubMchId(), // 子商户号
                Configure.NOTIFY_URL, // 回调URL
                "NATIVE", // trade_type
                "", // openid
                RandomUtil.generateProductId(), // 商品id
                req.getParameter("subject"), // 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
                storeId, // 附加数据
                outTradeNo, // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                price.multiply(new BigDecimal("100")).intValue(), // 订单总金额，单位为“分”，只能整数
                "", // 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                req.getRemoteAddr(), // 订单生成的机器IP
                DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"), // 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                "", // 订单失效时间，格式同上
                "" // 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        );
        Result result = null;
        try {
            String qrCode = WXPay.doQrCodePay(payReqData);
            
       	   mPointsDetailService.saveMPointsDetail(deal);
       	 
            if (qrCode != null) {
//                result = Result.success(qrCode);
                result = Result.build(200, "预下单成功",qrCode, null, deal.getSerialNum());
            } else {
                result = Result.build(40004, "微信统一下单请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",微信扫码支付异常信息:" + e.getMessage());
            result = Result.build(40004, "微信支付失败!" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/refund")
    @ResponseBody
    public Result refund(HttpServletRequest req) {
        Result result = null;
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        WxSub wxSub = this.wxSubService.getWxSubByStoreId(storeId);
        if (wxSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.获取订单
        String originalOutTradeNo = req.getParameter("originalOutTradeNo");
//        String refundType = req.getParameter("refundType");
        String outRefundNo = req.getParameter("outRefundNo");
        if (StringUtils.isBlank(outRefundNo)) {
            outRefundNo = UUID.randomUUID().toString();
         
        }
        // 3.保存退款订单
        String newOutTradeNo = storeId + RandomUtil.generateRefundCode();
        String tradeType = "2";
        Map createMap = this.createRefundOrder(req, "2", originalOutTradeNo, newOutTradeNo, wxSub,tradeType,outRefundNo);
        
        boolean flag =    (boolean) createMap.get("flag");
        
        if (!flag) {
            return Result.build(500, "退款时，创建订单失败!");
        }
        
        Deal deal =    (Deal) createMap.get("deal");
      
        // 保存交易时所用的授权账户
     	dealAccountService.saveDealAccount(DealAccount.getDealAccount(deal, null,wxSub));
        
        
        // 4.调用接口
        RefundReqData refundReqData = new RefundReqData(
                wxSub.getSubMchId(), 
                "", // 微信订单号
                originalOutTradeNo, // 商户订单号
                "", // 设备号
                outRefundNo, 
                new BigDecimal(req.getParameter("totalFee")).multiply(new BigDecimal("100")).intValue(),
                new BigDecimal(req.getParameter("refundFee")).multiply(new BigDecimal("100")).intValue(), 
                Configure.getMchid(), "CNY");

        try {
            DefaultRefundBusinessResultListener resultListener = new DefaultRefundBusinessResultListener();
            WXPay.doRefundBusiness(refundReqData, resultListener);
            if (resultListener.getResult() != null && "on_refund_success".equals(resultListener.getResult())) {
                this.dealService.updateRefundDeal(newOutTradeNo, "");
                result = Result.build(10000, "微信退款成功!", true);
            } else {
                logger.error("微信订单号[" + originalOutTradeNo + "]退款错误: ");
                result = Result.build(40004, "微信退款失败!" + resultListener.getResult(), outRefundNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",微信订单号[" + originalOutTradeNo + "]退款失败");
            result = Result.build(40004, "微信退款失败!" + e.getMessage());
        }

        return result;
    }

    @RequestMapping("/notifyURL")
    @SuppressWarnings("unchecked")
    public void notifyURL(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            String ret = "";
            // 解析结果存储在HashMap
            Map<String, String> map = new HashMap<String, String>();
            // 读取输入流
            SAXReader reader = new SAXReader();
            inputStream = request.getInputStream();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }

            Map<String, String> retMap = new HashMap<String, String>();

            String returnCode = map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = map.get("result_code");
                String outTradeNo = map.get("out_trade_no");
                if ("SUCCESS".equals(resultCode)) {
                	logger.info("流水号:" + outTradeNo + "订单修改状态");
                    Deal deal = this.dealService.getDealBySerialNum(outTradeNo);
                    
                    
                    if("1".equals(deal.getStatus()+"")){
						logger.info("（微信回调）但是----->修改状态时，发现该订单是成功订单，所以不进行更新操作");
					   
					}else{
						
					    deal.setStatus(Byte.parseByte("1"));// 订单完成
	                    deal.setTradeStatus("已支付");
	                    Boolean updateRT = this.dealService.updateDeal(deal);
	                    
	                  
	                    
	                    if (updateRT) {
	                    	  mPointsDetailService.updateMPointsDetailBySerialnum(deal.getSerialNum(), "1");
	                        retMap.put("return_code", "SUCCESS");
	                        retMap.put("return_msg", "OK");
	                        logger.info("支付成功！out_trade_no:" + outTradeNo + ", result_code:" + resultCode);
	                    }
					}
                    
                    //用流水去缓存中取，判断是否使用scrm优惠，如使用，通知scrm
                    Map<String,Object> noticeMap = new HashMap<>();
                    noticeMap.put("outTradeNo", outTradeNo);
                    noticeMap.put("totalFee",  map.get("total_fee"));
                    wxSubService.noticeSCRM(noticeMap);
                
                } else {
                    String errCode = map.get("err_code");
                    logger.error("支付失败！out_trade_no:" + outTradeNo + ",result_code:" + resultCode
                            + ", err_code:" + errCode);
                    retMap.put("return_code", returnCode);
                    retMap.put("return_msg", resultCode);
                }
            } else {
                String returnMsg = map.get("return_msg");
                retMap.put("return_code", returnCode);
                retMap.put("return_msg", returnMsg);
                logger.error("支付通信失败！" + returnMsg);
            }
            ret = PaymentUtil.mapToXml(retMap);
            response.getWriter().print(ret);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/finish")
    public String finish(HttpServletRequest request) {
        request.setAttribute("total_fee", request.getParameter("total_fee"));
        request.setAttribute("trade_time", request.getParameter("trade_time"));
        request.setAttribute("out_trade_no", request.getParameter("out_trade_no"));
        return "pay/wx/finish";
    }

    /**
     * 扫描台卡 流水订单
     * 
     * @param out_trade_no 流水号
     * @param price 金额
     * @return
     */
    private Map createDecometerOrder(String outTradeNo, HttpServletRequest req, WxSub wxSub,String tradeType) {
        // 创建台卡流水订单

        Deal deal = DealBuilder.buildDecometerOrder("21", outTradeNo, req,"",tradeType);// 参照
                                                                                     // com.aiiju.pay.constant.TradeConstant中定义的参数

        Result result =  this.dealService.saveDeal(deal, "interface");
        
        Map map = new HashMap();
        map.put("deal", deal);
        if(result.getStatus()==200){
        	 map.put("flag", true);
        }else{
        	 map.put("flag", false);
        }
        return map;
        
        
    }

    /**
     * 统一下单支付订单
     * 
     * @param request
     * @param receType
     * @param outTradeNo
     * @param wxSub
     * @return
     */
    private Map createUnifiedOrder(HttpServletRequest request, String receType, String outTradeNo,WxSub wxSub,String tradeType) {
        // 创建条码支付流水订单
        Map<String, String> params = new HashMap<String, String>();
        
        
        AppAuth auth = new AppAuth();
        auth.setStoreId(wxSub.getStoreId());
        Deal deal = DealBuilder.buildOrder(request, receType, outTradeNo, auth,tradeType);
       // return this.dealService.saveDeal(deal, "interface");
        

        Result result = this.dealService.saveDeal(deal, "interface");
        
        Map map = new HashMap();
        map.put("deal", deal);
        if(result.getStatus()==200){
        	 map.put("flag", true);
        }else{
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
            String newOutTradeNo, WxSub wxSub,String tradeType,String outRefundNo) {
        // 创建条码支付流水订单
        Map<String, String> params = new HashMap<String, String>();
        params.put("storeId", wxSub.getStoreId());
        Deal deal = DealBuilder.buildRefundOrder(req, refundType, originalOutTradeNo, newOutTradeNo, params,tradeType,outRefundNo);
        
       Result result = this.dealService.saveRefundDeal(deal, "interface");
        
        Map map = new HashMap();
        map.put("deal", deal);
        if(result.getStatus()==200){
        	 map.put("flag", true);
        }else{
        	 map.put("flag", false);
        }

        
        
        
        return map;
    }
}
