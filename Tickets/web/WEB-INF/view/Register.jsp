
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
                <a href="/Login">登录</a>
                <b>·</b>
                <a class="active" href="/Register">注册</a>
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
                <input class="input_prepend input_down_prepend" placeholder="请输入邮箱验证码"
                       type="text" name="verificationCode" id="verificationCode" required/>

                <!-- 获得验证码 -->
                <input class="sign_button" type="submit" id="getCodeBtn" value="获得邮箱验证码" />

                <!-- 注册 -->
                <input class="sign_button" type="submit" id="signUpSubmit" value="注册" style="display:none;" />

            </form>
            <div id="showIDDiv">
                <input class="input_prepend" style="margin-bottom: 50px;padding: 0;text-align: center;"
                       type="text" id="userIDShow" value="${message}" readonly />
            </div>
        </div>
    </div>
</div>

<script src="../javascript/jquery-3.2.1.min.js"></script>
<script>
    var passwordReady = false;

    $("#userEmail").bind("input propertychange change", function(){
        var email = $("#userEmail").val().toString();
        var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-z.])+$/;
        if( emailReg.test(email) ){
            $("#userEmail").css("border","1px solid #c8c8c8");
            $("#userEmail").css("border-bottom","none");
        }
        else {
            $("#userEmail").css("border","1px solid red");
        }
    });

    $("#userPassword").bind("input propertychange change", function(){
        var passwordAgain = $("#userPasswordAgain").val().toString();
        if( passwordAgain.length!==0 ){
            var password = $("#userPassword").val().toString();
            if( passwordAgain!==password ){
                $("#userPassword").css("border","1px solid red");
                $("#userPasswordAgain").css("border","1px solid red");
                passwordReady = false;
            }
            else {
                $("#userPassword").css("border","1px solid #c8c8c8");
                $("#userPassword").css("border-bottom","none");
                $("#userPasswordAgain").css("border","1px solid #c8c8c8");
                $("#userPasswordAgain").css("border-bottom","none");
                passwordReady = true;
            }
        }
        else {
            passwordReady = false;
        }
    });

    $("#userPasswordAgain").bind("input propertychange change", function(){
        var password = $("#userPassword").val().toString();
        var passwordAgain = $("#userPasswordAgain").val().toString();
        if( passwordAgain!==password ){
            $("#userPassword").css("border","1px solid red");
            $("#userPasswordAgain").css("border","1px solid red");
            passwordReady = false;
        }
        else {
            $("#userPassword").css("border","1px solid #c8c8c8");
            $("#userPassword").css("border-bottom","none");
            $("#userPasswordAgain").css("border","1px solid #c8c8c8");
            $("#userPasswordAgain").css("border-bottom","none");
            passwordReady = true;
        }
    });

    $("#getCodeBtn").click(function () {
        if( passwordReady ){
            var userEmail = $("#userEmail").val().toString();
            var data = { "userEmail":userEmail };
            $.post("getVerificationCode", data, function(rs){
                alert(rs);
                if( rs.result=="true" ){
                    $("#showIDDiv").val("验证码已发送");
                    $("#getCodeBtn").hide();
                    $("#signUpSubmit").show();
                }
                else {
                    $("#showIDDiv").val("验证码发送失败");
                }
            });
            // $.ajax({
            //     type : "post",
            //     url : "/getVerificationCode",
            //     data : data,
            //     success : function (rs) {
            //         if( rs.result ){
            //             $("#showIDDiv").val("验证码已发送");
            //                     $("#getCodeBtn").hide();
            //                     $("#signUpSubmit").show();
            //         }
            //         else {
            //             $("#showIDDiv").val("验证码发送失败");
            //         }
            //     },
            //     fail : function () {
            //         $("#showIDDiv").val("验证码发送失败");
            //     }
            // });
        }
        else {
            $("#showIDDiv").val("请输入正确密码!");
            $("#showIDDiv").attr("value", "请输入正确密码!");
            $("#showIDDiv").css("color", "red");
        }
    });
</script>
</body>

</html>

