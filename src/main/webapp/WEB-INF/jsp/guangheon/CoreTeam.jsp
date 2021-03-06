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
  <title>核心团队列表</title>
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
  <div class="title">首页 >走进广和>核心团队</div>
  <div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 15%;">名字</th>
        <th style="width: 20%;">缩略图</th>
        <th style="width: 20%;">职位</th>
        <th style="width: 30% ">简介</th>
        <th style="width: 15%;">操作</th>
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
  $.getJSON("/CoreTeam/detail", function (rs) {

    var html = '';
    for (var i = 0; i < rs.data.employee.length; i++) {
      html += '<tr>' +
              '<td style="line-height: 135px;font-size: 20px">' + rs.data.employee[i]['employeeName']+ '</td>' +
              '<td><img style="width: 150px;height: 150px" src=" '+rs.data.Url+rs.data.employee[i]['imageUrl']+ '"/></td>' +
              '<td style="font-size:15px;text-align: left">' + rs.data.employee[i]['employeePosition']+ '</td>' +
              '<td style="font-size:15px;text-align: left">' + rs.data.employee[i]['introduction']+ '</td>' +
              '<td style="line-height: 105px">' +
              '<button type="button" class="btn btn-info" onclick="ModuleUpdate(' + "'" + rs.data.employee[i].id + "'" + ')">修改</button>' +
              '<button type="button" class="btn btn-info" onclick="add()">添加</button>' +
              '<button type="button" class="btn btn-info" onclick="deleteBanner(' + "'" + rs.data.employee[i].id + "'" + ')">删除</button>' +
              '</td>' +
              '</tr>';
    }
    $("#contentData").html(html);

  })

  function ModuleUpdate(id){
    window.location.href = '/CoreTeam/toupdate?id=' + id;
  }
  function add(){
    window.location.href = '/CoreTeam/toAdd';
  }

  function deleteBanner(id){
    $.ajax({
      type : "post",
      url : "/CoreTeam/delete",
      data : {"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("删除成功！");
          window.location.href="/CoreTeam/page";
        }
      }
    });
  }
</script>
