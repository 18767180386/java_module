package com.aiiju.common.pa_utils.shoppay;

import java.util.Date;
import java.util.TreeMap;

import com.aiiju.pojo.Order;

import net.sf.json.JSONObject;

//~--- JDK imports ------------------------------------------------------------

//~--- classes ----------------------------------------------------------------

/**
 * Class PayCancelDemo
 * Description
 * Create 2017-04-19 16:45:25
 * @author Benny.YEE
 */
public class ShopPayUtils {

    /**
     * Method payCancel
     * Description 说明：
     *
     * @param ordNo 说明：订单号            二者必须填一个
     * @param outNo 说明：开发者流水号       二者必须填一个
     */
    public static JSONObject payCancel(Order order) {
    	JSONObject respObject = new JSONObject();
        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // 请求参数的map

            postmap.put("open_id", order.getOpenId());
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("out_no", order.getOutNo());
            datamap.put("ord_no", order.getOrdNo());

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,order.getOpenKey());

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,order.getOpenKey());

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.PAYCANCEL);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
		return respObject;
    }
    
   
    public static JSONObject payOrder(Order order) {

    	JSONObject respObject = new JSONObject();
        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // 请求参数的map

            postmap.put("open_id", order.getOpenId());
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("out_no", order.getOutNo());
            datamap.put("pmt_tag", order.getPmtTag());
            datamap.put("pmt_name", order.getPmtName());
            datamap.put("ord_name", order.getOrdName());
            datamap.put("original_amount", order.getOriginalAmount()+"");
            datamap.put("discount_amount", order.getDiscountAmount()+"");
            datamap.put("ignore_amount", order.getIgnoreAmount()+"");
            datamap.put("trade_amount", order.getTradeAmount()+"");
            datamap.put("trade_account", order.getTradeAccount());
            datamap.put("trade_no", order.getTradeNo());
            datamap.put("remark", order.getRemark());
            datamap.put("auth_code", order.getAuthCode());
            datamap.put("tag", order.getTag());
            datamap.put("jump_url", order.getJumpUrl());
            datamap.put("notify_url", order.getNotifyUrl());

            
            
//        	String outNo, 17         ++++++++
//    		String pmtTag, 16          ++++++++
//    		String pmtName,15
//    		String ordName,14             ++++++++
//    		Integer originalAmount,13      ++++++++
//            Integer discountAmount,12
//            Integer ignoreAmount,11 
//            Integer tradeAmount, 10     ++++++++
//            String tradeAccount,9
//            String tradeNo, 8
//            String tradeResult, 7
//            String remark,6
//            String authCode,5
//            String tag,4
//            String jumpUrl,3 
//            String notifyUrl,2       ++++++++
//            String auth_code)1      ++++++++
            
            
            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,order.getOpenKey());

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,order.getOpenKey());

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.PAYORDER);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);
     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respObject;
    }
    
    /**
     * Method payRefund
     * Description 说明：
     *
     * @param outNo 说明：
     * @param refundOutNo 说明：
     * @param refundOrdName 说明：
     * @param refundAmount 说明：
     * @param tradeAccount 说明：
     * @param tradeNo 说明：
     * @param tradeResult 说明：
     * @param tmlToken 说明：
     * @param remark 说明：
     * @param shopPass 说明：
     */
    public static JSONObject payRefund(Order order) {
    	JSONObject respObject = new JSONObject();
        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // 请求参数的map

            postmap.put("open_id", order.getOpenId());
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("out_no", order.getRefundOriginalOutNo()); //原始订单的开发者交易流水号
            datamap.put("refund_out_no", order.getRefundOutNo());
            datamap.put("refund_ord_name", order.getRefundOrdName());
            datamap.put("refund_amount", order.getRefundAmount() + "");
            datamap.put("trade_account", order.getTradeAccount());
            datamap.put("trade_no", order.getTradeNo());
            datamap.put("trade_result", order.getTradeResult());
            datamap.put("tml_token", order.getTmlToken());
            datamap.put("remark", order.getRemark());
            datamap.put("shop_pass", TLinxSHA1.SHA1(order.getShopPass()));

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,order.getOpenKey());

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,order.getOpenKey());

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.PAYREFUND);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);
            
            return respObject;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respObject;
    }
    
    
    /**
     * Method queryOrderList
     * Description 说明：
     *
     * @param outNo 说明：开发者流水号 可为空
     * @param tradeNo 说明：交易单号（收单机构交易号，可为空）
     * @param ordNo 说明：付款订单号
     * @param pmtTag 说明：支付方式标签
     * @param ordType 说明：交易方式1消费，2辙单（退款）
     * @param status 说明：订单状态（1交易成功，2待支付，4已取消，9等待用户输入密码确认）
     * @param sdate 说明：订单开始日期
     * @param edate 说明：订单结束日期
     */
    public String queryOrderList(Order order,String sdate, String edate) {

        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {
            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();//请求参数的map
            postmap.put("open_id", order.getOpenId());
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();//data参数的map
            datamap.put("out_no", order.getOutNo());
            datamap.put("trade_no", order.getTradeNo());
            datamap.put("ord_no", order.getOrdNo());
            datamap.put("pmt_tag", order.getPmtTag());
            
            
            datamap.put("ord_type", order.getOrdType());
            
            
            if(!"2".equals(order.getOrdType())){
            	 datamap.put("status", order.getPayStatus());
            }else{
            	 datamap.put("status", order.getRefundStatus());
            }
           
            datamap.put("sdate", sdate);
            datamap.put("edate", edate);

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,order.getOpenKey());

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,order.getOpenKey());

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.ORDERLIST);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);
            Object dataStr    = respObject.get("data");

            if (!rspStr.isEmpty() || ( dataStr != null )) {
                if (TLinx2Util.verifySign(respObject,order.getOpenKey())) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                    String respData = TLinxAESCoder.decrypt(dataStr.toString(), order.getOpenKey());

                    System.out.println("==================响应data内容:" + respData);
                    return respData;
                } else {
                    System.out.println("=====验签失败=====");
                }
            } else {
                System.out.println("=====没有返回data数据=====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * Method queryOrderList
     * Description 说明：
     *
     * @param outNo 说明：开发者流水号
     */
    public static String queryOrderView(String open_id,String open_key,String ord_no,String out_no) {
    	
        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // 请求参数的map

            postmap.put("open_id", open_id);
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map
            datamap.put("out_no", out_no);
            datamap.put("ord_no", ord_no);
          

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,open_key);

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,open_key);

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.ORDERVIEW);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);
            Object dataStr    = respObject.get("data");

            if (!rspStr.isEmpty() || ( dataStr != null )) {
                if (TLinx2Util.verifySign(respObject,open_key)) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                    String respData = TLinxAESCoder.decrypt(dataStr.toString(), open_key);

                    System.out.println("==================响应data内容:" + respData);
                    return respData;
                } else {
                    System.out.println("=====验签失败=====");
                }
            } else {
                System.out.println("=====没有返回data数据=====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String queryPayList(String open_id,String open_key){
        // 初始化参数
        String pmtType   = "0,1,2,3";
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {
            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();//请求参数的map
            postmap.put("open_id", open_id);
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();//data参数的map
            datamap.put("pmt_type", pmtType);

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,open_key);

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,open_key);

            /**
             * 3 请求、响应
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.PAYLIST);
            System.out.println("返回字符串="+rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);
            Object dataStr    = respObject.get("data");

            if (!rspStr.isEmpty() || (dataStr != null)) {
                if (TLinx2Util.verifySign(respObject,open_key)) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                    String respData = TLinxAESCoder.decrypt(dataStr.toString(), open_key);

                    System.out.println("==================响应data内容:" + respData);
                    return respData;
                } else {
                    System.out.println("=====验签失败=====");
                }
            } else {
                System.out.println("=====没有返回data数据=====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Method queryPayStatus
     * Description 说明：
     *
     * @param ordNo 说明：订单号            二者必须填一个
     * @param outNo 说明：开发者流水号       二者必须填一个
     */
    public static JSONObject queryPayStatus(Order order) {

    	JSONObject respObject = new JSONObject();
    	
        // 初始化参数
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // 请求参数的map

            postmap.put("open_id", order.getOpenId());
            postmap.put("timestamp", timestamp);

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("out_no", order.getOutNo());
            datamap.put("ord_no", order.getOrdNo());

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            TLinx2Util.handleEncrypt(datamap, postmap,order.getOpenKey());

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行md5加密(小写)，得到签名
             */
            TLinx2Util.handleSign(postmap,order.getOpenKey());

            /**
             * 3 请求、响应
             *
             */
            String rspStr = TLinx2Util.handlePost(postmap, TestParams.QUERYPAYSTATUS);

            System.out.println("返回字符串=" + rspStr);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return respObject;
    }
  
    
     public static void main(String[] args) {
		
    	 String open_id= "c8b54ee8ded0c3ad18ac079304af19de";
    	 String open_key= "a0128cfe57f430445b367450e7b2b1a6";
    	 String ord_no= "9150359075987847213026911";
    	 String out_no= "";

    	// queryOrderView(open_id, open_key, ord_no,null);
    	// queryPayStatus(open_id, open_key, ord_no, out_no);
	}

}

