$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_banner']").addClass("on");

    $("#B_submit").click(addpartener);
    uploadImageUrl();
})

function addpartener() {
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
        alert("请选择轮播图片");
        return;
    }
    console.log($("#bannerForm").serialize())
    $.ajax({
        type: "post",
        url: "add",
        data: {"image" : imageUrl},
        dataType: "json",
        success : function (data){
            if(data.success == false){
                alert(data.errMsg);
                return;
            } else {
                // 添加成功，跳转到列表页面
                alert("添加信息成功");
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