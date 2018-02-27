package com.aiiju.common.printtemplate;

public class Template1 {

	
	public static String template(String printContent){
		
			StringBuffer headerandfoot = new StringBuffer();
			
		

		

		//标签说明："<BR>"为换行符,"<CB></CB>"为居中放大,"<B></B>"为放大,"<C></C>"为居中,<L></L>字体变高
		//"<QR></QR>"为二维码,"<CODE>"为条形码,后面接12个数字
		String[] orders={"五香牛肉","默哀主席"};
		String[] prices={"1.09","10.09"};
		String[] sl={"1","10"};
		String hc="\r\n";
		StringBuffer mess=new StringBuffer("");
		mess.append("<B>====No1小票====</B>\r\n");
		mess.append("<C>店铺名称</C>\r\n");
		mess.append("时间：2017.04.12 17:190:3 收银员001\r\n");
		mess.append("<C>小票号</C>\r\n");
		mess.append("<C>XS1234596707945<C>\r\n");

		mess.append("名称　　　　         单价  数量 \r\n");
		

		for(int i=0;i<orders.length;i++){
			int xh=i;String ordertitle=orders[i];String price=prices[i];String ordernum=sl[i];
			String xhstr= xh<=9?xh+". ":xh+".";
			mess.append(xhstr).append(ordertitle);
			if(EnChValidate.getChineseLength(ordertitle)>16){
				mess.append(hc);
				for(int j=0;j<21;j++){
					mess.append(" "); 				
				}				
			}else{
				for(int j=EnChValidate.getChineseLength(ordertitle);j<18;j++){
					mess.append(" "); 				
				}
			}
			mess.append(price);
			for(int j=EnChValidate.getChineseLength(price);j<8;j++){
				mess.append(" "); 				
			}
			mess.append(ordernum).append(hc); 
			//if(i%3==1)mess.append("我是很长的产品规格").append(";我是更长的买家留言，比长城还长，虽然只有万分之一的客户才能写到这么变态的长留言，但是韩靖说最好还是做，完美一点，我觉得这样也对，但是我为什么不开心呢，还有你是谁啊？又一个妹妹吗？").append(hc); 
		}
		

		String content = mess.toString();

		
	//	content +="---品名--------数量---------小计----\r\n";
		content +=printContent+"\r\n";
		content +="-------------------------------\r\n";	
		content +="合计        共5件        ￥19000000.99\r\n";
		content +="优惠类型                   立减1999.00元\r\n";
		content +="优惠金额                             1999.00\r\n";
		content +="--------------------------------\r\n";
		content +="应收金额                   ￥19000000.99\r\n";
		content +="实收金额                   ￥19000000.99\r\n";
		content +="找零                          ￥19000000.99\r\n";
		
		
		content += "********************************\r\n";
		content += "<C>谢谢惠顾，欢迎常来！</C>\r\n";
		content += "================================\r\n";
		content += "<C>此二维码用于订单追溯</C>\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content += "<QR>http://www.dzist.com</QR>\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content += "============No1小票完==========\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
       return content;

		
	}
	
}
