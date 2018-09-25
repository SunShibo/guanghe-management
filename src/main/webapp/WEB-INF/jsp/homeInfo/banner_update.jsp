<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>修改</title>
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
  <div class="title">banner修改</div>
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
      <button  style="width:15%;" type="button" class="btn btn-primary" id="B_submit">修改</button>
    </div>
  </div>
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

    var privateInvestmentId =  getParam("id");
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
      url : "/Banner/update",
      data : {"id":privateInvestmentId,
        "url":pcUrl,
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
          alert("修改成功！");
          window.location.href="/home/list";
        }
      }
    });
  }


  function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
      arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
      while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
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
  $(function(){

    var id =  getParam("id");

    $.ajax({
      type : "post",
      url : "/Banner/detail",
      data : {"Id" : id},
      dataType : "json",
      success : function (data){
        console.log(data);
        if(data.success == false){
          alert(data.data.errMsg);
          return;
        }else{
          $("#wapUrl").val(data.data.wapUrl);
          $("#pcUrl").val(data.data.url);
          $("#uploadImage").attr("src",'${Url}'+data.data.image);
          $("#uploadImagewap").attr("src",'${Url}'+data.data.wapImage);
          $("input[name='imageUrl']").val(data.data.image);
          $("input[name='imageUrlwap']").val(data.data.wapImage);
        }
      }
    });

  });

  function getInvestmentPoin(investmentPoinId){
    $.ajax({
      type : "post",
      url : "/investmentPoin/list",
      data : {},
      dataType : "json",
      success : function (data){
        var html = '';
        for(var i=0;i<data.data.count;i++){
          html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
        }

        $("#investmentPoinId").append(html);

        var investmentPoinIds = $("#investmentPoinId").find("option"); //获取select下拉框的所有值
        for (var j = 1; j < investmentPoinIds.length; j++) {
          if ($(investmentPoinIds[j]).val() == investmentPoinId) {
            $(investmentPoinIds[j]).attr("selected", "selected");
          }
        }
      }
    });
  }

  function getProductTerm(productTermId){
    $.ajax({
      type : "post",
      url : "/productTerm/list",
      data : {},
      dataType : "json",
      success : function (data){
        var html = '';
        for(var i=0;i<data.data.count;i++){
          html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
        }

        $("#productTermId").append(html);
        var productTermIds = $("#productTermId").find("option"); //获取select下拉框的所有值
        for (var j = 1; j < productTermIds.length; j++) {
          if ($(productTermIds[j]).val() == productTermId) {
            $(productTermIds[j]).attr("selected", "selected");
          }
        }
      }
    });
  }

  function getRiskLevel(riskLevelId){
    $.ajax({
      type : "post",
      url : "/riskLevel/list",
      data : {},
      dataType : "json",
      success : function (data){
        var html = '';
        for(var i=0;i<data.data.count;i++){
          html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
        }

        $("#riskLevelId").append(html);

        $("#riskLevelId").append(html);
        var riskLevelIds = $("#riskLevelId").find("option"); //获取select下拉框的所有值
        for (var j = 1; j < riskLevelIds.length; j++) {
          if ($(riskLevelIds[j]).val() == riskLevelId) {
            $(riskLevelIds[j]).attr("selected", "selected");
          }
        }
      }
    });
  }

  function getIncomeType(incomeTypeId){
    $.ajax({
      type : "post",
      url : "/incomeType/list",
      data : {},
      dataType : "json",
      success : function (data){
        var html = '';
        for(var i=0;i<data.data.count;i++){
          html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
        }

        $("#incomeTypeId").append(html);
        var incomeTypeIds = $("#incomeTypeId").find("option"); //获取select下拉框的所有值
        for (var j = 1; j < incomeTypeIds.length; j++) {
          if ($(incomeTypeIds[j]).val() == incomeTypeId) {
            $(incomeTypeIds[j]).attr("selected", "selected");
          }
        }
      }
    });
  }
  // 时间转长时间串，yyyy-MM-dd hh:mm:ss
  function DateToLStr(dt) {
    try {
      var y, m, m1, d, d1, h, h1, mm, mm1, s, s1;
      y = dt.getFullYear();
      m = dt.getMonth() + 1; //1-12
      d = dt.getDate();
      h = dt.getHours();
      mm = dt.getMinutes();
      s = dt.getSeconds();

      m1 = (m < 10 ? "0" + m : m);
      d1 = (d < 10 ? "0" + d : d);
      h1 = (h < 10 ? "0" + h : h);
      mm1 = (mm < 10 ? "0" + mm : mm);
      s1 = (s < 10 ? "0" + s : s);
      return "" + y + "-" + m1 + "-" + d1;
//            return "" + y + "-" + m1 + "-" + d1 + " " + h1 + ":" + mm1 + ":" + s1;
    } catch(e) {
      console.log("error");
      return "";
    }
  }
</script>
</html>