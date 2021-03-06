<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>私享顾问添加</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">私享顾问 > 私享顾问添加</div>
    <div class="tablebox2">
      <form id="moduleForm" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td class="td1">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
            <td class="td2">
              <input type="text" id="name">
            </td>
          </tr>
          <tr>
            <td class="td1">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
            <td class="td2">
              <select type="text" id="gender">
                <option value="">-请选择-</option>
                <option value="female">女</option>
                <option value="male">男</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>职&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
            <td>
              <input type="text" id="position">
            </td>
          </tr>
          <tr>
            <td>所属公司：</td>
            <td>
              <input type="text" id="company"></td>
          </tr>
          <tr>
            <td>工&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
            <td>
              <input type="text" id="jobNumber"></td>

            </td>
          </tr>
          <tr>
            <td>简&nbsp;&nbsp;&nbsp;&nbsp;介：</td>
            <td>
              <textarea rows="10" type="text" id="synopsis" style="margin: 0px; height: 263px; width: 987px;"></textarea>
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
  function addprivateConsultant(){
    var company = $("#company").val();
    if (company == "") {
      alert("所属公司不能为空！");
      return;
    }
    var jobNumber = $("#jobNumber").val();
    if (jobNumber == "") {
      alert("工号不能为空！");
      return;
    }
    var name = $("#name").val();
    if (name == "") {
      alert("姓名不能为空！");
      return;
    }

    var gender = $("#gender").val();
    if (gender == "") {
      alert("性别不能为空！");
      return;
    }
    var position = $("#position").val();
    if (position == "") {
      alert("职位不能为空！");
      return;
    }
    var synopsis = $("#synopsis").val();
    if (synopsis=="" || synopsis.length == 0) {
      alert("简介不能为空！");
      return;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
      alert("请选择图片");
      return;
    }
    $.ajax({
      type : "post",
      url : "/privateConsultant/add",
      data : {"name":name,"gender":gender,"position":position,"synopsis":synopsis,"imgUrl":imageUrl,"company":company,"jobNumber":jobNumber,},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("添加成功！");
          window.location.href="/privateConsultant/page";
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
      action: "uploadImage",
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
</script>