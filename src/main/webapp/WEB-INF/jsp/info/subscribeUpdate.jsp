<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>修改</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <link href="/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="/static/css/page.css" rel="stylesheet"/>
  <style>
    .btn {

      /*height: 35px;*/
    }
    .btn-xs {
      width: 60px;
    }


    tr td {
      text-align: center;
      /*height: 50px;*/
      /*line-height: 50px;*/
    }
    tr th {
      text-align: center;
    }
    #contentData tr td {
      height: 45px;
      line-height: 45px;
    }
    .row{
      padding: 5px 30px;
    }
    .row label{
      width: 100px;text-align: left;
    }
    .hide{
      /*display: none;*/
    }
  </style>

  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../index.jsp"></jsp:include>
<div class="indexRight1">
  <div class="title"> 产品系统消息</div>
  <div class="row">
    <label>投资中资产</label>
    <input value="" id="counton" type="number" />
  </div>
  <div class="row">
    <label>待收益 </label>
    <input value="" id="collected" type="number" />
  </div>
  <div class="row">
    <label>累计投资</label>
    <input value="" id="cumulative" type="number" />
  </div>
  <div class="row">
    <label>已收资产</label>
    <input value="" id="received" type="number" />
  </div>
  <div class="row">
    <label>已赚收益</label>
    <input value="" id="earn" type="number" />
  </div>

  <div class="row type1 hide">
    <label>购买金额</label>
    <input value="" id="confirmationOfAmount1" type="number" />
  </div>
  <div class="row type2 hide">
    <label>购买金额</label>
    <input value="" id="confirmationOfAmount2" type="number" />
  </div>
  <div class="row type2 hide">
    <label>购买份额</label>
    <input value="" id="payCount2" type="number" />
  </div>
  <div class="row type2 hide">
    <label>平均单位购入净值</label>
    <input value="" id="aveNet2" type="number" />
  </div>
  <div class="row type2 hide">
    <label>现单位净值</label>
    <input value="" id="nowNet2" type="number" />
  </div>
  <div class="row type3 hide">
    <label>认购金额</label>
    <input value="" id="confirmationOfAmount3" type="number" />
  </div>
  <div class="row type3 hide">
    <label>到期日</label>
    <input id="maturity3" type="date" />
  </div>
  <div class="row">
    <button class="btn-primary" style="width: 100px;height: 40px;"onclick="submitForm();">提交</button>
  </div>

</div>
</body>
<script>
  var v = localStorage.modifySubscribe;
  $.getJSON("/Subscribe/AccountDetail?id="+ v.split(',')[0], function(rs) {
    console.log(rs.data.accountBo);
    var ab = rs.data.accountBo;
    var nd = rs.data.newsDetail;
    if(ab.collected) $("#collected").val(ab.collected)
    if(ab.counton) $("#counton").val(ab.counton)
    if(ab.cumulative) $("#cumulative").val(ab.cumulative)
    if(ab.received) $("#received").val(ab.received)
    if(ab.earn) $("#earn").val(ab.earn)
    if(v.split(',')[1]==1){
      $(".type1").removeClass("hide");
      if(nd.confirmationOfAmount) $("#confirmationOfAmount1").val(nd.confirmationOfAmount)
    }else if(v.split(',')[1]==2){
      $(".type2").removeClass("hide");
      if(nd.confirmationOfAmount) $("#confirmationOfAmount2").val(nd.confirmationOfAmount)
      if(nd.payCount) $("#payCount2").val(nd.payCount)
      if(nd.aveNet) $("#aveNet2").val(nd.aveNet)
      if(nd.nowNet) $("#nowNet2").val(nd.nowNet)
    }else{
      $(".type3").removeClass("hide");
      if(nd.confirmationOfAmount) $("#confirmationOfAmount3").val(nd.confirmationOfAmount)
      if(nd.maturity) $("#maturity3").val(DateToLStr(new Date(nd.maturity.time)));
    }
   })


    function submitForm(){
      var pd={
        id:v.split(',')[0],
        earn:$("#earn").val(),
        received:$("#received").val(),
        cumulative:$("#cumulative").val(),
        collected:$("#collected").val(),
        counton:$("#counton").val()
      };
      var flag = true;
      if(v.split(',')[1]==1){
        if($("#confirmationOfAmount1").val()=='')flag=false;
        pd.confirmationOfAmount=$("#confirmationOfAmount1").val();
      }else if(v.split(',')[1]==2){
        if($("#confirmationOfAmount2").val()=='')flag=false;
        if($("#payCount2").val()=='')flag=false;
        if($("#aveNet2").val()=='')flag=false;
        if($("#nowNet2").val()=='')flag=false;
        pd.confirmationOfAmount=$("#confirmationOfAmount2").val();
        pd.payCount=$("#payCount2").val();
        pd.aveNet=$("#aveNet2").val();
        pd.nowNet=$("#nowNet2").val();
      }else{
        if($("#maturity3").val()=='')flag=false;
        if($("#confirmationOfAmount3").val()=='')flag=false;
        pd.confirmationOfAmount=$("#confirmationOfAmount3").val();
        pd.maturity=new Date($("#maturity3").val());
      }
//      counton.collected.cumulative.received.earn
      if($("#counton").val()=='')flag=false;
      if($("#collected").val()=='')flag=false;
      if($("#cumulative").val()=='')flag=false;
      if($("#received").val()=='')flag=false;
      if($("#earn").val()=='')flag=false;
      if(!flag){
        alert("缺少必填项")
        return;
      }
      $.ajax({
        type : "post",
        url : "/Subscribe/updateInfo",
        data : pd,
        dataType : "json",
        success : function (data){
          if(!data.success){
            alert(data.errMsg);
            return;
          }else{
            alert("操作成功！");
            window.location.href="/Subscribe/page";
          }
        }
      });

    }
  // 时间转长时间串，yyyy-MM-dd hh:mm:ss
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
      return "" + y + "-" + m1 + "-" + d1;
//            return "" + y + "-" + m1 + "-" + d1 + " " + h1 + ":" + mm1 + ":" + s1;
    } catch(e) {
      console.log("error");
      return "";
    }
  }
</script>
