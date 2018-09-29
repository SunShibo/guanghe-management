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
  <title>会员尊享</title>
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
  <div class="title">会员尊享 >首页</div>
  <div>
    <div class="screen clear">
      <div class="form">
        <%--<input type="text" placeholder="请输入新闻标题" id="title">--%>
        <%--<button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>--%>
        <button type="button" style=" margin-left: 20px;" data-toggle="modal" data-target="#addOneModal" class="btn btn-primary" onclick="add();">新增</button>
      </div>

    </div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 15%;">标题</th>
        <th style="width: 30%;">缩略图</th>
        <th style="width: 40% ">内容</th>
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
  $.getJSON("/ClubSystem/list", function (rs) {

    var html = '';
    for (var i = 0; i < rs.data.news.length; i++) {
      html += '<tr>' +
              '<td style="line-height: 135px;font-size: 20px">'+rs.data.news[i]['title']+'</td>';
      if (rs.data.news[i].image == '') {
        html +='<td></td>'
      } else {

        html +='<td><img style="width: 150px;height: 150px" src=" ' + rs.data.Url + rs.data.news[i]['image'] + '"/></td>';
      }
      if(rs.data.news[i].content.length>100){
        html += '<td style="font-size:15px;text-align: left">' + rs.data.news[i]['content'].substr(0,100)+"..."+'</td>'
      }else{
        html += '<td style="font-size:15px;text-align: left">' + rs.data.news[i]['content']+'</td>'
      }
      html +='<td style="line-height: 105px">' +
              '<button type="button" class="btn btn-info" onclick="clubUpdate(' + "'" + rs.data.news[i].id + "'" + ')">修改</button>' +
              '<button type="button" class="btn btn-info" onclick="deleteBanner(' + "'" + rs.data.news[i].id + "'" + ')">删除</button>' +
              '</td>' +
              '</tr>';
    }
    $("#contentData").html(html);

  })

  function clubUpdate(id){
    window.location.href = '/ClubSystem/toupdate?id=' + id;

  }
  function add(){
    window.location.href ='/ClubSystem/toAdd'
  }

  function deleteBanner(id){
    $.ajax({
      type : "post",
      url : "/ClubSystem/delete",
      data : {"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("删除成功！");
          window.location.href="/ClubSystem/page";
        }
      }
    });
  }
</script>
