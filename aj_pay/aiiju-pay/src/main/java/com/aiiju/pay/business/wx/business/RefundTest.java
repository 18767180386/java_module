package com.aiiju.pay.business.wx.business;

import java.math.BigDecimal;

import com.aiiju.common.pojo.Result;
import com.aiiju.pay.business.wx.WXPay;
import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.listener.DefaultRefundBusinessResultListener;
import com.aiiju.pay.business.wx.protocol.refund_protocol.RefundReqData;

/** 
 * @ClassName RefundTest
 * @Description
 * @author zong
 * @CreateDate 2017年8月10日 上午10:42:23
 */
public class RefundTest {

	
	public static void main(String[] args) {
		
//		<appid>wxc4e0baf5b835ef99</appid>
//		  <mch_id>1415979202</mch_id>
//		  <sub_mch_id>1425512902</sub_mch_id>
//		  <device_info></device_info>
//		  <nonce_str>wzaa0ogkat37zkpt4wx2cu0eccxwm307</nonce_str>
//		  <sign>DC13ACF53DCE15B981B960DDE88CE300</sign>
//		  <transaction_id></transaction_id>
//		  <out_trade_no>11256839XS15023307839118573278</out_trade_no>
//		  <out_refund_no>0bd95b35-4128-4a5c-9d2d-5ef36609db24</out_refund_no>
//		  <total_fee>2222</total_fee>
//		  <refund_fee>1900</refund_fee>
//		  <refund_fee_type>CNY</refund_fee_type>
//		  <op_user_id>1415979202</op_user_id>

		
		String subMchId = "1425512902";
		
		String originalOutTradeNo = "11256839XS15023307839118573278";
		
		String outRefundNo ="0bd95b35-4128-4a5c-9d2d-5ef36609db25";
		
		int total_fee = 1999;
		int refund_fee = 1999;
		
	    // 4.调用接口
        RefundReqData refundReqData = new RefundReqData(
        		subMchId, 
                "", // 微信订单号
                originalOutTradeNo, // 商户订单号
                "", // 设备号
                outRefundNo, 
                total_fee,
                refund_fee, 
                Configure.getMchid(), "CNY");

     
            DefaultRefundBusinessResultListener resultListener = new DefaultRefundBusinessResultListener();
            try {
				WXPay.doRefundBusiness(refundReqData, resultListener);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (resultListener.getResult() != null && "on_refund_success".equals(resultListener.getResult())) {
            
                System.out.println("微信退款成功!");
            } else {
                System.out.println(("微信订单号[" + originalOutTradeNo + "]退款错误: "));
                System.out.println("微信退款失败!" + resultListener.getResult()+"     "+outRefundNo);
            }
		
	}
	
}
