package com.aiiju.pay.business.wx.common;

import java.util.Map;

import com.aiiju.common.util.HttpClientUtil;
import com.aiiju.common.util.JsonUtils;
/**
 * 
 * @ClassName: PaymentUtil 
 * @Description: TODO
 * @author 小飞 
 * @date 2016年12月21日 下午1:12:03 
 *
 */
public class PaymentUtil {

	 /**
	  * 获取openid
	  * @param code
	  * @return
	  * @throws Exception
	  */
	@SuppressWarnings("unchecked")
	public static String getOpenid(String code){
		String url = Configure.GET_OPEN_ID+"?appid="+Configure.getAppid()+"&secret="+Configure.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
        String result = HttpClientUtil.doGet(url);
		Map<String,String> resultMap = JsonUtils.jsonToPojo(result, Map.class);
        if (resultMap.get("openid") == null) {
            return null;
        }
        return resultMap.get("openid").toString();
    }
	 
	/**
	 * map转xml，返回给微信
	 * @param map
	 * @return
	 */
	public static String mapToXml(Map<String, String> map) {
		return "<xml>" + "<return_code><![CDATA["+map.get("return_code")+"]]></return_code>"  
                + "<return_msg><![CDATA["+map.get("return_msg")+"]]></return_msg>" + "</xml> ";  
	}
}
