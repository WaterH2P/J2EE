
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.UserInfo" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Info</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
</head>

<body>
    <div class="sign">
        <div class="main">
            <div id="aSign" class="title">
                <a>订票</a>
                <b>·</b>
                <a href="/OrderMain">订单查看</a>
                <b>·</b>
                <a class="active">个人信息</a>
            </div>

            <div>
                <p>
                    <label>邮箱 : </label>
                    <input type="email" id="userEmail" value="${userInfo.email}" readonly />
                </p>
                <p>
                    <label>昵称 : </label>
                    <input type="text" id="userName" value="${userInfo.name}" readonly />
                    <button id="changeName" style="display: inline-block" onclick="changeName()">修改昵称</button>
                    <button id="submitName" style="display: none" onclick="submitName()">提交修改</button>
                </p>
                <p>
                    <label>VIP : </label>
                    <input type="text" value="${userInfo.vipLevel}" readonly />
                    <button style="display: none"/>
                </p>
                <p>
                    <label>余额 : </label>
                    <input type="text" value="${userInfo.balance}" readonly />
                    <button style="display: none"/>
                </p>
            </div>

            <div>
                <form action="/Logout" method="post">
                    <input type="submit" value="退出登录">
                </form>
            </div>
        </div>
    </div>

    <script src="../../javascript/jquery-3.2.1.min.js" ></script>
    <script>
        $("#changeName").click(function () {
            $("#userName").removeAttr("readonly");
            $("#userName").css("border", "1px solid blue");
            $("#changeName").hide();
            $("#submitName").show();
        });

        $("#submitName").click(function () {
            $("#userName").attr("readonly", "readonly");
            $("#userName").css("border", "1px solid gray");
            $("#changeName").show();
            $("#submitName").hide();
            var userEmail = $("#userEmail").val().toString();
            var userName = $("#userName").val().toString();
            var data = {"userEmail":userEmail, "userName":userName};
            $.post("/UserInfo", data);
        });
    </script>
</body>
</html>
