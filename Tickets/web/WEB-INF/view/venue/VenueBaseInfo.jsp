
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.VenueBaseInfo" %>
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
            <a href="/VenueHallPage">场厅管理</a>
            <b>·</b>
            <a href="/VenuePlanManage">计划管理</a>
            <b>·</b>
            <a class="active">个人信息</a>
        </div>

        <div>
            <p>
                <label>帐号 : </label>
                <input type="text" id="venueID" readonly />
            </p>
            <p>
                <label>省 : </label>
                <input type="text" id="venueProvince" readonly />
            </p>
            <p>
                <label>市 : </label>
                <input type="text" id="venueCity" readonly />
            </p>
            <p>
                <label>地址 : </label>
                <input class="info_change" type="text" id="venueAddress" readonly />
            </p>
            <p>
                <label>电话 : </label>
                <input class="info_change" type="tel" id="venueTel" readonly />
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

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script>
    var venueAddress = "";
    var venueTel = "";
    var isChanging = false;
    $(function () {
        $.post("GetVenueBaseInfo", function (rs) {
            var res = $.parseJSON(rs);
            $("#venueID").val(res.venueID);
            $("#venueProvince").val(res.province);
            $("#venueCity").val(res.city);
            $("#venueAddress").val(res.address);
            $("#venueTel").val(res.telephone);
            venueAddress = res.address;
            venueTel = res.telephone;
            isChanging = res.isChanging;
        });
    });
</script>
<script>
    $("#venueAddress").blur(function(){
        var venuseAddress = $("#venueAddress").val().toString();
        venuseAddress = deleteSpace(venuseAddress);
        $("#venueAddress").val(venuseAddress);
    });

    $("#venueTel").blur(function(){
        var venueTel = $("#venueTel").val().toString();
        venueTel = deleteSpace(venueTel);
        $("#venueTel").val(venueTel);
    });

    $("#changeInfo").click(function () {
        if( isChanging ){
            alert("上次修改信息正在审核，请审核通过后再次修改！");
        }
        else{
            $(".info_change").removeAttr("readonly");
            $(".info_change").css("border", "2px solid blue");
            $("#changeInfo").hide();
            $("#submitInfo").show();
        }
    });

    $("#submitInfo").click(function () {
        $(".info_change").attr("readonly", "readonly");
        $(".info_change").css("border", "1px solid gray");
        $("#changeInfo").show();
        $("#submitInfo").hide();

        var address = $("#venueAddress").val().toString();
        var telephone = $("#venueTel").val().toString();
        address = deleteSpace(address);
        telephone = deleteSpace(telephone);

        var infoIsChanged = false;
        if( address!==venueAddress || telephone!==venueTel ){
            infoIsChanged = true;
        }

        if( infoIsChanged ){
            var venueID = $("#venueID").val().toString();
            var province = $("#venueProvince").val().toString();
            var city = $("#venueCity").val().toString();

            var data = {"venueID":venueID, "province":province, "city":city,
                "address":address, "telephone":telephone};
            $.post("ChangeVenueBaseInfo", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    isChanging = true;
                    alert(res.message);
                }
                else {
                    alert(res.message);
                }
            });
        }
    });

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }
</script>
</body>
</html>
