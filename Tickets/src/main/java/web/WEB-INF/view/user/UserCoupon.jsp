
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>UserCoupon</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
</head>
<body>
<div class="common">
    <div class="main minMain bigMain">
        <div id="aSign" class="title">
            <a href="UserBuyTicket">订票</a>
            <b>·</b>
            <a href="UserOrder">订单查看</a>
            <b>·</b>
            <a class="active">优惠券</a>
            <b>·</b>
            <a href="UserInfo">个人信息</a>
        </div>

        <div id="main_div">
            <div>
                <button onclick="main_to_exchangeCoupon()">兑换优惠券</button>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            </div>
            <div id="showUserCouponInfo_div">

            </div>
        </div>

        <div id="exchangeCoupon_div" style="display: none">
            <div>
                <button onclick='exchangeCoupon_back_showUserCouponInfo()'>🔙</button>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            </div>
            <div id="showCouponExchanged_div">

            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        getAllUserCouponAndShow();
    });
</script>
<script>
    function getAllUserCouponAndShow() {
        $.post("GetAllUserCoupons", function (rs) {
            var res = $.parseJSON(rs);
            $("#showUserCouponInfo_div").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {

                    var infoDiv = "<div>" +
                        "<p>名称：<input type='text' value='" + value.name + "' readonly></p>" +
                        "<p>优惠金额：<input type='text' value='" + value.discount + "' readonly></p>" +
                        "<p>拥有数量：<input type='text' value='" + value.number + "' readonly></p>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#showUserCouponInfo_div").append(infoDiv);
                });
            }
            else {
                var infoDiv = "<p>还未拥有优惠券！</p>";
                $("#showUserCouponInfo_div").append(infoDiv);
            }
        });
    }

    function main_to_exchangeCoupon() {
        $("#main_div").hide();
        $("#exchangeCoupon_div").show();
        $("#showCouponExchanged_div").empty;
        $.post("GetAllCouponInfosExchanged", function (rs) {
            var res = $.parseJSON(rs);
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var couponID = value.couponID;
                    var infoDiv = "<div id='coupon_" + couponID + "_info_div'>" +
                        "<p>名称：<input type='text' value='" + value.name + "' readonly></p>" +
                        "<p>优惠金额：<input type='text' value='" + value.discount + "' readonly></p>" +
                        "<p>所需积分：<input type='text' value='" + value.point + "' readonly></p>" +
                        "<button id='btn_" + couponID + "_exchange' onclick='exchangeCoupon(this)' '>兑换</button>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#showCouponExchanged_div").append(infoDiv);
                });
            }
            else {
                var infoDiv = "<p>没有优惠券可以兑换！</p>";
                $("#showCouponExchanged_div").append(infoDiv);
            }
        });
    }

    function exchangeCoupon_back_showUserCouponInfo() {
        $("#main_div").show();
        $("#exchangeCoupon_div").hide();
        $("#showCouponExchanged_div").empty;
    }

    function exchangeCoupon(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var couponID = temp2[1];
        var data = {"couponID":couponID};
        $.post("ExchangeCoupon", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                alert(res.message);
                getAllUserCouponAndShow();
            }
            else {
                alert(res.message);
            }
        });
    }
</script>
</body>
</html>
