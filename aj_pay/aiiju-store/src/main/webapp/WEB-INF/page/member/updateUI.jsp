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
<title>完善信息</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<link rel="stylesheet" type="text/css"
	href="https://store.ecbao.cn/js/date/css/LCalendar.min.css">
<link rel="stylesheet" type="text/css"
	href="https://store.ecbao.cn/js/area/css/lArea.css">
<style>
html, body {
	margin: 0;
	padding: 0;
}

body {
	background-color: #fff;
}

.container {
	width: 100%;
}

.content {
	width: 100%;
	height: 40%;
	position: relative;
}

.content .row {
	width:100%;
	height: 10%;
	padding: 6%;
	border-bottom: 2px solid #EEE;
	line-height: 10%;
	display: table;
}

.left, .right {
	display: table-cell;
	vertical-align: middle;
}

.right {
	position: absolute;
	right: 6%;
}

#name,#birthday {
	border: 0px;
	outline: none;
	text-align: right;
	font-size: 16px;
}

#input-area {
	height: 30px;
	width: 200px;
	border: 0px;
	outline: none;
	text-align: right;
	font-size: 16px;
}

#detail-address, #remark {
	width: 99%;
	height: 30%;
	border: 0px;
	padding-left:6%;
}

.custom-area {
	text-align: center;
}

.btn {
	text-align: center;
	margin-top: 20px;
}

.btn button {
	font-size: 18px;
	width: 80%;
	height: 40px;
	border-radius: 5px;
	background-color: #01A0E8;
	color: #FFF;
	border: 0px;
}
</style>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<span class="left">姓名</span> <span class="right" style="margin-top: -2%;"><input type="text" id="name" value="${member.name}" /></span>
			</div>
			<div class="row">
				<span class="left">手机号</span> <span class="right">${member.phone}</span>
			</div>
			<div class="row">
				<span class="left">性别</span> <span class="right" style="margin-top: -3%;"> <input
					type="radio" name="gender" value="1" />男 <input type="radio"
					name="gender" value="2" />女
				</span>
			</div>
			<div class="row">
				<span class="left">生日</span> <span class="right" style="margin-top: -3%;"> <input
					id="birthday" type="text" placeholder="请选择生日" readonly="readonly" />
				</span>
			</div>
			<div class="row">
				<span class="left">地区</span> <span class="right" style="margin-top: -3%;"> <input
					id="input-area" type="text" readonly="" placeholder="请选择城市"
					readonly="readonly" />
				</span>
			</div>
			<div class="row" style="border-bottom: 0px;">
				<span class="left">详细地址</span><br />
			</div>
			<div class="custom-area" style="border-bottom: 2px solid #EEE;">
				<textarea id="detail-address" placeholder="请填写详细地址"></textarea>
			</div>
			<div class="row" style="border-bottom: 0px;">
				<span class="left">备注</span>
			</div>
			<div class="custom-area" style="border-bottom: 2px solid #EEE;">
				<textarea id="remark" placeholder="请填写详情"></textarea>
			</div>
			<div class="btn" style="border:0px;">
				<button type="button" id="submitBtn">确认提交</button>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://store.ecbao.cn/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"
		src="https://store.ecbao.cn/js/date/js/LCalendar.min.js"></script>
	<script type="text/javascript"
		src="https://store.ecbao.cn/js/area/js/lArea.min.js"></script>
	<script>
		$(function() {
			//日期控件
			var calendar = new LCalendar();
			calendar.init({
				'trigger' : '#birthday', //标签id
				'type' : 'date', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
				'minDate' : '1970-1-1', //最小日期
				'maxDate' : new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate() //最大日期
			});
			//地区控件
			var area = new lArea();
			area.init({
				'trigger' : '#input-area',
				'data' : lAreaData //'js/AreaData.json'
			});
	
			//提交按钮
			$("#submitBtn").on("click", function() {
				var that = this;
	        	$(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
				var inputArea = $("#input-area").val();
				var data = inputArea.split(" ");
				var params = {
					id : "${member.id}",
					name : "${member.name}",
					gender : $("input[type=radio]:checked").val() || "",
					birthday : $("#birthday").val() || "",
					province : data[0] || "",
					city : data[1] || "",
					area : data[2] || "",
					address : $("#detail-address").val(),
					remark : $("#remark").val()
				};
				var host = "https://store.ecbao.cn";
				$.post(host + "/member/update",params,function(res) {
					if (res.status == 200) {
							window.location.href = host + "/member/updateResult";
						} else {
							$(that).removeAttr("disabled").css("backgroundColor","#01A0E8");
							alert(res.msg);
						}
				});
			});
		});
	</script>
</body>
</html>
