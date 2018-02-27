package com.aiiju.pay.controller.qq;

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
import com.aiiju.pay.business.qq.QqPay;
import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.listener.QqRefundBusinessResultListener;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqScanPayReqData;
import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundReqData;
import com.aiiju.pay.business.wx.common.PaymentUtil;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pay.service.QqSubService;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.QqSub;

/**
 * 
 * @ClassName: QQPayController
 * @Description: qq钱包
 * @author 小飞
 * @date 2017年2月7日 上午9:53:45
 *
 */
@Controller
@RequestMapping("/qqpay")
public class QQPayController {

    private static Logger logger = Logger.getLogger(QQPayController.class);

    @Autowired
    private DealService dealService;

    @Autowired
    private QqSubService qqSubService;

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
    public Result sendToQQ(HttpServletRequest req, HttpServletResponse resp) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        QqSub qqSub = this.qqSubService.getQqSubByStoreId(storeId);
        if (qqSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType = "1";
        Map createMap= this.createDecometerOrder(outTradeNo, req, qqSub,tradeType);
      
       boolean flag =    (boolean) createMap.get("flag");
      
      if (!flag) {
          return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
      }
      
      Deal deal =    (Deal) createMap.get("deal");
      
        
        
        // 3.预支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        QqPayReqData payReqData = new QqPayReqData(qqSub.getSubMchId(), QqConfigure.NOTIFY_URL, "JSAPI",
                req.getParameter("subject"), "", outTradeNo, price.multiply(new BigDecimal("100")).intValue(),
                req.getRemoteAddr());
        try {
            String prepay_id = QqPay.doGZHPay(payReqData);
            if (prepay_id != null) {
                Map<String, Object> signMap = new HashMap<String, Object>();
                // 返回
                signMap.put("tokenId", prepay_id);
                // 补充
                signMap.put("total_fee", price.toString());
                signMap.put("trade_time", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                signMap.put("out_trade_no", outTradeNo);
                signMap.put("finish_url", "https://trade.ecbao.cn/qqpay/finish");
                // 返回结果
                mPointsDetailService.saveMPointsDetail(deal);
                return Result.success(signMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",qq钱包一码多付异常信息:" + e.getMessage());
        }
        return Result.build(500, "发起qq钱包预订单请求失败!");
    }

    /**
     * 付款码支付
     *  ok
     * @param req
     * @return
     */
    @RequestMapping("/barCodePay")
    @ResponseBody
    public Result barCodePay(HttpServletRequest req) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        QqSub qqSub = this.qqSubService.getQqSubByStoreId(storeId);
        if (qqSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType = "1";
        Map createMap = this.createUnifiedOrder(req, "32", outTradeNo, qqSub,tradeType);
   
        boolean flag =    (boolean) createMap.get("flag");
       
       if (!flag) {
           return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
       }
       
       Deal returndeal =    (Deal) createMap.get("deal");
         
         
        
        
        // 3.支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        QqScanPayReqData scanPayReqData = new QqScanPayReqData(qqSub.getSubMchId(),
                req.getParameter("authCode"), QqConfigure.NOTIFY_URL, req.getParameter("subject"), "",
                outTradeNo, price.multiply(new BigDecimal("100")).intValue(), "", // TODO 设备号
                req.getRemoteAddr());
        Result result = null;
        try {
            boolean payRT = QqPay.doScanPayBusiness(scanPayReqData);
            if (payRT) {
                Deal deal = this.dealService.getDealBySerialNum(outTradeNo);
                deal.setStatus(Byte.parseByte("1"));// 订单完成
                Boolean updateRT = this.dealService.updateDeal(deal);
                if (updateRT) {
                    result = Result.build(10000, "qq钱包支付成功!");
                    mPointsDetailService.saveMPointsDetail(returndeal);
                    
                } else {
                    result = Result.build(10000, "qq钱包支付成功!但订单状态修改失败!");
                    mPointsDetailService.saveMPointsDetail(deal);
                    
                }
            } else {
                result = Result.build(40004, "qq钱包支付失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",qq钱包付款码支付异常信息:" + e.getMessage());
            result = Result.build(40004, "qq钱包支付失败!" + e.getMessage());
        }
        return result;
    }

    /**
     * 扫码支付
     * 
     * @param req
     * @return
     */
    @RequestMapping("/qrCodePay")
    @ResponseBody
    public Result tradePrecreate(HttpServletRequest req) {
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        QqSub qqSub = this.qqSubService.getQqSubByStoreId(storeId);
        if (qqSub == null) {
            return Result.build(500, "商户未签约");
        }
        // 2.保存订单
        String outTradeNo = storeId + RandomUtil.generateCode();
        String tradeType = "1";
        Map createMap = this.createUnifiedOrder(req, "33", outTradeNo, qqSub,tradeType);
 

        boolean flag =    (boolean) createMap.get("flag");
       
       if (!flag) {
           return Result.build(500, "创建订单失败!请重新尝试扫描二维码");
       }
       
       Deal deal =    (Deal) createMap.get("deal");
    
        // 3.支付请求
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        QqPayReqData payReqData = new QqPayReqData(qqSub.getSubMchId(), QqConfigure.NOTIFY_URL, "NATIVE",
                req.getParameter("subject"), "", outTradeNo, price.multiply(new BigDecimal("100")).intValue(),
                req.getRemoteAddr());
        Result result = null;
        try {
            String qrCode = QqPay.doQrCodePay(payReqData);
            if (qrCode != null) {
                result = Result.success(qrCode);
                mPointsDetailService.saveMPointsDetail(deal);
            } else {
                result = Result.build(40004, "qq钱包统一下单请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("当前店铺编号:" + storeId + ",qq钱包扫码支付异常信息:" + e.getMessage());
            result = Result.build(40004, "qq钱包支付失败!" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/refund")
    @ResponseBody
    public Result refund(HttpServletRequest req) {
        Result result = null;
        // 1.查询子商户信息
        String storeId = req.getParameter("storeId");
        QqSub qqSub = this.qqSubService.getQqSubByStoreId(storeId);
        if (qqSub == null) {
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
        Boolean rt = this.createRefundOrder(req, "3", originalOutTradeNo, newOutTradeNo, qqSub,"2",outRefundNo);
        if (!rt) {
            return Result.build(500, "创建退款订单失败!请重新尝试扫描条码");
        }
        // 4.调用接口
        QqRefundReqData refundReqData = new QqRefundReqData(qqSub.getSubMchId(), "", // transaction_id
                originalOutTradeNo, outRefundNo, Integer.parseInt(req.getParameter("refundFee")));

        try {
            QqRefundBusinessResultListener resultListener = new QqRefundBusinessResultListener();
            QqPay.doRefundBusiness(refundReqData, resultListener);
            if (resultListener.getResult() != null
                    && "on_refund_success".equals(resultListener.getResult())) {
                Deal deal = this.dealService.getDealBySerialNum(newOutTradeNo);
                deal.setStatus(Byte.parseByte("1"));// 订单完成
                this.dealService.updateDeal(deal);
                result = Result.build(10000, "qq钱包退款成功!", true);
            } else {
                logger.error("qq钱包订单号[" + originalOutTradeNo + "]退款错误: ");
                result = Result.build(40004, "qq钱包退款失败!" + resultListener.getResult(), outRefundNo);
            }
        } catch (Exception e) {
            logger.error("当前店铺编号:" + storeId + ",qq钱包订单号[" + originalOutTradeNo + "]退款失败");
            result = Result.build(40004, "qq钱包退款失败!" + e.getMessage());
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
                    Deal deal = this.dealService.getDealBySerialNum(outTradeNo);
                    deal.setStatus(Byte.parseByte("1"));// 订单完成
                    Boolean updateRT = this.dealService.updateDeal(deal);
                    if (updateRT) {
                        retMap.put("return_code", "SUCCESS");
                        retMap.put("return_msg", "OK");
                        
                        mPointsDetailService.updateMPointsDetailBySerialnum(deal.getSerialNum(), "1");
                        
                        logger.info("支付成功！out_trade_no:" + outTradeNo + ", result_code:" + resultCode);
                    }
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
        return "pay/qq/finish";
    }

    /**
     * 扫描台卡 流水订单
     * 
     * @param out_trade_no 流水号
     * @param price 金额
     * @return
     */
    private Map createDecometerOrder(String outTradeNo, HttpServletRequest req, QqSub qqSub,String tradeType) {
        // 创建台卡流水订单

        Deal deal = DealBuilder.buildDecometerOrder("31", outTradeNo, req, "",tradeType);// 参照
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
    private Map createUnifiedOrder(HttpServletRequest request, String receType, String outTradeNo,
            QqSub qqSub,String tradeType) {
        // 创建条码支付流水订单

        AppAuth auth = new  AppAuth ();
        auth.setStoreId(qqSub.getStoreId());
   
        Deal deal = DealBuilder.buildOrder(request, receType, outTradeNo, auth,tradeType);
    
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

    private Boolean createRefundOrder(HttpServletRequest req, String refundType, String originalOutTradeNo,
            String newOutTradeNo, QqSub qqSub,String tradeType,String outRefundNo) {
        // 创建条码支付流水订单
        Map<String, String> params = new HashMap<String, String>();
        params.put("storeId", qqSub.getStoreId());
        Deal deal = DealBuilder.buildRefundOrder(req, refundType, originalOutTradeNo, newOutTradeNo, params,tradeType,outRefundNo);
        return this.dealService.saveRefundDeal(deal, "interface").getStatus() == 200;
    }
}
