<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>添加私募产品</title>
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

    </style>

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
                运作方式:
            </div>
            <div class="col-xs-3 column">
                <input id="operations" />
            </div>
            <div class="col-xs-1 column text-right">
                基金管理人:
            </div>
            <div class="col-xs-3 column">
                <input id="fundManager" />
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
                基金托管人:
            </div>
            <div class="col-xs-3 column">
                <input id="fundCustodian" />
            </div>

            <div class="col-xs-1 column text-right">
                产品规模:
            </div>
            <div class="col-xs-3 column">
                <input id="productScale" />
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
                认购起点:
            </div>
            <div class="col-xs-3 column">
                <input id="subscribeStartingPoint" />
            </div>

            <div class="col-xs-1 column text-right">
                产品期限:
            </div>
            <div class="col-xs-3 column">
                <input id="productTerm" />
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
                基金投向:
            </div>
            <div class="col-xs-3 column">
                <input id="fundInvestment" />
            </div>

            <div class="col-xs-1 column text-right">
                比较基准:
            </div>
            <div class="col-xs-7 column">
                <input id="comparisonDatum" />
            </div>
        </div>

        <div class="row clearfix" >
            <div class="col-xs-1 column text-right">
                业绩报酬:
            </div>
            <div class="col-xs-3 column">
                <input id="performanceReward" />
            </div>

            <div class="col-xs-1 column text-right">
                资金费用:
            </div>
            <div class="col-xs-7 column">
                <input id="capitalCost" />
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
            <div class="col-xs-7 column">
                <input id="increasingAmount" />
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
            <div class="col-xs-7 column">
                <input id="productScaleEnd" />
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



        <div class="row clearfix" style="margin-top: 30px;">
            <div class="col-xs-10 column text-right">
                <button  style="width:85%;" type="button" class="btn btn-primary" onclick="addPrivateInvestment();">添加</button>
            </div>
        </div>
        <%--<div style="height: 28px; width: 360px; margin: 0 auto;">--%>
            <%--<ul class="page" id="page"></ul>--%>
        <%--</div>--%>
    </div>
</body>


<script>

    function addPrivateInvestment(){


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

        var operations = $("#operations").val();
        if(operations == ""){
            alert("运作方式不能为空！");
            return;
        }
        if(operations.length > 100){
            alert("运作方式输入过长！")
            return;
        }

        var fundManager = $("#fundManager").val();
        if(fundManager == ""){
            alert("基金管理人不能为空！");
            return;
        }
        if(fundManager.length > 100){
            alert("基金管理人输入过长！")
            return;
        }

        var fundCustodian = $("#fundCustodian").val();
        if(fundCustodian == ""){
            alert("基金托管人不能为空！");
            return;
        }
        if(fundCustodian.length > 100){
            alert("基金托管人输入过长！")
            return;
        }


        var productScale = $("#productScale").val();
        if(productScale == ""){
            alert("产品规模不能为空！");
            return;
        }
        if(productScale.length > 100){
            alert("产品规模输入过长！")
            return;
        }

        var subscribeStartingPoint = $("#subscribeStartingPoint").val();
        if(subscribeStartingPoint == ""){
            alert("认购起点不能为空！");
            return;
        }
        if(subscribeStartingPoint.length > 100){
            alert("认购起点输入过长！")
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

        var fundInvestment = $("#fundInvestment").val();
        if(fundInvestment == ""){
            alert("基金投向不能为空！");
            return;
        }

        if(fundInvestment.length > 100){
            alert("基金投向输入过长！")
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

        var performanceReward = $("#performanceReward").val();
        if(performanceReward == ""){
            alert("业绩报酬不能为空！");
            return;
        }
        if(performanceReward.length > 100){
            alert("业绩报酬输入过长！")
            return;
        }

        var capitalCost = $("#capitalCost").val();
        if(capitalCost == ""){
            alert("资金费用不能为空！")
            return;
        }
        if(capitalCost.length > 100){
            alert("资金费用输入过长！")
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

        var investmentPoinIds = $("#investmentPoinId option:selected");
        var investmentPoinId = investmentPoinIds.val();
        alert(investmentPoinId);
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

        $.ajax({
            type : "post",
            url : "/privateInvestment/add",
            data : {"fundName" : fundName,"fundType":fundType,"operations":operations,
                "fundManager" : fundManager,"fundCustodian":fundCustodian,"productScale":productScale,
                "subscribeStartingPoint" : subscribeStartingPoint,"productTerm":productTerm,
                "fundInvestment":fundInvestment,"comparisonDatum":comparisonDatum,
                "performanceReward":performanceReward,"amountOfInvestment":amountOfInvestment,
                "increasingAmount":increasingAmount,"productScaleStart":productScaleStart,
                "productScaleEnd":productScaleEnd,"startTime":startTime,"endTime":endTime,"capitalCost" : capitalCost,
                "incomeTypeId":incomeTypeId,"riskLevelId":riskLevelId,"productTermId":productTermId,"investmentPoinId":investmentPoinId},
            dataType : "json",
            async : false,
            success : function (data){
                if(data.success == false){
                    alert(data.errMsg);
                    return;
                }else{
                    alert("添加成功！");
                    window.location.href="/privateInvestment/page";
                }
            }
        });
    }


    $(function(){
        getInvestmentPoin();
        getProductTerm();
        getRiskLevel();
        getIncomeType();
    });

    function getInvestmentPoin(){
        $.ajax({
            type : "post",
            url : "/investmentPoin/list",
            data : {},
            dataType : "json",
            async : false,
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#investmentPoinId").append(html);
            }
        });
    }

    function getProductTerm(){
        $.ajax({
            type : "post",
            url : "/productTerm/list",
            data : {},
            dataType : "json",
            async : false,
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#productTermId").append(html);
            }
        });
    }

    function getRiskLevel(){
        $.ajax({
            type : "post",
            url : "/riskLevel/list",
            data : {},
            dataType : "json",
            async : false,
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#riskLevelId").append(html);
            }
        });
    }

    function getIncomeType(){
        $.ajax({
            type : "post",
            url : "/incomeType/list",
            data : {},
            dataType : "json",
            async : false,
            success : function (data){
                var html = '';
                for(var i=0;i<data.data.count;i++){
                    html += '<option value="'+ data.data.data[i].id +'">'+ data.data.data[i].name +'</option>';
                }

                $("#incomeTypeId").append(html);
            }
        });
    }
</script>
</html>