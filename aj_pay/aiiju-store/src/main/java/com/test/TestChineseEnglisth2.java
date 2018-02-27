package com.test;

import com.aiiju.common.printtemplate.EnChValidate;

public class TestChineseEnglisth2 {

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
			
			StringBuffer headerandfoot = new StringBuffer();
			
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
		mess.append("================================\r\n");
		mess.append("品名             数量     小计  \r\n");
	

		
		
		for(int i=0;i<goods.length;i++){
			
			String a = goods[i]+getBlank(32-EnChValidate.getBytesStrLength(goods[i]));
			mess.append(a+hh);
			String b =getBlank(21-EnChValidate.getBytesStrLength(count[i]))+count[i]+getBlank(11-EnChValidate.getBytesStrLength(sum[i]))+sum[i];
		
			mess.append(b+hh);
			
			
		}
//			for(int j=0;j<20;j++){
//				mess.append(" "); 				
//			}
//			mess.append(count[i]+"    "+sum[i].length()+hh);
//			
//			
//			
		
        
		
//		for(int i=0;i<orders.length;i++){
//			int xh=i;String ordertitle=orders[i];String price=prices[i];String ordernum=sl[i];
//			String xhstr= xh<=9?xh+". ":xh+".";
//			mess.append(xhstr).append(ordertitle);
//			if(EnChValidate.getChineseLength(ordertitle)>16){
//				mess.append(hc);
//				for(int j=0;j<21;j++){
//					mess.append(" "); 				
//				}				
//			}else{
//				for(int j=EnChValidate.getChineseLength(ordertitle);j<18;j++){
//					mess.append(" "); 				
//				}
//			}
//			mess.append(price);
//			for(int j=EnChValidate.getChineseLength(price);j<8;j++){
//				mess.append(" "); 				
//			}
//			mess.append(ordernum).append(hc); 
//			//if(i%3==1)mess.append("我是很长的产品规格").append(";我是更长的买家留言，比长城还长，虽然只有万分之一的客户才能写到这么变态的长留言，但是韩靖说最好还是做，完美一点，我觉得这样也对，但是我为什么不开心呢，还有你是谁啊？又一个妹妹吗？").append(hc); 
//		}
		

		String content = mess.toString();

		String totalcount = "10";
		String totalsum = "101111.99";
		
//		content +="---品名--------数量---------小计----\r\n";
		content +=printContent+"\r\n";
		content += getSomeMark(32,"-")+"\r\n";
		
		
		
		content +="合计           共"+totalcount+"件"+getBlank(32-21-totalcount.length()-totalsum.length())+"￥1900.99\r\n";
		
		String youhuiType = "立减1999.00元";
		String youhuiSum = "￥1999.00元";
		String yingshouSum = "￥1999.00元";
		String shijiSum = "￥1999.00元";
		String getChange = "￥1999.00元";
		
		content +="优惠类型"+getBlank(24-EnChValidate.getChineseLength(youhuiType))+youhuiType+"\r\n";
		content +="优惠金额"+getBlank(24-EnChValidate.getChineseLength(youhuiSum))+youhuiSum+"\r\n";
		content += getSomeMark(32,"-")+"\r\n";
		
		
		
		content +="应收金额"+getBlank(24-EnChValidate.getChineseLength(yingshouSum))+yingshouSum+"\r\n";
		content +="实收金额"+getBlank(24-EnChValidate.getChineseLength(shijiSum))+shijiSum+"\r\n";                                               
		content +="找零"+getBlank(28-EnChValidate.getChineseLength(getChange))+getChange+"\r\n";

		content += getSomeMark(32,"*")+"\r\n";
		content += "<C>谢谢惠顾，欢迎常来！</C>\r\n";
		content += getSomeMark(32,"-")+"\r\n";
		//content += "<CODE>1234567890</CODE>\r\n"
		content += "<C>此二维码用于订单追溯</C>\r\n";
		content += "<QR>http://www.dzist.com</QR>\r\n";
		System.out.println(content);
	   return content;

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
