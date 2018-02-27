<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>授权成功</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<style type="text/css">
		html, body {
			margin: 0px;
			padding: 0px;
		}
		
		.container {
			height: 100%;
			margin: 70px auto;
			font-family: "Microsoft YaHei", "雅黑", "微软雅黑";
		}
		.header {
			text-align: center;
		}
		 .header img {
		   	width:50%;
		}
		
		.rt-text span {
			display: inline-block;
			width: 100%;
			font-weight: 600;
			text-align: center;
			font-size: 22px;
		}
		/*
			成功提示
		*/
		.tip-success {
		     width: 100%;
		     color: #868686;
		     text-align: center;
		     margin-top:20px;
		}
		
		.btn {
		    width: 100%;
		    height: 200px;
		    margin-top:20px;
		}
		.btn button {
		    padding: 5px 10px;
		    width:90%;
		    height: 50px;
		    font-size: 20px;
		    border:0px;
		    border-radius: 5px;
		    background-color: #28A1F7;
		    color: #FFF;
		    position: fixed;
		    bottom:10px;
		    left:0px;
		    right:0px;
		    margin:0px auto;
		}
	</style>
  </head>
  
  <body>
    <div class="container">
        <div class="header">
            <img src="https://trade.ecbao.cn/image/auth_succes.png">
        </div>
        <div class="rt-text"><span>恭喜您，已成功授权！</span></div>
        <div class="tip-success">
            <div>现在，您可以在爱聚收银记账APP内使用</div>
            <div>开单、收款等功能。</div>
        </div>
        <div class="btn">
            <button type="button" id="btn">确定</button>
        </div>
    </div>
    <script type="text/javascript">
        (function() {
            var btn = document.getElementById("btn");
            btn.addEventListener("click",function() {
               AlipayJSBridge.call('closeWebview');
            },false);
        })();
    </script>
  </body>
</html>