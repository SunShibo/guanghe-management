<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>行业资讯修改</title>
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
  <div class="indexRight1">
    <div class="title">行业资讯 > 行业资讯修改</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td class="td1">标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
            <td class="td2">
              <input type="text" id="title">
            </td>
          </tr>
          <tr>
            <td class="td1">英文标题：</td>
            <td class="td2">
              <input type="text" id="englishTitle">
            </td>
          </tr>
          <tr>
            <td>图&nbsp;&nbsp;&nbsp;&nbsp;片：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="160px;" height="160px;" id="uploadImage">
                  <input type="hidden" name="imageUrl"><br/>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td>来&nbsp;&nbsp;&nbsp;&nbsp;源：</td>
            <td>
              <input type="text" id="source">
            </td>
          </tr>
          <tr>
            <td>简&nbsp;&nbsp;&nbsp;&nbsp;介：</td>
            <td>
              <textarea rows="10" type="text" id="synopsis" style="margin: 0px; height: 120px; width: 987px;"></textarea>
            </td>
          </tr>

          <tr>
            <td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
            <td>
              <%--<textarea rows="10" type="text" id="content" style="margin: 0px; height: 240px; width: 987px;"></textarea>--%>

                <div class="edit">
                  <textarea name="messageContent" style="margin: 0px; height: 120px; width: 987px;"></textarea>
                  <input type="hidden" name="content"/>
                </div>
            </td>
          </tr>

          <tr>
            <td></td>
            <td>
              <button type="button" class="btn btn-primary" onclick="updatePrivateConsultant();">修改</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</body>


<script>
  function updatePrivateConsultant(){

    var title = $("#title").val();
    if (title == "") {
      alert("标题不能为空！");
      return;
    }

    var englishTitle = $("#englishTitle").val();
    if (englishTitle == "") {
      alert("英文标题不能为空！");
      return;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择图片");
      return;
    }
    var source = $("#source").val();
    if (source == "") {
      alert("来源不能为空！");
      return;
    }

    var synopsis = $("#synopsis").val();
    if (synopsis == "") {
      alert("来源不能为空！");
      return;
    }

    editor.sync();
    $("input[name='content']").val(editor.html());
    var messageContent =$("input[name='content']").val();
    if (messageContent=="") {
      alert("内容不能为空！");
      return;
    }

    var id = getParam('id');
    $.ajax({
      type : "post",
      url : "/industryInformation/update",
      data : {"title":title,"englishTitle":englishTitle,"content":messageContent,
          "source":source,"synopsis":synopsis,"imgUrl":imageUrl,"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("修改成功！");
          window.location.href="/industryInformation/page";
        }
      }
    });
  }


  $(function(){

    var id = getParam('id');

    $.ajax({
      type : "post",
      url : "/industryInformation/detail",
      data : {"newsId":id},
      dataType : "json",
      async : false,
      success : function (data){
        console.log(data.data.title)
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          $("#title").val(data.data.title);
          $("#englishTitle").val(data.data.englishTitle);
          $("#uploadImage").attr("src",'${Url}'+data.data.imgUrl);
          $("input[name='imageUrl']").val(data.data.imgUrl);
          $("#source").val(data.data.source);
          $("#synopsis").val(data.data.synopsis);
          editor.html(data.data.content);
          uploadImageUrl();
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


  //简单模式初始化
  var editor;
  KindEditor.ready(function(K) {
    editor = K.create('textarea[name="messageContent"]', {
//            resizeType : 1,
//            allowPreviewEmoticons : false,
      allowFileManager : true,
      items : [
        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
        'insertunorderedlist', '|', 'emoticons', 'image', 'media', 'link','code','source',
        'fullscreen'],
      uploadJson : '/static/js/kindeditor/kd_upload_image.jsp',
//      allowFileManager : true,
//      fileManagerJson : '/static/js/kindeditor/kd_upload_file.jsp'
      afterCreate : function() {
        this.sync();
      },
      afterBlur:function(){
        this.sync();
      }

    });


  });



</script>