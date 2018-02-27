<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// String shopName =new String(request.getParameter("shopName").getBytes("ISO-8859-1"),"UTF8");
String shopName1 =request.getParameter("shopName");

String fromPhone = request.getParameter("fromPhone");
String toPhone = request.getParameter("toPhone");

// System.out.println("页面shopName="+shopName);
System.out.println("页面shopName1="+shopName1);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>加入店铺</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_tkcyp7lvrxwxko6r.css">
    <style type="text/css">
        html,body {
            margin: 0;
            padding: 0;
        }
        .container {
            width:100%;
        }
        .header img {
        	width:100%;
        }
/*         .nav  { */
/*             margin:10px auto; */
/*             width:100%; */
/*             height:25px; */
/*             border-bottom: 2px solid #F2F2F2; */
/*         } */
        .nav-title {
        	font-size: 16px;
/*         	padding:0px 20px 5px 20px; */
/*             position: absolute; */
        }
        #new-title {
/*         	left:20%; */
        }
        #old-title {
        	right:50%;
        }
        .content{
           margin-top:10%;
           width:100%;
           height:300px;
        }
        .content div {
        	 width:100%;
        	 margin-bottom: 20px;
        	 text-align: center;
        }
         input {
            border: 0px;
            border-bottom: 2px solid #F2F2F2;
            font-size: 14px;
            padding-left:8px;
            padding-bottom: 8px;
            width:70%;
            margin-left: 20px;
        }
        .content input:focus { 
        	outline:medium; 
        }
       .code input {
            width:38%;
            margin-right: 8px
       }
       .code button {
            border: 0px;
            padding:5px 8px;
            font-size: 14px;
            background-color: #01A0E8;
            color: #FFF;
            border-radius: 5px;
            width:30%;
       }
       /* .code button:active {
             background-color: #027EB6;
       } */
       .btn button {
             margin-top: 15px;
             font-size: 18px;
             width:82%;
             padding: 12px 0px;
             border: 0px;
             border-radius: 5px;
             background-color: #01A0E8;
             color: #FFF;
       }
       .btn button:active {
             background-color: #027EB6;
       }
       .iconfont {
         font-size: 22px;
       }
       .active {
           margin-top: 10px;
       text-align:center;
/* 	       	color : #42B5EE; */
/* 	        border-bottom: 2px solid #42B5EE; */
       }
    </style>
  </head>
  
  <body>
    <div class="container">
        <div class="header">
        	<img src="https://store.ecbao.cn/image/join.png"/>
        </div>
        <div class="nav">
        
           <div class="nav-title active" id="new-title"><%=fromPhone%>邀请您加入<%=shopName1%>店铺成为店长</div>
<!--            <div class="nav-title" id="old-title">已有账号</div> -->
        </div>
        <div class="content">
             <div class="new-user">
                   <div class="phone">
                       <span class="icon iconfont icon-shoujihao"></span><input id="new-phone" type="text" class="input" placeholder="请输入您的手机号" maxlength="11">
                   </div>
                   <div class="code">
                       <span class="icon iconfont icon-yanzhengma"></span><input id="code" type="text" class="input" placeholder="请输入短信验证码" maxlength="6"><button id="codeBtn">获取验证码</button>
                   </div>
                   <div class="password">
                       <span class="icon iconfont icon-mima"></span><input id="new-password" type="password" class="input" placeholder="请输入密码(8~20位字母与数字)" maxlength="20">
                   </div>
                   <div class="btn">
	                 <button id="newJoinBtn">加入店铺</button>
	               </div>
             </div>
<!--              <div class="old-user" style="display:none"> -->
<!--                    <div class="phone"> -->
<!--                        <span class="icon iconfont icon-shoujihao"></span><input id="old-phone" type="text" class="input" placeholder="请输入您的手机号" maxlength="11"> -->
<!--                    </div> -->
<!--                    <div class="password"> -->
<!--                        <span class="icon iconfont icon-mima"></span><input id="old-password" type="password" class="input" placeholder="请输入密码(8~20位字母与数字)" maxlength="20"> -->
<!--                    </div> -->
<!--                    <div class="btn"> -->
<!-- 	                 <button id="oldJoinBtn">加入店铺</button> -->
<!-- 	               </div> -->
<!--              </div> -->
        </div>
    </div>
    <script type="text/javascript" src="https://store.ecbao.cn/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    
    var toPhone = <%=toPhone%>;
    $("#new-phone").val(toPhone)
        $(function() {
        	$("#new-title").on("click",function() {
        		 $(this).addClass("active");
        		 $("#old-title").removeClass("active");
				 $(".new-user").show();
				 $(".old-user").hide();       	
        	});
        	$("#old-title").on("click",function() {
        		 $(this).addClass("active");
        		 $("#new-title").removeClass("active");
				 $(".new-user").hide();
				 $(".old-user").show();       	
        	});
     
     var host = "https://store.ecbao.cn";
      //测试
         //  var host = "http://16794ui705.iok.la:32542"; 
            $("#codeBtn").on("click",function() {
            	var newPhone = $("#new-phone").val();
            	if (!newPhone || newPhone.length < 11) {
		            alert("手机号为空或长度不足");
		            return;
		        }
		        if(isNaN(newPhone)) {
		        	alert("手机号格式不正确");
		        	return;
		        }
		        if(toPhone!=newPhone) {
		        	alert("只能输入被邀请者手机号码");
		        	return;
		        }
				var that = this;
			
			
				$.post(host+"/note/getClerkCode",{"phone":newPhone,"storeId":"${storeId}","type":"1"},function(resp) {
					if (resp.status == "501") {
						alert(resp.msg + "");
						clearInterval(si);
					} else if (resp.status == "505") {
						alert(resp.msg + "");
					} else if (resp.status == "500") {
						alert(resp.msg + ",请重试!");
					}else{
						$(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
				        var timeout = 59;
						var si = setInterval(function() {
					$(that).text(timeout+"s后再获取");
					--timeout;
					if (timeout < 0) {
						clearInterval(si);
						$(that).text("获取验证码").removeAttr("disabled").css("backgroundColor","#01A0E8");
					}
				},1000);
					}
					
				});
            });

            $("#newJoinBtn").on("click",function() {
            	var newPhone = $("#new-phone").val();
				if (!newPhone || newPhone.length < 11) {
		            alert("手机号为空或长度不足");
		            return;
		        }
		        if(isNaN(newPhone)) {
		        	alert("手机号格式不正确");
		        	return;
		        }
		        var code = $("#code").val();
				if (code == "") {
					alert("请输入验证码");
					return;
				}
				var password = $("#new-password").val();
				if (password == "") {
					alert("请输入密码！");
					return;
				}
				var that = this;
				$(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
				$.post(host+"/note/check",{"phone":newPhone,"code":code},function(resp) {
					$(that).removeAttr("disabled").css("backgroundColor","#01A0E8");
					if (resp.status == "401") {
						alert(resp.msg);
						return;
					}
					
					var shopnameTemp = '<%=shopName1%>';
					
					$.post(host+"/user/inviteUser",{"phone":newPhone,"password":password,"storeId":"${storeId}"},function(res) {
						if (res.status == "200") {
						var shopname_now = '<%=shopName1%>';
						var nowurl = host+"/user/inviteResult?shopName="+shopnameTemp;
					    window.location.href =nowurl;
						} else {
							alert(res.msg);
						}
					});
				});
            });
            
             $("#oldJoinBtn").on("click",function() {
            	var oldPhone = $("#old-phone").val();
            	var password = $("#old-password").val();
            	if (!oldPhone || oldPhone.length < 11) {
		            alert("手机号为空或长度不足");
		            return;
		        }
		        if(isNaN(oldPhone)) {
		        	alert("手机号格式不正确");
		        	return;
		        }
				if (password == "") {
					alert("请输入密码！");
					return;
				}
				var that = this;
				$(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
				$.post(host+"/user/inviteUser",{"phone":oldPhone,"password":password,"storeId":"${storeId}"},function(res) {
					$(that).removeAttr("disabled").css("backgroundColor","#01A0E8");
					if (res.status == "200") {
						window.location.href = host+"/user/inviteResult?shopName="+res.data;
					}else if (res.status == "201") {
						alert(res.msg + ",请解除关系后再进行该店铺绑定");
					} else if (res.status == "400") {
						alert(res.msg + ",请重新获取链接加入店铺");
					} else if (res.status == "401") {
						alert(res.msg);
					}
				});
            });
        });
    </script>
  </body>
</html>
