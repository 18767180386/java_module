package com.aiiju.pay.business.qq;

import com.aiiju.pay.business.qq.business.QqGZHPayBussiness;
import com.aiiju.pay.business.qq.business.QqQrCodePayBusiness;
import com.aiiju.pay.business.qq.business.QqRefundBusiness;
import com.aiiju.pay.business.qq.business.QqScanPayBusiness;
import com.aiiju.pay.business.qq.listener.QqRefundBusinessResultListener;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqScanPayReqData;
import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundReqData;

/**
 * 
 * @ClassName: QqPay 
 * @Description: qq钱包支付调用入口
 * @author 小飞 
 * @date 2017年2月7日 下午6:06:00 
 *
 */
public class QqPay {

	/**
     * 直接执行被扫支付业务逻辑（包含最佳实践流程）
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @throws Exception
     */
    public static boolean doScanPayBusiness(QqScanPayReqData scanPayReqData) throws Exception {
        return new QqScanPayBusiness().run(scanPayReqData);
    }
    
    /**
     *  扫码支付
     * @param qrCodePayReqData
     * @return
     * @throws Exception
     */
	public static String doQrCodePay(QqPayReqData payReqData) throws Exception {
		return new QqQrCodePayBusiness().run(payReqData);
	}

	/**
	 *  公众号支付
	 * @param gzhPayReqData
	 * @return
	 * @throws Exception 
	 */
	public static String doGZHPay(QqPayReqData payReqData) throws Exception {
		return new QqGZHPayBussiness().run(payReqData);
	}

	public static void doRefundBusiness(QqRefundReqData refundReqData,
			QqRefundBusinessResultListener resultListener) throws Exception{
		new QqRefundBusiness().run(refundReqData, resultListener);
	}
}
