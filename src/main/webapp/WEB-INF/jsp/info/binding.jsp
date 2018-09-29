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
  <div class="title"> 理财师系统消息</div>
  <div class="screen clear">
    <div class="form">
      <input type="text" placeholder="请输入联系电话" id="title">
      <select id="type">
        <option value="1">全部</option>
        <option value="2">预约中</option>
        <option value="3">已联系</option>
      </select>
      <button type="button" style=" margin-left: 20px;" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>
    </div>

  </div>
  <div>
    <table class="table table-bordered">
      <%--<caption>边框表格布局</caption>--%>
      <thead>
      <tr>
        <th style="width:10%;">登录账号</th>
        <th style="width: 10%;">联系电话</th>
        <th style="width: 10%;">顾问姓名</th>
        <th style="width: 10%;">顾问工号</th>
        <th style="width: 10%;">状态</th>
        <th style="width: 30%;">操作</th>

      </tr>
      </thead>
      <tbody id="contentData">
      <%--<tr>--%>
      <%--<td>五莲绽放-广和旅游产业基金</td>--%>
      <%--<td>6.5%</td>--%>
      <%--<td>5+2+2</td>--%>
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
    var title = $("#title").val();
    var type = $("#type").val();
    $.getJSON("/binding/list?pageNo="+cur+"&pageSize="+size+"&phone="+title+"&status="+type, function (rs) {
      var datas=rs.data.data;
      console.log(datas);
      options={
        "id":"page",//显示页码的元素
        "data":datas,//显示数据
        "maxshowpageitem":3,//最多显示的页码个数
        "pagelistcount":size,//每页显示数据个数
        "callBack":function(result){
          var html = '';
          for (var i = 0; i < rs.data.data.length; i++) {

            var homeState = rs.data.data[i].status;
            var html1 = '';
            if(homeState == 1){
              homeState = '预约中';
            }else if(homeState == 2){
              homeState = '已处理';
            }else{
              homeState = '' ;
            }


            html += '<tr>'+
                    '<td>'+ rs.data.data[i].phoneNumber +'</td>'+
                    '<td>'+ rs.data.data[i].phone +'</td>'+
                    '<td>'+ rs.data.data[i].name +'</td>'+
                    '<td>'+ rs.data.data[i].jobNumber +'</td>'+
                    '<td>'+ homeState +'</td>'
            if(rs.data.data[i].status==1) {
              html += '<td>' +
                      '<button type="button" class="btn btn-info" onclick="updateState(' + "'" + rs.data.data[i].id+"'" + ')">处理</button>' +
                      '</td>'+
                      '</tr>';
            }
            if(rs.data.data[i].status==2) {
              html += '<td>' +
                      '<a>已联系客户</a>'+
                      '</td>'+
                      '</tr>';
            }

          }
          $("#contentData").html(html);
        }
      };
      page.init(rs.data.count,cur,options);
    })
  }

  function updateState(id){
    $.ajax({
      type : "post",
      url : "/binding/update",
      data : {"id":id},
      dataType : "json",
      async : false,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("修改成功！");
          window.location.href="/binding/page";
        }
      }
    });
  }

  function updateImgUrl(id,Url,imgUrl){
    window.location.href = '/homeGoods/toUpdate?id='+id+"&imgUrl="+imgUrl+"&Url="+Url;
  }

</script>