
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/venue/venueRegister.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <h4 class="title">
            <div id="aSign">
                <a href="/Login">登录</a>
                <b>·</b>
                <a href="/UserRegister">注册</a>
                <b>·</b>
                <a class="active" href="/VenueRegister">场馆注册</a>
            </div>
        </h4>
        <div id="div_info">
            <form role="form" id="signUpForm" accept-charset="utf-8" method="post" onsubmit="return false">
                <p>
                    <select class="selectPC" id="province" onchange="selectProvince(this)"></select>
                    <select class="selectPC" id="city"></select>
                </p>

                <p>
                    <input type="text" style="width: 250px" placeholder="具体地址"
                           id="address"/>
                </p>

                <p>
                    <input type="text" style="width: 190px" placeholder="电话"
                           id="telephone"/>
                </p>

                <p>
                    <input type="password" style="width: 190px" placeholder="密码"
                           id="userPassword"/>
                    <input type="password" style="width: 190px" placeholder="再次输入密码"
                           id="userPasswordAgain"/>
                </p>

                <!-- 注册 -->
                <input id="signUpSubmit" value="注册" type="submit" />

            </form>
            <div id="showIDDiv">
                <input class="input_prepend" style="margin-bottom: 50px;padding: 0;text-align: center;"
                       type="text" id="messageShow" value="${message}" readonly />
            </div>
        </div>
        <div id="div_result" style="display: none">

        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script src="../../javascript/venue/venueRegister.js"></script>
<script src="../../javascript/password.js"></script>
<script>
    var addressReady = false;
    $("#address").blur(function(){
        var address = $("#address").val().toString();
        address = deleteSpace(address);
        $("#address").val(address);

        if( address.length>0 ){
            $("#address").css("border","1px solid grey");
            addressReady = true;
        }
        else {
            $("#address").css("border","1px solid red");
            addressReady = false;
        }
    });

    var telephoneReady = false;
    $("#telephone").blur(function(){
        var telephone = $("#telephone").val().toString();
        telephone = deleteSpace(telephone);
        $("#telephone").val(telephone);

        if( telephone.length==11 || telephone.length==8 ){
            $("#telephone").css("border","1px solid grey");
            telephoneReady = true;
        }
        else {
            $("#telephone").css("border","1px solid red");
            telephoneReady = false;
        }
    });

    $("#signUpSubmit").click(function () {
        var message = "";
        if( !addressReady ){
            if( message.length==0 ){
                message += "请输入 ";
            }
            message += "地址！";
        }
        if( !telephoneReady ){
            if( message.length==0 ){
                message += "请输入 ";
            }
            message += "正确电话！";
        }
        if( !passwordReady ){
            if( message.length==0 ){
                message += "请输入 ";
            }
            message += "正确密码！";
        }
        if( message.length==0 ){
            var province = $("#province option:selected").text();
            var city = $("#city option:selected").text();
            var address = $("#address").val().toString();
            var telephone = $("#telephone").val().toString();
            var password = $("#userPassword").val().toString();
            address = deleteSpace(address);
            telephone = deleteSpace(telephone);
            password = deleteSpace(password);
            var data = {"province":province, "city":city, "address":address,
                "telephone":telephone, "password":password};
            $.post("VenueRegister", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result){
                    alert("你的帐号是：" + res.message + "，审批通过后可登录！");
                    var newHref = "/Login";
                    window.location.replace(newHref);
                }
            });
        }
        else {
            $("#messageShow").val(message);
            $("#messageShow").css("color", "red");
        }
    });

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }
</script>
</body>
</html>
