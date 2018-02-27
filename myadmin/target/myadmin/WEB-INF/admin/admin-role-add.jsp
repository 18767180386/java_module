<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 2017/9/4
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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
    <!--/meta 作为公共模版分离出去-->

    <title>新建网站角色 - 管理员管理 - H-ui.admin 3.0</title>
    <meta name="keywords" content="H-ui.admin 3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin 3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-admin-role-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${role.roleName}" placeholder="" id="roleName" name="roleName">
                <input type="hidden" class="input-text" value="${role.id}" placeholder="" id="id" name="id">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${role.roleDesc}" placeholder="" id="roleDesc" name="roleDesc">
                <input type="hidden" class="input-text" value=""  placeholder=""  id="rids"  name="rids">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">用户角色：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>

      <!--  <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">网站角色：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <dl class="permission-list">
                    <dt>
                        <label>
                            <input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
                            资讯管理</label>
                    </dt>
                    <dd>
                        <dl class="cl permission-list2">
                            <dt>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0" id="user-Character-0-0">
                                    栏目管理</label>
                            </dt>
                            <dd>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-0">
                                    添加</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-1">
                                    修改</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-2">
                                    删除</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-3">
                                    查看</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-4">
                                    审核</label>
                                <label class="c-orange"><input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-5"> 只能操作自己发布的</label>
                            </dd>
                        </dl>
                        <dl class="cl permission-list2">
                            <dt>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1" id="user-Character-0-1">
                                    文章管理</label>
                            </dt>
                            <dd>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-0">
                                    添加</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-1">
                                    修改</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-2">
                                    删除</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-3">
                                    查看</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-4">
                                    审核</label>
                                <label class="c-orange"><input type="checkbox" value="" name="user-Character-0-2-0" id="user-Character-0-2-5"> 只能操作自己发布的</label>
                            </dd>
                        </dl>
                    </dd>
                </dl>
                <dl class="permission-list">
                    <dt>
                        <label>
                            <input type="checkbox" value="" name="user-Character-0" id="user-Character-1">
                            用户中心</label>
                    </dt>
                    <dd>
                        <dl class="cl permission-list2">
                            <dt>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0" id="user-Character-1-0">
                                    用户管理</label>
                            </dt>
                            <dd>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0-0" id="user-Character-1-0-0">
                                    添加</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0-0" id="user-Character-1-0-1">
                                    修改</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0-0" id="user-Character-1-0-2">
                                    删除</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0-0" id="user-Character-1-0-3">
                                    查看</label>
                                <label class="">
                                    <input type="checkbox" value="" name="user-Character-1-0-0" id="user-Character-1-0-4">
                                    审核</label>
                            </dd>
                        </dl>
                    </dd>
                </dl>
            </div>
        </div>  -->
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
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
<SCRIPT type="text/javascript">
    <!--
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }
    };

    /*
    var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", open:true},
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
    ];
    */
    var str=${resourcelist};
    var zNodes=eval(str);//[{"id":27,"name":"咨询管理","pId":0},{"id":28,"name":"添加咨询","pId":27},{"id":29,"name":"删除咨询","pId":27},{"id":30,"name":"搜索咨询","pId":27},{"id":31,"name":"图片管理","pId":0},{"id":32,"name":"删除图片","pId":31}];//JSON.parse();


    var code;

    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            py = $("#py").attr("checked")? "p":"",
            sy = $("#sy").attr("checked")? "s":"",
            pn = $("#pn").attr("checked")? "p":"",
            sn = $("#sn").attr("checked")? "s":"",
            type = { "Y":py + sy, "N":pn + sn};
        zTree.setting.check.chkboxType = type;
        showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
    }
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        //setCheck();
        //$("#py").bind("change", setCheck);
        //$("#sy").bind("change", setCheck);
        //$("#pn").bind("change", setCheck);
        //$("#sn").bind("change", setCheck);
    });
    //-->

    function onClick(e, treeId, treeNode) {
        //这是单机节点展开
        //var zTree = $.fn.zTree.getZTreeObj("leftTree");
        //zTree.expandNode(treeNode);
        //判断是否是ie浏览器，对iframe赋值
        var kws = treeNode.name;

        alert(treeId+"---"+treeNode.name);
        // var url = "BigDataIndex.aspx?prokw=" + escape(kws) + "&entname=" + escape(kws) + "&time=" + new Date().getTime();
        //由于ie和火狐，其他浏览器不同，ie支持location,貌似不支持src,不知道是什么原因，所以做了判断，可能是iframe的原因吧，没做深究，
        //有大神可以告诉我一下
        // if ($.browser.msie) {
        ///获取iframe的id
        //  window.parent.frames['FlashData'].location = url;
        //}
        // else {
        //  window.parent.frames['FlashData'].src = url;
        //}
    }

    function onCheck(e,treeId,treeNode){
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            v="";
        var id="";
        for(var i=0;i<nodes.length;i++){
            v+=nodes[i].name + ",";
            id+=nodes[i].id+",";
         }
        $("#rids").val(id);
    }

   function   checkId () {
       var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
           nodes=treeObj.getCheckedNodes(true),
           v="";
       var id="";
       for(var i=0;i<nodes.length;i++){
           v+=nodes[i].name + ",";
           id+=nodes[i].id+",";
       }
       $("#rids").val(id);
   }

    /*
    $(function(){
        $.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            url: root+"/ospm/loginInfo/doGetPrivilegeTree.action",//请求的action路径
            error: function () {//请求失败处理函数
                alert('请求失败');
            },
            success:function(data){ //请求成功后处理函数。
                alert(data);
                treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
            }
        });

        zTree = $("#tree").zTree(setting, treeNodes);
    });
    */
</SCRIPT>


<script type="text/javascript">
    $(function(){
        $(".permission-list dt input:checkbox").click(function(){
            $(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
        });
        $(".permission-list2 dd input:checkbox").click(function(){
            var l =$(this).parent().parent().find("input:checked").length;
            var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
            if($(this).prop("checked")){
                $(this).closest("dl").find("dt input:checkbox").prop("checked",true);
                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
            }
            else{
                if(l==0){
                    $(this).closest("dl").find("dt input:checkbox").prop("checked",false);
                }
                if(l2==0){
                    $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
                }
            }
        });

        $("#form-admin-role-add").validate({
            rules:{
                roleName:{
                    required:true,
                },
            },
            messages:{
                roleName: {
                    required: "请输入角色名称"
                },
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                checkId ();
                var ret=$("#rids").val();
                if(ret.length==0){
                    alert("请选择用户角色");
                    return false;
                }
                $(form).ajaxSubmit({
                    type: 'post',
                    url: "<%=path%>/resource/roleaddaction" ,
                    success: function(data){
                        layer.msg('添加成功!',{icon:1,time:1000});
                        window.parent.location.reload(); //刷新父页面

                        var index = parent.layer.getFrameIndex(window.name);
                        //  parent.$('.btn-refresh').click();
                        parent.layer.close(index)

                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('error!',{icon:1,time:1000});
                    }
                });


            }
        });
    });
    function  checkIsEmpty()
    {
        var ret=$("#rids").val();
        if(ret.length==0){
            alert("请选择用户角色");
        }
        return false;
    }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>