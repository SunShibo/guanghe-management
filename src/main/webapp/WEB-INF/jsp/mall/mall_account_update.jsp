<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>修改会员级别</title>
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
<div style="padding-left: 300px;width:800px;height: 100%;padding-top: 50px;">
<select id="type" style="padding:10px 20px;">
  <option value="0">普通会员</option>
  <option value="1">悦享卡会员</option>
  <option value="2">悦亲卡会员</option>
  <option value="3">悦荣卡会员</option>
  <option value="4">悦尊卡会员</option>
</select>

<tr>
  <td></td>
  <td>
    <a href="javascript:void(0);" id="B_submit" style="margin: 20px;background: #ccc;padding: 10px;">发布</a>
  </td>
</tr>
</div>
</body>
</html>

<script>
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  $("#B_submit").click(updateBanner);
  function updateBanner(){
    var type = $("#type").val();
    var  id = GetQueryString('id');
    $.ajax({
      type: "post",
      url: "/Account/update1",
      data: {"type" : type,"id":id},
      dataType: "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        } else {
          // 添加成功，跳转到列表页面
          alert("修改信息成功");
          window.location.href = "/Account/page";
        }
      }
    })
  }
</script>

