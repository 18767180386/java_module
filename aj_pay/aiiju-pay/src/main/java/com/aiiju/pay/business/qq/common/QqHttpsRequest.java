package com.aiiju.pay.business.qq.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.aiiju.pay.business.wx.common.Util;
import com.aiiju.pay.business.wx.service.IServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class QqHttpsRequest implements IServiceRequest{

	@Override
	public String sendPost(String api_url, Object xmlObj)
			throws UnrecoverableKeyException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
		
		String result = null;
		
		//解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String sPostXml = xStreamForRequestPostData.toXML(xmlObj);

        Util.log("API，POST过去的数据是：");
        Util.log(sPostXml);
		
        URL url = new URL(api_url);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("charset", "UTF-8");

		conn.setDoOutput(true);
		// 不使用缓存
		conn.setUseCaches(false);

		// 允许输入输出
		conn.setDoInput(true);
		conn.setDoOutput(true);
//		 是否需要双向证书。
//		if (this.needClientPem()) {
			File caFile = new File(QqConfigure.CA_FILE_PATH);
			// ca目录
			String caPath = caFile.getParent();

			File jksCAFile = new File(caPath + "/" + QqConfigure.JKS_CA_FILENAME);
			if (!jksCAFile.isFile()) {
				X509Certificate cert = (X509Certificate) HttpUtil
						.getCertificate(caFile);

				FileOutputStream out = new FileOutputStream(jksCAFile);

				// store jks file
				HttpUtil.storeCACert(cert, QqConfigure.JKS_CA_ALIAS, QqConfigure.JKS_CA_PASSWORD,
						out);

				out.close();

			}

			FileInputStream trustStream = new FileInputStream(jksCAFile);
			FileInputStream keyStream = new FileInputStream(QqConfigure.CA_FILE_PATH);

			SSLContext sslContext = HttpUtil.getSSLContext(trustStream,
					QqConfigure.JKS_CA_PASSWORD, keyStream, QqConfigure.CERTPASSWD);

			// 关闭流
			keyStream.close();
			trustStream.close();

			SSLSocketFactory sf = sslContext.getSocketFactory();
			conn.setSSLSocketFactory(sf);
//		}

		// 发送请求
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write(sPostXml.getBytes(StandardCharsets.UTF_8));
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		if (200 != responseCode) {
			return null;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(),StandardCharsets.UTF_8));
		String tmpInputLine;
		StringBuffer responseBuffer = new StringBuffer();
		while ((tmpInputLine = in.readLine()) != null) {
			responseBuffer.append(tmpInputLine);
		}
		in.close();
		result = responseBuffer.toString();
		return result;
	}

}
