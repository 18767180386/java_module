package com.aiiju.pay.business.wx.service;

import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.protocol.refund_protocol.RefundReqData;

/**
 * 
 * @ClassName: RefundService 
 * @Description: 退款
 * @author 小飞 
 * @date 2016年12月7日 下午4:46:02 
 *
 */
public class RefundService extends BaseService{

    public RefundService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(RefundReqData refundReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(Configure.REFUND_API,refundReqData);

        return responseString;
    }

}
