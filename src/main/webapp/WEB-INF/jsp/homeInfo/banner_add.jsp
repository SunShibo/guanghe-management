<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>添加私募产品</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <link href="/static/css/bootstrap.min.css" rel="stylesheet">
  <style>

    .column{
      margin: 0px;
      padding-top: 15px !important;
    }
    .col-xs-1{
      font-size: 15px;
    }
    .column input {
      width: 260px;
      height: 30px;
    }
    .column select {
      width: 260px;
      height: 30px;
    }

  </style>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../index.jsp"></jsp:include>
<div class="indexRight1">
  <div class="title">私募投资 > 添加私募产品</div>
  <%--<div class="screen clear">--%>
  <%--<div class="form">--%>
  <%--<input type="text" placeholder="请输入产品名称" id="privateInvestmentName">--%>
  <%--<button type="button" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>--%>
  <%--<button type="button" class="btn btn-primary" onclick="addPrivateInvestment();">新增</button>--%>
  <%--</div>--%>

  <%--</div>--%>
  <div class="row clearfix" >
    <div class="col-xs-1 column text-right">
      pcUrl:
    </div>
    <div class="col-xs-3 column">
      <input id="pcUrl" />
    </div>

    <div class="col-xs-1 column text-right">
      wapUrl:
    </div>
    <div class="col-xs-3 column">
      <input id="wapUrl" />
    </div>
  </div>
  <div class="row clearfix">
    <div class="col-xs-1 column text-right">
      banner详情图pc:
    </div>
    <div class="col-xs-3 column">
      <div class="suolue">
        <div class="uploadimg">
          <img width="160px;" height="160px;" id="uploadImage">
          <input type="hidden" name="imageUrl"><br/>
        </div>
      </div>
    </div>
    <div class="col-xs-1 column">
      banner详情图wap:
    </div>
    <div class="col-xs-3 column text-right">
      <div class="suolue">
        <div class="uploadimg">
          <img width="160px;" height="160px;" id="uploadImagewap">
          <input type="hidden" name="imageUrlwap"><br/>
        </div>
      </div>
    </div>


  </div>



  <div class="row clearfix" style="margin-top: 30px;">
    <div class="col-xs-10 column text-right" style="text-align:center ">
      <button  style="width:15%;" type="button" class="btn btn-primary" id="B_submit">添加</button>
    </div>
  </div>
  <%--<div style="height: 28px; width: 360px; margin: 0 auto;">--%>
  <%--<ul class="page" id="page"></ul>--%>
  <%--</div>--%>
</div>
</body>


<script>
  $(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_banner']").addClass("on");

    $("#B_submit").click(addPrivateInvestment);
    uploadImageUrl();
    uploadwapImageUrl()
  })
  function addPrivateInvestment(){

    var wapUrl = $("#wapUrl").val();
    var pcUrl = $("#pcUrl").val();

    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择图片");
      return;
    }
    var wapImage = $("input[name='imageUrlwap']").val();
    if (wapImage == "") {
      alert("请选择图片");
      return;
    }

    $.ajax({
      type : "post",
      url : "/Banner/add",
      data : {"url":pcUrl,
         "wapUrl":wapUrl,
        "image":imageUrl,
        "wapImage":wapImage},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("添加成功！");
          window.location.href="/home/list";
        }
      }
    });
  }


  function uploadImageUrl(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
      action: "/privateConsultant/uploadImage",
      type:"post",
      name: 'myFile',
      responseType : 'json',
      onSubmit: function(file, ext) {
        if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
          alert("您上传的图片格式不对，请重新选择！");
          return false;
        }
      },
      onComplete: function(file, response) {
        if(response.success == true){
          var resultData = response.data;
          console.log(resultData)
          $("#uploadImage").attr("src", resultData.Url);
          $("input[name='imageUrl']").val(resultData.imageUrl);
        }else{
          alert("上传失败");
        }
      }
    });
  }

  function uploadwapImageUrl(){
    var button = $("#uploadImagewap"), interval;
    new AjaxUpload(button, {
      action: "/privateConsultant/uploadImage",
      type:"post",
      name: 'myFile',
      responseType : 'json',
      onSubmit: function(file, ext) {
        if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
          alert("您上传的图片格式不对，请重新选择！");
          return false;
        }
      },
      onComplete: function(file, response) {
        if(response.success == true){
          var resultData = response.data;
          console.log(resultData)
          $("#uploadImagewap").attr("src", resultData.Url);
          $("input[name='imageUrlwap']").val(resultData.imageUrl);
        }else{
          alert("上传失败");
        }
      }
    });
  }
</script>
</html>