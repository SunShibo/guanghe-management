<%--
  Created by IntelliJ IDEA.
  User: yxw
  Date: 2018/9/12
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>layui</title>
  <link rel="stylesheet" href="/static/layui/css/layui.css">
  <style>
    .layui-form {
      padding: 20px;
    }

    .layui-upload-img {
      width: 120px;
      height: 120px;
    }

    .layui-upload {
      margin-left: 60px;
    }
  </style>
</head>

<body>

<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">品牌</label>
    <div class="layui-input-inline">
      <select name="leaveId" id="oneList" lay-filter="oneList">
      </select>
    </div>
    <div class="layui-input-inline">
      <select name="goodsTypeId" id="twoList" lay-filter="twoList">
      </select>
    </div>
    <div class="layui-input-inline">
      <select name="brandId" id="threeList">
      </select>
    </div>
  </div>

  <hr />


  <hr />
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo" >发布</button>
    </div>
  </div>
</form>
<script src="/static/js/jquery-2.2.0.min.js"></script>
<script src="/static/layui/layui.all.js"></script>

<script>
  var introduceImgUrl,imageList=[];

  var oneList, twoList, threeList, allList;
  var laydate = layui.laydate;


  var form = layui.form;
  getType(form); //获取一级二级分类
  //监听提交
  form.on('submit(formDemo)', function(data) {
    saveData1(data.field);
    return false;
  });
  form.on('select(oneList)', function(data) {
    console.info(data)
    renderTwoList(data.value, form)
  });
  form.on('select(twoList)', function(data) {
    console.info(data)
    renderThreeList(data.value, form)
  });


  function getType(form) {
    $.ajax({
      url: "/GoodsType/detail",
      dataType: "json",
      success: function(rs) {
        allList = rs.data;
        getCurrData();
      }
    });
  }

  function renderTwoList(id, form) {
    var arr = twoTab(allList, id);
    $("#twoList").empty();
    for(var i = 0; i < arr.length; i++) {
      $("#twoList").append('<option value="' + arr[i].id + '">' + arr[i].name + '</option>');
    }
    form.render();
    renderThreeList(arr[0].id, form);

  }

  function renderThreeList(ids, form) {
    $.ajax({
      url: "/Brand/onclickdetail?id=" + ids + "&pid=" + document.getElementById("oneList").value,
      dataType: "json",
      success: function(rs) {
        $("#threeList").empty();
        for(var i = 0; i < rs.data.length; i++) {
          $("#threeList").append('<option data-leaveId="' + rs.data[i].leaveId + '" data-goodsTypeId="' + rs.data[i].goodsTypeId + '" value="' + rs.data[i].id + '">' + rs.data[i].name + '</option>')
        }
        form.render();
      }
    });
  }

  function oneTab(arr) {
    var newArr = [];
    for(var i = 0; i < arr.length; i++) {
      if(arr[i].pid == 0) newArr.push(arr[i]);
    }
    return newArr.sort(compare("sort"));
  }

  function twoTab(arr, pid) {
    var newArr = [];
    for(var i = 0; i < arr.length; i++) {
      if(arr[i].pid == pid) newArr.push(arr[i]);
    }
    return newArr.sort(compare("sort"));
  }
  var compare = function(prop) {
    return function(obj1, obj2) {
      var val1 = obj1[prop];
      var val2 = obj2[prop];
      if(val1 < val2) {
        return -1;
      } else if(val1 > val2) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
  }
  function getCurrData(){
    var  id = GetQueryString('id');
    $.getJSON("/Goods/queryGoodsUpdateInfo?id="+id,function(rs){
      var oneList = $("#oneList");
      var twoList = $("#twoList");
      var threeList = $("#threeList");
      var f = rs.data.first;
      var s = rs.data.second;
      var g = rs.data.goodsBo;
      var b = rs.data.brand;
      for(var i=0;i<f.length;i++){
        if(g.leaveId == f[i].id){
          oneList.append('<option selected="selected" value="'+f[i].id+'">'+f[i].name+'</option>');
          continue;
        }
        oneList.append('<option value="'+f[i].id+'">'+f[i].name+'</option>');
      }
      for (var i = 0; i < s.length; i++) {
        if(g.goodsTypeId == s[i].id){
          twoList.append('<option selected="selected" value="'+s[i].id+'">'+s[i].name+'</option>');
          continue;
        }
        twoList.append('<option value="'+s[i].id+'">'+s[i].name+'</option>');
      }
      for (var i = 0; i < b.length; i++) {
        if(g.brandId == b[i].id){
          threeList.append('<option selected="selected" value="'+b[i].id+'">'+b[i].name+'</option>');
          continue;
        }
        threeList.append('<option value="'+b[i].id+'">'+b[i].name+'</option>');
      }
      form.render();
    })
  }
  function saveData1(d){
//				debugger;

    leaveId = d.leaveId;
    goodsTypeId = d.goodsTypeId;
    brandId = d.brandId;
    id=GetQueryString('id');
    $.ajax({
      type:"post",
      url: "/Goods/updateBrand",
      data :{"leaveId" : leaveId,"goodsTypeId":goodsTypeId,"brandId":brandId,"id":id,},
      dataType: "json",
      xhrFields: {
        withCredentials: true
      },
      crossDomain: true,
      success : function (data){
        if(data.success == false){
          alert(data.errMsg);
          return;
        } else {
          // 添加成功，跳转到列表页面
          alert("添加成功");
          window.location.href = "/Goods/page";
        }
      }
    })

  }
</script>
</body>

</html>