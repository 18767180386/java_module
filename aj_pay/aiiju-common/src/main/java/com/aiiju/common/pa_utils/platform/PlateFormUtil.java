package com.aiiju.common.pa_utils.platform;

import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.common.pa_utils.platform.util.TLinxAESCoder;

import net.sf.json.JSONObject;

public class PlateFormUtil {

	/**
	 * 请求的参数和请求的地址
	 * @param urlRequestPath  请求的地址
	 * @param datamap  请求的参数
	 * @return
	 */
    public static String apiUtils(String urlRequestPath ,TreeMap<String, Object> datamap) {
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
//            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            String data = TLinx2Util.handleEncrypt1(datamap);
            postmap.put("data", data);
            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = TLinx2Util.handleSign(getmap, postmap);
            postmap.put("sign", sign);
            /**
             * 3 请求、响应
             */
            /*String uri = StaticConfig.MERCHANT_ADD + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                    + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");*/
            String uri = urlRequestPath + "?open_id=" + getmap.get("open_id") /*+ "&lang=" + getmap.get("lang")*/
            + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);
             Object dataStr    = respObject.get("data");
             
             if (!rspStr.isEmpty() && ( dataStr != null )) {
                 if (TLinx2Util.verifySign(respObject, StaticConfig.publickey)) {    // 验签成功

                     /**
                      * 5 AES解密，并hex2bin
                      */
                     String respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);

                     System.out.println("==================响应data内容:" + respData);
                     return respData;
                 } else {
                     System.out.println("=====验签失败=====");
                 }
             } else {
                 System.out.println("=====没有返回data数据=====");
             }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
 
    /**
     * 测试类  测试查看商户的列表
     * @param args
     */
    public static void main(String[] args) {
    	
    	/**
    	 * 访问的 路径
    	 */
        String urlRequestPath = StaticConfig.MERCHANT_VIEW;
        
        // 设置参数
        TreeMap<String, Object> datamap = new TreeMap<String, Object>();    // data参数的map
        /**
         * 需要提交的参数，如果没有 ，只需要不写即可
         * 这样只需要修改参数即可，就可以。得到结果
         */
        datamap.put("mct_no", "860157793");
        
        // 结果集合的处理
        String result = new PlateFormUtil().apiUtils(urlRequestPath, datamap);
        System.err.println("得到的结果是：" + result);
        
    }
}

