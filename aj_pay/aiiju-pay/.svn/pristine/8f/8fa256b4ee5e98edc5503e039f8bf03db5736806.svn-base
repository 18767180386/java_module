package com.aiiju.pay.business.wx.service;

import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.protocol.pay_query_protocol.ScanPayQueryReqData;

/**
 * 
 * @ClassName: ScanPayQueryService 
 * @Description: 刷卡支付查询
 * @author 小飞 
 * @date 2016年12月7日 下午4:42:50 
 *
 */
public class ScanPayQueryService extends BaseService{

    public ScanPayQueryService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(ScanPayQueryReqData scanPayQueryReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(Configure.PAY_QUERY_API,scanPayQueryReqData);

        return responseString;
    }


}
