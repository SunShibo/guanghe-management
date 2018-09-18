<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>添加规格</title>
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
    <div class="title">规格添加</div>
    <div class="tablebox2">
      <form class="layui-form" action="">
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
        var form = layui.form;
        form.on('submit(formDemo)', function(data) {
//				layer.msg(JSON.stringify(data.field));
//				console.info(data.field)
          saveData(data.field);
          return false;
        });
        var upload = layui.upload;
        var laydate = layui.laydate;

        //日期时间范围
        laydate.render({
          elem: '.yhsj',
          type: 'datetime',
          range: true
        });
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

        function GetQueryString(name) {
          var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
          var r = window.location.search.substr(1).match(reg);
          if(r!=null)return  unescape(r[2]);
          return null;
        }
        function saveData(d){
//				debugger;
          var goods = {}
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
          var  sid = GetQueryString('goodsId');
          request.goodsSpeciFication = specifications;
          console.info(request)
          $.ajax({
            type:"post",
            url: "/GoodsSpeciFication/add",
            data: {"goodsInfo" : JSON.stringify(request),"id":sid},
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