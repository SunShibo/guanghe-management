$(function (){
    $("#B_login").click(login);
})

function login(){
    var account = $("input[name='account']").val();
    var password = $("input[name='password']").val();
    if(account == ""){
        alert("账号不能为空");
        return ;
    }
    if(password == ""){
        alert("密码不能为空");
        return ;
    }
    $.ajax({
        type : "post",
        url : "/AdminController/login",
        data : {"account" :account, "password":password},
        dataType : "json",
        success: function(result) {
            console.log(result.success == false)
            if(result.success == false){
                alert(result.errMsg);
                return ;
            }else {
                window.location.href = "/AdminController/page";
            }
        }
    })
}