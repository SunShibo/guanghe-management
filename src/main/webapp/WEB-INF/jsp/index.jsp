<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/plugins/dist/sidebar-menu.css">
    <style type="text/css">
        .home_span{
            font-size: 17px;
        }
        .main-sidebar{
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            min-height: 100%;
            width: 230px;
            z-index: 810;
            background-color: #222d32;
        }
    </style>
</head>
<body>
<aside class="main-sidebar" >
    <section  class="sidebar">
        <ul class="sidebar-menu">
            <li class="header">后台管理系统</li>
            <li class="treeview">
                <a href="/home/list">
                    <i class="fa fa-files-o"></i> <span class="home_span">首页</span>
                </a>
            </li>
            <li class="treeview">
                <a >
                    <i class="fa fa-files-o"></i>
                    <span class="home_span">走进广和</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="/CorporateHonor/page"><i class="fa fa-circle-o"></i>企业荣誉</a></li>
                    <li><a href="/CoreTeam/page"><i class="fa fa-circle-o"></i>核心团队</a></li>
                    <li><a href="/companyCultrue/page"><i class="fa fa-circle-o"></i>企业文化pc</a></li>
                    <li><a href="/companyCultrue/pagewap"><i class="fa fa-circle-o"></i>企业文化wap</a></li>
                    <li><a href="/ChairmanSpeech/page"><i class="fa fa-circle-o"></i>董事长致辞pc</a></li>
                    <li><a href="/ChairmanSpeech/wappage"><i class="fa fa-circle-o"></i>董事长致辞wap</a></li>
                    <li><a href="/CompanyIntroduction/page"><i class="fa fa-circle-o"></i>公司介绍pc</a></li>
                    <li><a href="/CompanyIntroduction/wappage"><i class="fa fa-circle-o"></i>公司介绍wap</a></li>
                    <li><a href="/BigEvent/page"><i class="fa fa-circle-o"></i>发展历程</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span class="home_span">广和新闻</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="/newsInformation/page"><i class="fa fa-circle-o"></i>新闻动态</a></li>
                    <li><a href="/industryInformation/page"><i class="fa fa-circle-o"></i>行业资讯</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span class="home_span">财富管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <%--<li><a href="/wealthManagement/page"><i class="fa fa-circle-o"></i>财富管理</a></li>--%>
                    <li><a href="/privateInvestment/page"><i class="fa fa-circle-o"></i>私慕投资</a></li>
                    <li><a href="/privateConsultant/page"><i class="fa fa-circle-o"></i>私享顾问</a></li>
                </ul>
            </li>
            <li class="treeview">
            <a href="/assetManagement/page">
                <i class="fa fa-files-o"></i>
                <span class="home_span">资产管理</span>
                <i class="fa fa-angle-left pull-right"></i>
            </a>
        </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-files-o"></i>
                <span class="home_span">广和商学院</span>
                <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu" style="display: none;">
                <li><a href="/KnowledgeTraining/page"><i class="fa fa-circle-o"></i>理财知识培养</a></li>
                <li><a href="/PolicyInterpreTation/page"><i class="fa fa-circle-o"></i>政策解读</a></li>
                <li><a href="/ExpertIectureHall/page"><i class="fa fa-circle-o"></i>专家解读</a></li>
            </ul>
        </li>

        <li class="treeview">
            <a href="#">
                <i class="fa fa-files-o"></i> <span class="home_span">会员尊享</span>
                <i class="fa fa-angle-left pull-right"></i>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="/activites/page"><i class="fa fa-circle-o"></i>会员活动</a></li>
                    <li><a href="/ClubSystem/page"><i class="fa fa-circle-o"></i>会员尊享管理</a></li>
                </ul>
            </a>
        </li>


        <li class="treeview">
            <a href="#">
                <i class="fa fa-files-o"></i>
                <span class="home_span">商品分类&品牌</span>
                <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu" style="display: none;">
                <li><a href="/GoodsType/onePage"><i class="fa fa-circle-o"></i>分类</a></li>
                <li><a href="/Brand/page"><i class="fa fa-circle-o"></i>品牌</a></li>
            </ul>
        </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span class="home_span">商品</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="/Goods/update"><i class="fa fa-circle-o"></i>添加</a></li>
                    <li><a href="/Goods/page"><i class="fa fa-circle-o"></i>修改</a></li>
                </ul>
            </li>
         <%--   <li class="treeview">
                <a href="/order/list">
                    <i class="fa fa-files-o"></i> <span class="home_span">订单管理</span>
                </a>
            </li>--%>
            <li class="treeview">
                <a href="/Account/page">
                    <i class="fa fa-files-o"></i> <span class="home_span">积分管理</span>
                </a>
            </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-files-o"></i>
                <span class="home_span">商城首页</span>
                <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu" style="display: none;">
                <li><a href="/MallBanner/page"><i class="fa fa-circle-o"></i>Banner轮播图</a></li>
                <li><a href="/RecommendImage/page"><i class="fa fa-circle-o"></i>竖图</a></li>
                <li><a href="/MallImage/page"><i class="fa fa-circle-o"></i>横图</a></li>
                <li><a href="/homeGoods/page"><i class="fa fa-circle-o"></i>首页推荐商品</a></li>
            </ul>
        </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span class="home_span">系统消息</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="/ActivityReservation/page"><i class="fa fa-circle-o"></i>预约活动</a></li>
                    <li><a href="/Subscribe/page"><i class="fa fa-circle-o"></i>预约产品</a></li>
                    <li><a href="/binding/page"><i class="fa fa-circle-o"></i>私享顾问</a></li>
                </ul>
            </li>
        </ul>
    </section>
</aside>

<script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
<script src="/static/plugins/dist/sidebar-menu.js"></script>
<script>
    $.sidebarMenu($('.sidebar-menu'))
    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }
</script>


</body>
</html>