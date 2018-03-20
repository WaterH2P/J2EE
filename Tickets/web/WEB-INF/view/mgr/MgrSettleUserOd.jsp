
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
            <a href="MgrStatistics">数据统计</a>
            <b>·</b>
            <a class="active" href="MgrSettleUserOd">订单结算</a>
            <b>·</b>
            <a href="MgrSetVIPDiscount">VIP 优惠</a>
            <b>·</b>
            <a href="MgrExamineVenueRegister">审批注册</a>
            <b>·</b>
            <a href="MgrExamineVenueInfoChange">审批修改信息</a>
            <b>·</b>
            <a href="MgrInfo">个人信息</a>
        </div>

        <div id="userOd_show_div">

        </div>

    </div>
</div>
<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script src="../../javascript/deleteSpace.js"></script>
<script src="../../javascript/date-format.js"></script>
<script>
    var dateFormat = "yyyy-MM-dd hh:mm:ss";
    $(function () {
        $.post("GetAllUserOdIsCheckIsNotSettled", function (rs) {
            var res = $.parseJSON(rs);
            $("#userOd_show_div").empty();
            $.each(res, function (index, value, array) {
                var infoDiv = "<div id='userOd_" + value.odID + "_show_div'>" +
                    "<p><label>订单ID：</label><input type='text' value='" + value.odID + "' readonly/></p>" +
                    "<p><label>计划ID：</label><input type='text' value='" + value.planID + "' readonly/></p>" +
                    "<p><label>下订单时间：</label><input type='text' value='" + new Date(value.makeTime).format(dateFormat) + "' readonly/></p>" +
                    "<p><label>购票数：</label><input type='text' value='" + value.numOfTicket + "' readonly></p>" +
                    "<p><label>实付：</label><input type='text' value='" + value.totalPay + "' readonly></p>";
                if( value.paid ){
                    infoDiv += "<p><label>订单：</label><input type='text' value='已付款' readonly></p>";
                }
                else {
                    infoDiv += "<p><label>订单：</label><input type='text' value='未付款' readonly></p>";
                }
                if( value.checked ){
                    infoDiv += "<p><label>检票：</label><input type='text' value='已检票' readonly></p>";
                }
                else {
                    infoDiv += "<p><label>检票：</label><input type='text' value='未检票' readonly></p>";
                }
                if( value.timeout ){
                    infoDiv += "<p><input type='text' value='订单超时' readonly></p>";
                }
                if( value.deleted ){
                    infoDiv += "<p><input type='text' value='订单退票' readonly></p>";
                }

                infoDiv += "<p><label>结算比例：</label><input type='text' id='settlePer_" + value.odID + "_input' /></p>" +
                    "<button id='btn_" + value.odID + "_settle' onclick='settleUserOd(this)'>结算</button>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#userOd_show_div").append(infoDiv);
            });
        })
    });
</script>
<script>
    function settleUserOd(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var OdID = temp2[1];

        var percent = $("#settlePer_" + OdID + "_input").val().toString();
        percent = deleteSpace(percent);
        var perReg = /^[1-9][0-9]{0,1}$/;
        if( perReg.test(percent) ){
            var data = {"OdID":OdID};
            $.post("SettleUserOd", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    alert("结算成功！")
                    $("#userOd_" + OdID + "_show_div").remove();
                }
            });
        }
        else {
            alert("请输入正确比例！")
        }
    }
</script>
</body>
</html>
