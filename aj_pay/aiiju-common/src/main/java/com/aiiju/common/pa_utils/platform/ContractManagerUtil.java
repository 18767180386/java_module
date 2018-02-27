package com.aiiju.common.pa_utils.platform;

import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.common.pa_utils.platform.util.TLinxAESCoder;

import net.sf.json.JSONObject;

/** 
 * @ClassName ContractManagerUtil
 * @Description
 * @author zong
 * @CreateDate 2017年8月2日 下午1:53:19
 */
public class ContractManagerUtil {

    public static String view(String cttId) {
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("ctt_id", cttId);

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            String data = TLinx2Util.handleEncrypt(datamap);
            postmap.put("data", data);

            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = TLinx2Util.handleSign(getmap, postmap);
            postmap.put("sign", sign);

            /**
             * 3 请求、响应
             */
            String uri = StaticConfig.CONTRACT_VIEW + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);

            
            if(0!=(Integer)respObject.get("errcode")){
            	 System.out.println("====响应错误码：" + respObject.get("errcode"));
                 System.out.println("====响应错误提示：" + respObject.get("msg"));

            }
           
            Object dataStr = respObject.get("data");
            String respData = null;
            if (!rspStr.isEmpty() && ( dataStr != null )) {
                if (TLinx2Util.verifySign(respObject, StaticConfig.publickey)) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                     respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
                     
//                    System.out.println("==================响应data内容:" + respData);
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
	
	
	
}
