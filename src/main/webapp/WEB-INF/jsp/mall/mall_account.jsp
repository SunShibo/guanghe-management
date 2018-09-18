<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>私募投资列表</title>
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
  <div class="title">账号管理 > 用户账号列表</div>
  <div class="screen clear">
    <div class="form">
      <input type="text" placeholder="请输入用户电话号码" id="phone">
      <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
    </div>

  </div>
  <div>
    <table class="table table-bordered">
      <%--<caption>边框表格布局</caption>--%>
      <thead>
      <tr>
        <th style="width: 20%;">电话号码</th>
        <th style="width: 20%;">积分余额</th>
        <th style="width: 20%;">会员级别</th>
        <th style="width: 40%;">操作</th>
      </tr>
      </thead>
      <tbody id="contentData">
      <%--<tr>--%>
      <%--<td>五莲绽放-广和旅游产业基金</td>--%>
      <%--<td>6.5%</td>--%>
      <%--<td>5+2+2</td>--%>
      <%--<td>300万元</td>--%>
      <%--<td>--%>
      <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
      <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
      <%--</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
      <%--<td>五莲绽放-广和旅游产业基金</td>--%>
      <%--<td>6.5%</td>--%>
      <%--<td>5+2+2</td>--%>
      <%--<td>300万元</td>--%>
      <%--<td>--%>
      <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
      <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
      <%--</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
      <%--<td>五莲绽放-广和旅游产业基金</td>--%>
      <%--<td>6.5%</td>--%>
      <%--<td>5+2+2</td>--%>
      <%--<td>300万元</td>--%>
      <%--<td>--%>
      <%--<button type="button" class="btn btn-info btn-xs">编辑</button>--%>
      <%--<button type="button" class="btn btn-danger btn-xs">删除</button>--%>
      <%--</td>--%>
      <%--</tr>--%>
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
    var phone = $("#phone").val();
    console.log(cur+',,,'+size)
    $.getJSON("/Account/list?pageNo="+cur+"&pageSize="+size+"&phone="+phone, function (rs) {
      var datas=rs.data.data;
      options={
        "id":"page",//显示页码的元素
        "data":datas,//显示数据
        "maxshowpageitem":3,//最多显示的页码个数
        "pagelistcount":size,//每页显示数据个数
        "callBack":function(result){
          var html = '';
          for (var i = 0; i < rs.data.data.length; i++) {
            html += '<tr>' +
                    '<td>' + rs.data.data[i].phoneNumber + '</td>' +
                    '<td>' + rs.data.data[i].integral + '</td>'
                     if(rs.data.data[i].leaveStatus==0){
                              html +=   '<td>' + "普通会员" + '</td>'
                               }
                     if(rs.data.data[i].leaveStatus==1){
                               html +=   '<td>' + "悦享卡会员" + '</td>'
                            }
                     if(rs.data.data[i].leaveStatus==2){
                                   html +=   '<td>' + "悦亲卡会员" + '</td>'
                                                 }
                     if(rs.data.data[i].leaveStatus==3){
                               html +=   '<td>' + "悦荣卡会员" + '</td>'
                                     }else if(rs.data.data[i].leaveStatus==4){
                               html +=   '<td>' + "悦尊卡会员" + '</td>'
                                  }
                    html += '<td>' +
                    '<button type="button" class="btn btn-info" onclick="query(' + "'" + rs.data.data[i].id + "'" + ')">查看用户积分记录</button>' +
                    '<button type="button" class="btn btn-info" onclick="add(' + "'" + rs.data.data[i].id + "'" + ')">增加积分</button>' +
                    '<button type="button" class="btn btn-info" onclick="update(' + "'" + rs.data.data[i].id + "'" + ')">修改会员级别</button>' +
                    '</td>' +
                    '</tr>';
          }
          $("#contentData").html(html);
        }
      };
      page.init(rs.data.count,cur,options);
    })
  }













  function add(id){
    window.location.href = '/Account/toadd?id=' + id;
  }




  function query(id){
    window.location.href = '/Account/querycount?id=' + id;
  }








</script>