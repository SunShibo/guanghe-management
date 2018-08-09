<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>修改私募产品</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/page.css" rel="stylesheet"/>
    <style>
        .btn {
            margin-left: 20px;
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
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>

    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">私募投资 > 修改私募产品</div>
        <div class="screen clear">
            <div class="form">
                <%--<input type="text" placeholder="请输入产品名称" id="privateInvestmentName">--%>
                <%--<button type="button" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>--%>
                <button class="btn btn-primary" data-toggle="modal" data-target="#addModal">新增</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 30%;">名称</th>
                    <th style="width: 30%;">操作</th>
                </tr>
                </thead>
                <tbody id="contentData">
                <%--<tr>--%>
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


    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">新增采集产品</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>名称：<input id="name"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureAdd();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="">修改产品采集</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>名称：<input id="name1"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureUpdate();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
<script src="/static/js/web/page.js"></script>
<script>

    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }



    $(function(){

        var privateInvestmentId =  getParam("privateInvestmentId");

        $.ajax({
            type : "post",
            url : "/collectProduct/list",
            data : {"privateInvestmentId":privateInvestmentId},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    console.log(data);
                    var html = '';
                    for(var i=0;i<data.data.data.length;i++){
                        html += '<tr>'+
                                    '<td>'+ data.data.data[i].name +'</td>'+
                                    '<td>'+
                                        '<button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-info" onclick="updateById(' + "'" + data.data.data[i].id + "',"+ "'" + data.data.data[i].name+ "'" + ')">编辑</button>'+
                                        '<button type="button" data-toggle="modal" data-target="#deleteModal" class="btn btn-danger" onclick="deleteById(' + "'" + data.data.data[i].id + "'" + ')">删除</button>'+
                                    '</td>'+
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            }
        });
    });

    function sureAdd(){
        var privateInvestmentId =  getParam("privateInvestmentId");
        var name = $("#name").val();


        $.ajax({
            type : "post",
            url : "/collectProduct/add",
            data : {"privateInvestmentId":privateInvestmentId,
                    "name":name},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("添加成功！");
                    window.location.reload();
                }
            }
        });

    }


    var updateId;
    function updateById(id,name){
        $("#name1").val('');


        $("#name1").val(name);

        updateId = id;

    }

    function sureUpdate(){
        var name = $("#name1").val();

        $.ajax({
            type : "post",
            url : "/collectProduct/update",
            data : {"id":updateId,
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

    function deleteById(id){
        $.ajax({
            type : "post",
            url : "/collectProduct/delete",
            data : {"id":id},
            dataType : "json",
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("删除成功！");
                    window.location.reload();
                }
            }
        });
    }



</script>