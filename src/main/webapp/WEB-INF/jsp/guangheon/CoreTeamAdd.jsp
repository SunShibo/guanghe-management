<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>添加</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/manage/coreteam_add.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title"> 添加</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td class="td1">名&nbsp;&nbsp;&nbsp;&nbsp;字：</td>
            <td class="td2">
              <input type="text" name="moduleTitle">
            </td>
          </tr>
          <tr>
            <td>职位：</td>
            <td>
              <input type="text" name="moduleEngTitle" ">
            </td>
          </tr>
          <tr>
            <td>简&nbsp;&nbsp;&nbsp;&nbsp;介：</td>
            <td>
              <textarea rows="10" type="text" name="moduleContent"></textarea>
              <input name="introduce" type="hidden"/>
            </td>
          </tr>
          <tr>
            <td>头&nbsp;&nbsp;&nbsp;&nbsp;像：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="160px;" height="160px;" id="uploadImage"/>
                  <input type="hidden" name="imageUrl"><br/>
                </div>
              </div>
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