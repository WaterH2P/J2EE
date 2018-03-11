
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MgrInfo</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <div id="aSign" class="title">
            <a href="/MgrExamineVenueRegister">审批注册</a>
            <b>·</b>
            <a href="/MgrExamineVenueInfoChange">审批修改信息</a>
            <b>·</b>
            <a class="active">个人信息</a>
        </div>

        <div>
            <form action="/Logout" method="post">
                <input type="submit" value="退出登录">
            </form>
        </div>
    </div>
</div>
</body>
</html>
