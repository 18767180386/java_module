package com.test;

import com.aiiju.common.printtemplate.EnChValidate;

public class TestChineseEnglisth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] goods={"五香牛肉","默哀主席"};
		
		
		System.out.println(getBlank(32-EnChValidate.getBytesStrLength(goods[1]))+goods[1]);
		System.out.println(getBlank(4).length());
		//String validateStr = "你好123abc你好123abc";
		String validateStr = "￥";
		System.out.println("查询的字符串长度为："+EnChValidate.getBytesStrLength(validateStr));
		
		
		System.out.println(getSomeMark(4, "*"));
		
//		System.out.println(EnChValidate.getChineseLength(validateStr));
//		System.out.println(EnChValidate.getRegExpLength(validateStr));
		
		System.out.println(template(""));;
	}
	 public static String template(String printContent){	
		//标签说明："<BR>"为换行符,"<CB></CB>"为居中放大,"<B></B>"为放大,"<C></C>"为居中,<L></L>字体变高
		//"<QR></QR>"为二维码,"<CODE>"为条形码,后面接12个数字
		String[] goods={"五香牛肉","默哀主席"};
		String[] count={"11kg","200"};
		String[] sum={"3512.00","1120.00"};
		String hh="\r\n";
		StringBuffer mess=new StringBuffer("");
		
		mess.append("<B>====No1小票====</B>\r\n");
		
		mess.append("<C>爱聚收银店铺</C>\r\n");
		
		mess.append("<C>时间：2017.04.12 日期：17:19:30</C> \r\n");
		
		mess.append("<C>收银员001</C> \r\n");
		
		mess.append("<C>小票号:XS1234596707945<C>\r\n");
	//	mess.append("--品名------------数量----小计--\r\n");
		mess.append( getSomeMark(32,"=")+"\r\n");
		mess.append("品名             数量     小计  \r\n");

		for(int i=0;i<goods.length;i++){
			
			String a = goods[i]+getBlank(32-EnChValidate.getBytesStrLength(goods[i]));
			mess.append(a+hh);
			String b =getBlank(21-EnChValidate.getBytesStrLength(count[i]))+count[i]+getBlank(11-EnChValidate.getBytesStrLength(sum[i]))+sum[i];
			mess.append(b+hh);

		}


		String totalcount = "10";
		String totalsum = "￥101111.99";

		mess.append(printContent+"\r\n");
		mess.append( getSomeMark(32,"-")+"\r\n");

		
		mess.append("合计           共"+totalcount+"件"+getBlank(32-21-totalcount.length()-totalsum.length())+totalsum+"\r\n");
		
		String youhuiType = "立减1999.00元";
		String youhuiSum = "￥1999.00元";
		String yingshouSum = "￥1999.00元";
		String shijiSum = "￥1999.00元";
		String getChange = "￥1999.00元";
		
		mess.append("优惠类型"+getBlank(24-EnChValidate.getChineseLength(youhuiType))+youhuiType+"\r\n");
		mess.append("优惠金额"+getBlank(24-EnChValidate.getChineseLength(youhuiSum))+youhuiSum+"\r\n");
		mess.append( getSomeMark(32,"-")+"\r\n");

		mess.append("应收金额"+getBlank(24-EnChValidate.getChineseLength(yingshouSum))+yingshouSum+"\r\n");
		mess.append("实收金额"+getBlank(24-EnChValidate.getChineseLength(shijiSum))+shijiSum+"\r\n");                                               
		mess.append("找零"+getBlank(28-EnChValidate.getChineseLength(getChange))+getChange+"\r\n");

		mess.append( getSomeMark(32,"*")+"\r\n");
		mess.append( "<C>谢谢惠顾，欢迎常来！</C>\r\n");
		mess.append( getSomeMark(32,"-")+"\r\n");
		//mess.append( "<CODE>1234567890</CODE>\r\n"
		mess.append( "<C>此二维码用于订单追溯</C>\r\n");
		mess.append( "<QR>http://www.ecbao.cn</QR>\r\n");
		
	   return mess.toString();

	}
	 
	 public static String getBlank(int num){
		 
	   StringBuffer sb = new StringBuffer();
			for(int j=0;j<num;j++){
				sb.append(" "); 				
			}
		 return sb.toString();
	 }
	 
	 public static String getSomeMark(int num,String mark){
		 
		   StringBuffer sb = new StringBuffer();
				for(int j=0;j<num;j++){
					sb.append(mark); 				
				}
			 return sb.toString();
		 }
	 
}
