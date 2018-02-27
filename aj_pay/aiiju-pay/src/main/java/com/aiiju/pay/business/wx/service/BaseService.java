package com.aiiju.pay.business.wx.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.aiiju.pay.business.wx.common.Configure;

/**
 * 
 * @ClassName: BaseService 
 * @Description: 服务的基类
 * @author 小飞 
 * @date 2016年12月7日 下午4:41:37 
 *
 */
public class BaseService{

    //发请求的HTTPS请求器
    private IServiceRequest serviceRequest;

    @SuppressWarnings("rawtypes")
	public BaseService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(Configure.HttpsRequestClassName);
        serviceRequest = (IServiceRequest) c.newInstance();
    }

    protected String sendPost(String apiURL,Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException {
        return serviceRequest.sendPost(apiURL,xmlObj);
    }

    /**
     * 供商户想自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(IServiceRequest request){
        serviceRequest = request;
    }
}
