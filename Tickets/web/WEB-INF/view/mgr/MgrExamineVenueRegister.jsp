
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.VenueBaseInfo" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ExamineVenueRegister</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <div id="aSign" class="title">
            <a class="active">审批注册</a>
            <b>·</b>
            <a href="/MgrExamineVenueInfoChange">审批修改信息</a>
            <b>·</b>
            <a href="/MgrInfo">个人信息</a>
        </div>

        <div id="div_VenueRegister">

        </div>
    </div>
</div>
<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $.post("GetAllUnconfirmedVenues", function (rs) {
            var res = $.parseJSON(rs);
            for( var i=0; i<res.length; i++ ){
                var venueBaseInfo = res[i];
                var infoDiv = "<div id='" + venueBaseInfo.venueID + "_info_div'>" +
                    "<p><label>帐号 : </label><input type='text' value='" + venueBaseInfo.venueID + "' readonly /></p>" +
                    "<p><label>省市 : </label><input type='text' value='" + venueBaseInfo.province + venueBaseInfo.city + "' readonly /></p>" +
                    "<p><label>地址 : </label><input type='text' value='" + venueBaseInfo.address + "' readonly /></p>" +
                    "<p><label>电话 : </label><input type='tel' value='" + venueBaseInfo.telephone + "' readonly /></p>" +
                    "<p><button id='btn_" + venueBaseInfo.venueID + "_agree' onclick='agreeVenueRegister(this)'>同意</button>" +
                    "<button id='btn_" + venueBaseInfo.venueID + "_disagree' onclick='disagreeVenueRegister()'>不同意</button></p>" +
                    "</div>" +
                    "<br/>";
                $("#div_VenueRegister").append(infoDiv);
            }
        });
    });

    function agreeVenueRegister(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var venueID = temp2[1];
        var data = {"venueID":venueID};
        $.post("AgreeWithVenueRegister", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                $("#" + venueID + "_info_div").remove();
            }
        });
    }

    function disagreeVenueRegister() {
        alert("暂未提供此功能！");
    }
</script>
</body>
</html>
