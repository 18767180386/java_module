<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/17
  Time: 19:24
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
    <!--[if IE 6]>
    <script type="text/javascript" src="<%=path%>/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <!--日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
        -->
        <input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="keyword" name="keyword">
        <button type="submit" class="btn btn-success" id="" name=""  onclick="search(1)"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="admin_add('添加管理员','<%=path%>/user/adminadd','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span> <span class="r">共有数据：<strong>${count}</strong> 条</span> </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="10">员工列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value="" id="allSelect"></th>
            <th width="40">ID</th>
            <th width="150">登录名</th>
            <th width="90">手机</th>
            <th width="150">邮箱</th>
            <th  width="150">图像</th>
            <th  width="150">拥有角色</th>
            <th width="130">加入时间</th>
            <th width="100">是否已启用</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userlist}">
        <tr class="text-c">
            <td><input type="checkbox" value="${user.userId}" name="" name="id" ></td>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.userPhone}</td>
            <td>${user.userEmail}</td>
            <td><img src="<%=path%>/poster/${user.pic}" width="100px" height="100px"> </td>
            <td>${user.roleName}</td>
            <td>${user.createTime}</td>
            <td class="td-status">  <c:choose>
                <c:when test="${user.status==1}">

                    <span class="label label-success radius">已启用</span>
                </c:when>
                <c:otherwise>
                    <span class="label radius">已停用</span>
                </c:otherwise>
            </c:choose> </td>
            <td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'${user.userId}','${user.status}')" href="javascript:;"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','<%=path%>/user/adminedit?id='+${user.userId},'1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,${user.userId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<div id="pageDemo"></div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    //分页
    laypage({
        cont: 'pageDemo', //分页容器的id
         pages: ${totalpagenum}, //总页数
         skin: '#5FB878', //自定义选中色值
        //,skip: true //开启跳页
        curr: ${curpage}, //初始化当前页
        jump: function(obj, first){
            if(!first){
                //layer.msg('第'+ obj.curr +'页');
               // location.href="/user/adminlist?pageNum="+obj.curr+"&pageSize=10&name="+name;
                search(obj.curr);
            }
        }
    });

    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */


    function search(page)
    {
        var  name=$("#keyword").val();
        location.href="<%=path%>/user/adminlist?pageNum="+page+"&pageSize=10&name="+name;
    }


    /*管理员-增加*/
    function admin_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-删除*/
    function admin_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '<%=path%>/user/delUser',
                data:{ user_id: id},
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }

    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-停用*/
    function admin_stop(obj,uid,status){
         var tip=(status==1?"确认要停用吗？":"确认要启用吗？");
         var _status=status==1?0:1;
        layer.confirm(tip,function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                type: "POST",
                url:'<%=path%>/user/updateuserstatus',
                data : {user_id:uid,status:_status},
                dataType:"json",
                success : function(data) {
                   // layer.msg('已删除!',{icon:1,time:1000});
                    window.location.reload();//刷新当前页面.
                    // setTimeout("serachFromSubmit()", 1000);
                },
                error:function(code){
                    parent.layer.msg('操作失败!'+code,{icon: 5,time:1000});
                }
            });

            /*
            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
            $(obj).remove();
            layer.msg('已停用!',{icon: 5,time:1000});
            */
        });
    }

    /*管理员-启用*/
    function admin_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……


            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6,time:1000});
        });
    }

    //删除所选中的管理员
    function datadel(){
        layer.confirm('确认要删除吗？',function(index){
            // 获取所有选中的checked框
            var option = $(":checked");
            var checkedId = "";
            var boo=true;
            //拼接除全选框外,所有选中的id,
            for (var i = 0, len = option.length; i < len; i++) {
                if (boo) {
                    if (option[i].id=='allSelect') {
                        boo=true;
                    }else {
                        boo=false;
                        checkedId += option[i].value;
                    }
                }else{
                    checkedId += ","+option[i].value;
                }
            }
            if(checkedId.length<=0)
            {
                alert("至少选择一项");
                return false;
            }
            $.ajax({
                type: "POST",
                url:'<%=path%>/user/delAllUser',
                data : {user_ids:checkedId},
                dataType:"json",
                success : function(data) {
                    layer.msg('已删除!',{icon:1,time:1000});
                    window.location.reload();//刷新当前页面.
                   // setTimeout("serachFromSubmit()", 1000);
                },
                error:function(code){
                    parent.layer.msg('操作失败!'+code,{icon: 5,time:1000});
                }
            });
        });
    }

</script>
</body>
</html>