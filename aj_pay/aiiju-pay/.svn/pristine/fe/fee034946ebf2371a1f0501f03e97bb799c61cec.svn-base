<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<title>第三方应用授权</title>
    <style type="text/css">
    html,
    body {
        margin: 0px;
        padding: 0px;
        height: 100%;
    }

    .container {
        height: 100%;
    }

    .header {
        width: 90%;
        height: 30%;
        margin: 20px auto;
        text-align: center;
        border-radius: 10px;
        border: 1px solid #F1F1F2;
        box-shadow: 1px 1px 5px 4px rgba(0, 0, 0, .1);
    }

    .header-content {
        width: 90%;
        height: 100%;
        text-align: center;
        position: relative;
    }

    .header-content input {
        position: absolute;
        top: 18%;
        left: 5%;
        width: 100%;
        height: 35px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        padding-left: 10px;
        border-radius: 6px;
        border: 2px solid #D3DDE5;
        outline: none;
        display: block;
        margin-bottom:35px;
    }

    .header-content button {
        bottom: 18%;
        left: 5%;
        width: 100%;
        background-color: #27A0F6;
        color: #FFF;
        border: 0px;
        padding: 12px;
        border-radius: 6px;
        font-size: 16px;
        outline: none;
        position: absolute;
        display: block;
    }

    .body {
        width: 90%;
        height: 55%;
        margin: 30px auto;
        text-align: center;
        border-radius: 10px;
        border: 1px solid #F1F1F2;
        box-shadow: 1px 1px 5px 4px rgba(0, 0, 0, .1);
    }

    .body-content {
        width: 100%;
        height: 100%;
        text-align: center;
        position: relative;
    }

    .qrcode img {
        display: block;
        position: absolute;
        z-index: 99;
        top: 50%;
        left: 50%;
        height: 78px;
        width: 78px;
        margin-top: -39px;
        margin-left: -39px;
    }

    #msg {
        margin-top: 20px;
    }

    @media screen and (min-width: 320px) and (max-width: 360px) {
        .qrcode {
            position: absolute;
            width: 180px;
            height: 180px;
            top: 50%;
            left: 50%;
            margin-top: -90px;
            margin-left: -90px;
        }
        .qrcode canvas {
            width: 180px;
            height: 180px;
        }
    }

    @media screen and (min-width: 375px) and (max-width: 400px) {
        .qrcode {
            position: absolute;
            width: 210px;
            height: 210px;
            top: 50%;
            left: 50%;
            margin-top: -105px;
            margin-left: -105px;
        }
        .qrcode canvas {
            width: 210px;
            height: 210px;
        }
    }

    @media screen and (min-width: 412px) and (max-width: 500px) {
        .qrcode {
            position: absolute;
            width: 256px;
            height: 256px;
            top: 50%;
            left: 50%;
            margin-top: -128px;
            margin-left: -128px;
        }
        .qrcode canvas {
            width: 256px;
            height: 256px;
        }
    }

    @media screen and (min-width: 600px) and (max-width: 800px) {
        .qrcode {
            position: absolute;
            width: 356px;
            height: 356px;
            top: 50%;
            left: 50%;
            margin-top: -178px;
            margin-left: -178px;
        }
        .qrcode canvas {
            width: 356px;
            height: 356px;
        }
    }
    </style>
</head>

<body>
    <div class="container">
        <div class="header">
            <div class="header-content">
                <input type="text" name="userName" id="userName" placeholder="请输入手机号" />
                <button id="searchBtn" type="button">查询</button>
            </div>
        </div>
        <div class="body">
            <div class="body-content">
                <div class="qrcode"><img src="https://trade.ecbao.cn/image/logo.png" style="display:none;"/></div>
                <div id="msg"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="https://trade.ecbao.cn/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://trade.ecbao.cn/js/jquery.qrcode.min.js"></script>
<script type="text/javascript">
$(function() {
    var name;
    $("#searchBtn").on("click", function() {
        var userName = $("#userName").val();
        if(isNaN(userName)) {
        	alert("手机号格式不正确");
        	return;
        }
        if (!userName || userName.length != 11) {
            alert("手机号为空或长度不正确");
            return;
        }
        if (name == userName) {
        	return;
        }
        var that = this;
		$(that).attr("disabled","disabled").css("backgroundColor","#CDCDCD");
       	$.ajax({
       		"type":"POST",
       		"url":"https://trade.ecbao.cn/zfbauth/check",
       		"data":{"userName":userName},
       		"dataType":"json",
       		"success":function(resp) {
       			name = userName;
       			$(that).removeAttr("disabled").css("backgroundColor","#27A0F6");
	            if (resp.status == "200") {
	           		$(".qrcode").qrcode({"text":resp.data});
	           		$("img").show();
			        $("#msg").html("<div>让客户扫描二维码进行授权</div>");
	            } else if (resp.status == "400") {
	                alert(resp.msg);
	            }
       		}
       	});
    });
    
    $("input").on("focus",function() {
        $(".header").css("height","60%");
    }).on("blur",function() {
        $(".header").css("height","30%");
    });
});
</script>

</html>
