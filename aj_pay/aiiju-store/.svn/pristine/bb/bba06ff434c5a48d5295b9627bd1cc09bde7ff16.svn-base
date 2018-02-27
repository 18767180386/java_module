<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>添加会员</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<link rel="stylesheet" type="text/css"
	href="http://at.alicdn.com/t/font_tkcyp7lvrxwxko6r.css">
<link rel="stylesheet" type="text/css"
	href="https://store.ecbao.cn/js/layer_mobile/need/layer.css" />
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
}

.container {
	width: 100%;
}

.msg {
	margin-top: 10%;
	text-align: center;
	width: 100%;
}

.mcard {
	width: 80%;
	height: 30%;
	border-radius: 10px;
	margin: 20px auto;
	position: relative;
	background-color: #6FCBF4;
	color: #fff;
}

.mcard-header, .mcard-footer {
	width: 100%;
	height: 20%;
}

.mcard-footer {
	padding-top: 2%;
	text-align: center;
	border-top: 1px solid #fff;
	text-align: center;
}

.mcard-footer a {
	color: #fff;
}

.mcard-body {
	width: 100%;
	height: 60%;
	position: relative;
}
.mcard-body .left {
	bottom: 8px;
}

.limit-font {
	font-size: 13px;
}

.left {
	position: absolute;
	left: 8%;
}

.right {
	position: absolute;
	right: 8%;
}

.mcard-top {
	top: 8%;
	font-size: 20px;
}

.mcard-bottom {
	bottom: 5%;
}

.discount {
	font-size: 50px;
}

a {
	text-decoration: none;
}

.content {
	margin-top: 10%;
	width: 100%;
	height: 300px;
}

.content div {
	width: 100%;
	margin-bottom: 20px;
	text-align: center;
}

input {
	border: 0px;
	border-bottom: 2px solid #F2F2F2;
	font-size: 14px;
	padding-left: 8px;
	padding-bottom: 8px;
	width: 70%;
	margin-left: 20px;
	line-height: 15px;
}

.content input:focus {
	outline: medium;
}

.code input {
	width: 38%;
	margin-right: 8px
}

.code button {
	border: 0px;
	padding: 5px 8px;
	font-size: 14px;
	background-color: #01A0E8;
	color: #FFF;
	border-radius: 5px;
	width: 30%;
}
/* .code button:active {
             background-color: #027EB6;
       } */
.btn button {
	margin-top: 5px;
	font-size: 18px;
	width: 82%;
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
	color: #42B5EE;
	border-bottom: 2px solid #42B5EE;
}
</style>
</head>

<body>
	<div class="container">
		<div class="msg">【${shopName}】店铺送您一张会员卡</div>
		<div class="header">
			<div class="mcard">
				<div class="mcard-header">
					<span class="left mcard-top">${mcard.name}</span>
				</div>
				<div class="mcard-body">
					<div class="right">
						<span class="discount">${mcard.discount}</span>折
					</div>
					<div>
						<div class="left">
							<c:if test="${mcard.limitTime == 1}">
								永久有效
							</c:if>
							<c:if test="${mcard.limitTime == 2}">
								<span class="limit-font">有效期:<fmt:formatDate value="${mcard.startDate}"
									pattern="yyyy-MM-dd" />至 <fmt:formatDate
									value="${mcard.endDate}" pattern="yyyy-MM-dd" /></span>
							</c:if>
						</div>
					</div>
				</div>
				<div class="mcard-footer">
					<c:if test="${mcard.notice != ''}">
						<a href="javascript:;;;" id="noticeBtn">查看使用须知</a>
					</c:if>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="new-user">
				<div class="phone">
					<span class="icon iconfont icon-shoujihao"></span><input id="phone"
						type="text" class="input" placeholder="请输入您的手机号" maxlength="11">
				</div>
				<div class="name">
					<span class="icon iconfont icon-xingming"></span><input id="name"
						type="text" class="input" placeholder="请输入您的姓名" maxlength="11">
				</div>
				<div class="code">
					<span class="icon iconfont icon-yanzhengma"></span><input id="code"
						type="text" class="input" placeholder="请输入短信验证码" maxlength="6">
					<button id="codeBtn">获取验证码</button>
				</div>
				<div class="btn">
					<button id="getBtn">领取</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://store.ecbao.cn/js/jquery-1.11.3.min.js"></script>
	<script
		src="https://store.ecbao.cn/js/layer_mobile/layer.js"></script>
	<script type="text/javascript">
    	$("#noticeBtn").on("click",function() {
             layer.open({
			    content: '${mcard.notice}'
			    ,btn: '确定'
			  });
          });
        	var host = "https://store.ecbao.cn";
            $("#codeBtn").on("click",function() {
                var phone = $("#phone").val();
                if (!phone || phone.length < 11) {
                    alert("手机号为空或长度不足");
                    return;
                }
                if(isNaN(phone)) {
                    alert("手机号格式不正确");
                    return;
                }
                var that = this;
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
                $.post(host+"/note/getByConsumer",{"phone":phone},function(resp) {
                    if (resp.status == "500") {
                        alert(resp.msg + ",请重试!");
                    }
                });
            });

            $("#getBtn").on("click",function() {
                var phone = $("#phone").val();
                if (!phone || phone.length < 11) {
                    alert("手机号为空或长度不足");
                    return;
                }
                if(isNaN(phone)) {
                    alert("手机号格式不正确");
                    return;
                }
                var code = $("#code").val();
                if (code == "") {
                    alert("请输入验证码");
                    return;
                }
                if (confirm("领取本卡则表示您已阅读并同意遵守该会员卡的相关规定及使用须知")) {
	                var that = this;
	                $(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
	                $.post(host+"/note/check",{"phone":phone,"code":code},function(resp) {
	                    if (resp.status == "401") {
	                        alert(resp.msg);
	                         $(that).removeAttr("disabled").css("backgroundColor","#01A0E8");
	                        return;
	                    }
                    	var param = {
	                        "phone":phone,
	                        "name":$("#name").val(),
	                        "mCardId":${mcard.id},
	                        "storeId":${mcard.storeId}
	                    };
	                    $.post(host+"/member/saveByConsumer",param,function(res) {
	                        if (res.status == "200") {
	                            window.location.href = host+"/member/saveResult?id="+res.data.id;
	                        } else {
	                        	$(that).removeAttr("disabled").css("backgroundColor","#01A0E8");
	                            alert(res.msg);
	                        }
	                    });
                	});
                 }
            });
    </script>
</body>
</html>

