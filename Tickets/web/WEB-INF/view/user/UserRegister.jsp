
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/sign.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/register.css">
</head>

<body>
<div class="sign">
    <div class="main">
        <h4 class="title">
            <div id="aSign">
                <a href="/Login">登录</a>
                <b>·</b>
                <a class="active" href="/UserRegister">注册</a>
                <b>·</b>
                <a href="/VenueRegister">场馆注册</a>
            </div>
        </h4>

        <div>
            <form role="form" id="signUpForm" accept-charset="utf-8" method="post" onsubmit="return false">
                <!-- 邮箱 -->
                <input class="input_prepend input_up_prepend" placeholder="请输入邮箱"
                       type="email" name="userEmail" id="userEmail" required/>

                <!-- 登录密码输入框 -->
                <input class="input_prepend input_middle_prepend" placeholder="请输入密码"
                       type="password" name="userPassword" id="userPassword" required/>
                <input class="input_prepend input_middle_prepend" placeholder="请再次输入密码"
                       type="password" name="userPasswordAgain" id="userPasswordAgain" required/>

                <!-- 昵称 -->
                <input class="input_prepend input_middle_prepend" placeholder="请输入昵称"
                       type="text" name="userName" id="userName" required/>

                <!-- 验证码 -->
                <div class="div_code">
                    <input class="input_code" placeholder="请输入邮箱验证码"
                           type="text" name="verificationCode" id="verificationCode" required/>

                    <!-- 获得验证码 -->
                    <button class="btn_code" id="getCodeBtn">获得邮箱验证码</button>
                </div>

                <!-- 注册 -->
                <input class="sign_button" id="signUpSubmit" value="注册" type="submit" />

            </form>
            <div id="showIDDiv">
                <input class="input_prepend" style="margin-bottom: 50px;padding: 0;text-align: center;"
                       type="text" id="messageShow" value="${message}" readonly />
            </div>

        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script src="../../javascript/password.js"></script>
<script>
    var emailReady = false;
    var nameReady = false;

    $("#userEmail").blur(function(){
        var email = $("#userEmail").val().toString();
        email = deleteSpace(email);
        $("#userEmail").val(email);

        var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-z.])+$/;
        if( emailReg.test(email) ){
            $("#userEmail").css("border","1px solid #c8c8c8");
            $("#userEmail").css("border-bottom","none");
            emailReady = true;
        }
        else {
            $("#userEmail").css("border","1px solid red");
            emailReady = false;
        }
    });

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

    $("#getCodeBtn").click(function(){
        var message = "";
        if( !emailReady ){
            if( message.length==0 ){
                message += "请输入 ";
            }
            message += "正确邮箱！";
        }
        if( !nameReady ){
            if( message.length==0 ){
                message += "请输入 ";
            }
            message += "正确昵称！";
        }
        if( message.length==0 ){
            $("#messageShow").val(message);
            $("#messageShow").css("color", "black");

            var userEmail = $("#userEmail").val().toString();
            var userName = $("#userName").val().toString();
            var data = { "userEmail":userEmail, "userName":userName };
            $.post("GetVerificationCode", data, function(rs){
                var res = $.parseJSON( rs );
                if( res.result ){
                    alert(res.message);
                }
                else {
                    alert(res.message);
                }
            });
        }
        else{
            $("#messageShow").val(message);
            $("#messageShow").css("color", "red");
        }
    });

    $("#signUpSubmit").click(function(){
        if( passwordReady ){
            var userEmail = $("#userEmail").val().toString();
            var userPassword = $("#userPassword").val().toString();
            var verificationCode = $("#verificationCode").val().toString();
            userEmail = deleteSpace(userEmail);
            userPassword = deleteSpace(userPassword);
            verificationCode = deleteSpace(verificationCode);
            var data = { "userEmail":userEmail, "userPassword":userPassword, "verificationCode":verificationCode };
            $.post("UserRegister", data, function (rs) {
                var res = $.parseJSON( rs );
                if( res.result ){
                    var newHref = "/Login";
                    window.location.replace(newHref);
                }
                else {
                    $("#messageShow").val(res.message);
                    $("#messageShow").css("color", "red");
                }
            });
        }
        else {
            $("#messageShow").val("请输入正确密码!");
            $("#messageShow").css("color", "red");
        }
    });

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }
</script>
</body>

</html>

