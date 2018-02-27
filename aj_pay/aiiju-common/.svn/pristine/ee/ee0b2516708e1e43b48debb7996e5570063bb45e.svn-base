package com.aiiju.common.pa_utils.platform;

import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.pojo.PAShop;

import net.sf.json.JSONObject;

/** 
 * @ClassName ShopManagerUtil
 * @Description
 * @author zong
 * @CreateDate 2017年7月25日 上午10:06:01
 */
public class ShopManagerUtil {

	
	 public static JSONObject add(PAShop shop) {
		    JSONObject respObject = new JSONObject();
	        String timestamp = new Date().getTime() / 1000 + "";    // 时间

	        try {

	            // 固定参数
	            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
	            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

	            getmap.put("open_id", StaticConfig.open_id);
//	            getmap.put("lang", StaticConfig.lang);
	            getmap.put("timestamp", timestamp);
	            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

	            TreeMap<String, Object> datamap = new TreeMap<String, Object>();    // data参数的map

	            datamap.put("mct_no", shop.getMctNo());//商户编号
	            datamap.put("tra_id", shop.getTraId());// true 机构门店主键（系统有唯一性校验），建议使用门店表的主键ID，防止重复添加门店
	            datamap.put("shop_name", shop.getShopName());// true 门店简称（
	            datamap.put("shop_full_name", shop.getShopFullName());// true 门店全称
	            datamap.put("cityid", shop.getCityid());// true 门店所在的城市编码  101210101
	            datamap.put("address", shop.getAddress());// true 门店详细地址，不含省市区县名称  杭州市西湖区
	            datamap.put("tel", shop.getTel());// true 门店电话
	            datamap.put("pic1", shop.getPic1());// true 整体门面（含招牌）图片【公共区】
	            datamap.put("pic2", shop.getPic2());// false 收银台图片【公共区】
	            datamap.put("pic3", shop.getPic3());// false 店内环境图片【公共区】
	            datamap.put("lng", shop.getLng());//百度地图经度
	            datamap.put("lat", shop.getLat());//百度地图纬度
	      
	            datamap.put("open_hours", shop.getOpenHours());// 营业时间，多个以小写逗号分开(9:00-12:00,13:00-18:00)
//	            datamap.put("contact", shop.getContact());// false 门店负责人
//	            datamap.put("financial_tel", shop.getFinancialTel());// false 负责人手机号码
//	            datamap.put("pic4", shop.getPic4());// false 其他照片【公共区】

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
	            String uri = StaticConfig.SHOP_ADD + "?open_id=" + getmap.get("open_id") /*+ "&lang=" + getmap.get("lang")*/
	                    + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
	            String rspStr = TLinx2Util.handlePost(postmap, uri);

	            /**
	             * 4 验签  有data节点时才验签
	             */
	             respObject = JSONObject.fromObject(rspStr);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return respObject;
	    }

	 
	  public static JSONObject sbCheck(String shopNo) {
		  JSONObject respObject = new JSONObject();
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

	            datamap.put("shop_no", shopNo);

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
	            String uri = StaticConfig.SHOP_SBCHECK + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
	                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
	            String rspStr = TLinx2Util.handlePost(postmap, uri);

	            /**
	             * 4 验签  有data节点时才验签
	             */
	             respObject = JSONObject.fromObject(rspStr);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return respObject;
	    }
	    public static JSONObject check(String shopNo, String status, String remark) {
	    	JSONObject respObject = new JSONObject();
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

	            datamap.put("shop_no", shopNo);
	            datamap.put("status", status);
	            datamap.put("remark", remark);

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
	            String uri = StaticConfig.SHOP_CHECK + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
	                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
	            String rspStr = TLinx2Util.handlePost(postmap, uri);

	            /**
	             * 4 验签  有data节点时才验签
	             */
	             respObject = JSONObject.fromObject(rspStr);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return respObject;
	    }
	    
	    
	    public static JSONObject getOpenIdAndKey(String shopNo) {
	    	  JSONObject respObject = new JSONObject();
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

	            datamap.put("shop_no", shopNo);

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
	            String uri = StaticConfig.SHOP_OPENID + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
	                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
	            String rspStr = TLinx2Util.handlePost(postmap, uri);

	            /**
	             * 4 验签  有data节点时才验签
	             */
	             respObject = JSONObject.fromObject(rspStr);

	     
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return respObject;
	    }
	    
	    

	    public static JSONObject edit(PAShop shop) {
	    	
	    	JSONObject respObject = new JSONObject();
	    	
	        String timestamp = new Date().getTime() / 1000 + "";    // 时间

	        try {

	            // 固定参数
	            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
	            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

	            getmap.put("open_id", StaticConfig.open_id);
	            getmap.put("lang", StaticConfig.lang);
	            getmap.put("timestamp", timestamp);
	            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

	            TreeMap<String, Object> datamap = new TreeMap<String, Object>();    // data参数的map

	            datamap.put("shop_no", shop.getShopNo());
	            datamap.put("shop_name", shop.getShopName());
	            datamap.put("shop_full_name", shop.getShopFullName());
	            datamap.put("cityid", shop.getCityid());
	            datamap.put("address", shop.getAddress());
	            datamap.put("tel", shop.getTel());
	            datamap.put("pic1", shop.getPic1());
	            datamap.put("pic2", shop.getPic2());
	            datamap.put("pic3", shop.getPic3());
	            datamap.put("lng", shop.getLng());
	            datamap.put("lat", shop.getLat());
	        
	            datamap.put("open_hours", shop.getOpenHours());
//	            datamap.put("contact", shop.get);
	            datamap.put("financial_tel", shop.getTel());

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
	            String uri = StaticConfig.SHOP_EDITSAVE + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
	                    + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
	            String rspStr = TLinx2Util.handlePost(postmap, uri);

	            /**
	             * 4 验签  有data节点时才验签
	             */
	             respObject = JSONObject.fromObject(rspStr);

	              
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return respObject;
	    }
	    
}
