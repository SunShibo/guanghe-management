<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>修改私募产品</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <style>

        .column{
            margin: 0px;
            padding-top: 15px !important;
        }
        .col-xs-1{
            font-size: 15px;
        }
        .column input {
            width: 260px;
            height: 30px;
        }
        .column select {
            width: 260px;
            height: 30px;
        }
        .tab {
            background: #fafafa;
            color: #333;
            font-size: 14px;
            border: 1px solid #ccc;

            height: 40px;
            width: 88px;
            margin-right: 2px;
        }

        .tab.act {
            color: #D3A359;
            background: #fff;
            height: 40px;
        }
        .hide{
            display: none;
        }
    </style>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../index.jsp"></jsp:include>
<div class="indexRight1">
    <div class="title">私募投资 > 添加私募产品</div>
    <%--<div class="screen clear">--%>
    <%--<div class="form">--%>
    <%--<input type="text" placeholder="请输入产品名称" id="privateInvestmentName">--%>
    <%--<button type="button" class="btn btn-primary" onclick="getdata(1,10);">搜索</button>--%>
    <%--<button type="button" class="btn btn-primary" onclick="addPrivateInvestment();">新增</button>--%>
    <%--</div>--%>

    <%--</div>--%>

    <div class="row clearfix" >
        <div class="col-xs-1 column text-right">
            基金名称:
        </div>
        <div class="col-xs-3 column">
            <input id="fundName" />
        </div>
        <div class="col-xs-1 column text-right">
            基金类型:
        </div>
        <div class="col-xs-3 column">
            <input id="fundType" />
        </div>
        <div class="col-xs-1 column text-right">
            投资起点:
        </div>
        <div class="col-xs-3 column">
            <select id="investmentPoinId">
                <option value="">-请选择-</option>
            </select>
        </div>
    </div>

    <div class="row clearfix" >
        <div class="col-xs-1 column text-right">
            产品期限:
        </div>
        <div class="col-xs-3 column">
            <input id="productTerm" />
        </div>
        <div class="col-xs-1 column text-right">
            比较基准:
        </div>
        <div class="col-xs-3 column">
            <input id="comparisonDatum" />
        </div>

        <div class="col-xs-1 column text-right">
            产品期限:
        </div>
        <div class="col-xs-3 column">
            <select id="productTermId">
                <option value="">-请选择-</option>
            </select>
        </div>
    </div>

    <div class="row clearfix" >
        <div class="col-xs-1 column text-right">
            起投金额:
        </div>
        <div class="col-xs-3 column">
            <input id="amountOfInvestment" />
        </div>

        <div class="col-xs-1 column text-right">
            递增金额:
        </div>
        <div class="col-xs-3 column">
            <input id="increasingAmount" />
        </div>

        <div class="col-xs-1 column text-right">
            风险级别:
        </div>
        <div class="col-xs-3 column">
            <select id="riskLevelId">
                <option value="">-请选择-</option>
            </select>
        </div>
    </div>

    <div class="row clearfix" >
        <div class="col-xs-1 column text-right">
            产品规模(最少):
        </div>
        <div class="col-xs-3 column">
            <input id="productScaleStart" />
        </div>

        <div class="col-xs-1 column text-right">
            产品规模(最多):
        </div>
        <div class="col-xs-3 column">
            <input id="productScaleEnd" />
        </div>
        <div class="col-xs-1 column text-right">
            收益类型:
        </div>
        <div class="col-xs-3 column">
            <select id="incomeTypeId">
                <option value="">-请选择-</option>
            </select>
        </div>
    </div>

    <div class="row clearfix" >
        <div class="col-xs-1 column text-right">
            开始时间:
        </div>
        <div class="col-xs-3 column">
            <input id="startTime" type="date"/>
        </div>
        <div class="col-xs-1 column text-right">
            结束时间:
        </div>
        <div class="col-xs-7 column">
            <input id="endTime" type="date"/>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-xs-1 column text-right">
            产品详情图pc:
        </div>
        <div class="col-xs-3 column">
            <div class="suolue">
                <div class="uploadimg">
                    <img width="160px;" height="160px;" id="uploadImage">
                    <input type="hidden" name="imageUrl"><br/>
                </div>
            </div>
        </div>
        <div class="col-xs-1 column">
            产品详情图wap:
        </div>
        <div class="col-xs-3 column text-right">
            <div class="suolue">
                <div class="uploadimg">
                    <img width="160px;" height="160px;" id="uploadImagewap">
                    <input type="hidden" name="imageUrlwap"><br/>
                </div>
            </div>
        </div>


    </div>

    <div class="row clearfix" style="margin-top: 20px;">
        <div class="col-xs-1 column">
            请选择类型:
        </div>
        <div class="col-xs-3 column">
            <button data-type="1" class="tab guquann">股权</button>
            <button data-type="2" class="tab zhengquann">证券</button>
            <button data-type="3" class="tab piaojuu">票据</button>
        </div>
    </div>
    <div class="row clearfix guquan _sele_ hide" >
        <div class="col-xs-1 column text-right">
            开放日:
        </div>
        <div class="col-xs-3 column">
            <input id="openDay1" />
        </div>

        <div class="col-xs-1 column text-right">
            退出期:
        </div>
        <div class="col-xs-3 column">
            <input id="exitday1" />
        </div>
        <div class="col-xs-1 column text-right">
            退出延长期:
        </div>
        <div class="col-xs-3 column">
            <input id="extendedday1" />
        </div>
    </div>
    <div class="row clearfix zhengquan _sele_ hide" >
        <div class="col-xs-1 column text-right">
            开放日:
        </div>
        <div class="col-xs-3 column">
            <input id="openDay2" />
        </div>
        <div class="col-xs-1 column text-right">
            封闭结束日:
        </div>
        <div class="col-xs-3 column">
            <input id="closeTime2" type="date" />
        </div>


    </div>
    <div class="row clearfix piaoju _sele_ hide" >
        <div class="col-xs-1 column text-right">
            年化收益率:
        </div>
        <div class="col-xs-3 column">
            <input id="earnings3" />
        </div>

        <div class="col-xs-1 column text-right">
            预存期限:
        </div>
        <div class="col-xs-3 column">
            <input id="prestorelimit3" />
        </div>

    </div>

    <div class="row clearfix" style="margin-top: 30px;">
        <div class="col-xs-10 column text-right">
            <button  style="width:85%;" type="button" class="btn btn-primary" id="B_submit">修改</button>
        </div>
    </div>
    <%--<div style="height: 28px; width: 360px; margin: 0 auto;">--%>
    <%--<ul class="page" id="page"></ul>--%>
    <%--</div>--%>
</div>
</body>


<script>
    $(function (){
        // 添加菜单样式
        $("div[id^='menu_']").removeClass("on");
        $("div[id='menu_banner']").addClass("on");

        $("#B_submit").click(addPrivateInvestment);
        uploadImageUrl();
        uploadwapImageUrl()
    })

    function addPrivateInvestment(){

        var privateInvestmentId =  getParam("id");
        var fundName = $("#fundName").val();
        if(fundName == ""){
            alert("基金名称不能为空！");
            return;
        }
        if(fundName.length > 100){
            alert("基金名称输入过长！")
            return;
        }

        var fundType = $("#fundType").val();
        if(fundType == ""){
            alert("基金类型不能为空！");
            return;
        }
        if(fundType.length > 100){
            alert("基金类型输入过长！")
            return;
        }
        var productTerm = $("#productTerm").val();
        if(productTerm == ""){
            alert("产品期限不能为空！");
            return;
        }
        if(productTerm.length > 100){
            alert("产品期限输入过长！")
            return;
        }
        var comparisonDatum = $("#comparisonDatum").val();
        if(comparisonDatum == ""){
            alert("比较基准不能为空！");
            return;
        }
        if(comparisonDatum.length > 100){
            alert("比较基准输入过长！")
            return;
        }
        var amountOfInvestment = $("#amountOfInvestment").val();
        if(amountOfInvestment == ""){
            alert("起投金额不能为空！");
            return;
        }
        if(amountOfInvestment.length > 20){
            alert("起投金额输入过长！")
            return;
        }
        var r = /^\+?[1-9][0-9]*$/;　　//正整数
        if(!r.test(amountOfInvestment)){
            alert("起投金额输入正整数！")
            return;
        }


        var increasingAmount = $("#increasingAmount").val();
        if(increasingAmount == ""){
            alert("递增金额不能为空！");
            return;
        }
        if(increasingAmount.length > 20){
            alert("递增金额输入过长！")
            return;
        }
        if(!r.test(increasingAmount)){
            alert("递增金额输入正整数！")
            return;
        }
        var productScaleStart = $("#productScaleStart").val();
        if(productScaleStart == ""){
            alert("产品规模起点不能为空！");
            return;
        }
        if(productScaleStart.length > 20){
            alert("产品规模起点输入过长！")
            return;
        }
        if(!r.test(productScaleStart)){
            alert("产品规模起点输入正整数！")
            return;
        }

        var productScaleEnd = $("#productScaleEnd").val();
        if(productScaleEnd == ""){
            alert("产品规模结束不能为空！");
            return;
        }
        if(productScaleEnd.length > 20){
            alert("产品规模结束输入过长！")
            return;
        }
        if(!r.test(productScaleEnd)){
            alert("产品规模结束输入正整数！")
            return;
        }
        var startTime = $("#startTime").val();
        if(startTime == ""){
            alert("开始时间不能为空！");
            return;
        }

        var endTime = $("#endTime").val();
        if(endTime == ""){
            alert("结束时间不能为空！");
            return;
        }
        var imageUrl = $("input[name='imageUrl']").val();
        if (imageUrl == "") {
            alert("请选择pc图片");
            return;
        }
        var wapImage = $("input[name='imageUrlwap']").val();
        if (wapImage == "") {
            alert("请选择wap图片");
            return;
        }
        var investmentPoinIds = $("#investmentPoinId option:selected");
        var investmentPoinId = investmentPoinIds.val();
        if(investmentPoinId == ""){
            alert("请选择投资起点！");
            return;
        }

        var productTermIds = $("#productTermId option:selected");
        var productTermId = productTermIds.val();
        if(productTermId == ""){
            alert("请选择产品期限！");
            return;
        }

        var riskLevelIds = $("#riskLevelId option:selected");
        var riskLevelId = riskLevelIds.val();
        if(riskLevelId == ""){
            alert("请选择风险级别！");
            return;
        }

        var incomeTypeIds = $("#incomeTypeId option:selected");
        var incomeTypeId = incomeTypeIds.val();
        if(incomeTypeId == ""){
            alert("请选择收益类型！");
            return;
        }
        var pd = {
            "id":privateInvestmentId,
            "fundName" : fundName,
            "fundType":fundType,
            "productTerm":productTerm,
            "comparisonDatum":comparisonDatum,
            "amountOfInvestment":amountOfInvestment,
            "startTime":new Date(startTime),
            "endTime":new Date(endTime),
            "increasingAmount":increasingAmount,
            "productScaleStart":productScaleStart,
            "productScaleEnd":productScaleEnd,
            "imgUrl":imageUrl,
            "incomeTypeId":incomeTypeId,
            "riskLevelId":riskLevelId,
            "productTermId":productTermId,
            "investmentPoinId":investmentPoinId,
            "openDay":document.getElementById("openDay1").value,
            "exitday":document.getElementById("exitday1").value,
            "extendedday":document.getElementById("extendedday1").value,
            "closeTime":new Date(document.getElementById("closeTime2").value),
            "earnings":document.getElementById("earnings3").value,
            "prestorelimit":document.getElementById("prestorelimit3").value,
            "wapImage":wapImage};
        var _t = $(".act").data("type");
        if(_t==1){
            delete pd.closeTime;
            delete pd.earnings;
            delete pd.prestorelimit;
            pd.productType = 1;
        }else if(_t==2){
            pd.openDay = document.getElementById("openDay2").value;
            delete pd.exitday;
            delete pd.extendedday;
            delete pd.earnings;
            delete pd.prestorelimit;
            pd.productType = 2;
        }else{
            delete pd.openDay;
            delete pd.exitday;
            delete pd.extendedday;
            delete pd.closeTime;
            pd.productType = 3;
        }
        $.ajax({
            type : "post",
            url : "/privateInvestment/update",
            data : pd,
            dataType : "json",
            async : false,
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("修改成功！");
                    window.location.href="/privateInvestment/page";
                }
            }
        });
    }


    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }


    function uploadImageUrl(){
        var button = $("#uploadImage"), interval;
        new AjaxUpload(button, {
            action: "/privateConsultant/uploadImage",
            type:"post",
            name: 'myFile',
            responseType : 'json',
            onSubmit: function(file, ext) {
                if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert("您上传的图片格式不对，请重新选择！");
                    return false;
                }
            },
            onComplete: function(file, response) {
                if(response.success == true){
                    var resultData = response.data;
                    console.log(resultData)
                    $("#uploadImage").attr("src", resultData.Url);
                    $("input[name='imageUrl']").val(resultData.imageUrl);

                }else{
                    alert("上传失败");
                }
            }
        });
    }

    function uploadwapImageUrl(){
        var button = $("#uploadImagewap"), interval;
        new AjaxUpload(button, {
            action: "/privateConsultant/uploadImage",
            type:"post",
            name: 'myFile',
            responseType : 'json',
            onSubmit: function(file, ext) {
                if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert("您上传的图片格式不对，请重新选择！");
                    return false;
                }
            },
            onComplete: function(file, response) {
                if(response.success == true){
                    var resultData = response.data;
                    console.log(resultData)
                    $("#uploadImagewap").attr("src", resultData.Url);
                    $("input[name='imageUrlwap']").val(resultData.imageUrl);
                }else{
                    alert("上传失败");
                }
            }
        });
    }
    $(function(){

        var privateInvestmentId =  getParam("id");

        var investmentPoinId;
        var productTermId;
        var riskLevelId;
        var incomeTypeId;

        $.ajax({
            type : "post",
            url : "/privateInvestment/detail",
            data : {"id" : privateInvestmentId},
            dataType : "json",
            success : function (data){
                console.log(data);
                if(data.success == false){
                    alert(data.data.errMsg);
                    return;
                }else{
                    $("#fundName").val(data.data.fundName);
                    $("#fundType").val(data.data.fundType);
                    $("#productTerm").val(data.data.productTerm);
                    $("#comparisonDatum").val(data.data.comparisonDatum);
                    $("#amountOfInvestment").val(data.data.amountOfInvestment);
                    $("#increasingAmount").val(data.data.increasingAmount);
                    $("#productScaleStart").val(data.data.productScaleStart);
                    $("#productScaleEnd").val(data.data.productScaleEnd);
                    var startTime = data.data.startTime;
                    var endTime = data.data.endTime;
                    $("#startTime").val(DateToLStr(new Date(startTime.time)));
                    $("#endTime").val(DateToLStr(new Date(endTime.time)));
                    investmentPoinId = data.data.investmentPoinId;
                    productTermId = data.data.productTermId;
                    riskLevelId = data.data.riskLevelId;
                    incomeTypeId = data.data.incomeTypeId;
                    getInvestmentPoin(investmentPoinId);
                    getProductTerm(productTermId);
                    getRiskLevel(riskLevelId);
                    getIncomeType(incomeTypeId);
                    $("#uploadImage").attr("src",'${Url}'+data.data.imgUrl);
                    $("#uploadImagewap").attr("src",'${Url}'+data.data.wapImage);
                    $("input[name='imageUrl']").val(data.data.imgUrl);
                    $("input[name='imageUrlwap']").val(data.data.wapImage);
                    var _t = data.data.productType;
                    if(_t==1){

                        $(".guquann").addClass("act");
                        $(".guquan").removeClass("hide");
                        $("#openDay1").val(data.data.openDay)
                        $("#exitday1").val(data.data.exitday)
                        $("#extendedday1").val(data.data.extendedday)
                    }else if(_t==2){

                        $(".zhengquann").addClass("act");
                        $(".zhengquan").removeClass("hide");
                        $("#closeTime2").val(DateToLStr(new Date(data.data.closeTime.time)));
                        $("#openDay2").val(data.data.openDay)
                    }else{

                        $(".piaojuu").addClass("act");
                        $(".piaoju").removeClass("hide");
                        $("#prestorelimit3").val(data.data.prestorelimit)
                        $("#earnings3").val(data.data.earnings)
                    }
                }
            }
        });

    });

    function getInvestmentPoin(investmentPoinId){
        $.ajax({
            type : "post",
            url : "/investmentPoin/list",
            data : {},
            dataType : "json",
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#investmentPoinId").append(html);

                var investmentPoinIds = $("#investmentPoinId").find("option"); //获取select下拉框的所有值
                for (var j = 1; j < investmentPoinIds.length; j++) {
                    if ($(investmentPoinIds[j]).val() == investmentPoinId) {
                        $(investmentPoinIds[j]).attr("selected", "selected");
                    }
                }
            }
        });
    }

    function getProductTerm(productTermId){
        $.ajax({
            type : "post",
            url : "/productTerm/list",
            data : {},
            dataType : "json",
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#productTermId").append(html);
                var productTermIds = $("#productTermId").find("option"); //获取select下拉框的所有值
                for (var j = 1; j < productTermIds.length; j++) {
                    if ($(productTermIds[j]).val() == productTermId) {
                        $(productTermIds[j]).attr("selected", "selected");
                    }
                }
            }
        });
    }

    function getRiskLevel(riskLevelId){
        $.ajax({
            type : "post",
            url : "/riskLevel/list",
            data : {},
            dataType : "json",
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#riskLevelId").append(html);

                $("#riskLevelId").append(html);
                var riskLevelIds = $("#riskLevelId").find("option"); //获取select下拉框的所有值
                for (var j = 1; j < riskLevelIds.length; j++) {
                    if ($(riskLevelIds[j]).val() == riskLevelId) {
                        $(riskLevelIds[j]).attr("selected", "selected");
                    }
                }
            }
        });
    }

    function getIncomeType(incomeTypeId){
        $.ajax({
            type : "post",
            url : "/incomeType/list",
            data : {},
            dataType : "json",
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#incomeTypeId").append(html);
                var incomeTypeIds = $("#incomeTypeId").find("option"); //获取select下拉框的所有值
                for (var j = 1; j < incomeTypeIds.length; j++) {
                    if ($(incomeTypeIds[j]).val() == incomeTypeId) {
                        $(incomeTypeIds[j]).attr("selected", "selected");
                    }
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
    $tab = $(".tab");
    $tab.on("click", function() {
        $tab.removeClass("act");
        $(this).addClass("act");
        var type = $(this).data("type");
        $("._sele_").addClass("hide")
        if(type==1){
            $(".guquan").removeClass("hide")
        }else if(type==2){
            $(".zhengquan").removeClass("hide")
        }else{
            $(".piaoju").removeClass("hide")
        }
    })
</script>
</html>