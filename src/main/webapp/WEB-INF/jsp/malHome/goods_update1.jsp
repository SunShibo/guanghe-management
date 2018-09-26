<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>新品推荐</title>
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
    <div class="title">新品推荐 > 新品推荐</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td class="td1">序列号：</td>
            <td class="td2">
              <input type="text" id="title">
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
            <td></td>
            <td>
              <button type="button" class="btn btn-primary" onclick="addprivateConsultant();">添加</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</body>


<script>
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  function addprivateConsultant(){


    var title = $("#title").val();
    if (title == "") {
      alert("序列号不能为空！");
      return;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择图片");
      return;
    }

    var  s=1;
    var  id = GetQueryString('id');
    $.ajax({
      type : "post",
      url : "/homeGoods/add",
      data : {"sort":title,"imgUrl":imageUrl,"id":id,"status":s},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("添加成功！");
          window.location.href="/homeGoods/page";
        }
      }
    });
  }


  $(function(){
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