<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>费率说明</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	 <style>
    html,
    body {
        margin: 0px;
        padding: 0px;
        background-color: #F5F5F5;
    }

    .container {
        height: 100%;
        margin: 10px auto;
        font-family: "Microsoft YaHei", "雅黑", "微软雅黑";
    }
    h4 {
        padding: 0px 0px 15px 15px;
    }
    p {
        padding: 0px 15px 0px 15px;
    }
    .title {
        border-bottom: 2px solid #F0F1F1;
    }
    .wx,.zfb {
        padding: 0px 0px 0px 15px;
    }
    .wx {
        color: #24BD28;
    }
    .zfb {
        color: #3FB3EC;
    }
   .pa {
        color: #24BD28;
    }
    </style>
  </head>
  
  <body>
    <div class="container">
        <h4 class="title">三方支付平台费率说明</h4>
        <p class="desc">商户在使用第三方支付的交易过程中会产生一定费率，扣除相应的费率后再结算余下资金</p>
        <h3 class="wx">微信支付</h3>
        <p class="wx-desc">
            按每笔交易金额的0.6%实时收取，费用四舍五入，保留小数点后两位
        </p>
        <h3 class="zfb">支付宝支付</h3>
        <p class="zfb-desc">
            按每笔交易金额的0.55%实时收取，费用四舍五入，保留小数点后两位
        </p>
       <h3 class="wx">银行渠道（支付宝、微信）支付</h3>
        <p class="wx-desc">
                  按每笔交易金额的0.6%实时收取，费用四舍五入，保留小数点后两位
        </p>
        <h4 class="ps">我司不加收手续费</h4>
        <p class="ps-desc">
            比如，商户通过微信支付发起一笔1314元的交易，则微信支付收取手续费计算方式如下:1314 * 0.6% = 7.884，四舍五入后为7.88元。即微信支付对本笔交易将实时收取7.88元手续费。
        </p>
        <p class="ps-desc">
            若商户通过支付宝发起一笔1314元的交易，则支付宝收取手续费的计算方式如下:1314 * 0.55% = 7.227，四舍五入后为7.23元。即支付宝对本笔交易将实时收取7.23元手续费。
        </p>
    </div>
  </body>
</html>
