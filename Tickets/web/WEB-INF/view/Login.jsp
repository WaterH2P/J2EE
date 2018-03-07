
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <a href="/Register">注册</a>
                </div>
            </h4>

            <div>
                <form role="form" id="signInForm" accept-charset="utf-8" method="post" action="/Login">
                    <!-- 登录名输入框 -->
                    <input class="input_prepend input_up_prepend" placeholder="邮箱"
                           type="email" name="userEmail" id="userEmail" required/>

                    <!-- 登录密码输入框 -->
                    <input class="input_prepend input_down_prepend" placeholder="密码"
                           type="password" name="userPassword" id="userPassword" required/>

                    <!-- 登录 -->
                    <input type="submit" id="signInSubmit" name="signInSubmit" value="登录" class="sign_button" />
                </form>
                <div id="showStatusDiv">
                    <input class="input_prepend" style="margin-bottom: 50px;padding: 0;text-align: center;"
                           type="text" id="signInStatusShow" value="${message}" readonly />
                </div>
            </div>
        </div>
    </div>
</body>

</html>