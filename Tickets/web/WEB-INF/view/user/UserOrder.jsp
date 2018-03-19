
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Order</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/title.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/fix.css">
</head>
<body>
<div class="common">
    <div class="main bigMain">
        <div id="aSign" class="title">
            <a href="UserBuyTicket">订票</a>
            <b>·</b>
            <a class="active">订单查看</a>
            <b>·</b>
            <a href="UserCoupon">优惠券</a>
            <b>·</b>
            <a href="UserInfo">个人信息</a>
        </div>

        <div class="title_2" id="title_2">
            <a class="active" id="userOd_historical" onclick="show_userOd_historical()">历史订单</a>
            <b>·</b>
            <a id="userOd_timeout" onclick="show_userOd_timeout()">超时订单</a>
            <b>·</b>
            <a id="userOd_deleted" onclick="show_userOd_deleted()">退订订单</a>
            <b>·</b>
            <a id="userOd_future" onclick="show_userOd_future()">未来订单</a>
            <b>·</b>
            <a id="userOd_unfinished" onclick="show_userOd_unfinished()">未完成订单</a>
        </div>

        <div id="OdList_show_div">

        </div>

        <div id="OdInfo_show_div" style="display: none">
            <button onclick="OdInfo_show_div_back_OdList_show_div()">🔙</button>
            <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            <div id="Od_Info_div">
                <p><label>影片：</label><input id="Od_nameOfPlan_input" type='text' readonly/></p>
                <p><label>时间：</label></p>
                <p><input id="Od_beginTimeOfPlan_div" type='text' readonly/></p>
                <p>-></p>
                <p><input id="Od_endTimeOfPlan_div" type='text' readonly/></p>
                <p><label>座位：</label><input id="Od_seat_input" style="visibility: hidden"/></p>
                <ul id='Od_seatSelected_ul'></ul>
                <p><label>总价：</label><input id="Od_totalPrice_input" type="text" readonly /></p>
                <p><label>VIP优惠：</label><input id="Od_vipDiscount_input" type="text" readonly /></p>
                <p id="OdInfo_payImmediately_coupon_p">
                    <label>优惠券：</label>
                    <select id="Od_coupon_select" onchange="showCouponDiscountAndChangeTotalPay()"></select>
                    <input id="OdInfo_payImmediately_couponDiscount_input" type="text" readonly />
                </p>
                <p id="OdInfo_show_couponDiscount_p" style="display: none">
                    <label>优惠券优惠：</label>
                    <input id="OdInfo_show_couponDiscount_input" type="text" readonly />
                </p>
                <p><label>实付：</label><input id="Od_totalPay_input" type="text" readonly /></p>
            </div>
            <div id="Od_info_expand_div">

            </div>
        </div>

        <div id="planHallSeat_show_div" style="display: none">

        </div>

        <div id="seatMap_div" style="display:none;">
            <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            <p><label>场厅信息</label></p>
            <div id="seatMap">

            </div>
        </div>

        <div class="fixRightFar" id="venueInfo_div" style="display: none">
            <p><label>场馆信息</label></p>
            <p><label>省：</label><input type="text" id="venueProvince" readonly /></p>
            <p><label>市：</label><input type="text" id="venueCity" readonly /></p>
            <p><label>地址：</label><input class="info_change" type="text" id="venueAddress" readonly /></p>
            <p><label>电话：</label><input class="info_change" type="tel" id="venueTel" readonly /></p>
        </div>
    </div>
</div>

<script>
    var dateFormat = "yyyy-MM-dd hh:mm:ss";
    var OdInfos = [];
    var totalPrice = 0.0;
    var seatRowAndCol = new Array();
</script>
<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/date-format.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script src="../../javascript/user/userOrder-future.js" ></script>
<script src="../../javascript/user/userOrder-unfinished.js" ></script>
<script src="../../javascript/user/userOrder-showSeat.js" ></script>
<script>

    $(function () {
        show_userOd_historical();
    });
</script>
<script>
    function getInfoAndShow(url, btnOnclick) {
        $("#userOd_historical").removeClass("active");
        $("#userOd_timeout").removeClass("active");
        $("#userOd_deleted").removeClass("active");
        $("#userOd_future").removeClass("active");
        $("#userOd_unfinished").removeClass("active");

        $.post(url, function (rs) {
            var res = $.parseJSON(rs);
            OdInfos = res;
            $("#OdList_show_div").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var infoDiv = "<div>" +
                        "<p><label>计划ID：</label><input type='text' value='" + value.planID + "' readonly/></p>" +
                        "<p><label>总价：</label><input type='text' value='" + value.totalPrice + "' readonly></p>\n" +
                        "<p><label>VIP优惠：</label><input type='text' value='" + value.vipDiscount + "' readonly></p>" +
                        "<p><label>优惠券节省：</label><input type='text' value='" + value.couponDiscount + "' readonly></p>" +
                        "<p><label>实付：</label><input type='text' value='" + value.totalPay + "' readonly></p>" +
                        "<button id='btn_" + value.odID + "_" + value.planID + "_check_info' onclick='" + btnOnclick + "'>详细信息</button>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#OdList_show_div").append(infoDiv);
                });
            }
            else {
                var infoDiv = "<input type='text' value='没有该类型订单！' readonly />";
                $("#OdList_show_div").append(infoDiv);
            }
        });
    }

    function show_userOd_deleted() {
        getInfoAndShow("GetAllUserOdDeleted", "showUserOd_noChange_info(this)");
        $("#userOd_deleted").addClass("active");
    }

    function show_userOd_historical() {
        getInfoAndShow("GetAllHistoricalUserOd", "showUserOd_noChange_info(this)");
        $("#userOd_historical").addClass("active");
    }

    function show_userOd_timeout() {
        getInfoAndShow("GetAllUserOdTimeout", "showUserOd_noChange_info(this)");
        $("#userOd_timeout").addClass("active");
    }

    function showUserOd_noChange_info(obj) {
        $("#title_2").hide();
        $("#OdList_show_div").hide();
        $("#OdInfo_payImmediately_coupon_p").hide();

        $("#OdInfo_show_div").show();
        $("#OdInfo_show_couponDiscount_p").show();
        $("#Od_info_expand_div").show();
        $("#seatMap_div").show();
        $("#venueInfo_div").show();

        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var OdID = temp2[1];
        var planID = temp2[2];

        var OdInfo = {};
        for( var i=0; i<OdInfos.length; i++ ){
            OdInfo = OdInfos[i];
            if( OdID==OdInfo.odID ){
                break;
            }
        }

        var data = {"planID":planID};
        $.post("GetUserOdPlanInfo", data, function (rs) {
            var res = $.parseJSON(rs);

            $("#Od_nameOfPlan_input").val(res.name);
            $("#Od_beginTimeOfPlan_div").val(new Date(res.beginTime).format(dateFormat));
            $("#Od_endTimeOfPlan_div").val(new Date(res.endTime).format(dateFormat));
        });

        $("#Od_seatSelected_ul").empty();

        totalPrice = parseFloat(OdInfo.totalPrice.toFixed(2));
        $("#Od_totalPrice_input").val(totalPrice);

        var vipDiscount = OdInfo.couponDiscount;
        $("#Od_vipDiscount_input").val(vipDiscount);

        var couponDiscount = OdInfo.couponDiscount;
        $("#OdInfo_show_couponDiscount_input").val(couponDiscount);

        var totalPay = OdInfo.totalPay;
        $("#Od_totalPay_input").val(totalPay)

        getHallInfoAndShow(planID);
    }

    function getHallInfoAndShow(planID) {
        var data = {"planID":planID};
        $.post("GetPlanVenueInfo", data, function (rs) {
            var res = $.parseJSON(rs);
            $("#venueProvince").val(res.province);
            $("#venueCity").val(res.city);
            $("#venueAddress").val(res.address);
            $("#venueTel").val(res.telephone);
        })
    }

</script>
<script>
    function OdInfo_show_div_back_OdList_show_div() {
        $("#OdInfo_show_div").hide();
        $("#Od_info_expand_div").hide();
        $("#seatMap_div").hide();
        $("#venueInfo_div").hide();

        $("#title_2").show();
        $("#OdList_show_div").show();
    }
</script>
</body>
</html>
