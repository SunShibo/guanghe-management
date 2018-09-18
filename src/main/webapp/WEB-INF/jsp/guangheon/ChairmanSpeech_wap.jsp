<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/8/27
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>公司介绍pc修改</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
  <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1" width="1200px;">
    <div class="title">公司介绍 > pc</div>
    <div class="tablebox2" >
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">

          <tr>
            <td>图&nbsp;&nbsp;&nbsp;&nbsp;片：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="1200px;" height="800px;" id="uploadImage">
                  <input type="hidden" name="imageUrl"><br/>
                </div>
              </div>
            </td>
          </tr>


          <tr>
            <td></td>
            <td>
              <button type="button" class="btn btn-primary" onclick="updateImgUrl();">修改</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</body>


<script>



  $(function(){

    $.ajax({
      type : "post",
      url : "/ChairmanSpeech/detail",
      data : {},
      dataType : "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          console.log(data);
          $("#uploadImage").attr("src",'${Url}'+data.data.wapImage);
        }
      }
    });

    uploadImageUrl();

  });

  function uploadImageUrl(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
      action: "/privateConsultant/uploadImage",
      type:"post",
      data:{},
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

  function updateImgUrl(){

    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择图片");
      return;
    }
    $.ajax({
      type : "post",
      url : "/ChairmanSpeech/update1",
      data : { "wapImage" : imageUrl},
      dataType : "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("修改成功");
          window.location.reload();
        }
      }
    });
  }


</script>