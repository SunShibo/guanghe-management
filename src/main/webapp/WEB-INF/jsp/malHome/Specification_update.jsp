<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/9/12
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title> New Document </title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <%--<script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>--%>
  <%--<script type="text/javascript" src="/static/manage/module_update.js"></script>--%>
  <link rel="stylesheet" href="/static/css/cxcalendar.css">
  <style>
    h1,h2,h3{font:bold 36px/1 "\5fae\8f6f\96c5\9ed1";}
    h2{font-size:20px;}
    h3{font-size:16px;}
    fieldset{margin:1em 0;}
    fieldset legend{font:bold 14px/2 "\5fae\8f6f\96c5\9ed1";}
    a{color:#06f;text-decoration:none;}
    a:hover{color:#00f;}
    .a{
      position: fixed;
      right: 0;
      left: 230px;
      top: 0;
      bottom: 0;
      margin: auto;
    }
    .wrap{width:600px;margin:0 auto;padding:20px 40px;border:2px solid #999;border-radius:8px;background:#fff;box-shadow:0 0 10px rgba(0,0,0,0.5);}
  </style>
  <script src="/static/js/jquery-1.9.1.js"></script>
  <script src="/static/js/calendar.js"></script>
  <%--<script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>--%>
</head>
<body>
  <div class="index clear">
    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1" style="padding-left: 100px;">
      <div class="title">修改</div>
      <div class="tablebox2">
        <form >
          <table cellpadding="0" cellspacing="0" border="0">
            <tr>
              <td class="td2"> 开始时间：
                <input name="mydate" type="text" id="preferentialStartTime" class="input_cxcalendar startTime"
                       value="<fmt:formatDate value='${module.preferentialStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
                        ></td>
            </tr>
            <tr>
              <td class="td2"> 结束时间：<input name="mydate2" type="text" id="preferentialEndTime"
                                           class="input_cxcalendar endTime"
                                           value="<fmt:formatDate value='${module.preferentialEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
                      ></td>
            </tr>
            <tr>
              <td>价&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
              <td class="td2"><input type="text" name="moduleEngTitle" id="price"value="${module.price}"></td>
            </tr>
            <tr>
              <td>规&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
              <td class="td2"> <input type="text" name="moduleEngTitle1" id="specification"value="${module.specification}"></td>
            </tr>
            <tr>
              <td>优惠价格：</td>
              <td class="td2"><input type="text" name="moduleEngTitle2" id ="preferentialPrice"value="${module.preferentialPrice}"></td>
            </tr>
            <tr>
              <td>库存</td>
              <td class="td2"> <input type="text" name="moduleEngTitle3" id="stock"value="${module.stock}"></td>
            </tr>
            <tr>
              <td></td>
              <td><button type="button" onclick="commit()">发布</button></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>




</body>
</html>

<script>
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  function commit(){
    var  id = GetQueryString('sku');
var pd ={
  "sku":id,
  "preferentialStartTime":new Date(document.getElementById("preferentialStartTime").value),
  "preferentialEndTime":new Date(document.getElementById("preferentialEndTime").value),
  "price":document.getElementById("price").value,
  "specification":document.getElementById("specification").value,
  "preferentialPrice":document.getElementById("preferentialPrice").value,
  "stock":document.getElementById("stock").value

};
    console.log(pd.preferentialEndTime)
    if(pd.preferentialEndTime == 'Invalid Date'){
      delete pd.preferentialEndTime
    }
    if(pd.preferentialStartTime == 'Invalid Date'){
     delete pd.preferentialStartTime
    }
    $.ajax({
      type : "post",
      url : "/GoodsSpeciFication/update",
      data :pd,
      dataType : "json",
      success: function(result, status) {
        if(result.success){
          alert("发布成功");
          window.location.href="/Goods/page";
        }
      }
    })
  }
  $('.input_cxcalendar').each(function(){
    var a = new Calendar({
      targetCls: $(this),
      type: 'yyyy-mm-dd HH:MM:SS',
      wday:2
    },function(val){
      console.log(val);
    });
  });
  /* function GetQueryString(name) {
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)return  unescape(r[2]);
   return null;
   }
   function getTime(){
   var  id = GetQueryString('sku');
   $.ajax({
   type : "post",
   url : "/GoodsSpeciFication/query",
   data :{"sku":sku,},
   dataType : "json",
   success: function(result, status) {
   if(result.success== false){
   alert(result.errMsg);
   return ;
   }else {
   $('.startTime').val(result.data.preferentialStartTime);
   $('.endTime').val(result.data.preferentialEndTime);
   }
   }
   })
   }*/
  <%--alert(new Date('${module.preferentialStartTime}'))--%>
  <%--alert(DateToLStr(new Date('${module.preferentialStartTime}')))--%>
  function DateToLStr(dt) {
    try {
      var y, m, m1, d, d1, h, h1, mm, mm1, s, s1;
      y = dt.getFullYear();
      m = dt.getMonth() + 1; //1-12
      d = dt.getDate();
      h = dt.getHours();
      mm = dt.getMinutes();
      s = dt.getSeconds();

      m1 = (m < 10 ? "0" + m : m);
      d1 = (d < 10 ? "0" + d : d);
      h1 = (h < 10 ? "0" + h : h);
      mm1 = (mm < 10 ? "0" + mm : mm);
      s1 = (s < 10 ? "0" + s : s);
      return "" + y + "-" + m1 + "-" + d1 + " " + h1 + ":" + mm1 + ":" + s1;
    } catch(e) {
      console.log("error");
      return "";
    }
  }
</script>