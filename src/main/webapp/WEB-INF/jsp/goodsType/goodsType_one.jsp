<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>商品分类列表</title>
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
        <div class="title">商品分类 > 商品分类列表</div>
        <div class="screen clear">
            <div class="form">
                <%--<input type="text" placeholder="请输入新闻标题" id="title">--%>
                <%--<button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>--%>
                <button type="button" style=" margin-left: 20px;" data-toggle="modal" data-target="#addOneModal" class="btn btn-primary" onclick="addOneType();">新增一级分类</button>
                <button type="button" style=" margin-left: 20px;" data-toggle="modal" data-target="#addTwoModal" class="btn btn-primary" onclick="addTwoType();">新增二级分类</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 30%;">一级分类</th>
                    <th style="width: 35%;">二级分类</th>
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
                    <h4 class="modal-title" id="myModalLabel">修改一级分类</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>名称：<input id="name1"/></p>
                    <p>
                    <div class="suolue">图片：
                        <div class="uploadimg">
                            <img width="160px;" height="160px;" id="uploadImage">
                            <input type="hidden" name="imageUrl"><br/>
                        </div>
                    </div>
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureOne();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="addOneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addmyModalLabel">新增一级分类</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>名称：<input id="addOneName"/></p>
                    <p>
                    <div class="suolue">图片：
                        <div class="uploadimg1">
                            <img width="160px;" height="160px;" id="uploadImage1">
                            <input type="hidden" name="imageUrl1"><br/>
                        </div>
                    </div>
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureAddOne();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="twoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel2">修改二级分类</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>一级分类：
                        <select  id="oneType" style="width: 174px;height: 24px;">

                        </select>
                    </p>
                    <p>二级分类：
                        <input id="name2"/></p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureTwo();">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="addTwoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 300px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel3">修改二级分类</h4>
                </div>
                <div class="modal-body" style="margin-left:10px;">
                    <p>一级分类：
                        <select  id="oneType3" style="width: 174px;height: 24px;">

                        </select>
                    </p>
                    <p>二级分类：
                        <input id="name3"/></p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sureAddTwo();">提交更改</button>
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
        $.getJSON("/GoodsType/list?pageNo="+cur+"&pageSize="+size, function (rs) {
            var datas=rs.data.data;
            options={
                "id":"page",//显示页码的元素
                "data":datas,//显示数据
                "maxshowpageitem":3,//最多显示的页码个数
                "pagelistcount":size,//每页显示数据个数
                "callBack":function(result){
                    var html = '';
                    for (var i = 0; i < rs.data.data.length; i++) {
                        var name2 = rs.data.data[i].name2;
                        if(name2 == undefined){
                            name2 = '';
                        }
                        html += '<tr>'+
                                    '<td>'+ rs.data.data[i].name +'</td>'+
                                    '<td>'+ name2 +'</td>'+
                                    '<td>'+
                                        '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#oneModal" onclick="updateGoodsTypeOne('+"'"+ rs.data.data[i].id +"',"+"'"+ rs.data.data[i].img_url +"',"+"'"+ rs.data.data[i].name +"'"+')">编辑一级分类</button>'+
                                        '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#twoModal" onclick="updateGoodsTypeTwo('+"'"+ rs.data.data[i].id +"',"+"'"+ rs.data.data[i].id2 +"',"+"'"+ rs.data.data[i].name2 +"'"+')">编辑二级分类</button>'+
                                    '</td>'+
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            };
            page.init(rs.data.count,cur,options);
        })
        uploadImageUrl();
        getOneTypeList();
        uploadImageUrl1();
    }

    var oneId ;
    var oneName;
    function updateGoodsTypeOne(id,urlImg,name){
        $("#name1").val('');
        oneId = id;
        oneName = name;

        $("#uploadImage").attr("src",'${Url}'+urlImg);
        $("input[name='imageUrl']").val(urlImg);

        $("#name1").val(oneName);
    }

    function sureOne(){
        var name = $("#name1").val();
        if(name.length == 0){
            alert("请输入分类名称！");
            return;
        }
        var imageUrl = $("input[name='imageUrl']").val();
        if (imageUrl == "") {
            alert("请选择图片");
            return;
        }

        $.ajax({
            type : "post",
            url : "/GoodsType/updateById",
            data : {"id":oneId,"imgUrl":imageUrl,
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


    var twoId ;
    var twoName;
    function updateGoodsTypeTwo(id,id2,name2){
        if(name2 == undefined || name2 == "undefined"){
            name2 = '';
        }
        $("#name2").val('');

        twoId = id2;
        twoName = name2;

        $("#name2").val(twoName);
        document.getElementById("oneType").value = id;
    }

    function sureTwo(){
        var name = $("#name2").val();
        if(name.length == 0){
            alert("请输入分类名称！");
            return;
        }
        var pid = $('#oneType option:selected') .val();
        $.ajax({
            type : "post",
            url : "/GoodsType/updateTwoTypeById",
            data : {"id":twoId,"pid":pid,
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

    function uploadImageUrl(){
        var button = $("#uploadImage"), interval;
        new AjaxUpload(button, {
            action: "/privateConsultant/uploadImage",
            type:"post",
            data:{},
            name: 'myFile',
            responseType : 'json',
            onSubmit: function(file, ext) {
                if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert("您上传的图片格式不对，请重新选择！");
                    return false;
                }
            },
            onComplete: function(file, response) {
                if(response.success == true){
                    var resultData = response.data;
                    $("#uploadImage").attr("src", resultData.Url);
                    $("input[name='imageUrl']").val(resultData.imageUrl);
                }else{
                    alert("上传失败");
                }
            }
        });
    }

    function uploadImageUrl1(){
        var button = $("#uploadImage1"), interval;
        new AjaxUpload(button, {
            action: "/privateConsultant/uploadImage",
            type:"post",
            data:{},
            name: 'myFile',
            responseType : 'json',
            onSubmit: function(file, ext) {
                if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert("您上传的图片格式不对，请重新选择！");
                    return false;
                }
            },
            onComplete: function(file, response) {
                if(response.success == true){
                    var resultData = response.data;
                    $("#uploadImage1").attr("src", resultData.Url);
                    $("input[name='imageUrl1']").val(resultData.imageUrl);
                }else{
                    alert("上传失败");
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
                    $("#oneType3").html(html);
                }
            }
        });
    }


    function addOneType(){
        $("#addOneName").val('');
        $("input[name='imageUrl1']").val('');
    }

    function sureAddOne(){
        var name = $("#addOneName").val();
        if(name.length == 0){
            alert("请输入分类名称！");
            return;
        }
        var imageUrl = $("input[name='imageUrl1']").val();
        if (imageUrl == "") {
            alert("请选择图片");
            return;
        }
        $.ajax({
            type : "post",
            url : "/GoodsType/addOneType",
            data : {"name":name,"imgUrl":imageUrl},
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


    function addTwoType(){
        $("#name3").val('');
//        document.getElementById("oneType3").value = 1;
    }

    function sureAddTwo(){
        var name = $("#name3").val();
        if(name.length == 0){
            alert("请输入分类名称！");
            return;
        }
        var pid = $('#oneType3 option:selected') .val();
        $.ajax({
            type : "post",
            url : "/GoodsType/addTwoType",
            data : {"name":name,"pid":pid},
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
</script>