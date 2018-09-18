<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/8/9
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>首页列表</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <link href="/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="/static/css/page.css" rel="stylesheet"/>
  <style>
    .btn {
      margin-left: 20px;
    }
    s {
      width: 60px;
    }

    tr td {
      text-align: center;
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
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title" style="height:70px; line-height:65px; background:#eaecf2; color:#333; font-size:14px; padding-left:26px;font-size: 30px;text-align: center">首页</div>
    <form id="activityMessageForm" action="list" method="post">
      <div class="tablebox1" style="width : 1500px">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr class="tr">
            <td style="height: 70px;font-size: 25px">模块</td>
            <td style="height: 70px;font-size: 25px">操作</td>
          </tr>
          <tr>
          <td style="height: 70px;">
            <a style="font-size: 20px">banner图</a>
          </td>
          <td style="height: 70px;">
            <a style="font-size: 20px"class="btn btn-info" href="/Banner/page">编辑</a>
            <a style="font-size: 20px"class="btn btn-info" href="/Banner/toAdd">添加</a>
          </td>
        </tr>
          <tr>
            <td style="height: 70px;">
              <a style="font-size: 20px">广和商学院/私享顾问/财富管理</a>
            </td>
            <td style="height: 70px;">
              <a style="font-size: 20px"class="btn btn-info" href="/Module/page">编辑</a>
            </td>
          </tr>
          <tr>
            <td style="height: 70px;">
              <a style="font-size: 20px">俱乐部</a>
            </td>
            <td style="height: 70px;">
              <a style="font-size: 20px"class="btn btn-info" href="/PrivateClub/page">编辑</a>
            </td>
          </tr>
          <tr>
          <td style="height: 70px;">
            <a style="font-size: 20px">合作伙伴</a>
          </td>
          <td style="height: 70px;">
            <a style="font-size: 20px"class="btn btn-info" href="/partener/page">编辑</a>
            <a style="font-size: 20px"class="btn btn-info" href="/partner/toAdd">添加</a>
          </td>
        </tr>
          <tr>
            <td style="height: 70px;">
              <a style="font-size: 20px">首页公司介绍</a>
            </td>
            <td style="height: 70px;">
              <a style="font-size: 20px"class="btn btn-info" href="/partener/page">编辑</a></td>
          </tr>
        </table>
      </div>
    </form>
  </div>
</div>
</body>

