<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>完善个人信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<style>
html, body {
	margin: 0;
	padding: 0;
}

.container {
	width: 100%;
}

.pic img {
	width: 100%;
}

.tip {
	padding: 10px 20px;
}

.btns {
	text-align: center;
}

button {
	width: 90%;
	height: 45px;
	margin-bottom: 20px;
	font-size: 18px;
	border-radius: 10px;
	background-color: #01A0E8;
	color: #FFF;
	border: 0px;
}
</style>
</head>

<body>
	<div class="container">
		<div class="pic">
			<img src="https://store.ecbao.cn/image/get_success.png" />
		</div>
		<div class="tip">
			<p>现在您在店内消费时，向收银员报出您的手机号即可使用会员卡</p>
			<p>您可以选择完善个人信息，以便商家更好地服务</p>
		</div>
		<div class="btns">
			<button type="button" id="updateBtn">现在就完善</button>
			<br />
			<button type="button" id="cancelBtn">算了,下次再说</button>
		</div>
	</div>
	<script type="text/javascript"
		src="https://store.ecbao.cn/js/jquery-1.11.3.min.js"></script>
	<script>
		$(function() {
			var host = "https://store.ecbao.cn";
			//修改按钮
			$("#updateBtn").on("click",function() {
				$(this).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
				window.location.href = host + "/member/updateUI?id="+${id};
			});
			//取消按钮		
			$("#cancelBtn").on("click", function() {
				var ua = navigator.userAgent.toLowerCase();
				console.log(ua);
				if (ua.match(/MicroMessenger/i) == "micromessenger") {
					WeixinJSBridge.call('closeWindow');
				} else if (ua.indexOf("alipay") != -1) {
					AlipayJSBridge.call('closeWebview');
				} else if (ua.indexOf("baidu") != -1) {
					BLightApp.closeWindow();
				} else {
					window.history.go(-2);
				}
			});
		});
	</script>
</body>
</html>
