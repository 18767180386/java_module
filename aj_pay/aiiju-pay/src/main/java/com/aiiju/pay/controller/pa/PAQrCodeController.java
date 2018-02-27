package com.aiiju.pay.controller.pa;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiiju.pay.service.PAService;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.PAShop;

/**
 * 
 * @ClassName: PAQrCodeController
 * @Description: 扫描台卡判断支付方式的控制器(平安银行)  （银行台卡已和微信支付宝台卡合并一起，此处代码已没用）
 */
@Controller
@RequestMapping("/paCode")
public class PAQrCodeController {
    
	@Autowired
	private PAService paService;

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
		String parentStoreId = request.getParameter("parentStoreId");
		String shopType = request.getParameter("shopType");
		
		PAShop shop = paService.getPAShop(storeId, parentStoreId, shopType);
		PAMerchant merchant = paService.getPAMerchant(storeId, parentStoreId, shopType);
		
		if(shop==null){
            request.setAttribute("msg", "对不起,该商户未签约,不能进行付款");
            return "pay/payError";
		}
        StringBuilder sb = new StringBuilder();
        sb.append("https://q.tlinx.com/?O=").append(shop.getOpenId()).append("&remark1=测试台卡1");
        

        request.setAttribute("url", sb.toString());
        return "pay/wx/transition";
        

    }
}
