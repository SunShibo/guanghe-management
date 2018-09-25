$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_coach']").addClass("on");

    $("#B_submit").click(updateModule);
    uploadImageUrl();
})
function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
}
function updateModule(){
    var title = $("input[name='moduleTitle']").val();
    if(title == ""){
        alert("名字不能为空");
        return ;
    }
    var engtitle = $("input[name='moduleEngTitle']").val();
    if(engtitle == ""){
        alert("职位不能为空");
        return ;
    }
    var content = $("textarea[name='moduleContent']").val();
    if(content == ""){
        alert("简介不能为空");
        return ;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if(imageUrl == ""){
        alert("头像不能为空");
        return ;
    }
    var  id = GetQueryString('id');
    $.ajax({
        type : "post",
        url : "update",
        data :{"imageUrl" : imageUrl,"id":id,"employeeName":title,"employeePosition":engtitle,"introduction":content},
        dataType : "json",
        success: function(result, status) {
            if(result.success== false){
                alert(result.errMsg);
                return ;
            }else {
                alert("修改成功");
                window.location.href = "page";
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