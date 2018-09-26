<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>修改</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/manage/compannyIntroduction_home.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">广和商学院/私享顾问/财富管理/> 修改</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
            <td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
            <td>
              <textarea rows="10" type="text" name="moduleContent" value="${module.companyIntroduction}">${module.companyIntroduction}</textarea>
              <input name="introduce" type="hidden"/>
              <input type="hidden" name="id" value="${module.id}">
            </td>
          </tr>
          <tr>
            <td>视&nbsp;&nbsp;&nbsp;&nbsp;频：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="160px;" height="160px;" id="uploadImage" src="${Url}${module.video}"/>
                  <input type="hidden" name="imageUrl" value="${module.video}"><br/>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td>视&nbsp;&nbsp;&nbsp;&nbsp;频：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <video width="160px;" height="160px;" id="uploadImage1" style="object-fit: fill" controls>
                    <source src="${Url}${module.video}">
                  </video>
                  <input type="hidden" name="imageUrl1" value="${module.video}"><br/>
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