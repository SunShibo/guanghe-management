<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>修改</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/manage/club_update.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">私享俱乐部> 修改</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">

          <tr>
            <td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
            <td>
              <textarea rows="10" type="text" name="moduleContent" value="${club.content}">${club.content}</textarea>
              <input name="introduce" type="hidden"/>
              <input type="hidden" name="id" value="${club.id}">
            </td>
          </tr>
          <tr>
            <td class="td1">url:</td>
            <td class="td2">
              <input type="text" name="url" value="${club.url}">
            </td>
          </tr>
          <tr>
            <td class="td1">wapurl:</td>
            <td class="td2">
              <input type="text" name="wapurl" value="${club.wapurl}">
            </td>
          </tr>
          <tr id="tdd">
            <td>图&nbsp;&nbsp;&nbsp;&nbsp;片：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="160px;" height="160px;" id="uploadImage" src="${Url}${club.image}"/>
                  <input type="hidden" name="imageUrl" value="${club.image}"><br/>
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