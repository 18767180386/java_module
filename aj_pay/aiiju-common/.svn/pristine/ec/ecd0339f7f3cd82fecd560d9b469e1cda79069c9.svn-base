package com.aiiju.common.util;

import java.util.HashMap;
import java.util.Map;

public class Print365Util {

	public static final String PRINTER_SN = "kdt2100749";// "0101";//"kdt1010236";//"915500404";//"abc1234";//
	public static final String PRINTER_KEY = "008c6";// "eed90"// ;//"Q2RaD3lp";//"abc1234";//
	public static final String PRINTER_IP = "http://open.printcenter.cn:8080";

	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer("测试打印");
		//String orderindex = addOrder(sb);
	//	queryOrder(orderindex);
	//	queryPrinterStatus();

	}

	public static String addOrder(String printContent) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("deviceNo", PRINTER_SN);
		param.put("key", PRINTER_KEY);
		param.put("printContent", printContent.toString());
		param.put("times", "1");
		String sr = HttpClientUtil.doPost(PRINTER_IP + "/addOrder", param);
		System.out.println("服务器返回内容：" + sr);
		return sr;
	}

	public static String queryOrder(String orderindex) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("deviceNo", PRINTER_SN);
		param.put("key", PRINTER_KEY);
		param.put("orderindex", orderindex);
		String sr = HttpClientUtil.doPost(PRINTER_IP + "/queryOrder", param);
		System.out.println("服务器返回内容：" + sr+"；打印单号："+orderindex);
		return sr;
	}

	public static String queryPrinterStatus() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("deviceNo", PRINTER_SN);
		param.put("key", PRINTER_KEY);
		String sr = HttpClientUtil.doPost(PRINTER_IP + "/queryPrinterStatus", param);
		System.out.println("服务器返回内容：" + sr);

		return sr;
	}

}
