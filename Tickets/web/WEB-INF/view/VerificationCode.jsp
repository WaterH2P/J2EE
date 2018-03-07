
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="../stylesheet/sign.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <h4 class="title">
            <div id="aSign">
                <a class="active">验证码</a>
            </div>
        </h4>

        <div>
            <form role="form" id="signUpForm" accept-charset="utf-8" method="post" onsubmit="return false">
                <!-- 邮箱 -->
                <input class="input_prepend input_up_prepend" value="${userEmail}"
                       type="email" name="userEmail" id="userEmail" readonly/>

                <!-- 验证码 -->
                <input class="input_prepend input_down_prepend" placeholder="请输入邮箱验证码"
                       type="text" name="verificationCode" id="verificationCode" required/>

                <!-- 注册 -->
                <input class="sign_button" type="submit" id="signUpSubmit" value="注册" style="display:none;" />
            </form>
        </div>
    </div>
</body>
</html>
