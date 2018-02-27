<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>关于我们</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
   <style type="text/css">
   html,
    body {
        background-color: #F5F5F5;
        margin: 0;
        padding: 0;
    }

    .container {
        position: relative;
        font-size: 15px;
    }

    .icon {
        margin: 40px auto;
        text-align: center;
        margin-bottom: 20px;
    }
    img {
        display:inline-block;
        transform: scale(0.6);
    }
    .icon div {
        margin-top:15px;
        color: #999;
    }
    .items {
        background-color: #FFF;
        border-bottom:1px solid #EEEEEE;
    }
    .item {
        border-bottom:1px solid #EEEEEE;
    }

    .left,
    .right {
        display: inline-block;
    }

    .right {
        color: #A2A2A2;
        float: right;
    }

    a {
        color: #4691CC;
        text-decoration: none;
    }
    .tel {
        color: #4691CC;
    }
    @media screen and (min-width: 320px) and (max-width: 362px) {
         .item {
            padding: 6px 10px;
             font-size: 13px;
        }
        .right {
            font-size: 14px;
        }
         .qq iframe{
            position: absolute;
            display: block;
            width: 210px;
            height: 100px;
            margin: 0px auto;
        }
    }

    @media screen and (min-width: 375px) and (max-width: 400px) {
        .item {
            padding: 9px 15px;
            font-size: 15px;
        }
        .right {
            font-size: 16px;
        }
         .qq iframe{
            position: absolute;
            display: block;
            width: 240px;
            height: 110px;
            margin: 0px auto;
        }
    }

    @media screen and (min-width: 412px) and (max-width: 500px) {
         .container {
             font-size: 16px;
        }
         .item {
            padding: 10px 15px;
            font-size: 17px;
        }
        .qq iframe{
            position: absolute;
            display: block;
            width: 255px;
            height: 150px;
            margin: 0px auto;
        }
    }

    @media screen and (min-width: 600px) and (max-width: 800px) {
        .container {
             font-size: 20px;
        }
        .item {
            padding: 20px 20px;
            font-size: 22px;
        }
        .qq iframe{
            position: absolute;
            display: block;
            width: 350px;
            height: 250px;
            margin: 0px auto;
        }
    }
    </style>
</head>

<body>
    <div class="container">
        <div class="icon">
            <img src="https://store.ecbao.cn/image/logo.png">
            <div>爱聚收银记账 V2.0.0</div>
        </div>
        <div class="items">
            <div class="item">
                <div class="left">官网网址</div>
                <div class="right"><a href="http://pay.ecbao.cn">pay.ecbao.cn</a></div>
            </div>
            <div class="item">
                <div class="left">公众号</div>
                <div class="right">ajsyjz</div>
            </div>
            <div class="item">
                <div class="left">客服专线</div>
                <div class="right"><a tel="0571-89935939" class="tel">0571-89935939</a></div>
            </div>
        </div>
        <div class="qq">
            <script src="https://wp.qiye.qq.com/qidian/2852068262/17298e804e7312ee8d5325225faee82c" charset="utf-8"></script>
        </div>
    </div>
</body>

</html>

