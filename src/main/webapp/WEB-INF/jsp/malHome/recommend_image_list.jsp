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
  <div class="title">首页 > 图列表
    <%--<button type="button" class="btn btn-info pull-right" onclick="toAdd();">新增</button>--%>
  </div>
  <div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 35%;">名称</th>
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
    $.getJSON("/RecommendImage/detailList", function (rs) {

      console.log(rs)
          var html = '';
          for (var i = 0; i < rs.data.data.length; i++) {
            html += '<tr>' +
                    '<td style="line-height: 105px;font-size: 20px"> 图片' + [i+1] + '</td>' +
                    '<td><img style="width: 200px;height: 100px" src=" '+rs.data.Url + rs.data.data[i]['image']+ '"/></td>' +
                    '<td style="line-height: 105px">' +
                    '<button type="button" class="btn btn-info" onclick="updateBanner(' + "'" + rs.data.data[i].id + "'" + ')">修改</button>' +
                    '</td>' +
                    '</tr>';
          }
          $("#contentData").html(html);

  })

  function updateBanner(id){
    window.location.href = '/RecommendImage/toUpdate?id=' + id;
  }



</script>
