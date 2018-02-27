<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/21
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
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
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/h-ui.admin/css/style.css" />

    <script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<%=path%>/lib/zTree_v3-master/css/demo.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/lib/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/lib/zTree_v3-master/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=path%>/lib/zTree_v3-master/js/jquery.ztree.excheck.js"></script>

    <!--[if IE 6]>
    <script type="text/javascript" src="<%=path%>/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>添加管理员 - 管理员管理 - H-ui.admin v2.4</title>
    <meta name="keywords" content="H-ui.admin 3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin 3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-admin-add"  enctype="multipart/form-data"  >
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.userName}" placeholder="" id="adminName" name="userName">
                <input type="hidden" class="input-text" value="${user.userId}" placeholder="" id="userId" name="userId">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" value="${user.userPassword}" autocomplete="off"  placeholder="初始密码" id="userPassword" name="userPassword">
            </div>
        </div>


        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" autocomplete="off" value="${user.userPassword}" placeholder="确认新密码" id="confirmPassword" name="confirmPassword">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上传图像：</label>
            <div class="formControls col-xs-8 col-sm-9">
             <input type="file" name="file"    /><input type="hidden" class="input-text" autocomplete="off"    id="pic" name="pic"  value="${user.pic}">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="sex" type="radio" id="sex-1" value="1" <c:if test="${user.sex==1}"> checked="checked" </c:if>>
                    <label for="sex-1">男</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="sex-2" name="sex"  value="2"  <c:if test="${user.sex==2}"> checked="checked" </c:if>>
                    <label for="sex-2">女</label>
                </div>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账号状态：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="status" type="radio"  value="1"  <c:if test="${user.status==1}"> checked="checked" </c:if>>
                    <label for="sex-1">可用</label>
                </div>
                <div class="radio-box">
                    <input name="status" type="radio"   value="0"  <c:if test="${user.status==0}"> checked="checked" </c:if>>
                    <label for="sex-2">不可用</label>
                </div>
            </div>
        </div>


        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.userPhone}" placeholder="" id="phone" name="userPhone" >
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="@" name="userEmail" id="email"  value="${user.userEmail}">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">角色：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <dt>
                    <label>
                        <input type="checkbox" name="" value="" id="allSelect"  onclick="selectAll()" <c:if test="${isall}"> checked="checked" </c:if> >全部</label>
                </dt>
                <c:forEach var="role" items="${rolelist}">
                   <label class=""><input type="checkbox" value="${role.id}"  onclick="checkIsSelectAll()" name="_roleid" id="_roleid" <c:if test="${role.check}"> checked="checked" </c:if> >${role.roleName}</label>
                </c:forEach>

                <input type="hidden" class="input-text" placeholder="@" name="ids" id="ids" value="">

            </div>
        </div>



        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=path%>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    function selectAll(){
        var checklist = document.getElementsByName("_roleid");
        if(document.getElementById("allSelect").checked)
        {
            for(var i=0;i<checklist.length;i++)
            {
                checklist[i].checked = 1;
            }
        }else{
            for(var j=0;j<checklist.length;j++)
            {
                checklist[j].checked = 0;
            }
        }
    }
    function  checkIsSelectAll() {
        var checklist = document.getElementsByName("_roleid");
        var flag=false;
        for(var i=0;i<checklist.length;i++)
        {
            if(!checklist[i].checked)
            {
                flag=true;
                break;
            }
        }

        if(flag)
        {
            document.getElementById("allSelect").checked=0;
        }else
        {
            document.getElementById("allSelect").checked=1;
        }
    }

    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });


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
                confirmPassword:{
                    required:true,
                    equalTo: "#userPassword"
                },
                sex:{
                    required:true,
                },
                userPhone:{
                    required:true,
                    isPhone:true,
                },
                userEmail:{
                    required:true,
                },
                status:{
                    required:true,
                },


            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                getAll();
                if($("#ids").val().length<=0)
                {
                  alert("请选择角色");
                  return false;
                }
                $("#form-admin-add").ajaxSubmit({
                    type: 'post',
                    url: "<%=path%>/user/adminuseraddaction",
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



    function  getAll() {
        // 获取所有选中的checked框
       // var option = $(":checked");
        var  option= $("input[name='_roleid']:checkbox:checked");
        var checkedId = "";
        var boo = true;
        //拼接除全选框外,所有选中的id,
        for (var i = 0, len = option.length; i < len; i++) {
            if (boo) {
                if (option[i].id == 'allSelect') {
                    boo = true;
                } else {
                    boo = false;
                    checkedId += option[i].value;
                }
            } else {
                checkedId += "," + option[i].value;
            }
        }
        $("#ids").val(checkedId);
    }

    //删除所选中的管理员
    function datadel() {
        layer.confirm('确认要删除吗？', function (index) {
            // 获取所有选中的checked框
            var option = $(":checked");
            var checkedId = "";
            var boo = true;
            //拼接除全选框外,所有选中的id,
            for (var i = 0, len = option.length; i < len; i++) {
                if (boo) {
                    if (option[i].id == 'allSelect') {
                        boo = true;
                    } else {
                        boo = false;
                        checkedId += option[i].value;
                    }
                } else {
                    checkedId += "," + option[i].value;
                }
            }
            $.ajax({
                type: "POST",
                url: '<%=path%>/resource/delAllrole',
                data: {ids: checkedId},
                dataType: "json",
                success: function (data) {

                    layer.msg('已删除!', {icon: 1, time: 1000});
                    window.location.reload();//刷新当前页面.
                    // setTimeout("serachFromSubmit()", 1000);

                },
                error: function (code) {
                    parent.layer.msg('操作失败!' + code, {icon: 5, time: 1000});
                }
            });
        });
    }


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>