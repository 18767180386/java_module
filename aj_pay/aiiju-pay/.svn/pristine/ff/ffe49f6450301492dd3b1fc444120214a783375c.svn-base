package com.aiiju.pay.business.wx.service;

import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.protocol.pay_protocol.ScanPayReqData;

/**
 * 
 * @ClassName: ScanPayService 
 * @Description: 刷卡支付
 * @author 小飞 
 * @date 2016年12月7日 下午4:42:00 
 *
 */
public class ScanPayService extends BaseService{

    public ScanPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(ScanPayReqData scanPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(Configure.SCAN_PAY_API,scanPayReqData);

        return responseString;
    }
}
