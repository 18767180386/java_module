package com.aiiju.pay.business.wx.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * 
 * @ClassName: IServiceRequest 
 * @Description: 服务层需要请求器标准接口
 * @author 小飞 
 * @date 2016年12月7日 下午4:41:06 
 *
 */
public interface IServiceRequest {

    //Service依赖的底层https请求器必须实现这么一个接口
    public String sendPost(String api_url,Object xmlObj) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException;

}
