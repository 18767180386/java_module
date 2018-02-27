package com.aiiju.pay.business.qq.business;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.aiiju.pay.business.qq.protocol.refund_query_protocol.QqRefundQueryReqData;
import com.aiiju.pay.business.qq.protocol.refund_query_protocol.QqRefundQueryResData;
import com.aiiju.pay.business.qq.service.QqRefundQueryService;
import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;
import com.aiiju.pay.business.wx.common.XMLParser;
import com.aiiju.pay.business.wx.protocol.refund_query_protocol.RefundOrderData;
/**
 * 
 * @ClassName: QqRefundQueryBussiness 
 * @Description: 退款查询
 * @author 小飞 
 * @date 2017年2月13日 上午11:15:01 
 *
 */
public class QqRefundQueryBusiness {

	 //打log用
    private static Log log = new Log(LoggerFactory.getLogger(QqRefundQueryBusiness.class));

    //执行结果
    private static String result = "";

    //查询到的结果
    private static String orderListResult = "";

    private QqRefundQueryService refundQueryService;
    
    public QqRefundQueryBusiness() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        refundQueryService = new QqRefundQueryService();
    }
    
    public interface ResultListener{
        //API返回ReturnCode不合法，支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问
        void onFailByReturnCodeError(QqRefundQueryResData refundQueryResData);

        //API返回ReturnCode为FAIL，支付API系统返回失败，请检测Post给API的数据是否规范合法
        void onFailByReturnCodeFail(QqRefundQueryResData refundQueryResData);

        //支付请求API返回的数据签名验证失败，有可能数据被篡改了
        void onFailBySignInvalid(QqRefundQueryResData refundQueryResData);

        //退款查询失败
        void onRefundQueryFail(QqRefundQueryResData refundQueryResData);

        //退款查询成功
        void onRefundQuerySuccess(QqRefundQueryResData refundQueryResData);

    }
    
    public void run(QqRefundQueryReqData refundQueryReqData,QqRefundQueryBusiness.ResultListener resultListener) throws Exception {

        //接受API返回
        String refundQueryServiceResponseString;

        long costTimeStart = System.currentTimeMillis();

        //表示是本地测试数据
        log.i("退款查询API返回的数据如下：");
        refundQueryServiceResponseString = refundQueryService.request(refundQueryReqData);

        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        log.i(refundQueryServiceResponseString);

        //将从API返回的XML数据映射到Java对象
        QqRefundQueryResData refundQueryResData = (QqRefundQueryResData) Util.getObjectFromXML(refundQueryServiceResponseString, QqRefundQueryResData.class);

//        ReportReqData reportReqData = new ReportReqData(
//                refundQueryReqData.getDevice_info(),
//                Configure.REFUND_QUERY_API,
//                (int) (totalTimeCost),//本次请求耗时
//                refundQueryResData.getReturn_code(),
//                refundQueryResData.getReturn_msg(),
//                refundQueryResData.getResult_code(),
//                refundQueryResData.getErr_code(),
//                refundQueryResData.getErr_code_des(),
//                refundQueryResData.getOut_trade_no(),
//                Configure.getIP()
//        );
//
//        long timeAfterReport;
//        if(Configure.isUseThreadToDoReport()){
//            ReporterFactory.getReporter(reportReqData).run();
//            timeAfterReport = System.currentTimeMillis();
//            Util.log("pay+report总耗时（异步方式上报）："+(timeAfterReport-costTimeStart) + "ms");
//        }else{
//            ReportService.request(reportReqData);
//            timeAfterReport = System.currentTimeMillis();
//            Util.log("pay+report总耗时（同步方式上报）："+(timeAfterReport-costTimeStart) + "ms");
//        }


        if (refundQueryResData == null || refundQueryResData.getReturn_code() == null) {
            setResult("Case1:退款查询API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问",Log.LOG_TYPE_ERROR);
            resultListener.onFailByReturnCodeError(refundQueryResData);
            return;
        }

        //Debug:查看数据是否正常被填充到scanPayResponseData这个对象中
        //Util.reflect(refundQueryResData);

        if (refundQueryResData.getReturn_code().equals("FAIL")) {
            ///注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            setResult("Case2:退款查询API系统返回失败，请检测Post给API的数据是否规范合法",Log.LOG_TYPE_ERROR);
            resultListener.onFailByReturnCodeFail(refundQueryResData);
        } else {
            log.i("退款查询API系统成功返回数据");
            //--------------------------------------------------------------------
            //收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
            //--------------------------------------------------------------------

            if (!Signature.checkIsSignValidFromResponseString(refundQueryServiceResponseString)) {
                setResult("Case3:退款查询API返回的数据签名验证失败，有可能数据被篡改了",Log.LOG_TYPE_ERROR);
                resultListener.onFailBySignInvalid(refundQueryResData);
                return;
            }

            if (refundQueryResData.getResult_code().equals("FAIL")) {
                Util.log("出错，错误码：" + refundQueryResData.getErr_code() + "     错误信息：" + refundQueryResData.getErr_code_des());
                setResult("Case4:【退款查询失败】",Log.LOG_TYPE_ERROR);
                resultListener.onRefundQueryFail(refundQueryResData);
                //退款失败时再怎么延时查询退款状态都没有意义，这个时间建议要么再手动重试一次，依然失败的话请走投诉渠道进行投诉
            } else {
                //退款成功
                getRefundOrderListResult(refundQueryServiceResponseString);
                setResult("Case5:【退款查询成功】",Log.LOG_TYPE_INFO);
                resultListener.onRefundQuerySuccess(refundQueryResData);
            }
        }
    }

    /**
     * 打印出服务器返回的订单查询结果
     * @param refundQueryResponseString 退款查询返回API返回的数据
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    private void getRefundOrderListResult(String refundQueryResponseString) throws ParserConfigurationException, SAXException, IOException {
        List<RefundOrderData> refundOrderList = XMLParser.getRefundOrderList(refundQueryResponseString);
        int count = 1;
        for(RefundOrderData refundOrderData : refundOrderList){
            Util.log("退款订单数据NO" + count + ":");
            Util.log(refundOrderData.toMap());
            orderListResult += refundOrderData.toMap().toString();
            count++;
        }
        log.i("查询到的结果如下：");
        log.i(orderListResult);
    }
    
    public void setQqRefundQueryService(QqRefundQueryService service) {
        refundQueryService = service;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
    	QqRefundQueryBusiness.result = result;
    }

    public void setResult(String result,String type){
        setResult(result);
        log.log(type,result);
    }
    
    public String getOrderListResult() {
        return orderListResult;
    }

    public void setOrderListResult(String orderListResult) {
    	QqRefundQueryBusiness.orderListResult = orderListResult;
    }
    
}
