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
  <title>积分信息</title>
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
  <div class="title">积分信息
  </div>
  <div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th style="width: 15%;">消费信息</th>
        <th style="width: 20%;">交易时间</th>
        <th style="width: 15%;">积分金额</th>
        <th style="width: 15%;">交易状态</th>
        <th style="width:20%">订单号</th>
        <th style="width: 15%;">积分剩余</th>
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
  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  var options;
  getdata(1,10);
  function getdata(cur,size) {
    var id = GetQueryString('id');
    $.getJSON("/integralTransaction/list?pageNo=" + cur + "&pageSize=" + size + "&userId=" + id, function (rs) {

      console.log(rs)
      var datas=rs.data.data;
      options={
        "id":"page",//显示页码的元素
        "data":datas,//显示数据
        "maxshowpageitem":3,//最多显示的页码个数
        "pagelistcount":size,//每页显示数据个数
        "callBack":function(result) {
          var html = '';
          for (var i = 0; i < rs.data.data.length; i++) {
            html += '<tr>' +
                    '<td>' + rs.data.data[i].payinfo + '</td>' +
                    '<td>' + DateToLStr(new Date(rs.data.data[i].createTime.time)) + '</td>' +
                    '<td>' + rs.data.data[i].deal + '</td>'
                            if(rs.data.data[i].state=1) {
                              html += '<td>' + "交易成功" + '</td>'
                            }else{
                              html += '<td>' + "交易失败" + '</td>'
                            }
            html += '<td>' + rs.data.data[i].orderId + '</td>' +
                    '<td>' + rs.data.data[i].oddintegral + '</td>' +
                    '</tr>';
          }$("#contentData").html(html);
        }
        };

      page.init(rs.data.count,cur,options);
    })
  }

</script>
