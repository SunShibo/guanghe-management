<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/9/11
  Time: 14:04
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
  <script type="text/javascript">
    var orderMaster = {
      "status" : '${state}',
    };
  </script>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">订单管理 > 订单列表</div>
    <form id="orderForm" action="list" method="post">
      <div class="screen clear">
        <div class="form">
          <input type="text" placeholder="订单号" name="orderInfo" value="${orderInfo}">
          <a href="javascript:void(0);" id="B_query">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
          <div class="select">
            <div>
              <select name="status">
                <option value="">--全部--</option>
                <option value="0">未受理</option>
                <option value="1">已受理</option>
                <option value="2">出库成功</option>
                <option value="3">出库失败</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="tablebox1">
        <table cellpadding="0" cellspacing="0" border="0">
          <tr class="tr">
            <td>订单号</td>
            <td>消费金额</td>
            <td>支付时间</td>
            <td>状态</td>
            <td>操作</td>
          </tr>
          <c:forEach items="${lstOrder}" var="order">
            <tr>
              <td>${order.orderId}</td>
              <td>${order.count}</td>
              <td>
                <fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              </td>
              <td>
              <label id="status_${order.dealstatus}">
                <c:if test="${order.dealstatus == 0}">未受理</c:if>
                <c:if test="${order.dealstatus == 1}">已受理</c:if>
                <c:if test="${order.dealstatus == 2}">出库成功</c:if>
                <c:if test="${order.dealstatus == 3}">出库失败</c:if>
              </label>
            </td>
              <td>
                <a href="javascript:void(0);" name="A_query_${order.orderId}"
                   data-ordernum="${order.orderId}">查看商品信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0);" name="B_query_${order.orderId}"
                data-ordernum="${order.orderId}">查看用户信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${order.dealstatus == 0}">
                  <label id="update_${order.orderId}">
                    <a href="javascript:void(0);" name="c_reason_${order.orderId}"
                       data-ordernum="${order.orderId}" onclick="a(this)">受理</a>

                    <a href="javascript:void(0);" name="d_reason_${order.orderId}"
                       data-ordernum="${order.orderId}" onclick="b(this)">退款</a>
                  </label>
                </c:if>
                <c:if test="${order.dealstatus == 1}">
                  <label id="update_${order.orderId}">
                    <a href="javascript:void(0);" name="e_reason_${order.orderId}"
                       data-ordernum="${order.orderId}" onclick="c(this)">录入物流信息</a>
                  </label>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
      <div class="page clear">
        <div class="pages">
          <jsp:include page="../common/pager.jsp">
            <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
            <jsp:param value="list" name="url"/>
          </jsp:include>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
</div>
<script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/common/distpicker.js"></script>
<script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
<script type="text/javascript" src="/static/manage/order_master_list.js"></script>
</body>
</html>
<script>
  function a(ordernum) {
    var a = $(ordernum).data("ordernum")
    var status = 1;
    $.ajax({
      type: "post",
      url: "/order/updatestatus",
      data: {"orderId": a, "status": status},
      dataType: "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("修改成功！");
          window.location.href="/order/list";
        }
      }
    })
  }
  function b(ordernum) {
    var a = $(ordernum).data("ordernum")
    var status = 3;
    $.ajax({
      type: "post",
      url: "/order/updatestatus",
      data: {"orderId": a, "status": status},
      dataType: "json",
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        }else{
          alert("修改成功！");
          window.location.href="/order/list";
        }
      }
    })
  }

</script>
