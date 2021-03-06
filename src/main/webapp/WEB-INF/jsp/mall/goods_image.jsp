<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/8/13
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
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
  <div class="title">商品图列表</div>
  <div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 35%;">类别</th>
        <th style="width: 30%;">缩略图</th>
        <th style="width: 35%;">操作</th>
      </tr>
      </thead>
      <tbody id="contentData">
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
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  $.getJSON("/GoodsImage/detail?id="+GetQueryString("id"), function (rs) {

    var html = '';
    for (var i = 0; i < rs.data.news.length; i++) {
    if(rs.data.news[i].status==1) {
      html += '<tr>' +
              '<td style="line-height: 105px;font-size: 20px"> 商品介绍图' + [i + 1] + '</td>'
    }else{
      html += '<tr>' +
              '<td style="line-height: 105px;font-size: 20px"> 商品规格图' + [i + 1] + '</td>'
    }

           html+='<td><img style="width: 200px;height: 100px" src=" '+rs.data.Url + rs.data.news[i]['imgUrl']+ '"/></td>' +
              '<td style="line-height: 105px">' +
              '<button type="button" class="btn btn-info" onclick="addImage(' + "'" + rs.data.news[i].goodsId + "'" + ')">添加图片</button>' +
              '<button type="button" class="btn btn-danger" onclick="deleteiamge(' + "'" + rs.data.news[i].id + "'" + ')">删除</button>' +
              '</td>' +
              '</tr>';
    }
    $("#contentData").html(html);

  })

  function addImage(goodsId){
    window.location.href = '/GoodsImage/toUpdate?goodsId=' + goodsId;
  }

  function deleteiamge(id){
    $.ajax({
      type : "post",
      url : "/GoodsImage/delete",
      data : {"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("删除成功！");
          window.location.href="/Goods/page";
        }
      }
    });
  }
</script>
