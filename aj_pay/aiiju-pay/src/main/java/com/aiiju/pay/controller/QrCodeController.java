package com.aiiju.pay.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiiju.common.pojo.Result;
import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.zfb.util.AlipayUtil;
import com.aiiju.pay.service.BusinessSwitchService;
import com.aiiju.pay.service.PAService;
import com.aiiju.pay.service.ShopService;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.PAShop;
import com.aiiju.pojo.Shop;

/**
 * 
 * @ClassName: QrCodeController
 * @Description: 扫描台卡判断支付方式的控制器（支付宝，微信，银行，由于银行台卡现在有问题，
 * 所以银行台卡功能已关闭，若要打开，修改数据库配置即可）
 * @author 小飞
 * @date 2016年11月17日 下午4:45:39
 *
 */
@Controller
@RequestMapping("/code")
public class QrCodeController {

    @Autowired
    private ShopService shopService;

	@Autowired
	private PAService paService;
	
	
    @Autowired
    private BusinessSwitchService businessSwitchService;
	
    /**
     * 一码多付
     * 
     * @param request
     * @param shopName
     * @return
     * @throws Exception
     */
    @RequestMapping("/showCode")
    public String showCode(HttpServletRequest request) throws Exception {
        String agent = request.getHeader("User-Agent").toLowerCase();
        String storeId = request.getParameter("storeId");
        Shop shop = this.shopService.getShopByStoreId(storeId);
        
        String parentStoreId = request.getParameter("parentStoreId");
		String shopType = request.getParameter("shopType");
		
		String shopName = request.getParameter("shopName");
		String token = request.getParameter("token");
		
		PAShop paShop = paService.getPAShop(storeId, parentStoreId, shopType);
		PAMerchant merchant = paService.getPAMerchant(storeId, parentStoreId, shopType);
        
       
    	Result isOpen = businessSwitchService.getPACodeSwitch();
        
        // 判断手机应用
        if (agent.indexOf("micromessenger") > 0) { // 微信
            if ("2".equals(String.valueOf(shop.getWx()))) {
            	//未签约 ,尝试银行通道
            	if(isOpen.getStatus()==200){
                	if(paShop==null){
                		//银行通道未签约
                        request.setAttribute("msg", "对不起,该商户未签约,不能进行付款");
                        return "pay/payError";
            		}else{
            		       StringBuilder sb = new StringBuilder();
            		        sb.append("https://q.tlinx.com/?O=").append(paShop.getOpenId());
            		        request.setAttribute("url", sb.toString());
            		        return "pay/wx/transition";
            		}
            	}else{
            		 request.setAttribute("msg", "对不起,银行台卡通道已关闭");
            		 System.out.println("银行台卡通道已关闭");
            		 return "pay/payError";
            	}

            }
            // 让买家授权，在回调url中获取code
            StringBuilder sb = new StringBuilder();
            sb.append(Configure.USER_AUTH_URI).append("?appid=").append(Configure.getAppid()).append("&redirect_uri=")
                    .append(URLEncoder.encode(Configure.AUTH_CALL_URL, "utf-8")).append("&response_type=code&scope=snsapi_base&state=")
                    .append(storeId).append("_").append(shopName)
                    .append("&wechat_redirect");
            request.setAttribute("url", sb.toString());
            return "pay/wx/transition";
            
        } else if (agent.indexOf("alipayclient") > 0) {// 支付宝
            if ("2".equals(String.valueOf(shop.getAlipay()))) {

            	//未签约 ,尝试银行通道
            	if(isOpen.getStatus()==200){
                	if(paShop==null){
                		//银行通道未签约
                        request.setAttribute("msg", "对不起,该商户未签约,不能进行付款");
                        return "pay/payError";
            		}else{
            		       StringBuilder sb = new StringBuilder();
            		        sb.append("https://q.tlinx.com/?O=").append(paShop.getOpenId());
            		        request.setAttribute("url", sb.toString());
            		        return "pay/wx/transition";
            		}
            	}else{
            		 request.setAttribute("msg", "对不起,银行台卡通道已关闭");
            		 System.out.println("银行台卡通道已关闭");
            		 return "pay/payError";
            	}

            }
            // 链接跳转自动让买家授权获取其token
            StringBuilder sb = new StringBuilder();
            sb.append(AlipayUtil.USER_AUTH_URI).append("?type=user&state=").append(storeId).append("_")
                    .append(shopName).append("&app_id=").append(AlipayUtil.APP_ID)
                    .append("&scope=auth_base&redirect_uri=").append(AlipayUtil.AUTH_CALL_URL).append("&token=").append(token); //AlipayUtil.AUTH_CALL_URL = "https://trade.ecbao.cn/zfbauth/getToken
            request.setAttribute("url", sb.toString());
            request.setAttribute("shopName1", shopName);
        
            return "pay/zfb/transition";
        }
        request.setAttribute("msg", "对不起,目前不支持该应用支付");
        return "pay/payError";
    }
    
    
    
    
    
    
}
