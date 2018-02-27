package com.aiiju.pay.business.wx.service;

import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.protocol.reverse_protocol.ReverseReqData;

/**
 * 
 * @ClassName: ReverseService 
 * @Description: 撤销
 * @author 小飞 
 * @date 2016年12月7日 下午4:45:12 
 *
 */
public class ReverseService extends BaseService{

    public ReverseService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    /**
     * 请求撤销服务
     * @param reverseReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(ReverseReqData reverseReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(Configure.REVERSE_API,reverseReqData);

        return responseString;
    }

}
