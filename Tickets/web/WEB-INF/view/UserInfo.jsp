
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.UserInfo" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Info</title>
    <link rel="stylesheet" type="text/css" href="../stylesheet/sign.css">
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
                <div>
                    <label>邮箱 : </label>
                    <input type="email" value="${userInfo.email}" readonly />
                    <button style="display: none"/>
                </div>
                <div>
                    <label>昵称 : </label>
                    <input id="nameInput" type="text" value="${userInfo.name}" readonly />
                    <button id="changeName" style="display: inline-block" onclick="changeName()">修改昵称</button>
                    <button id="submitName" style="display: none" onclick="submitName()">提交修改</button>
                </div>
                <div>
                    <label>VIP : </label>
                    <input type="text" value="${userInfo.vipLevel}" readonly />
                    <button style="display: none"/>
                </div>
                <div>
                    <label>余额 : </label>
                    <input type="text" value="${userInfo.balance}" readonly />
                    <button style="display: none"/>
                </div>
            </div>

            <div>
                <form action="/Logout" method="post">
                    <input type="submit" value="退出登录">
                </form>
            </div>
        </div>
    </div>

    <script src="../javascript/jquery-3.2.1.min.js" ></script>
    <script>
        $("#changeName").click(function () {
            $("#nameInput").removeAttr("readonly");
            $("#changeName").hide();
            $("#submitName").show();
        });
        // function changeName() {
        //     document.getElementById("nameInput").readOnly = false;
        //     document.getElementById("changeName").style.display = "none";
        //     document.getElementById("submitName").style.display = "inline-block";
        // }

        $("#submitName").click(function () {
            $("#nameInput").attr("readonly", "readonly");
            $("#changeName").show();
            $("#submitName").hide();
        });
        // function submitName() {
        //     document.getElementById("nameInput").readOnly = true;
        //     document.getElementById("changeName").style.display = "inline-block";
        //     document.getElementById("submitName").style.display = "none";
        // }
    </script>
</body>
</html>
