<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <title>付款完成</title>
	<style>
		html, body {
			margin: 0px;
			padding: 0px;
			width: 100%;
		}
		.container {
			font-family: "Microsoft YaHei", "雅黑", "微软雅黑";
			background-color: #FFF;
		}
		.header  {
			text-align: center;
		}
		.header img {
			margin-top:20px;
		}
		.header div {
			font-size: 20px;
			margin:20px 0px;
		}
		
		.center {
			width: 100%;
			text-align: center;
			font-size: 30px;
		}
		
		.footer {
			margin-top: 30px;
			padding: 5px 20px 20px 20px;
			border-top: 3px solid #F8F8F8;
		}
		
		.footer table {
			width: 100%;
			border-collapse: separate;
			border-spacing: 0px 20px;
			font-size: 16px;
		}
	</style>	
  </head>
  <body>
    <div class="container" id="container">
        <div class="header">
            <img src="https://trade.ecbao.cn/image/zfb_ok.png">
            <div>付款成功</div>
        </div>
        <div class="center">
            <div class="price"><span>¥</span>${total_fee}</div>
        </div>
        <div class="footer">
            <table>
                <tr>
                    <td>交易时间:</td>
                    <td align="right">${trade_time}</td>
                </tr>
                <tr>
                    <td>流水号:</td>
                    <td align="right" style="font-size: 12px">${out_trade_no}</td>
                </tr>
                <tr>
                    <td>支付方式:</td>
                    <td align="right">支付宝付款</td>
                <tr>
                    <td>实收:</td>
                    <td align="right">${total_fee}元</td>
                </tr>
            </table>
        </div>
    </div>
  </body>
</html>