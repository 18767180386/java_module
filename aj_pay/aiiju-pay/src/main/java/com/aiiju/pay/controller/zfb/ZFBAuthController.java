package com.aiiju.pay.controller.zfb;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pay.business.zfb.util.AlipayUtil;
import com.aiiju.pay.service.AppAuthService;
import com.aiiju.pay.service.ShopService;
import com.aiiju.pojo.AppAuth;
import com.aiiju.pojo.Shop;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

/**
 * 
 * @ClassName: ZFBAuthController
 * @Description: 支付宝 授权回调URL 控制器（由支付宝主动访问）
 * @author 小飞
 * @date 2016年11月29日 下午1:59:59
 *
 */
@Controller
@RequestMapping("/zfbauth")
public class ZFBAuthController {

    private static Logger logger = Logger.getLogger(ZFBAuthController.class);

    @Autowired
    private AppAuthService appAuthService;
    @Autowired
    private ShopService shopService;

    /**
     * 第三方授权，获取商户token
     * 
     * @param req
     * @return
     */
    @RequestMapping("/getToken")
    public String getToken(HttpServletRequest req, HttpServletResponse resp) {
//        resp.setContentType("text/html;charset=UTF-8");
        // 默认第三方应用授权
        if ("user".equals(req.getParameter("type"))) {// 买家授权
            return this.userToken(req);
        } else {
            return this.appToken(req);// 第三方应用授权(如支付宝授权回调方法)
        }
    }

    /**
     * 用户授权
     * 
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    private String userToken(HttpServletRequest req) {
        String app_id = req.getParameter("app_id");
        if (AlipayUtil.APP_ID.equals(app_id)) {
            AlipayClient alipayClient = AlipayUtil.getAlipayClient();
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
            request.setGrantType("authorization_code");
            request.setCode(req.getParameter("auth_code"));
            try {
                AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
                if (response.isSuccess()) {
                    String[] data = req.getParameter("state").split("_");// data[0]:storeId
                                                                         // data[1]:shopName
                    req.setAttribute("storeId", data[0]);

                   Shop shop =  shopService.getShopByStoreId(data[0]);
                   String shopNameNew  =   shop.getShopName();
                    req.setAttribute("shopName", shopNameNew);
                    
					    System.out.println("支付宝getToken ，storeId="+data[0]);
	                    System.out.println("支付宝getToken ，shopName="+data[1]);
	                    System.out.println("支付宝getToken ，shopName="+shopNameNew);
	                 
	                  
					
                    req.setAttribute("user_id", response.getUserId());// 买家pid
                    req.setAttribute("payUrl", "https://trade.ecbao.cn/zfbpay/pay");//付款接口
                    req.setAttribute("token",req.getParameter("token"));
                    
                    System.out.println("3333333333333---token ="+req.getParameter("token"));
                    return "pay/zfb/zhifubaoPay";//付款界面
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("当前应用:" + app_id + ",支付宝用户授权异常信息:" + e.getMessage());
            }
        }
        req.setAttribute("msg", "支付宝交易失败,请尝试重新扫描二维码");
        return "pay/payError";
    }

    /**
     * 第三方授权
     * 
     * @param req
     * @return
     */
    private String appToken(HttpServletRequest req) {
        String app_auth_code = req.getParameter("app_auth_code");
        String app_id = req.getParameter("app_id");
        String storeId = req.getParameter("storeId");

        if (AlipayUtil.APP_ID.equals(app_id)) {
            AlipayClient alipayClient = AlipayUtil.getAlipayClient();
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            request.setBizContent("{" + "\"grant_type\":\"authorization_code\"," + " \"code\":\""
                    + app_auth_code + "\"," + "}");

            AppAuth dbAppAuth = this.appAuthService.getAppAuthByStoreId(storeId);
            if (dbAppAuth != null) {
                // 刷新token
                request.setBizContent("{" + "    \"grant_type\":\"refresh_token\"," + "    \"code\":\""
                        + dbAppAuth.getAppRefreshToken() + "\"," + "  }");
            }

            try {
                // 设置url，跳转到授权失败页面会用到
                String app_auth_url = AlipayUtil.APP_AUTH_URI + "?storeId=" + storeId + "&app_id="
                        + AlipayUtil.APP_ID + "&redirect_uri=" + AlipayUtil.AUTH_CALL_URL;
                req.setAttribute("authURL", app_auth_url);
                AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
                if ("10000".equals(response.getCode())) {
                    if (dbAppAuth != null) {
                        this.appAuthService.updateAppAuth(this.initAppAuth(dbAppAuth, response));
                    } else {
                        AppAuth auth = this.initAppAuth(new AppAuth(), response);
                        auth.setStoreId(storeId);
                        this.appAuthService.saveAppAuth(auth);
                    }
                    return "auth/success";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "auth/app_fail";
            }
        }
        return "auth/app_fail";
    }

    @RequestMapping("/check")
    @ResponseBody
    public Result check(String userName) {
        try {
            return this.appAuthService.checkUser(userName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(400, "网络异常");
        }
    }

    /**
     * 设置AppAuth
     * @param auth
     * @param response
     * @return
     */
    private AppAuth initAppAuth(AppAuth auth, AlipayOpenAuthTokenAppResponse response) {
        auth.setAppAuthToken(response.getAppAuthToken());// 商户授权令牌
        auth.setUserId(response.getUserId());// 授权商户的ID 2088开头
        auth.setAuthAppId(response.getAuthAppId());// 授权商户的AppId 应用id
        auth.setExpiresIn(response.getExpiresIn());// 令牌有效期
        auth.setReExpiresIn(response.getReExpiresIn());// 刷新令牌有效期
        auth.setAppRefreshToken(response.getAppRefreshToken());// 刷新令牌时使用
        return auth;
    }
    
    public static void main(String[] args) {
    	  AlipayClient alipayClient = AlipayUtil.getAlipayClient();
    	AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
    	request.setBizContent("{" +
    	"    \"app_auth_token\":\"201705BB832c677de9b148a1a500f1fec3e06X05\"" +
    	"  }");
    	try {
			AlipayOpenAuthTokenAppQueryResponse response = alipayClient.execute(request);
			
			Map map = response.getParams()	;
			
			System.out.println(map.size());
			Set<String> set = map.keySet()   ;
			
			for (String object : set) {
				
				System.out.println(object+":"+map.get(object));
			}
			
			
			System.out.println(response.getAuthAppId());
			System.out.println(response.getBody());
			System.out.println(response.getCode());
			System.out.println(response.getErrorCode());
			System.out.println(response.getMsg());
			System.out.println(response.getStatus());
			System.out.println(response.getSubCode());
			System.out.println(response.getSubMsg());
			System.out.println(response.getUserId());
			System.out.println(response.getAuthEnd());
			System.out.println(response.getAuthMethods());
			System.out.println(response.getAuthStart());
			System.out.println(response.getExpiresIn());
		
	
			
			
			
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}
