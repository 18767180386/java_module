package com.aiiju.pay.controller.wx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiiju.common.util.HttpClientUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.service.ShopService;

/**
 * 
 * @ClassName: WxAuthController
 * @Description: 微信授权控制器
 * @author 小飞
 * @date 2016年12月9日 下午2:52:23
 *
 */
@Controller
@RequestMapping("/wxauth")
public class WxAuthController {

    private static Logger logger = Logger.getLogger(WxAuthController.class);

    @Autowired
    private ShopService shopService;
    /**
     * 第三方授权，获取商户token
     * 
     * @param req
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/getToken")
    public String getToken(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        String url = Configure.GET_OPEN_ID + "?appid=" + Configure.getAppid() + "&secret=" + Configure.getAppsecret() + "&code=" + code
                + "&grant_type=authorization_code";
        
        System.out.println("微信getToken ，url="+url);
        if (code != null) {
            String result = HttpClientUtil.doGet(url);
            Map<String, String> map = JsonUtils.jsonToPojo(result, Map.class);
            if (map.containsKey("openid")) {
                try {
                    String openid = map.get("openid");
                    req.setAttribute("openId", openid);
                    String[] data = req.getParameter("state").split("_");// data[0]:storeId
                                                                         // data[1]:shopName
                    req.setAttribute("storeId", data[0]);
//                    req.setAttribute("shopName", shopService.getShopName(data[0]));//因为乱码，所以需会重新查询
                    req.setAttribute("shopName", data[1]);//因为乱码，所以需会重新查询
                    
                    System.out.println("微信getToken ，storeId="+data[0]);
                    System.out.println("微信getToken ，shopName="+data[1]);
                    
                    req.setAttribute("payUrl", "https://trade.ecbao.cn/wxpay/pay");
                    return "pay/wx/weixinPay";
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        req.setAttribute("url", url);
        return "auth/user_fail";
    }

}
