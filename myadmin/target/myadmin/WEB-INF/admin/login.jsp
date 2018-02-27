<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<%
    //  String path = request.getContextPath();
    // String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //out.println(path+"---"+basePath);


%>
<script language="JavaScript">
    if (window != top)
        top.location.href = location.href;
</script>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=path%>/lib/html5shiv.js"></script>
    <script type="text/javascript" src="<%=path%>/lib/respond.min.js"></script>
    <![endif]-->
    <link href="<%=path%>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="<%=path%>/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>后台登录 - H-ui.admin 3.0</title>
    <meta name="keywords" content="H-ui.admin 3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin 3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal"  method="post"   id="form-admin-add"  action="<%=path%>/user/loginaction"  onsubmit="return loginCheck()">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userName" name="userName" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userPassword" name="userPassword" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}"  name="code"   id="code" value="验证码:" style="width:150px;">
                    <img src="<%=path%>/authcode/getcode" id="codeImage" onclick="chageCode()" /><!--<a id="kanbuq" href="javascript:chageCode();">看不清，换一张</a>--> </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <font color="red"> ${result}</font>
                </div>
            </div>
   <!--
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" value="">
                        使我保持登录状态</label>
                </div>
            </div>
            -->


            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright 你的公司名称 by H-ui.admin 3.0</div>
<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/jquery.validation/1.14.0/validate-methods.js"></script>
<!--此乃百度统计代码，请自行删除-->
<script  type="text/javascript">
    function chageCode(){
        $('#codeImage').attr('src','<%=path%>/authcode/getcode?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
    }
    /*
    $(function() {
        $("#form-admin-add").validate({
            rules:{
                userName:{
                    required:true,
                    minlength:4,
                    maxlength:16
                },
                userPassword:{
                    required:true,
                },
                 messages:{
                       userName:{required:"姓名不能为空"},
                       userPassword:{required:"密码不能为空"}
                 },
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                $("#form-admin-add").ajaxSubmit({
                    type: 'post',
                    url: "/user/loginaction",
                    success: function(data) {

                        if (data == "0") {
                            alert("该用户已存在");
                        } else {
                            layer.msg('添加成功!', {icon: 1, time: 1000});
                            window.parent.location.reload(); //刷新父页面

                            var index = parent.layer.getFrameIndex(window.name);
                            //  parent.$('.btn-refresh').click();
                            parent.layer.close(index)
                        }

                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('error!',{icon:1,time:1000});
                    }
                });
                // var index = parent.layer.getFrameIndex(window.name);
                // parent.$('.btn-refresh').click();
                // parent.layer.close(index);
            }
        });

    });
*/


    /**
     * 登录验证
     */
    function   loginCheck() {
        var name=document.getElementById("userName").value;
        var pas=document.getElementById("userPassword").value;
        var code=document.getElementById("code").value;
        if(name.length<=0)
        {
            alert("请输入用户名");
            return  false;
        }
        if(pas.length<=0)
        {
            alert("请输入用户密码");
            return false;
        }

        if(code.length<0 || code=="验证码:")
        {
            alert("请输入验证码");
            return false;
        }
    }
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
<!--/此乃百度统计代码，请自行删除
</body>
</html>
