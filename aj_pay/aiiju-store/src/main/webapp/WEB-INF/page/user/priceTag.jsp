<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.aiiju.pojo.Goods"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String content = (String) request.getAttribute("content");
	String code = (String) request.getAttribute("code");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>价签</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="https://store.ecbao.cn/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="https://store.ecbao.cn/js/JsBarcode.js"></script>
<link rel="stylesheet" type="text/css" href="https://store.ecbao.cn/css/priceTag.css" />
<script type="text/javascript" src="https://store.ecbao.cn/js/jquery.jqprint-0.3.js"></script>
<style>
* {
	box-sizing: border-box;
}

.box {
	width: 1240px;
	height: 1754px;
	overflow: hidden;
}

table {
	border-top: 1px dashed #333333;
	border-left: 1px dashed #333333;
}

tr, tbody, table {
	width: 100%;
}

table td {
	border-bottom: 1px dashed #333333;
	border-right: 1px dashed #333333;
	padding: 3px 10px;
	width: 33.33%;
	display: inline-block;
}

p {
	padding: 0 10px;
	text-align: center;
	line-height: 30px;
	margin: 0;
}

p.money {
	font-size: 24px;
}

canvas.bcode {
	width: 200px;
	margin: 0 auto;
	display: block;
}
</style>

  
</head>

		<body>
			<div id="content">
			
				<div class="box"><%=content%></div>
				
<!-- 				<div class="side-bar"> -->
<!-- 					<a href="javascript:print();" class="icon-qq">打印</a> -->
<!-- 				</div> -->
<!-- 				<input type="button" onclick="print()" value="打印" /> -->
<!-- 			</div> -->
		</body>


 
<script language="javascript">

	 <%=code%>
	 
</script>

</html>
<script language="javascript">

	function print() {
		$("#content").jqprint();
	}
	 
</script>