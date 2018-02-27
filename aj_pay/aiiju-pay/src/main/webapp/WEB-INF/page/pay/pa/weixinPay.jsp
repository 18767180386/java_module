<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
response.setContentType("text/html;charset=UTF-8");

// String shopName =new String(request.getParameter("shopName").getBytes("ISO-8859-1"),"UTF8");
String shopName1 =request.getParameter("shopName");



// System.out.println("页面shopName="+shopName);
System.out.println("页面shopName1="+shopName1);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<title>向店铺【<%=shopName1%>】付款</title>
<style type="text/css">
	html, body {
	margin: 0px;
	padding: 0px;
	background-color: #F0F2F1;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	height: 100%;
}

.container {
	font-family: "思源黑体";
	height: 100%;
}
.title  {
		text-align: center;
		height:30%;
	}
.title img {
	width:20%;
	margin-top: 15px;
}
.title div {
	margin-top: 10px;
	margin-bottom:15px;
}

.area {
	width: 100%;
	height:20%;
	background-color: #FFF;
	margin-bottom: 10px;
	position: relative;
}

.price-title {
	height:18%;
	padding-top: 10px;
	padding-left:25px;
	margin-bottom: 10px;
	font-weight: 500;
}

.price-body {
	height:38%;
	padding-left: 25px;
	padding-bottom:5px;
	font-weight: 700;
	position: absolute;
	width:100%;
	bottom:15%;
}

.icon {
	display:inline-block;
	font-size: 180%;
	color: #36A7F9;
}

#priceInput {
	font-size: 220%;
	color: #36A7F9;
	left: 60px;
	position: absolute;
	top:-5px;
}

.text {
	border-top: 2px solid #F0F2F1;
	padding:1px 0px 2px 25px;
	color: #9C9C9C;
	font-size: 13px;
	letter-spacing: 1px;
	position: absolute;
	bottom: 0px;
	width:100%;
}

.keyboard {
	width: 100%;
	height: 48%;
	background-color: #FFF;
	position: fixed;
	bottom:0px;
}

table {
	width: 100%;
	height:100%;
	cursor: pointer;
}

table tr {
	text-align: center;
}

table tr td {
	border: 1px solid #E6E6E6;
}

.key {
	font-size: 28px;
}

.btn img {
	width:100%;
}

#backBtn img{
	width:40%;
}

.btn:active {
	-webkit-transform: scale(0.9);
	-moz-transform: scale(0.9);
    transform: scale(0.9);
}
 </style>
</head>
<body>
	<div class="container">
        <div class="title">
            <img src="https://trade.ecbao.cn/image/shop.png" />
            <div>${shopName}</div>
        </div>
        <div class="area">
            <div class="price-title">付款金额</div>
            <div class="price-body"><span class="icon">¥</span><span id="priceInput"></span></div>
            <div class="text">支付完成后,如果退款请联系商家处理</div>
        </div>
        <div class="keyboard">
            <table cellspacing="0">
                <tr>
                    <td width="25%" height="25%"><div class="key">1</div></td>
                    <td width="25%" height="25%"><div class="key">2</div></td>
                    <td width="25%" height="25%"><div class="key">3</div></td>
                    <td width="25%" rowspan="2"><div class="btn" id="backBtn"><img src="https://trade.ecbao.cn/image/clear.png"></div></td>
                </tr>
                <tr>
                    <td height="25%"><div class="key">4</div></td>
                    <td height="25%"><div class="key">5</div></td>
                    <td height="25%"><div class="key">6</div></td>
                </tr>
                 <tr>
                    <td height="25%"><div class="key">7</div></td>
                    <td height="25%"><div class="key">8</div></td>
                    <td height="25%"><div class="key">9</div></td>
                     <td rowspan="2" style="background-color:#04B00F;color:#FFF;">
                        <div class="btn" id="subBtn"><img src="https://trade.ecbao.cn/image/wx.png"> </div>
                    </td>
                </tr>
                 <tr>
                    <td colspan="2" height="25%"><div class="key">0</div></td>
                    <td height="25%"><div class="key"><strong>.</strong></div></td>
                </tr>
            </table>
        </div>
    </div>
    <script type="text/javascript" src="https://trade.ecbao.cn/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        ;$(function() {
             $(".key").on("click", function() {
            var text = $(this).text();
            var input = $("#priceInput");
            if(text == ".") {
                if (input.text().trim().length == 0) {
                        input.text("0.");
                        return;
                    }
                    if (input.text().trim().indexOf(".") < 0) {
                        input.text(input.text().trim() + ".");
                    }
                }
                else {
                     var value = input.text().trim() + text;
                    if (value.length > 0 && value.split(".")[1] && value.split(".")[1].length > 2) {
                        return;
                    }
                    if (value > 199999.99) {
                        alert("金额不能超过199999.99元");
                        return;
                    }
                    input.text(value);
                }
        });

          $("#backBtn").on("click",function() {
                 var input = $("#priceInput");
                 var temp = input.text().trim();
                if (temp != "") {
                    input.text(temp.substring(0, temp.length - 1));
                }
            });
            
            $("#subBtn").on("click",function() {
            	var input = $("#priceInput");
            	if(input.text() == "" || input.text() <= 0) {
                    alert("请输入金额再进行付款");
                	return;
                }
                var arr = input.text().split(".");
                if (!arr[1]) {
                 	input.text(arr[0] + ".00");
                }
                prepay($(this));
            });
            
            function prepay(obj) {
            	obj.attr("disabled","disabled");
            	$.ajax({
            		"type": "POST",
            		"url" : "${payUrl}",
            		"dataType" : "json",
            		"data" : {
            			"price" : $("#priceInput").text(),
            			"storeId" : "${storeId }",
            			"shopName" : "${shopName }"
            		},
            		"success" : function(resp) {
            			obj.removeAttr("disabled");
            			if (resp.status == "200") {
            				 WeixinJSBridge.invoke("getBrandWCPayRequest", {
			                       "appId": resp.data.appId,     
			                       "timeStamp": resp.data.timeStamp, 
			                       "nonceStr": resp.data.nonceStr, 
			                       "package" : resp.data.prepay_id,     
			                       "signType": resp.data.signType, 
			                       "paySign": resp.data.paySign 
			                   },function(res){     
			                       if (res.err_msg == "get_brand_wcpay_request:ok" ) {
			                            window.location.href = resp.data.finish_url
			                            	+ "?total_fee=" + resp.data.total_fee
			                            	+ "&trade_time=" + resp.data.trade_time
			                            	+ "&out_trade_no=" + resp.data.out_trade_no;
			                       } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
			                       		alert("取消微信支付");
			                       		window.close();
			                       } else if (res.err_msg == "get_brand_wcpay_request:fail") {
			                       		alert("微信支付失败");
			                       		window.close();
			                       }   
			                   }
			               );
            			} else {
            				alert(resp.msg);
            			}
            		}
            	});
            }
        });
    </script>
</body>
</html>