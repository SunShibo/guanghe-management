<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>列表</title>
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
        <div class="title">商品 > 列表</div>
        <div class="screen clear">
            <div class="form">
                <input type="text" placeholder="请输入商品名" id="title">
                <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
            </div>

        </div>
        <div>
            <table class="table table-bordered">
                <%--<caption>边框表格布局</caption>--%>
                <thead>
                <tr>
                    <th style="width: 35%;">商品名</th>
                    <th style="width: 30%;">类型</th>
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
        $.getJSON("/homeGoods/detailList?pageNo="+cur+"&pageSize="+size+"&title="+title, function (rs) {
            var datas=rs.data.data;
            console.log(datas);
            options={
                "id":"page",//显示页码的元素
                "data":datas,//显示数据
                "maxshowpageitem":3,//最多显示的页码个数
                "pagelistcount":size,//每页显示数据个数
                "callBack":function(result){
                    var html = '';
                    for (var i = 0; i < rs.data.data.length; i++) {
                        var urlImg = rs.data.data[i].img_url;
                        if(urlImg == undefined){
                            urlImg = '';
                        }

                        var homeState = rs.data.data[i].homeState;
                        var html1 = '';
                        if(homeState == 1){
                            homeState = '精品推荐';
                            html1 = '<button type="button" class="btn btn-info" onclick="updateImgUrl('+"'"+ rs.data.data[i].id +"',"+"'"+ rs.data.Url +"',"+"'"+ urlImg +"'"+')">修改商品图片(首页)</button>';
                        }else if(homeState == 2){
                            homeState = '新品上架';
                            html1 = '<button type="button" class="btn btn-info" onclick="updateImgUrl('+"'"+ rs.data.data[i].id +"',"+"'"+ rs.data.Url +"',"+"'"+ urlImg +"'"+')">修改商品图片(首页)</button>';
                        }else{
                            homeState = '' ;
                            html1 = ' ';
                        }


                        html += '<tr>'+
                                    '<td>'+ rs.data.data[i].name +'</td>'+
                                    '<td>'+ homeState +'</td>'+
                                    '<td>'+
                                        '<button type="button" class="btn btn-info" onclick="updateState('+"'"+ rs.data.data[i].id +"',"+"'"+ 1 +"'"+')">设为精品推荐</button>'+
                                        '<button type="button" class="btn btn-info" onclick="updateState('+"'"+ rs.data.data[i].id +"',"+"'"+ 2 +"'"+')">设为新品上架</button>'+
                                        html1+
                                    '</td>'+
                                '</tr>';
                    }
                    $("#contentData").html(html);
                }
            };
            page.init(rs.data.count,cur,options);
        })
    }
    function updateState(id,homeState){
        $.ajax({
            type : "post",
            url : "/homeGoods/updateState",
            data : {"id":id,"homeState":homeState},
            dataType : "json",
            async : false,
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("修改成功！");
                    window.location.href="/homeGoods/page";
                }
            }
        });
    }

    function updateImgUrl(id,Url,imgUrl){
        window.location.href = '/homeGoods/toUpdate?id='+id+"&imgUrl="+imgUrl+"&Url="+Url;
    }

</script>