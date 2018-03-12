
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.UserInfo" %>
<html>
<head>
    <meta charset="utf-8">
    <title>UserInfo</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
</head>
<body>
<div class="common">
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
                <input type="email" id="userEmail" readonly />
            </p>
            <p>
                <label>昵称 : </label>
                <input type="text" id="userName" readonly />
            </p>
            <p>
                <label>VIP : </label>
                <input type="text" id="userVIP" readonly />
            </p>
            <p>
                <label>余额 : </label>
                <input type="text" id="userBalance" readonly />
            </p>
            <p>
                <label>积分 : </label>
                <input type="text" id="userPoint" readonly />
            </p>
            <p>
                <button id="changeInfo">修改信息</button>
                <button id="submitInfo" style="display: none">提交修改</button>
            </p>
            <p>
                <button id="deleteVIP_1">注销会员</button>
                <%--<button id="deleteVIP_2" style="display:none;">注销会员</button>--%>
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

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script>
    $(function () {
        $.post("GetUserInfo", function (rs) {
            var res = $.parseJSON(rs);
            $("#userEmail").val(res.email);
            $("#userName").val(res.name);
            $("#userVIP").val(res.vipLevel);
            $("#userBalance").val(res.balance);
            $("#userPoint").val(res.point);
        });
    });
</script>
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

    $("#deleteVIP_1").click(function () {
        var cancel = confirm("注销会员将导致该账户 下线！并且以后 无法登陆 ！无法享受VIP优惠！确认注销请点击确定。");
        if( cancel ){
            var userEmail = $("#userEmail").val().toString();
            var data = {"userEmail":userEmail};
            $.post("CancelAccountVIP", data);
        }
        // $("#deleteVIP_1").hide();
        // $("#deleteVIP_2").show();
        // setTimeout(function () {
        //     $("#deleteVIP_1").show();
        //     $("#deleteVIP_2").hide();
        // }, 4000);
    });

    // $("#deleteVIP_2").click(function () {
    //     var userEmail = $("#userEmail").val().toString();
    //     var data = {"userEmail":userEmail};
    //     $.post("CancelAccountVIP", data);
    // });
</script>
</body>
</html>
