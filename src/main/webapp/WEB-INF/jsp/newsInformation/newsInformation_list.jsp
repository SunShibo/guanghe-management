<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>新闻动态列表</title>
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
</head>
<body>

    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">新闻动态 > 新闻动态列表</div>
        <div class="screen clear">
            <div class="form">
                <input type="text" placeholder="请输入新闻标题" id="title">
                <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
                <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="add();">新增</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 30%;">标题</th>
                    <th style="width: 35%;">简介</th>
                    <th style="width: 15%;">来源</th>
                    <th style="width: 30%;">操作</th>
                </tr>
                </thead>
                <tbody id="contentData">
                <%--<tr>--%>
                    <%--<td>五莲绽放-广和旅游产业基金</td>--%>
                    <%--<td>6.5%</td>--%>
                    <%--<td>5+2+2</td>--%>
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
</body>
<script src="/static/js/web/page.js"></script>
<script>

    var options;
    getdata(1,10);
    function getdata(cur,size){
        var title = $("#title").val();
        console.log(cur+',,,'+size)
        $.getJSON("/newsInformation/list?pageNo="+cur+"&pageSize="+size+"&title="+title, function (rs) {
            var datas=rs.data.data;
            console.log(rs.data.count);
            options={
                "id":"page",//显示页码的元素
                "data":datas,//显示数据
                "maxshowpageitem":3,//最多显示的页码个数
                "pagelistcount":size,//每页显示数据个数
                "callBack":function(result){
                    var html = '';
                    for (var i = 0; i < rs.data.data.length; i++) {

                        var synopsis = rs.data.data[i].synopsis+'';
                        if(synopsis.length > 35 ){
                            synopsis = synopsis.substring(0,35) + '...';
                        }

                        html += '<tr>'+
                                    '<td>'+ rs.data.data[i].title +'</td>'+
                                    '<td>'+ synopsis +'</td>'+
                                    '<td>'+ rs.data.data[i].source +'</td>'+
                                    '<td>'+
                                        '<button type="button" class="btn btn-info" onclick="toUpdatePage('+"'"+ rs.data.data[i].id +"'"+')">编辑</button>'+
                                        '<button type="button" class="btn btn-danger" onclick="deletePrivateInvestment('+"'"+ rs.data.data[i].id +"'"+')">删除</button>'+
                                    '</td>'+
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            };
            page.init(rs.data.count,cur,options);
        })
    }


    function deletePrivateInvestment(id){
        $.ajax({
            type : "post",
            url : "/newsInformation/delete",
            data : {"newsId":id},
            dataType : "json",
            async : false,
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("删除成功！");
                    window.location.href="/newsInformation/page";
                }
            }
        });
    }

    function add(){
        window.location.href = "/newsInformation/toAdd";
    }

    function toUpdatePage(id){
        window.location.href = "/newsInformation/findOne?id="+id;
    }



</script>