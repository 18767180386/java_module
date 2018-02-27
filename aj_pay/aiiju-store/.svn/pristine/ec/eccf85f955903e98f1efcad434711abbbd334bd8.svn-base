package com.aiiju.store.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aiiju.pojo.Goods;
import com.aiiju.store.service.PriceTagService;

/**
 * @ClassName PriceTagController
 * @Description
 * @author zong
 * @CreateDate 2017年8月18日 下午2:32:19
 */
@Controller
@RequestMapping("/priceTag")
public class PriceTagController {

	private static Logger logger = Logger.getLogger(PriceTagController.class);

	@Autowired
	private PriceTagService priceTagService;

	@RequestMapping("/createPriceTag")
	public String createPriceTag(HttpServletRequest request) throws Exception {

		try {
			String phone = request.getParameter("phone");
			List<Goods> list = priceTagService.getAllGoodsByPhone(phone);
			if (list != null && list.size() > 0) {
				String content = getContent(list);
				String code = getCode(list);
				request.setAttribute("code", code);
				request.setAttribute("content", content);
				request.setAttribute("list", list);
				return "user/priceTag";
			} else {
				logger.info("生成价签出错,没找到该用户的商品信息");
				return "user/priceTagError";
			}
		} catch (Exception e) {
			logger.info("生成价签出错,"+e.getMessage());
			return "user/priceTagError";
		}

	}

	public static String getContent(List<Goods> list) {

		StringBuffer sb = new StringBuffer("</br>");
		
		
		sb.append("<table border='0' cellpadding='0' cellspacing='0'>\r\n");
		sb.append("<tr>\r\n");
		
		for (int i = 0; i < list.size(); i++) {

		

			sb.append("<td>\r\n");
			sb.append("<p>" + list.get(i).getName() + "</p>\r\n");
			sb.append("<p class=\"money\">" + list.get(i).getPrice() + "元/" + list.get(i).getUnit() + "</p>\r\n");
			sb.append("<canvas class=\"bcode\" id=\"code" + i + "\"></canvas>\r\n");
			sb.append("</td>\r\n");

		

		}
		sb.append("</tr>\r\n");
		sb.append("</table>\r\n");
		// for(int i=0;i<list.size();i++){
		// String num = get13Random();
		// System.out.println("$(\"#bcTarget"+i+"\").empty().barcode(\""+list.get(i).getCode()+"\",
		// \"ean13\",{barWidth:2, barHeight:30,showHRI:true})");
		// }

		// System.out.println("$(\"#bcTarget"+i+"\").empty().barcode(\""+num+"\",
		// \"ean13\",{barWidth:2, barHeight:30,showHRI:true})");

		// System.out.println(sb.toString());
		System.out.println(sb.toString());
		return sb.toString();

	}

	public static String getCode(List<Goods> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append("$(\"#code" + i + "\").JsBarcode(\"" + list.get(i).getCode() + "\");\r\n");
		}
		System.out.println(sb.toString());
		return sb.toString();

	}
}
