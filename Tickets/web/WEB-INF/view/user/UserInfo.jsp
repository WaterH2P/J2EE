
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.UserInfo" %>
<html>
<head>
    <meta charset="utf-8">
    <title>UserInfo</title>
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
            </p>
            <p>
                <label>VIP : </label>
                <input type="text" value="${userInfo.vipLevel}" readonly />
            </p>
            <p>
                <label>余额 : </label>
                <input type="text" value="${userInfo.balance}" readonly />
            </p>
            <p>
                <button id="changeInfo">修改信息</button>
                <button id="submitInfo" style="display: none">提交修改</button>
            </p>
        </div>

        <br />

        <div>
            <form action="/Logout" method="post">
                <input type="submit" value="退出登录">
            </form>
        </div>
    </div>
</div>

<script src="../../javascript/jquery-3.2.1.min.js" ></script>
<script>
    $("#userName").blur(function(){
        var name = $("#userName").val().toString();
        name = deleteSpace(name);
        $("#userName").val(name);

        if( name.length>0 ){
            nameReady = true;
        }
        else {
            nameReady = false;
        }
    });

    $("#changeName").click(function () {
        $("#userName").removeAttr("readonly");
        $("#userName").css("border", "2px solid blue");
        $("#changeInfo").hide();
        $("#submitInfo").show();
    });

    $("#submitName").click(function () {
        $("#userName").attr("readonly", "readonly");
        $("#userName").css("border", "1px solid gray");
        $("#changeInfo").show();
        $("#submitInfo").hide();
        var userEmail = $("#userEmail").val().toString();
        var userName = $("#userName").val().toString();
        userName = deleteSpace(userName);
        $("#userName").val(userName);
        var data = {"userEmail":userEmail, "userName":userName};
        $.post("ChangeUserInfo", data);
    });

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }
</script>
</body>
</html>
