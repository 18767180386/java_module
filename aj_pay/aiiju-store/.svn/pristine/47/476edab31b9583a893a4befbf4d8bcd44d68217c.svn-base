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
<title>priceTag</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style>
#main {
	position: absolute;
	width: 400px;
	height: 200px;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	margin-top: -100px;
}
</style>
</head>

<body>
	<div id="main"> <div style="overflow:hidden; margin: 0 auto;
	width:512px;">
	<input type="text" id="phone" placeholder="输入手机号后生成价签"   style="border:1px solid #00a0e9; width:400px; line-height:20px; padding:9px 5px; display:block; float:left;" />
    <input type="submit" style="width:100px; line-height:40px; background-color:#00a0e9; border:none; display:block; float:left; color:#fff;padding-top: 0px;" value="确定" onclick="go();"/>
</div>
</div> 

<script>
  
  function checkPhone(){ 

}
  
	function go() {
		var phone = document.getElementById("phone").value;
		
		  
	    if(!(/^1[34578]\d{9}$/.test(phone))){ 
	        alert("手机号码有误，请重填");  
	        return false; 
	    } 
		
		if (phone == '') {
			alert("请输入手机号");
			return;
		} else {
			location.href = "https://store.ecbao.cn/priceTag/createPriceTag?phone=" + phone;
		}
	}
</script> 

  </body>
</html>
