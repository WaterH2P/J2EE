
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.VenueInfo" %>
<%@ page import="tickets.model.VenueInfoChange" %>
<%@ page import="tickets.model.VenueInfoRedundancy" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ExamineVenueChangeInfo</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <div id="aSign" class="title">
            <a href="/MgrExamineVenueRegister">审批注册</a>
            <b>·</b>
            <a class="active">审批修改信息</a>
            <b>·</b>
            <a href="/MgrInfo">个人信息</a>
        </div>

        <div id="div_VenueChangeInfo">

        </div>
    </div>
</div>
<script src="../../javascript/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $.post("GetAllVenueInfoRedundancies", function (rs) {
            var res = $.parseJSON(rs);
            for( var i=0; i<res.length; i++ ){
                var venueInfoRedundancy = res[i];
                var venueInfo = venueInfoRedundancy.venueInfo;
                var venueInfoChange = venueInfoRedundancy.venueInfoChange;
                var infoDiv = "<div id='" + venueInfo.venueID + "_info_div'>" +
                    "<p><label>帐号 : </label><input type='text' value='" + venueInfo.venueID + "' readonly /></p>" +
                    "<p><label>省市 : </label><input type='text' value='" + venueInfo.province + venueInfo.city + "' readonly /></p>" +
                    "<p><label>地址 : </label><input type='text' value='" + venueInfo.address + "' readonly /></p>" +
                    "<p><label>电话 : </label><input type='tel' value='" + venueInfo.telephone + "' readonly /></p>" +
                    "<p><label>  ->  </label></p>" +
                    "<p><label>省市 : </label><input type='text' value='" + venueInfoChange.province + venueInfoChange.city + "' readonly /></p>" +
                    "<p><label>地址 : </label><input type='text' value='" + venueInfoChange.address + "' readonly /></p>" +
                    "<p><label>电话 : </label><input type='tel' value='" + venueInfoChange.telephone + "' readonly /></p>" +
                    "<p><button id='btn_" + venueInfo.venueID + "_agree' onclick='agreeVenueInfoChange(this)'>同意</button>" +
                    "<button id='btn_" + venueInfo.venueID + "_disagree' onclick='disagreeVenueInfoChange()'>不同意</button></p>" +
                    "</div>" +
                    "<br/>";
                $("#div_VenueChangeInfo").append(infoDiv);
            }
        });
    });

    function agreeVenueInfoChange(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var venueID = temp2[1];
        var data = {"venueID":venueID};
        $.post("AgreeWithVenueInfoChange", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                $("#" + venueID + "_info_div").remove();
            }
        });
    }

    function disagreeVenueInfoChange() {
        alert("暂未提供此功能！");
    }
</script>
</body>
</html>
