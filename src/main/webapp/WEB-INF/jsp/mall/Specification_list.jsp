<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/8/13
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>列表</title>
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
  </style>

  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../index.jsp"></jsp:include>
<div class="indexRight1">
  <div class="title">商品图列表</div>
  <div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 35%;">规格名</th>
        <th style="width: 30%;">价格</th>
        <th style="width: 35%;">操作</th>
      </tr>
      </thead>
      <tbody id="contentData">
      </tbody>
    </table>
  </div>

  <div style="height: 28px; width: 360px; margin: 0 auto;">
    <ul class="page" id="page"></ul>
  </div>
</div>
</body>
<script src="/static/js/web/page.js"></script>
<script>
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  $.getJSON("/GoodsSpeciFication/detailList?id="+GetQueryString("id"), function (rs) {

    var html = '';
    for (var i = 0; i < rs.data.length; i++) {
        html += '<tr>' +
             '<td>' + rs.data[i].specification + '</td>' +
                '<td>' + rs.data[i].price + '</td>'
                        if(rs.data[i].stock==0){
                          html+='<td>' +
                                  '<a>此商品已经下架</a>'+
                                  '<button type="button" class="btn btn-danger" onclick="updatestock1(' + "'" + rs.data[i].sku + "'" + ')">上架商品</button>' +
                                  '</td>' +
                                  '</tr>'
                        }else {
                          html += '<td>' +
                                  '<button type="button" class="btn btn-info" onclick="update1(' + "'" + rs.data[i].sku + "'" + ')">修改规格</button>' +
                                  '<button type="button" class="btn btn-danger" onclick="updatestock(' + "'" + rs.data[i].sku + "'" + ')">设为下架商品</button>' +
                                  '<button type="button" class="btn btn-danger" onclick="deletesku(' + "'" + rs.data[i].sku + "'" + ')">删除规格</button>' +
                                  '</td>' +
                                  '</tr>'
                        }
    }
    $("#contentData").html(html);

  })

  function addImage(goodsId){
    window.location.href = '/GoodsSpeciFication/SpecificationAdd?goodsId=' + goodsId;
  }
  function update1(sku){
    window.location.href = '/GoodsSpeciFication/update1?sku=' + sku;
  }

  function updatestock(sku){
    $.ajax({
      type : "post",
      url : "/GoodsSpeciFication/updatestock",
      data : {"sku":sku},
      dataType : "json",

      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("下架成功！");
          window.location.href="/Goods/page";
        }
      }
    });
  }
  function updatestock1(sku){
    $.ajax({
      type : "post",
      url : "/GoodsSpeciFication/updatestock1",
      data : {"sku":sku},
      dataType : "json",

      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("上架成功！");
          window.location.href="/Goods/page";
        }
      }
    });
  }
  function   deletesku(sku){
    $.ajax({
      type : "post",
      url : "/GoodsSpeciFication/delete",
      data : {"sku":sku},
      dataType : "json",

      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("删除成功！");
          window.location.href="/Goods/page";
        }
      }
    });
  }
</script>
