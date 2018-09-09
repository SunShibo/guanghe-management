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
    var name = $("input[name='name']").val();
    if(name == ""){
        alert("名称不能为空");
        return ;
    }
    var weight = $("input[name='weight']").val();
    if(weight == ""){
        alert("重量不能为空");
        return ;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if(imageUrl == ""){
        alert("图片不能为空");
        return ;
    }
    var  id = GetQueryString('id');
    $.ajax({
        type : "post",
        url : "updategoodsInfo1",
        data :{"imageUrl" : imageUrl,"id":id,"name":name,"weight":weight,},
        dataType : "json",
        success: function(result, status) {
            if(result.success== false){
                alert(result.errMsg);
                return ;
            }else {
                alert("修改成功");
                window.location.href = "/home/list";
            }
        }
    })
}
function uploadImageUrl(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
        action: "uploadImage",
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