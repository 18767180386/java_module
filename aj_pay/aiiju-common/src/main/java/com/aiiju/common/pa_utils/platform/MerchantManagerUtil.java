package com.aiiju.common.pa_utils.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.pojo.PAMerchant;

import net.sf.json.JSONObject;

public class MerchantManagerUtil {

  
    public static JSONObject add(PAMerchant merchant) throws Exception {
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间
            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, Object> datamap = new TreeMap<String, Object>();    // data参数的map

            datamap.put("tra_id", merchant.getTraId());
            datamap.put("mct_name", merchant.getMctName());
            datamap.put("brand_name", merchant.getBrandName());
            datamap.put("cityid",merchant.getCityid());
            datamap.put("address", merchant.getAddress());
            datamap.put("tel", merchant.getTel());
            datamap.put("open_hours", merchant.getOpenHours());
            datamap.put("clsid", merchant.getClsidList());
            datamap.put("boss_name", merchant.getBossName());
            datamap.put("boss_sex", merchant.getBossSex());
            datamap.put("boss_id_country", merchant.getBossIdCountry());
            datamap.put("boss_id_type", merchant.getBossIdType());
            datamap.put("boss_sdate", merchant.getBossSdate());
            datamap.put("boss_edate", merchant.getBossEdate());
            datamap.put("boss_id_no", merchant.getBossIdNo());
            datamap.put("boss_positive", merchant.getBossPositive());
            datamap.put("boss_back", merchant.getBossBack());
            datamap.put("bl_type", merchant.getBlType());
            datamap.put("bl_sdate", merchant.getBlSdate());
            datamap.put("bl_edate", merchant.getBlEdate());
            datamap.put("bl_no", merchant.getBlNo());
            datamap.put("bl_pic", merchant.getBlPic());
            datamap.put("bank_name", merchant.getBankName());
            datamap.put("account_name", merchant.getAccountName());
            datamap.put("account_no", merchant.getAccountNo());
            datamap.put("account_type", merchant.getAccountType());
            datamap.put("bank_no", merchant.getBankNo());
            
            
            
            datamap.put("account_id_type", merchant.getAccountIdType()); //法人亲属证件类型（1 居民身份证
            
            datamap.put("account_id_no", merchant.getAccountIdNo());
            datamap.put("account_boss", merchant.getAccountBoss());
            datamap.put("licence_pic2", merchant.getLicencePic2());
            datamap.put("boss_tel", merchant.getBossTel());
            

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
            String uri = StaticConfig.MERCHANT_ADD + "?open_id=" + getmap.get("open_id") /*+ "&lang=" + getmap.get("lang")*/
                    + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

            
     
        
        return respObject;
    }
    
    
    public static JSONObject edit(PAMerchant merchant) throws Exception {
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, Object> datamap = new TreeMap<String, Object>();    // data参数的map

            datamap.put("mct_no", merchant.getMctNo());
            datamap.put("tra_id", merchant.getTraId());
            datamap.put("mct_name", merchant.getMctName());
            datamap.put("brand_name", merchant.getBrandName());
            datamap.put("cityid",merchant.getCityid());
            datamap.put("address", merchant.getAddress());
            datamap.put("tel", merchant.getTel());
            datamap.put("open_hours", merchant.getOpenHours());
            datamap.put("clsid", merchant.getClsidList());
            datamap.put("boss_name", merchant.getBossName());
            datamap.put("boss_sex", merchant.getBossSex());
            datamap.put("boss_id_country", merchant.getBossIdCountry());
            datamap.put("boss_id_type", merchant.getBossIdType());
            datamap.put("boss_sdate", merchant.getBossSdate());
            datamap.put("boss_edate", merchant.getBossEdate());
            datamap.put("boss_id_no", merchant.getBossIdNo());
            datamap.put("boss_positive", merchant.getBossPositive());
            datamap.put("boss_back", merchant.getBossBack());
            datamap.put("bl_type", merchant.getBlType());
            datamap.put("bl_sdate", merchant.getBlSdate());
            datamap.put("bl_edate", merchant.getBlEdate());
            datamap.put("bl_no", merchant.getBlNo());
            datamap.put("bl_pic", merchant.getBlPic());
            datamap.put("bank_name", merchant.getBankName());
            datamap.put("account_name", merchant.getAccountName());
            datamap.put("account_no", merchant.getAccountNo());
            datamap.put("account_type", merchant.getAccountType());
            datamap.put("bank_no", merchant.getBankNo());

            
            datamap.put("account_id_type", merchant.getAccountIdType()); //法人亲属证件类型（1 居民身份证
            
            datamap.put("account_id_no", merchant.getAccountIdNo());
            datamap.put("account_boss", merchant.getAccountBoss());
            datamap.put("licence_pic2", merchant.getLicencePic2());
            datamap.put("boss_tel", merchant.getBossTel());
            
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
            String uri = StaticConfig.MERCHANT_EDITSAVE + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

        
        return respObject;
  
    }
    
    public static JSONObject sbCheck(String mctNo) throws Exception {
    	
    	
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间



            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("mct_no", mctNo);

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
            String uri = StaticConfig.MERCHANT_SBCHECK + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

        return respObject;
    }
    
    public static JSONObject check(String mctNo, String status, String remark) throws Exception {
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            datamap.put("mct_no", mctNo);
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
            String uri = StaticConfig.MERCHANT_CHECK + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
                         + "&timestamp=" + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

        return respObject;
    }

    public static  JSONObject  mctClass() {
    	JSONObject respObject = new JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间

            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map

            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

//            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map
//            datamap.put("tra_id", traId);

            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
//            String data = TLinx2Util.handleEncrypt(datamap);
//            postmap.put("data", data);
            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = TLinx2Util.handleSign(getmap, postmap);
            postmap.put("sign", sign);
            /**
             * 3 请求、响应
             */
            String uri = StaticConfig.MERCHANT_MCT_CLASS + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang") + "&timestamp="
                         + getmap.get("timestamp") + "&randStr=" + getmap.get("randStr");
            String rspStr = TLinx2Util.handlePost(postmap, uri);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

        return respObject;
    }
    public static JSONObject merView(String mctNo) {
    	
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

            datamap.put("mct_no", mctNo);

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
            String uri = StaticConfig.MERCHANT_VIEW + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang")
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
    
    /**
     * Method main
     * Description 说明：
     *
     * @param args 说明：
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        List list = new ArrayList();
        list.add("100");
        list.add("101");
        PAMerchant merchant = new PAMerchant();
        merchant.setClsidList(list);
        String traid = new Date().getTime() / 1000 + "";    // 时间
        
//        merchant.setMctNo("3");
        merchant.setTraId(traid);
        merchant.setMctName("伊俊斌"); //商户名称 （备注：需同营业执照名称一致）
        merchant.setBrandName("河南恒大花生油");
        merchant.setCityid("110101");
        merchant.setAddress("xxx镇");
        merchant.setTel("13245671584");
        merchant.setOpenHours("(9:00-12:00,13:00-18:00)");
        merchant.setBossName("伊俊斌");//法人姓名
        merchant.setBossSex("1");
        merchant.setBossIdCountry("CHN");//法人证件国别/地区（中国CHN，香港HKG，澳门MAC，台湾CTN）
        merchant.setBossIdType("1");//法人证件类型（1居民身份证,2临时居民身份证,3居民户口簿,4护照,5港澳居民来往内地通行证,6回乡证,7军人证,8武警身份证,9其他法定文件）
        merchant.setBossSdate("2007-07-11");
        merchant.setBossEdate("2027-10-18");
        merchant.setBossIdNo("330721199111065438"); //法人证件号码
        merchant.setBossPositive("442731199001251135"); //法人身份证正面【私密区】（系统返回的图片路径）
        merchant.setBossBack("442731199001251135");//法人身份证背面【私密区】（系统返回的图片路径
        merchant.setBlType("1");//营业执照类型（1三证合一，2非三证合一）
        merchant.setBlSdate("2016-04-25");//营业执照生效时间
        merchant.setBlEdate("2016-04-25");//营业执照过期时间
        merchant.setBlNo(traid);//营业执照编号（系统有唯一性校验）
        merchant.setBlPic("442731199001251135");//营业执照图片【私密区】（系统返回的图片路径）
        merchant.setBankName("123123124");//开户行名称
        merchant.setAccountName("伊俊斌");//银行户名
        merchant.setAccountNo("6225768740794407");//银行账号
        merchant.setAccountType("2"); //结算账户类型（2对私，1对公
        merchant.setIntro("商户简介：啦啦啦啦");
        merchant.setBankNo("10000"); //清算联行号，开户行行号
        merchant.setAdmId("860007274");
        merchant.setAdmName("宗介"); //客户经理姓名，必须为系统后台的管理员真实姓名
        new MerchantManagerUtil().add(merchant);
    }
    
    
    
    
}

