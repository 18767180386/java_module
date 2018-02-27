package com.aiiju.pay.business.wx;

import com.aiiju.pay.business.wx.business.DownloadBillBusiness;
import com.aiiju.pay.business.wx.business.GZHPayBussiness;
import com.aiiju.pay.business.wx.business.QrCodePayBusiness;
import com.aiiju.pay.business.wx.business.RefundBusiness;
import com.aiiju.pay.business.wx.business.RefundQueryBusiness;
import com.aiiju.pay.business.wx.business.ScanPayBusiness;
import com.aiiju.pay.business.wx.protocol.downloadbill_protocol.DownloadBillReqData;
import com.aiiju.pay.business.wx.protocol.pay_protocol.PayReqData;
import com.aiiju.pay.business.wx.protocol.pay_protocol.ScanPayReqData;
import com.aiiju.pay.business.wx.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.aiiju.pay.business.wx.protocol.refund_protocol.RefundReqData;
import com.aiiju.pay.business.wx.protocol.refund_query_protocol.RefundQueryReqData;
import com.aiiju.pay.business.wx.protocol.reverse_protocol.ReverseReqData;
import com.aiiju.pay.business.wx.service.DownloadBillService;
import com.aiiju.pay.business.wx.service.RefundQueryService;
import com.aiiju.pay.business.wx.service.RefundService;
import com.aiiju.pay.business.wx.service.ReverseService;
import com.aiiju.pay.business.wx.service.ScanPayQueryService;
import com.aiiju.pay.business.wx.service.ScanPayService;

/**
 * 
 * @ClassName: WXPay 
 * @Description: SDK总入口
 * @author 小飞 
 * @date 2016年12月7日 下午4:58:23 
 *
 */
public class WXPay {

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String requestScanPayService(ScanPayReqData scanPayReqData) throws Exception{
        return new ScanPayService().request(scanPayReqData);
    }

    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
	public static String requestScanPayQueryService(ScanPayQueryReqData scanPayQueryReqData) throws Exception{
		return new ScanPayQueryService().request(scanPayQueryReqData);
	}

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public static String requestRefundService(RefundReqData refundReqData) throws Exception{
        return new RefundService().request(refundReqData);
    }

    /**
     * 请求退款查询服务
     * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
	public static String requestRefundQueryService(RefundQueryReqData refundQueryReqData) throws Exception{
		return new RefundQueryService().request(refundQueryReqData);
	}

    /**
     * 请求撤销服务
     * @param reverseReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
	public static String requestReverseService(ReverseReqData reverseReqData) throws Exception{
		return new ReverseService().request(reverseReqData);
	}

    /**
     * 请求对账单下载服务
     * @param downloadBillReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public static String requestDownloadBillService(DownloadBillReqData downloadBillReqData) throws Exception{
        return new DownloadBillService().request(downloadBillReqData);
    }

    /**
     * 直接执行被扫支付业务逻辑（包含最佳实践流程）
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @throws Exception
     */
    public static void doScanPayBusiness(ScanPayReqData scanPayReqData, ScanPayBusiness.ResultListener resultListener) throws Exception {
        new ScanPayBusiness().run(scanPayReqData, resultListener);
    }

    /**
     * 调用退款业务逻辑
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 业务逻辑可能走到的结果分支，需要商户处理
     * @throws Exception
     */
    public static void doRefundBusiness(RefundReqData refundReqData, RefundBusiness.ResultListener resultListener) throws Exception {
        new RefundBusiness().run(refundReqData,resultListener);
    }

    /**
     * 运行退款查询的业务逻辑
     * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @throws Exception
     */
    public static void doRefundQueryBusiness(RefundQueryReqData refundQueryReqData,RefundQueryBusiness.ResultListener resultListener) throws Exception {
        new RefundQueryBusiness().run(refundQueryReqData,resultListener);
    }

    /**
     * 请求对账单下载服务
     * @param downloadBillReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @return API返回的XML数据
     * @throws Exception
     */
    public static void doDownloadBillBusiness(DownloadBillReqData downloadBillReqData,DownloadBillBusiness.ResultListener resultListener) throws Exception {
        new DownloadBillBusiness().run(downloadBillReqData,resultListener);
    }

    /**
     *  扫码支付
     * @param qrCodePayReqData
     * @return
     * @throws Exception
     */
	public static String doQrCodePay(PayReqData payReqData) throws Exception {
		return new QrCodePayBusiness().run(payReqData);
	}

	/**
	 *  公众号支付
	 * @param gzhPayReqData
	 * @return
	 * @throws Exception 
	 */
	public static String doGZHPay(PayReqData payReqData) throws Exception {
		return new GZHPayBussiness().run(payReqData);
	}


}
