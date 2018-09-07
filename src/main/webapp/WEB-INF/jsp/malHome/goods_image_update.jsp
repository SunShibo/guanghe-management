<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>修改</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <%--<script type="text/javascript" src="/static/manage/banner_update.js"></script>--%>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">修改> 修改</div>
    <div class="tablebox2">
      <form id="bannerForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td>图片：</td>
            <td>
              <div class="suolue">
                <div class="uploadimg">
                  <img width="160px;" height="160px;" id="uploadImage"/>
                  <input type="hidden" name="imageUrl"><br/>
                </div>
                <div class="zi">
                  <span style="color:#FF0000; position: absolute; top :150px; margin-left: 15px" >*请上传想替换的图片</span>
                </div>
              </div>
              <td>

          </td>
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <a href="javascript:void(0);" id="B_submit" style="margin: 20px 160px">发布</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
</div>
<script>

  $(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_banner']").addClass("on");

    $("#B_submit").click(updateImgUrl);
    uploadImageUrl();

    var id= getParam("id");
    var url_img= getParam("imgUrl");
    alert(url_img);
    var Url= getParam("Url");
    if(url_img!=null){
      $("#uploadImage").attr("src", Url+url_img);
      $("input[name='imageUrl']").val(url_img);
    }
  })



  function updateImgUrl() {
    var id= getParam("id");
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择轮播图片");
      return;
    }
    $.ajax({
      type: "post",
      url: "updateImgUrl",
      data: {"image" : imageUrl,"id":id},
      dataType: "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        } else {
          // 添加成功，跳转到列表页面
          alert("修改成功");
          window.location.href = "/homeGoods/page";
        }
      }
    })
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


</script>
</body>