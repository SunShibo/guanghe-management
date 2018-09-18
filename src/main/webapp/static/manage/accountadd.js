$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_coupon']").addClass("on");

    $("#B_submit").click(addCoupon);
})
function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
}
function addCoupon(){
    var id = GetQueryString('id');
    var integral = $("input[name='coachName']").val();
    if(integral == ""){
        alert("积分金额不能为空");
        return;
    }
    var payinfo = $("input[name='payinfo']").val();
    if(payinfo == ""){
        alert("消费信息不能为空");
        return;
    }

    $.ajax({
        type : "post",
        url : "/Account/add",
        data :{"integral" : integral,"id":id,"payinfo":payinfo,},
        dataType : "json",
        success: function(result, status) {
            if(result.success== false){
                alert(result.errMsg);
                return ;
            }else {
                alert("修改成功");
                window.location.href = "/Account/page";
            }
        }
    });
}