package com.aiiju.pay.business.qq.business;

import org.slf4j.LoggerFactory;

import com.aiiju.pay.business.qq.listener.QqRefundBusinessResultListener;
import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundReqData;
import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundResData;
import com.aiiju.pay.business.qq.service.QqRefundService;
import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;
/**
 * 
 * @ClassName: QqRefundBusiness 
 * @Description: 退款
 * @author 小飞 
 * @date 2017年2月13日 上午10:59:42 
 *
 */
public class QqRefundBusiness {

	// 打log用
	private static Log log = new Log(
			LoggerFactory.getLogger(QqRefundBusiness.class));

	// 执行结果
	private static String result = "";

	private QqRefundService refundService;
	
	public interface ResultListener{
        //API返回ReturnCode不合法，支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问
        void onFailByReturnCodeError(QqRefundResData refundResData);

        //API返回ReturnCode为FAIL，支付API系统返回失败，请检测Post给API的数据是否规范合法
        void onFailByReturnCodeFail(QqRefundResData refundResData);

        //支付请求API返回的数据签名验证失败，有可能数据被篡改了
        void onFailBySignInvalid(QqRefundResData refundResData);

        //退款失败
        void onRefundFail(QqRefundResData refundResData);

        //退款成功
        void onRefundSuccess(QqRefundResData refundResData);

    }

	public QqRefundBusiness() throws IllegalAccessException,
			ClassNotFoundException, InstantiationException {
		refundService = new QqRefundService();
	}

	public void run(QqRefundReqData refundReqData, QqRefundBusinessResultListener resultListener)
			throws Exception {

		// API返回的数据
		String refundServiceResponseString;

		long costTimeStart = System.currentTimeMillis();

		log.i("退款查询API返回的数据如下：");
		refundServiceResponseString = refundService.request(refundReqData);

		long costTimeEnd = System.currentTimeMillis();
		long totalTimeCost = costTimeEnd - costTimeStart;
		log.i("api请求总耗时：" + totalTimeCost + "ms");

		log.i(refundServiceResponseString);

		// 将从API返回的XML数据映射到Java对象
		QqRefundResData refundResData = (QqRefundResData) Util.getObjectFromXML(
				refundServiceResponseString, QqRefundResData.class);

		if (refundResData == null || refundResData.getReturn_code() == null) {
			setResult("Case1:退款API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问",
					Log.LOG_TYPE_ERROR);
			resultListener.onFailByReturnCodeError(refundResData);
			return;
		}

		// Debug:查看数据是否正常被填充到scanPayResponseData这个对象中
		// Util.reflect(refundResData);

		if (refundResData.getReturn_code().equals("FAIL")) {
			// /注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
			setResult("Case2:退款API系统返回失败，请检测Post给API的数据是否规范合法",
					Log.LOG_TYPE_ERROR);
			resultListener.onFailByReturnCodeFail(refundResData);
		} else {
			log.i("退款API系统成功返回数据");
			// --------------------------------------------------------------------
			// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
			// --------------------------------------------------------------------

			if (!Signature
					.checkIsSignValidFromResponseString(refundServiceResponseString)) {
				setResult("Case3:退款请求API返回的数据签名验证失败，有可能数据被篡改了",
						Log.LOG_TYPE_ERROR);
				resultListener.onFailBySignInvalid(refundResData);
				return;
			}

			if (refundResData.getResult_code().equals("FAIL")) {
				log.i("出错，错误码：" + refundResData.getErr_code() + "     错误信息："
						+ refundResData.getErr_code_des());
				setResult("Case4:【退款失败】", Log.LOG_TYPE_ERROR);
				// 退款失败时再怎么延时查询退款状态都没有意义，这个时间建议要么再手动重试一次，依然失败的话请走投诉渠道进行投诉
				resultListener.onRefundFail(refundResData);
			} else {
				// 退款成功
				setResult("Case5:【退款成功】", Log.LOG_TYPE_INFO);
				resultListener.onRefundSuccess(refundResData);
			}
		}
	}


	public  String getResult() {
		return result;
	}

	public  void setResult(String result) {
		QqRefundBusiness.result = result;
	}
	
	public void setResult(String result,String type){
        setResult(result);
        log.log(type,result);
    }

	public void setRefundService(QqRefundService refundService) {
		this.refundService = refundService;
	}
	
	
	
}