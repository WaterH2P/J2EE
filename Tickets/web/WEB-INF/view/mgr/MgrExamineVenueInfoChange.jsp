
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.venue.VenueBaseInfo" %>
<%@ page import="tickets.model.venue.VenueBaseInfoChange" %>
<%@ page import="tickets.model.venue.VenueBaseInfoRedundancy" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ExamineVenueChangeInfo</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
</head>
<body>
<div class="common">
    <div class="main minMain bigMain">
        <div id="aSign" class="title">
            <a href="MgrSetVIPDiscount">VIP 优惠</a>
            <b>·</b>
            <a href="MgrExamineVenueRegister">审批注册</a>
            <b>·</b>
            <a class="active">审批修改信息</a>
            <b>·</b>
            <a href="MgrInfo">个人信息</a>
        </div>

        <div id="changeVenueInfo_div">

        </div>
    </div>
</div>
<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $.post("GetAllVenueInfoRedundancies", function (rs) {
            var res = $.parseJSON(rs);
            for( var i=0; i<res.length; i++ ){
                var venueBaseInfoRedundancy = res[i];
                var venueBaseInfo = venueBaseInfoRedundancy.venueBaseInfo;
                var venueBaseInfoChange = venueBaseInfoRedundancy.venueBaseInfoChange;
                var infoDiv = "<div id='venue_" + venueBaseInfo.venueID + "_info_div'>" +
                    "<p><label>帐号 : </label><input type='text' value='" + venueBaseInfo.venueID + "' readonly /></p>" +
                    "<p><label>省市 : </label><input type='text' value='" + venueBaseInfo.province + venueBaseInfo.city + "' readonly /></p>" +
                    "<p><label>地址 : </label><input type='text' value='" + venueBaseInfo.address + "' readonly /></p>" +
                    "<p><label>电话 : </label><input type='tel' value='" + venueBaseInfo.telephone + "' readonly /></p>" +
                    "<p><label>  ->  </label></p>" +
                    "<p><label>省市 : </label><input type='text' value='" + venueBaseInfoChange.province + venueBaseInfoChange.city + "' readonly /></p>" +
                    "<p><label>地址 : </label><input type='text' value='" + venueBaseInfoChange.address + "' readonly /></p>" +
                    "<p><label>电话 : </label><input type='tel' value='" + venueBaseInfoChange.telephone + "' readonly /></p>" +
                    "<p>" +
                        "<button id='btn_" + venueBaseInfo.venueID + "_agree' onclick='agreeVenueInfoChange(this)'>同意</button>" +
                        "<button id='btn_" + venueBaseInfo.venueID + "_disagree' onclick='disagreeVenueInfoChange()'>不同意</button>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#changeVenueInfo_div").append(infoDiv);
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
                $("#venue_" + venueID + "_info_div").remove();
            }
            else {
                alert(rs.message);
            }
        });
    }

    function disagreeVenueInfoChange() {
        alert("暂未提供此功能！");
    }
</script>
</body>
</html>
