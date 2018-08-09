<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>私募投资列表</title>
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
            height: 50px;
            line-height: 50px;
        }
    </style>

    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>

    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">私募投资 > 私募产品列表</div>
        <div class="screen clear">
            <div class="form">
                <input type="text" placeholder="请输入产品名称" id="privateInvestmentName">
                <button type="button" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
                <button type="button" class="btn btn-primary" onclick="addPrivateInvestment();">新增</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 35%;">产品名称</th>
                    <th style="width: 15%;">比较基准</th>
                    <th style="width: 15%;">产品期限</th>
                    <th style="width: 15%;">投资起点</th>
                    <th style="width: 15%;">操作</th>
                </tr>
                </thead>
                <tbody id="contentData">
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
        var privateInvestmentName = $("#privateInvestmentName").val();
        console.log(cur+',,,'+size)
        $.getJSON("/privateInvestment/list?pageNo="+cur+"&pageSize="+size+"&privateInvestmentName="+privateInvestmentName, function (rs) {
            var datas=rs.data.data;
            options={
                "id":"page",//显示页码的元素
                "data":datas,//显示数据
                "maxshowpageitem":3,//最多显示的页码个数
                "pagelistcount":size,//每页显示数据个数
                "callBack":function(result){
                    var html = '';
                    for (var i = 0; i < rs.data.data.length; i++) {
                        var amountOfInvestment = rs.data.data[i].amountOfInvestment + "";
                        if (amountOfInvestment.length > 8) {
                            amountOfInvestment = amountOfInvestment.substring(0, amountOfInvestment.length - 8) + "亿元";
                        } else if (amountOfInvestment.length <= 8 && amountOfInvestment.length > 4) {
                            amountOfInvestment = amountOfInvestment.substring(0, amountOfInvestment.length - 4) + "万元";
                        } else {
                            amountOfInvestment = rs.data.data[i].amountOfInvestment + "元";
                        }
                        html += '<tr>' +
                                '<td>' + rs.data.data[i].fundName + '</td>' +
                                '<td>' + rs.data.data[i].comparisonDatum + '</td>' +
                                '<td>' + rs.data.data[i].productTerm + '</td>' +
                                '<td>' + amountOfInvestment + '</td>' +
                                '<td>' +
                                '<button type="button" class="btn btn-info" onclick="updatePrivateInvestment(' + "'" + rs.data.data[i].id + "'" + ')">编辑</button>' +
                                '<button type="button" class="btn btn-danger" onclick="deletePrivateInvestment(' + "'" + rs.data.data[i].id + "'" + ')">删除</button>' +
                                '</td>' +
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            };
            page.init(rs.data.count,cur,options);
        })
    }


    function addPrivateInvestment(){
        window.location.href = '/privateInvestment/toAdd';
    }



    function updatePrivateInvestment(id){
        alert(id);
    }

    function deletePrivateInvestment(id){
        alert(id);
    }

</script>