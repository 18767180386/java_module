<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>授权失败</title>
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
		
		 .header img {
		   	width:100%;
		}
		
		.rt-text span {
			display: inline-block;
			width: 100%;
			font-weight: 600;
			text-align: center;
			font-size: 22px;
			margin-top:20px;
		}
		.tip-fail {
		     width: 100%;
		     color: #868686;
		     text-align: center;
		     margin-top:20px;
		}
		.tip-fail div {
			margin-bottom:5px;
		}
		a {
		    text-decoration: none;
		    color: #868686;
		}
		.btns {
		    width: 100%;
		    height: 200px;
		}
		.btns button {
		    padding: 5px 10px;
		    bottom:10px;
		    width:120px;
		    height: 50px;
		    font-size: 20px;
		    border-radius: 5px;
		    border:0px;
		    text-align: center;
		    position: fixed;
		    bottom:10px;
		    margin:0px auto;
		}
		.left-btn {
		   border: 2px solid #009FE7;
		   background-color: #FFF;
		   color: #009FE7;
		   left:30px;
		}
		.right-btn {
		    background-color: #009FE7;
		    color: #FFF;
		    right:30px;
		}
  	</style>
  </head>
  
  <body>
    <div class="container">
        <div class="header">
            <img src="https://trade.ecbao.cn/image/auth_fail.png">
        </div>
        <div class="rt-text"><span>很抱歉，授权失败！</span></div>
        <div class="tip-fail">
            <div>请稍后重试，若多次授权失败，请联系</div>
            <div>爱聚收银记账APP的客服人员</div>
            <div class="tel"><a href="tel:0571-89935939" id="tel">0571-89935939</a></div>
        </div>
        <div class="btns">
            <button class="left-btn" id="call">联系我们</button>
            <button class="right-btn" id="re-auth">重新授权</button>
        </div>
    </div>
    <script>
    	(function() {
    		var call = document.getElementById("call");
    		call.addEventListener("click", function(e) {
    			document.getElementById("tel").click();
    		});
    		
    		var auth_btn = document.getElementById("re-auth");
    		auth_btn.addEventListener("click", function(e) {
    			window.location.href = "${authURL}";
    		});
    	})();
    </script>
  </body>
</html>