
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.Result" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="../stylesheet/sign.css">
</head>

<body>
<div class="sign">
    <div class="main">
        <h4 class="title">
            <div id="aSign">
                <a class="active" href="/Login">登录</a>
                <b>·</b>
                <a href="/UserRegister">注册</a>
                <b>·</b>
                <a href="/VenueRegister">场馆注册</a>
            </div>
        </h4>

        <div>
            <form role="form" id="signInForm" accept-charset="utf-8" method="post" onsubmit="return false">
                <!-- 登录名输入框 -->
                <input class="input_prepend input_up_prepend" placeholder="邮箱 或 场馆ID"
                       type="text" name="EmailORID" id="EmailORID" required/>

                <!-- 登录密码输入框 -->
                <input class="input_prepend input_down_prepend" placeholder="密码"
                       type="password" name="password" id="password" required/>

                <!-- 登录 -->
                <input type="submit" id="signInSubmit" name="signInSubmit" value="登录" class="sign_button" />
            </form>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script>
    $("#signInSubmit").click(function() {
        var EmailORID = $("#EmailORID").val().toString();
        var password = $("#password").val().toString();
        var data = {"EmailORID":EmailORID, "password":password};
        $.post("Login", data, function (rs) {
            var res = $.parseJSON(rs);
            if( !res.result ){
                alert(res.message);
            }
            window.location.reload();
        });
    });
</script>
</body>
</html>