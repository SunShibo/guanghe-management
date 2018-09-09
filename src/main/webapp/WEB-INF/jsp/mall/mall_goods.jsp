<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/9/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>商品列表</title>
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
  <div class="title">商品 > 商品列表</div>
  <div class="screen clear">
    <div class="form">
      <input type="text" placeholder="请输入商品名称" id="name">
      <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
    </div>

  </div>
  <div>
    <table class="table table-bordered">
      <%--<caption>边框表格布局</caption>--%>
      <thead>
      <tr>
        <th style="width: 30%;">商品名称</th>
        <th style="width: 30%;">商品重量</th>
        <th style="width: 70%;">操作</th>
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

  var options;
  getdata(1,10);
  function getdata(cur,size){
    var name = $("#name").val();
    console.log(cur+',,,'+size)
    $.getJSON("/Goods/list?pageNo="+cur+"&pageSize="+size+"&name="+name, function (rs) {
      var datas=rs.data.data;
      options={
        "id":"page",//显示页码的元素
        "data":datas,//显示数据
        "maxshowpageitem":10000,//最多显示的页码个数
        "pagelistcount":size,//每页显示数据个数
        "callBack":function(result){
          var html = '';
          for (var i = 0; i < rs.data.data.length; i++) {
            html += '<tr>' +
                    '<td>' + rs.data.data[i].name + '</td>' +
                    '<td>' + rs.data.data[i].weight + '</td>' +
                    '<td>' +
                    '<button type="button" class="btn btn-info" onclick="updateGoods(' + "'" + rs.data.data[i].id + "'" + ')">商品信息修改</button>' +
                    '<button type="button" class="btn btn-info" onclick="updateRiskManagement(' + "'" + rs.data.data[i].id + "'" + ')">商品所属品牌修改</button>' +
                    '<button type="button" class="btn btn-info" onclick="updateimage(' + "'" + rs.data.data[i].id + "'" + ')">商品图片修改</button>' +
                    '<button type="button" class="btn btn-info" onclick="updateGoodsSpeciFication(' + "'" + rs.data.data[i].id + "'" + ')">规格修改</button>' +
                    '</td>' +
                    '</tr>';
          }
          $("#contentData").html(html);
        }
      };
      page.init(rs.data.count,cur,options);
    })
  }
  function updateGoods(id){
    window.location.href = '/Goods/toUpdate?id=' + id;
  }
  function updateimage(id){
    window.location.href = '/GoodsImage/page?id=' + id;
  }


  function deletePrivateInvestment(id){
    $.ajax({
      type : "post",
      url : "/privateInvestment/delete",
      data : {"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("删除成功！");
          window.location.href="/privateInvestment/page";
        }
      }
    });
  }

  function updateGoodsSpeciFication(id){
    window.location.href="/GoodsSpeciFication/page?id=" + id;
  }

</script>
