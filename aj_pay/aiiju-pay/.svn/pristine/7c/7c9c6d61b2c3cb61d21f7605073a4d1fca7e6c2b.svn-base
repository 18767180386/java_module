package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;

public class BaseService {

	//发请求的HTTPS请求器
    private QqIServiceRequest serviceRequest;

    @SuppressWarnings("rawtypes")
	public BaseService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(QqConfigure.QqHttpsRequestClassName);
        serviceRequest = (QqIServiceRequest) c.newInstance();
    }

    protected String sendPost(String apiURL,Object xmlObj) {
        return serviceRequest.sendPost(apiURL,xmlObj);
    }

    /**
     * 供商户想自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(QqIServiceRequest request){
        serviceRequest = request;
    }
}
