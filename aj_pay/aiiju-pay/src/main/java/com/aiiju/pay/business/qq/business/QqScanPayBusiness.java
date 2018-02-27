package com.aiiju.pay.business.qq.business;

import static java.lang.Thread.sleep;

import org.slf4j.LoggerFactory;

import com.aiiju.pay.business.qq.protocol.pay_protocol.QqScanPayReqData;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqScanPayResData;
import com.aiiju.pay.business.qq.protocol.pay_query_protocol.QqOrderQueryReqData;
import com.aiiju.pay.business.qq.protocol.pay_query_protocol.QqOrderQueryResData;
import com.aiiju.pay.business.qq.protocol.reverse_protocol.QqReverseReqData;
import com.aiiju.pay.business.qq.service.QqReverseService;
import com.aiiju.pay.business.qq.service.QqScanPayQueryService;
import com.aiiju.pay.business.qq.service.QqScanPayService;
import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;
import com.aiiju.pay.business.wx.protocol.pay_query_protocol.ScanPayQueryResData;
import com.aiiju.pay.business.wx.protocol.reverse_protocol.ReverseResData;

/**
 * 
 * @ClassName: QQScanPayBusiness
 * @Description: qq钱包支付码
 * @author 小飞
 * @date 2017年2月7日 下午2:14:36
 *
 */
public class QqScanPayBusiness {

	private static Log log = new Log(
			LoggerFactory.getLogger(QqScanPayBusiness.class));

	// 每次调用订单查询API时的等待时间，因为当出现支付失败的时候，如果马上发起查询不一定就能查到结果，所以这里建议先等待一定时间再发起查询
	private int waitingTimeBeforePayQueryServiceInvoked = 5000;

	// 循环调用订单查询API的次数
	private int payQueryLoopInvokedCount = 3;

	// 每次调用撤销API的等待时间
	private int waitingTimeBeforeReverseServiceInvoked = 5000;

	private QqScanPayService scanPayService;

	private QqScanPayQueryService scanPayQueryService;

	private QqReverseService reverseService;

	public QqScanPayBusiness() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		scanPayService = new QqScanPayService();
		scanPayQueryService = new QqScanPayQueryService();
		reverseService = new QqReverseService();
	}

	public boolean run(QqScanPayReqData scanPayReqData) throws Exception{
		String outTradeNo = scanPayReqData.getOut_trade_no();
		String subMchId = scanPayReqData.getSub_mch_id();

		String payResult = null;
		long costTimeStart = System.currentTimeMillis();
		log.i("支付API返回的数据如下：");

		payResult = scanPayService.request(scanPayReqData);

		long costTimeEnd = System.currentTimeMillis();
		long totalTimeCost = costTimeEnd - costTimeStart;
		log.i("api请求总耗时：" + totalTimeCost + "ms");

		if (payResult == null) {
			doOnePayQuery(subMchId, "", outTradeNo);
			return false;
		}

		// 将从API返回的XML数据映射到Java对象
		QqScanPayResData scanPayResData = (QqScanPayResData) Util
				.getObjectFromXML(payResult, QqScanPayResData.class);
		
		if (scanPayResData == null || scanPayResData.getReturn_code() == null) {
            log.e("【支付失败】支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            return false;
        }
		 if (scanPayResData.getReturn_code().equals("FAIL")) {
	            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
	            log.e("【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
	            return false;
	        } else {
	            log.i("支付API系统成功返回数据");
	            //--------------------------------------------------------------------
	            //收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
	            //--------------------------------------------------------------------
	            if (!Signature.checkIsSignValidFromResponseString(payResult)) {
	                log.e("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
	                return false;
	            }

	            String transactionID = scanPayResData.getTransaction_id();
	            if (scanPayResData.getResult_code().equals("SUCCESS")) {
	            	if(doPayQueryLoop(payQueryLoopInvokedCount, subMchId,transactionID,outTradeNo)) {
	            		//--------------------------------------------------------------------
	            		//1)直接扣款成功
	            		//--------------------------------------------------------------------
	            		log.i("【一次性支付成功】");
	            		return true;
	            	}
	            }else{
	            	 //获取错误码
	                String errorCode = scanPayResData.getErr_code();
	                //获取错误描述
	                String errorCodeDes = scanPayResData.getErr_code_des();

	                //业务错误时错误码有好几种，商户重点提示以下几种
	                if (errorCode.equals("AUTHCODEEXPIRE") || errorCode.equals("AUTH_CODE_INVALID") || errorCode.equals("NOTENOUGH")) {
	                    //--------------------------------------------------------------------
	                    //2)扣款明确失败
	                    //--------------------------------------------------------------------
	                    //对于扣款明确失败的情况直接走撤销逻辑
	                    doReverseLoop(subMchId, transactionID,outTradeNo);

	                    //以下几种情况建议明确提示用户，指导接下来的工作
	                    if (errorCode.equals("AUTHCODEEXPIRE")) {
	                        //表示用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码
	                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
	                    } else if (errorCode.equals("AUTH_CODE_INVALID")) {
	                        //授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付
	                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
	                    } else if (errorCode.equals("NOTENOUGH")) {
	                        //提示用户余额不足，换其他卡支付或是用现金支付
	                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
	                    }
	                    return false;
	                } else if (errorCode.equals("USERPAYING")) {
	                    //--------------------------------------------------------------------
	                    //3)需要输入密码
	                    //--------------------------------------------------------------------
	                    //表示有可能单次消费超过300元，或是免输密码消费次数已经超过当天的最大限制，这个时候提示用户输入密码，商户自己隔一段时间去查单，查询一定次数，看用户是否已经输入了密码
	                    if (doPayQueryLoop(payQueryLoopInvokedCount,subMchId, transactionID,outTradeNo)) {
	                        log.i("【需要用户输入密码、查询到支付成功】");
	                        return true;
	                    } else {
	                        log.i("【需要用户输入密码、在一定时间内没有查询到支付成功、走撤销流程】");
	                        doReverseLoop(subMchId, transactionID,outTradeNo);
	                        return false;
	                    }
	                } else {
	                    //--------------------------------------------------------------------
	                    //4)扣款未知失败
	                    //--------------------------------------------------------------------
	                    if (doPayQueryLoop(payQueryLoopInvokedCount,subMchId,transactionID,outTradeNo)) {
	                        log.i("【支付扣款未知失败、查询到支付成功】");
	                        return true;
	                    } else {
	                        log.i("【支付扣款未知失败、在一定时间内没有查询到支付成功、走撤销流程】");
	                        doReverseLoop(subMchId,transactionID,outTradeNo);
	                        return false;
	                    }
	                }
	            }
	        }
		 return false;
	}
	
	/**
     * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，多试几次（建议3次）
     *
     * @param loopCount     循环次数，至少一次
     * @param outTradeNo    商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @return 该订单是否支付成功
     * @throws InterruptedException
     */
    private boolean doPayQueryLoop(int loopCount,String subMchId,String transactionID, String outTradeNo) throws Exception {
        //至少查询一次
        if (loopCount == 0) {
            loopCount = 1;
        }
        //进行循环查询
        for (int i = 0; i < loopCount; i++) {
            if (doOnePayQuery(subMchId,transactionID,outTradeNo)) {
                return true;
            }
        }
        return false;
    }

	private boolean doOnePayQuery(String subMchId, String transactionID,
			String outTradeNo) throws Exception {

		sleep(waitingTimeBeforePayQueryServiceInvoked);// 等待一定时间再进行查询，避免状态还没来得及被更新

		String payQueryServiceResponseString;

		QqOrderQueryReqData scanPayQueryReqData = new QqOrderQueryReqData(
				subMchId, transactionID, outTradeNo);
		payQueryServiceResponseString = scanPayQueryService
				.request(scanPayQueryReqData);

		log.i("支付订单查询API返回的数据如下：");
		log.i(payQueryServiceResponseString);

		// 将从API返回的XML数据映射到Java对象
		QqOrderQueryResData scanPayQueryResData = (QqOrderQueryResData) Util
				.getObjectFromXML(payQueryServiceResponseString,
						ScanPayQueryResData.class);
		if (scanPayQueryResData == null
				|| scanPayQueryResData.getReturn_code() == null) {
			log.i("支付订单查询请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
			return false;
		}

		if (scanPayQueryResData.getReturn_code().equals("FAIL")) {
			// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
			log.i("支付订单查询API系统返回失败，失败信息为："
					+ scanPayQueryResData.getReturn_msg());
			return false;
		} else {
			if (scanPayQueryResData.getResult_code().equals("SUCCESS")) {// 业务层成功
				if (scanPayQueryResData.getTrade_state().equals("SUCCESS")) {
					// 表示查单结果为“支付成功”
					log.i("查询到订单支付成功");
					return true;
				} else {
					// 支付不成功
					log.i("查询到订单支付不成功");
					return false;
				}
			} else {
				log.i("查询出错，错误码：" + scanPayQueryResData.getErr_code()
						+ "     错误信息：" + scanPayQueryResData.getErr_code_des());
				return false;
			}
		}
	}
	
	//是否需要再调一次撤销，这个值由撤销API回包的recall字段决定
    private boolean needRecallReverse = false;
    
    /**
     * 进行一次撤销操作
     *
     * @param outTradeNo    商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @return 该订单是否支付成功
     * @throws Exception
     */
    private boolean doOneReverse(String subMchId,String transactionID,String outTradeNo) throws Exception {

        sleep(waitingTimeBeforeReverseServiceInvoked);//等待一定时间再进行查询，避免状态还没来得及被更新

        String reverseResponseString;

        QqReverseReqData reverseReqData = new QqReverseReqData(subMchId,transactionID,outTradeNo);
        reverseResponseString = reverseService.request(reverseReqData);

        log.i("撤销API返回的数据如下：");
        log.i(reverseResponseString);
        //将从API返回的XML数据映射到Java对象
        ReverseResData reverseResData = (ReverseResData) Util.getObjectFromXML(reverseResponseString, ReverseResData.class);
        if (reverseResData == null) {
            log.i("支付订单撤销请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
            return false;
        }
        if (reverseResData.getReturn_code().equals("FAIL")) {
            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            log.i("支付订单撤销API系统返回失败，失败信息为：" + reverseResData.getReturn_msg());
            return false;
        } else {
            if (reverseResData.getResult_code().equals("FAIL")) {
                log.i("撤销出错，错误码：" + reverseResData.getErr_code() + "     错误信息：" + reverseResData.getErr_code_des());
                if (reverseResData.getRecall().equals("Y")) {
                    //表示需要重试
                    needRecallReverse = true;
                    return false;
                } else {
                    //表示不需要重试，也可以当作是撤销成功
                    needRecallReverse = false;
                    return true;
                }
            } else {
                //查询成功，打印交易状态
                log.i("支付订单撤销成功");
                return true;
            }
        }
    }
	
	/**
     * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，是否需要继续循环调用撤销API由撤销API回包里面的recall字段决定。
     *
     * @param outTradeNo    商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @throws InterruptedException
     */
    private void doReverseLoop(String subMchId,String transactionID,String outTradeNo) throws Exception {
        //初始化这个标记
        needRecallReverse = true;
        //进行循环撤销，直到撤销成功，或是API返回recall字段为"Y"
        while (needRecallReverse) {
            if (doOneReverse(subMchId,transactionID,outTradeNo)) {
                return;
            }
        }
    }

}
