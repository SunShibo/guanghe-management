<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>积分添加</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
  <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/manage/accountadd.js"></script>
  <style>
    .ke-container{width: 96% !important;}
  </style>
</head>
<body>
<jsp:include page="../index.jsp"></jsp:include>

<div class="index clear">
  <div class="indexRight1">
    <div class="title">积分添加</div>
    <div class="tablebox2">
      <form id="coachForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td class="td1">积分金额：</td>
            <td class="td2">
              <input type="text" name="coachName" placeholder="请输入积分金额"><em>*必填</em>
            </td>
          </tr>
          <tr>
            <td>消费信息：</td>
          <td>
            <input type="text" name="payinfo" placeholder="请输入消费信息"><em>*必填</em>
          </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <a href="javascript:void(0);" id="B_submit">发布</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
</div>
</body>