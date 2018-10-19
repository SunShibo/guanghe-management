$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_coach']").addClass("on");

    $("#B_submit").click(updateclub);
    uploadImageUrl();
    uploadWapImage();
    var  id = GetQueryString('id');
    if(id==1){
        $("#tdd").css('display','none')
        $("#td").css('display','none')
    }
})
function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
}
function updateclub(){
    var content = $("textarea[name='moduleContent']").val();
    if(content == ""){
        alert("内容不能为空");
        return ;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    var url=$("input[name='url']").val();
    var wapurl=$("input[name='wapurl']").val();
    var wapImageUrl = $("input[name='wapImageUrl']").val();
    var  id = GetQueryString('id');
    $.ajax({
        type : "post",
        url : "update",
        data :{"image" : imageUrl,"id":id,"content":content,"url":url,"wapUrl":wapurl,"wapImage":wapImageUrl},
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
function uploadWapImage(){
    var button = $("#uploadWapImage"), interval;
    new AjaxUpload(button, {
        action: "/privateConsultant/uploadImage",
        type:"post",
        name: 'myFile',
        responseType : 'json',
        onComplete: function(file, response) {
            if(response.success == true){
                var resultData = response.data;
                console.log(resultData)
                $("#uploadWapImage").attr("src", resultData.Url);
                $("input[name='wapImageUrl']").val(resultData.imageUrl);
            }else{
                alert("上传失败");
            }
        }
    });
}