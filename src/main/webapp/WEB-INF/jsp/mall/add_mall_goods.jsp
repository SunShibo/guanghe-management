<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>添加商品</title>
  <link rel="stylesheet" href="/static/layui/css/layui.css">
  <style>
    .layui-form {
      padding: 20px;
      padding-left: 250px;
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
<div class="index clear">
  <jsp:include page="../index.jsp"></jsp:include>
  <div class="indexRight1">
    <div class="title">goods管理> goods添加</div>
    <div class="tablebox2">
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
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">名称</label>
      <div class="layui-input-inline">
        <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">重量</label>
      <div class="layui-input-inline">
        <input type="number" name="weight" lay-verify="required|number" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  <hr />
  <div class="layui-upload">
    <button type="button" class="layui-btn" id="test1">上传首图</button>
    <div class="layui-upload-list">
      <img class="layui-upload-img" id="demo1">
      <p id="demoText1"></p>
      <input type="hidden" id="" />
    </div>
  </div>
  <hr />
  <div class="layui-upload">
    <button type="button" class="layui-btn" id="test3">上传商品介绍图</button>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
      预览：
      <div class="layui-upload-list" id="demo3">
        <p id="demoText3"></p>
      </div>
    </blockquote>
  </div>

  <hr />
  <div class="layui-upload">
    <button type="button" class="layui-btn" id="test2">上传商品明细图</button>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
      预览：
      <div class="layui-upload-list" id="demo2">
        <p id="demoText2"></p>
      </div>
    </blockquote>
  </div>
  <hr />
  <div id="guige">
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">规格</label>
        <div class="layui-input-inline">
          <input type="text" name="specification" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">库存</label>
        <div class="layui-input-inline">
          <input type="text" name="stock" lay-verify="required|number" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">价格</label>
        <div class="layui-input-inline">
          <input type="number" name="price" lay-verify="required|number" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">优惠价格</label>
        <div class="layui-input-inline">
          <input type="number" name="preferentialPrice" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">优惠时间</label>
        <div class="layui-input-inline">
          <input type="text" class="layui-input yhsj" name="yhsj" style="min-width: 300px;" placeholder="请选择优惠时间">
        </div>
      </div>
    </div>
  </div>

  <hr />

  <div class="layui-btn-group">
    <button class="layui-btn" type="button" onclick="add()">增加规格</button>

  </div>
  <hr />
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo" >发布</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
<script src="/static/js/jquery-2.2.0.min.js"></script>
<script src="/static/layui/layui.all.js"></script>

<script>
  var introduceImgUrl,imageList=[];

  var oneList, twoList, threeList, allList;
  var upload = layui.upload;
  var laydate = layui.laydate;
  //日期时间范围
  laydate.render({
    elem: '.yhsj',
    type: 'datetime',
    range: true
  });
  //首图上传
  var uploadInst = upload.render({
    elem: '#test1',
    field:"myFile",
    url: '/partener/uploadImage/',
    before: function(obj) {
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result) {
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    },
    done: function(res) {
      console.info(res)
      //如果上传失败
      if(!res.success) {
        return layer.msg('上传失败');
      }
      layer.msg('上传成功');
      introduceImgUrl = res.data.imageUrl;
    },
    error: function(res) {
      //演示失败状态，并实现重传
      var demoText = $('#demoText1');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function() {
        uploadInst.upload();
      });
      //	introduceImgUrl = "999";
    }
  });

  //商品介绍图片上传
  upload.render({
    elem: '#test2',
    field:"myFile",
    url: '/partener/uploadImage/',
    multiple: true,
    before: function(obj) {
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result) {
        $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
      });
    },
    done: function(res) {
      console.info(res)
      //如果上传失败
      if(!res.success) {
        return layer.msg('上传失败');
      }
      layer.msg('上传成功');
      imageList.push({imgUrl:res.data.imageUrl,status:1});
    },
    error: function() {
      //演示失败状态，并实现重传
      var demoText = $('#demoText2');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function() {
        uploadInst.upload();
      });
      //	imageList.push({ingUrl:"888",status:1});
    }
  });

  //商品详情图片上传
  upload.render({
    elem: '#test3',
    field:"myFile",
    url: '/partener/uploadImage/',
    multiple: true,
    before: function(obj) {
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result) {
        $('#demo3').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
      });
    },
    done: function(res) {
      console.info(res)
      //如果上传失败
      if(!res.success) {
        return layer.msg('上传失败');
      }
      layer.msg('上传成功');
      imageList.push({imgUrl:res.data.imageUrl,status:2});
    },
    error: function() {
      //演示失败状态，并实现重传
      var demoText = $('#demoText3');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function() {
        uploadInst.upload();
      });
      //	imageList.push({ingUrl:"777",status:2});
    }
  });

  var form = layui.form;
  getType(form); //获取一级二级分类
  //监听提交
  form.on('submit(formDemo)', function(data) {
//				layer.msg(JSON.stringify(data.field));
//				console.info(data.field)
    saveData(data.field);
    return false;
  });
  form.on('select(oneList)', function(data) {
    renderTwoList(data.value, form)
  });
  form.on('select(twoList)', function(data) {
    renderThreeList(data.value, form)
  });


  function getType(form) {
    $.ajax({
      url: "/GoodsType/detail",
      dataType: "json",
      success: function(rs) {
        allList = rs.data;
        var oneList = oneTab(rs.data);
        for(var i = 0; i < oneList.length; i++) {
          $("#oneList").append('<option value="' + oneList[i].id + '">' + oneList[i].name + '</option>')
        }
        renderTwoList(oneList[0].id, form);
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

  function remove(t){
    $(t).parent().parent().remove();
  }
  var num=0;
  function add(){

    var d =
            ['<div class="layui-form-item">',
              '<div class="layui-inline">',
              '<label class="layui-form-label">规格</label>',
              '<div class="layui-input-inline">',
              '<input type="text" name="specification'+num+'" lay-verify="required" autocomplete="off" class="layui-input">',
              '</div>',
              '</div>',
              '<div class="layui-inline">',
              '<label class="layui-form-label">库存</label>',
              '<div class="layui-input-inline">',
              '<input type="text" name="stock'+num+'" lay-verify="required|number" autocomplete="off" class="layui-input">',
              '</div>',
              '</div>	',
              '<div class="layui-inline">',
              '<label class="layui-form-label">价格</label>',
              '<div class="layui-input-inline">',
              '<input type="number" name="price'+num+'" lay-verify="required|number" autocomplete="off" class="layui-input">',
              '</div>',
              '</div>',
              '<div class="layui-inline">',
              '<label class="layui-form-label">优惠价格</label>',
              '<div class="layui-input-inline">',
              '<input type="number" name="preferentialPrice'+num+'" autocomplete="off" class="layui-input">',
              '</div>',
              '</div>',
              '<div class="layui-inline">',
              '<label class="layui-form-label">优惠时间</label>',
              '<div class="layui-input-inline">',
              '<input type="text" class="layui-input yhsj" name="yhsj'+num+'" style="min-width: 300px;" placeholder="请选择优惠时间">',
              '</div>',
              '</div>',
              '<div class="layui-inline" style="padding-left:100px">',
              '<button class="layui-btn" type="button" onclick="remove(this)">删除规格</button>',
              '</div>',
              '</div>'].join("");


    $("#guige").append(d);
    num++;
    lay('.yhsj').each(function () {
      laydate.render({
        elem: this
        , type: 'datetime'
        , range: true
      });
    });

  }


  function saveData(d){
//				debugger;
    var goods = {}
    goods.leaveId = d.leaveId;
    goods.goodsTypeId = d.goodsTypeId;
    goods.brandId = d.brandId;
    goods.name = d.name;
    goods.weight = d.weight;
    goods.introduceImgUrl = introduceImgUrl;
    var image = imageList;
    var specifications = [];
    var spec = {
      specification:d.specification,
      stock:d.stock,
      price:d.price
    };
    if(d.preferentialPrice) spec.preferentialPrice = d.preferentialPrice
    var time = str2time(d.yhsj);
    if(time){
//      spec.preferentialStartTime = new Date(time[0]).getTime();
//      spec.preferentialEndTime = new Date(time[1]).getTime();
      spec.preferentialStartTime = time[0];
      spec.preferentialEndTime = time[1];
    }
    specifications.push(spec);
    for(var j=0;j<num;j++){
      var o = {}
      for(var i in d){
        if(i==('specification'+j)){
          o.specification = d[i];
        }
        if(i==('stock'+j)){
          o.stock = d[i];
        }
        if(i==('price'+j)){
          o.price = d[i];
        }
        if(i==('preferentialPrice'+j)){
          o.preferentialPrice = d[i];
        }
        if(i==('yhsj'+j)){
          var time = str2time(d[i]);
          if(time){
            o.preferentialStartTime = new Date(time[0]).getTime();
            o.preferentialEndTime = new Date(time[1]).getTime();
          }
        }
      }
      if(!$.isEmptyObject(o))	specifications.push(o)


    }
//				debugger;
    var request = goods;
    request.goodsImg = imageList;
    request.goodsSpeciFication = specifications;
    console.info(request)
    $.ajax({
      type:"post",
      url: "/Goods/Add",
      data: {"goodsInfo" : JSON.stringify(request)},
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
          window.location.href = "/home/list";
        }
      }
    })

  }
  function str2time(str){
    if(!str)return 0;
    var arr = str.split(" - ");
    if(arr.length!=2)return 0;
    return arr;
  }
</script>
    </div>
  </div>
</div>
</div>
</body>

</html>