package com.aiiju.common.pa_utils.platform.util;

//~--- non-JDK imports --------------------------------------------------------

import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;

import java.util.*;

//~--- JDK imports ------------------------------------------------------------

//~--- classes ----------------------------------------------------------------

/**
 * Class TLinx2Util
 * Description tlinx api 工具类
 * Create 2017-03-07 14:01:23
 * @author Benny.YEE
 */
public class TLinx2Util {

    /**
     * 签名
     * @param postMap
     * @param privatekey 说明：
     * @return
     */
    public static String sign(Map<String, String> postMap, String privatekey) {
        String sortStr = null;
        String sign    = null;

        try {

            /**
             * 1 A~z排序
             */
            sortStr = sort(postMap);
//            System.out.println("====排序后的待签名字符串= "+sortStr);

            /**
             * 2 sha1加密(小写)
             */
            String sha1 = TLinxSHA1.SHA1(sortStr);
//            System.out.println("====sha1加密后的待签名字符串=" + sha1);

            /**
             * 3 RSA加密(小写),二进制转十六进制
             */
            sign = TLinxRSACoder.sign(sha1.getBytes("utf-8"), privatekey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sign;
    }

    /**
     * 验签
     * @param respObject
     * @param publickey 说明：
     * @return
     */
    public static Boolean verifySign(JSONObject respObject, String publickey) {
        boolean verify = false;
        try {
            String respSign = respObject.getString("sign");

            respObject.remove("sign");                          // 删除sign节点
//            System.out.println("==========开始验签==========");
            String rspparm = sortjson(respObject);    // ȥsign json
            String sha1    = TLinxSHA1.SHA1(rspparm);           // sha1����
            verify = TLinxRSACoder.verify(sha1.getBytes(), publickey, respSign);
//            System.out.println("==========结束验签==========");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verify;
    }

    /**
     * AES加密，再二进制转十六进制(bin2hex)
     * @param datamap 说明：
     *
     * @return 返回值说明：
     * @throws Exception
     */
    public static String handleEncrypt(TreeMap<String, String> datamap) throws Exception {
        JSONObject dataobj = JSONObject.fromObject(datamap);
        String     data    = TLinxAESCoder.encrypt(dataobj.toString(), StaticConfig.open_key);    // AES加密，并bin2hex
//        System.out.println("====加密后的data= "+data);
        return data;
    }

    public static String handleEncrypt1(TreeMap<String, Object> datamap) throws Exception {
        JSONObject dataobj = JSONObject.fromObject(datamap);
        String     data    = TLinxAESCoder.encrypt(dataobj.toString(), StaticConfig.open_key);    // AES加密，并bin2hex
//        System.out.println("====加密后的data= "+data);
        return data;
    }

    /**
     * 签名
     * @param getmap
     * @param datamap 说明：
     *
     * @return 返回值说明：
     */
    public static String handleSign(TreeMap<String, String> getmap, TreeMap<String, String> datamap) {
        Map<String, String> veriDataMap = new HashMap<String, String>();

        veriDataMap.putAll(getmap);
        veriDataMap.putAll(datamap);

        String sign = sign(veriDataMap, StaticConfig.privatekey);
//        System.out.println("====已签名字符串= " + sign);
        // 签名
        return sign;
    }

    /**
     * 根据返回格式来选择post请求处理方式
     * @param postmap
     * @param uri
     * @param tarType
     * @return
     */
    public static String handlePostbyTarType(TreeMap<String, String> postmap, String uri, String tarType) {
        if("gzip".equals(tarType)){
            return handlePostGZIP(postmap, uri);
        }else{
            return handlePost(postmap, uri);
        }
    }

    /**
     * 请求接口
     * @param postmap
     * @param uri 说明：
     * @return      响应字符串
     */
    public static String handlePost(TreeMap<String, String> postmap, String uri) {
        String url = StaticConfig.url + uri;
        String rspStr = "";
//        System.out.println("====请求地址= " + url);
        if (url.contains("https")) {
            rspStr = HttpsUtil.httpMethodPost(url, postmap, "UTF-8");
        } else {
            rspStr = HttpUtil.httpMethodPost(url, postmap, "UTF-8");
        }
//        System.out.println("====post响应字符串= " + rspStr);
        return rspStr;
    }

    public static String handlePost(String url, TreeMap<String, String> postmap) {
        //url = "http://openapi.tlinx.cn/org1/order?open_id=txaalQ4ae3fde16fab071bb1bc452dfb&lang=zh-cn&timestamp=1493791966&randStr=lAMUR5ALaxopwkTZSTrUQ4MSXEid9GdJ";
        if (url.contains("https")) {
            return HttpsUtil.httpMethodPost(url, postmap, "UTF-8");
        } else {
            return HttpUtil.httpMethodPost(url, postmap, "UTF-8");
        }
    }

    public static String handlePostGZIP(TreeMap<String, String> postmap, String uri) {
        String url = StaticConfig.url + uri;
        if (url.contains("https")) {
            return HttpsUtil.httpMethodPostGZIP(url, postmap, "UTF-8");
        } else {
            return HttpUtil.httpMethodPostGZIP(url, postmap, "UTF-8");
        }
    }

    /**
     * Method main
     * Description 说明：
     *
     * @param args 说明：
     */
    public static void main(String[] args) {
        // 初始化参数
        String page      = "1";
        String pagesize  = "10";
        String mctNo     = "20747484";
        String traId     = "TEST000000000000001";
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

        try {

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);

//          getmap.put("timestamp", "1493202106");
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

//          getmap.put("randStr", "6sNlTDqRqkoCyf4AhTKkEIuZ0sXwXjg4");

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("page", page);
            datamap.put("pagesize", pagesize);
            datamap.put("mct_no", mctNo);
            datamap.put("tra_id", traId);

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            String data = handleEncrypt(datamap);
            postmap.put("data", data);
            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = handleSign(getmap, postmap);
            postmap.put("sign", sign);
            /**
             * 3 请求、响应
             */
            String uri = StaticConfig.MERCHANT_LIST + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);
            Object     dataStr    = respObject.get("data");

            System.out.println("返回data字符串=" + dataStr);

            if (!rspStr.isEmpty() || ( dataStr != null )) {
                if (verifySign(respObject, StaticConfig.publickey)) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                    String respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);

                    System.out.println("==================响应data内容:" + respData);
                } else {
                    System.out.println("=====验签失败=====");
                }
            } else {
                System.out.println("=====没有返回data数据=====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 排序
    public static String sort(Map paramMap) throws Exception {
        String sort = "";
        TLinxMapUtil signMap = new TLinxMapUtil();
        if (paramMap != null) {
            String key;
            for (Iterator it = paramMap.keySet().iterator(); it.hasNext();) {
                key = (String) it.next();
                String value = ((paramMap.get(key) != null) && (!(""
                        .equals(paramMap.get(key).toString())))) ? paramMap
                        .get(key).toString() : "";
                signMap.put(key, value);
            }
            signMap.sort();
            for (Iterator it = signMap.keySet().iterator(); it.hasNext();) {
                key = (String) it.next();
                sort = sort + key + "=" + signMap.get(key).toString() + "&";
            }
            if ((sort != null) && (!("".equals(sort)))) {
                sort = sort.substring(0, sort.length() - 1);
            }
        }
        return sort;
    }


    // 排序
    public static String sortjson(JSONObject jsonMap) throws Exception {
        Map<String ,String > paramMap=new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        Iterator ite = jsonObject.keys();
        // 遍历jsonObject数据,添加到Map对象
        while (ite.hasNext()) {
            String key = ite.next().toString();
            String value = jsonObject.get(key).toString();
            paramMap.put(key, value);
        }
        String sort = "";
        TLinxMapUtil signMap = new TLinxMapUtil();
        if (paramMap != null) {
            String key1;
            for (Iterator it = paramMap.keySet().iterator(); it.hasNext();) {
                key1 = (String) it.next();
                String value = ((paramMap.get(key1) != null) && (!(""
                        .equals(paramMap.get(key1).toString())))) ? paramMap
                        .get(key1).toString() : "";
                signMap.put(key1, value);
            }
            signMap.sort();
            for (Iterator it = signMap.keySet().iterator(); it.hasNext();) {
                key1 = (String) it.next();
                sort = sort + key1 + "=" + signMap.get(key1).toString() + "&";
            }
            if ((sort != null) && (!("".equals(sort)))) {
                sort = sort.substring(0, sort.length() - 1);
            }
        }
        return sort;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
