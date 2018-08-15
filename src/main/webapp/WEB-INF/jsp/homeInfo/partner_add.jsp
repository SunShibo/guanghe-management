<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>banner添加</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/manage/banner_add.js"></script>
</head>
<body>
<div class="index clear">
    <jsp:include page="../index.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">合作伙伴管理> 合作伙伴添加</div>
        <div class="tablebox2">
            <form id="bannerForm" method="post">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td>图片：</td>
                        <td>
                            <div class="suolue">
                                <div class="uploadimg">
                                    <img width="160px;" height="160px;" id="uploadImage">
                                    <input type="hidden" name="imageUrl"><br/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a href="javascript:void(0);" id="B_submit">发布</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</div>
</body>
