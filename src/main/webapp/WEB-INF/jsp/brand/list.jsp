<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>品牌列表</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/page.css" rel="stylesheet"/>
    <style>
        .btn {

            /*height: 35px;*/
        }
        .btn-xs {
            width: 60px;
        }


        tr td {
            text-align: center;
            /*height: 50px;*/
            /*line-height: 50px;*/
        }
        tr th {
            text-align: center;
        }
        #contentData tr td {
            height: 45px;
            line-height: 45px;
        }
    </style>

    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
</head>
<body>

    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">品牌列表 > 品牌列表</div>
        <div class="screen clear">
            <div class="form">
                <input type="text" placeholder="请输入品牌名称" id="title">
                <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
                <button type="button" style=" margin-left: 20px;" data-toggle="modal" data-target="#addModal" class="btn btn-primary" onclick="add();">新增品牌</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 30%;">品牌</th>
                    <th style="width: 35%;">产地</th>
                    <%--<th style="width: 15%;">来源</th>--%>
                    <th style="width: 30%;">操作</th>
                </tr>
                </thead>
                <tbody id="contentData">
                <%--<tr>--%>
                    <%--<td>五莲绽放-广和旅游产业基金</td>--%>
                    <%--<td>6.5%</td>--%>
                    <%--<td>--%>
                        <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>五莲绽放-广和旅游产业基金</td>--%>
                    <%--<td>6.5%</td>--%>
                    <%--<td>5+2+2</td>--%>
                    <%--<td>300万元</td>--%>
                    <%--<td>--%>
                        <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>五莲绽放-广和旅游产业基金</td>--%>
                    <%--<td>6.5%</td>--%>
                    <%--<td>5+2+2</td>--%>
                    <%--<td>300万元</td>--%>
                    <%--<td>--%>
                        <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
                        <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>

        </div>

        <div style="height: 28px; width: 360px; margin: 0 auto;">
            <ul class="page" id="page"></ul>
        </div>
    </div>


    <div class="modal fade" id="oneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addmyModalLabel">修改</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p style="display: none;">一级：
                        <select  id="oneType" style="width: 174px;height: 24px;" onchange="show_sub(this.options[this.options.selectedIndex].value)">

                        </select>
                    </p>
                    <p style="display: none;">二级：
                        <select  id="twoType" style="width: 174px;height: 24px;">

                        </select>
                    </p>
                    <p>名称：
                        <input id="name"/></p>
                    <p>产地：
                        <input id="produced"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureUpdate();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>


    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addmyModalLabel1">新增</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>一级：
                        <select  id="oneType1" style="width: 174px;height: 24px;" onchange="show_sub1(this.options[this.options.selectedIndex].value)">

                        </select>
                    </p>
                    <p>二级：
                        <select  id="twoType1" style="width: 174px;height: 24px;">

                        </select>
                    </p>
                    <p>名称：
                        <input id="name1"/></p>
                    <p>产地：
                        <input id="produced1"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureAdd();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</body>
<script src="/static/js/web/page.js"></script>
<script>

    var options;
    getdata(1,10);
    function getdata(cur,size){
        var title = $("#title").val();
        $.getJSON("/Brand/list?pageNo="+cur+"&pageSize="+size+"&name="+title, function (rs) {
            var datas=rs.data.data;
            console.log(rs);
            options={
                "id":"page",//显示页码的元素
                "data":datas,//显示数据
                "maxshowpageitem":3,//最多显示的页码个数
                "pagelistcount":size,//每页显示数据个数
                "callBack":function(result){
                    var html = '';
                    for (var i = 0; i < rs.data.data.length; i++) {
                        var produced = rs.data.data[i].produced ;
                        if(produced == undefined){
                            produced = '';
                        }
                        html += '<tr>'+
                                    '<td>'+ rs.data.data[i].name +'</td>'+
                                    '<td>'+ produced +'</td>'+
                                    '<td>'+
                                        '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#oneModal" onclick="update('+"'"+ rs.data.data[i].id +"',"+"'"+ rs.data.data[i].oneId +"',"+"'"+ rs.data.data[i].twoId +"',"+"'"+ produced +"',"+"'"+ rs.data.data[i].name +"'"+')">编辑</button>'+
                                    '</td>'+
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            };
            page.init(rs.data.count,cur,options);
        })
        getOneTypeList();

    }

    var updateId;
    function update(id,oneId,twoId,produced,name){
        $("#name").val('');
        $("#produced").val('');

        updateId = id;


        $("#name").val(name);
        $("#produced").val(produced);

        document.getElementById("oneType").value = oneId;

        show_sub(oneId);
        document.getElementById("twoType").value = twoId;
    }

    function sureUpdate(){
        var name = $("#name").val();
        if(name.length == 0){
            alert("请输入名称！");
            return;
        }
        var produced = $("#produced").val();
        var oneId = $('#oneType option:selected') .val();
        var twoId = $('#twoType option:selected') .val();
        $.ajax({
            type : "post",
            url : "/Brand/updateById",
            data : {"id":updateId,"produced":produced,"goodsTypeId":twoId,"levelId":oneId,
                "name":name},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("修改成功！");
                    window.location.reload();
                }
            }
        });
    }


    function getOneTypeList(){
        $.ajax({
            type : "post",
            url : "/GoodsType/getOneTypeList",
            data : {},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    //oneType
                    var html = '';
                    for(var i=0;i<data.data.length;i++){
                        html += '<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>';
                    }
                    $("#oneType").html(html);
                    $("#oneType1").html(html);
                }
            }
        });
    }

    ///firstMenu


    function show_sub(pid){
        $.ajax({
            type : "post",
            url : "/GoodsType/firstMenu",
            data : {"id":pid},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    //oneType
                    var html = '';
                    for(var i=0;i<data.data.length;i++){
                        html += '<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>';
                    }
                    $("#twoType").html(html);
                }
            }
        });
    }

    function show_sub1(pid){
        $.ajax({
            type : "post",
            url : "/GoodsType/firstMenu",
            data : {"id":pid},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    //oneType
                    var html = '';
                    for(var i=0;i<data.data.length;i++){
                        html += '<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>';
                    }
                    $("#twoType1").html(html);
                }
            }
        });
    }

    function add(){
        var pid = $('#oneType1 option:selected') .val();
        show_sub1(pid);
    }


    function sureAdd(){
        var name = $("#name1").val();
        if(name.length == 0){
            alert("请输入名称！");
            return;
        }
        var produced = $("#produced1").val();
        var oneId = $('#oneType1 option:selected') .val();
        var twoId = $('#twoType1 option:selected') .val();
        $.ajax({
            type : "post",
            url : "/Brand/insertBrand",
            data : {"produced":produced,"goodsTypeId":twoId,"levelId":oneId,
                "name":name},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("修改成功！");
                    window.location.reload();
                }
            }
        });
    }
</script>