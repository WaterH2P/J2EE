
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
                <p><label>订单ID：</label><input id="Od_ID_input" type='text' readonly/></p>
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
            <p><label>订单付款倒计时：</label></p>
            <p><input style="color: red" type="text" id="Od_timeout_input" readonly/></p>
            <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
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
                        "<p><label>订单ID：</label><input type='text' value='" + value.odID + "' readonly/></p>" +
                        "<p><label>计划ID：</label><input type='text' value='" + value.planID + "' readonly/></p>" +
                        "<p><label>下订单时间：</label><input type='text' value='" + new Date(value.makeTime).format(dateFormat) + "' readonly/></p>" +
                        "<p><label>购票数：</label><input type='text' value='" + value.numOfTicket + "' readonly></p>" +
                        "<p><label>实付：</label><input type='text' value='" + value.totalPay + "' readonly></p>";
                    if( value.checked ){
                        infoDiv += "<p><label>检票：</label><input type='text' value='已检票' readonly></p>";
                    }
                    infoDiv += "<button id='btn_" + value.odID + "_" + value.planID + "_check_info' onclick='" + btnOnclick + "'>详细信息</button>" +
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
        $("#Od_ID_input").val(OdID);

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

        var data = {"OdID":OdID};
        $.post("GetUserOdAllSeatSelectedInfo", data, function (rs) {
            var res = $.parseJSON(rs);
            seatRowAndCol = new Array();

            $("#Od_seatSelected_ul").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var seatLi = "<li>" + value.row + "排" + value.col + "座  ¥" + value.price + "</li>";
                    $("#Od_seatSelected_ul").append(seatLi);
                    seatRowAndCol[seatRowAndCol.length] = value.row + "_" + value.col;
                });
            }
            else {
                var seatLi = "<li>还未分配座位！</li>";
                $("#Od_seatSelected_ul").append(seatLi);
            }
        });

        getHallInfoAndShow(planID);

        getSeatDistAndShow(planID);
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

<%-- 未来订单 --%>
<script>
    function show_userOd_future() {
        getInfoAndShow("GetAllFutureUserOd", "showUserOd_future_info(this)");
        $("#userOd_future").addClass("active");
    }

    function showUserOd_future_info(obj) {
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

        $("#Od_ID_input").val(OdID);

        var data = {"planID":planID};
        $.post("GetUserOdPlanInfo", data, function (rs) {
            var res = $.parseJSON(rs);

            $("#Od_nameOfPlan_input").val(res.name);
            $("#Od_beginTimeOfPlan_div").val(new Date(res.beginTime).format(dateFormat));
            $("#Od_endTimeOfPlan_div").val(new Date(res.endTime).format(dateFormat));
        });

        data = {"OdID":OdID};
        $.post("GetUserOdAllSeatSelectedInfo", data, function (rs) {
            var res = $.parseJSON(rs);
            seatRowAndCol = new Array();

            $("#Od_seatSelected_ul").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var seatLi = "<li>" + value.row + "排" + value.col + "座  ¥" + value.price + "</li>";
                    $("#Od_seatSelected_ul").append(seatLi);
                    seatRowAndCol[seatRowAndCol.length] = value.row + "_" + value.col;
                });
            }
            else {
                var seatLi = "<li>还未分配座位！</li>";
                $("#Od_seatSelected_ul").append(seatLi);
            }
        });

        totalPrice = parseFloat(OdInfo.totalPrice.toFixed(2));
        $("#Od_totalPrice_input").val(totalPrice);

        var vipDiscount = OdInfo.couponDiscount;
        $("#Od_vipDiscount_input").val(vipDiscount);

        var couponDiscount = OdInfo.couponDiscount;
        $("#OdInfo_show_couponDiscount_input").val(couponDiscount);

        var totalPay = OdInfo.totalPay;
        $("#Od_totalPay_input").val(totalPay)

        $("#Od_info_expand_div").empty();
        var submitBtn = "<button id='btn_" + OdID +"_deleteOd' onclick='deleteOd(this)'>取消订单</button>";
        $("#Od_info_expand_div").append(submitBtn);

        getSeatDistAndShow(planID);

        getHallInfoAndShow(planID);
    }

    function deleteOd(obj) {
        var isConfirmed = confirm("确认退单？");
        if( isConfirmed ){
            var temp1 = $(obj).attr("id");
            var temp2 = temp1.split("_");
            var OdID = temp2[1];
            var data = {"OdID":OdID};
            $.post("DeleteOrder", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    alert(res.message);
                    OdInfo_show_div_back_OdList_show_div();
                    window.location.reload();
                }
                else {
                    alert(res.message);
                }
            });
        }
    }
</script>
<%-- 未来订单 --%>

<%-- 未完成订单 --%>
<script>
    function show_userOd_unfinished() {
        getInfoAndShow("GetAllUserOdUnfinished", "showUserOd_unfinished_info(this)");
        $("#userOd_unfinished").addClass("active");
    }

    var intrval = undefined;
    function showUserOd_unfinished_info(obj) {
        $("#title_2").hide();
        $("#OdList_show_div").hide();
        $("#OdInfo_show_couponDiscount_p").hide();

        $("#OdInfo_show_div").show();
        $("#OdInfo_payImmediately_coupon_p").show();
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
        $("#Od_ID_input").val(OdID);

        $("#Od_timeout_div").show();
        $("#Od_timeout_input").val("");
        var makeTime = OdInfo.makeTime;
        intrval = undefined;
        intrval = setInterval(function(){
            var future = parseFloat( new Date(makeTime).getTime() + 15 * 60 * 1000 );
            var now = parseFloat( new Date().getTime() );

            if( future<now ){
                $("#Od_timeout_input").val("订单超时！");
            }
            else {
                var distance = parseFloat(future - now) / 1000;
                var minutes = parseInt(distance / 60);
                var seconds = parseInt(distance % 60);
                var message = minutes + ":" +seconds;
                $("#Od_timeout_input").val(message);
            }
        }, 1000);

        var data = {"planID":planID};
        $.post("GetUserOdPlanInfo", data, function (rs) {
            var res = $.parseJSON(rs);

            $("#Od_nameOfPlan_input").val(res.name);
            $("#Od_beginTimeOfPlan_div").val(new Date(res.beginTime).format(dateFormat));
            $("#Od_endTimeOfPlan_div").val(new Date(res.endTime).format(dateFormat));
        });

        data = {"OdID":OdID};
        $.post("GetUserOdAllSeatSelectedInfo", data, function (rs) {
            var res = $.parseJSON(rs);
            seatRowAndCol = new Array();

            $("#Od_seatSelected_ul").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var seatLi = "<li>" + value.row + "排" + value.col + "座  ¥" + value.price + "</li>";
                    $("#Od_seatSelected_ul").append(seatLi);
                    seatRowAndCol[seatRowAndCol.length] = value.row + "_" + value.col;
                })
            }
            else {
                var seatLi = "<li>还未分配座位！</li>";
                $("#Od_seatSelected_ul").append(seatLi);
            }
        });

        $("#Od_info_expand_div").empty();
        var submitBtn = "<button id='btn_" + OdID +"_payOdImmediately' onclick='payOdImmediately(this)'>立即付款</button>" +
            "<button onclick='OdInfo_show_div_back_OdList_show_div()'>稍后付款</button>" +
            "<button id='btn_" + OdID +"_deleteOd' onclick='deleteOd(this)'>取消订单</button>";
        $("#Od_info_expand_div").append(submitBtn);

        totalPrice = parseFloat(OdInfo.totalPrice.toFixed(2));
        $("#Od_totalPrice_input").val(totalPrice);

        getAndShowVIPCouponDiscount();

        getSeatDistAndShow(planID);

        getHallInfoAndShow(planID);
    }

    var coupons = [];
    function getAndShowVIPCouponDiscount() {
        // 获得所有优惠券
        $.post("GetAllUserCoupons", function (rs) {
            var res = $.parseJSON(rs);
            coupons = res;

            $("#Od_coupon_select").empty();

            var nullOption = "<option value='' selected></option>";
            $("#Od_coupon_select").append(nullOption);
            $.each(res, function (index, value, array) {
                var option = "<option value='" + value.couponID + "'>" + value.name + "</option>";
                $("#Od_coupon_select").append(option);
            });

            $("#OdInfo_payImmediately_couponDiscount_input").val("");
        });

        // 获得 VIP 等级对应优惠，并计算优惠金额
        $.post("GetUserVIPDiscount", function (rs) {
            var res = $.parseJSON(rs);
            var percent = parseInt(res.percent);
            var discount = (100 - percent)*totalPrice/100.0;
            discount = parseFloat(discount.toFixed(2));
            $("#Od_vipDiscount_input").val(discount);

            var totalPay = totalPrice - discount;
            totalPay = parseFloat(totalPay.toFixed(2));
            $("#Od_totalPay_input").val(totalPay);
        });
    }

    function showCouponDiscountAndChangeTotalPay() {
        var couponID = $("#Od_coupon_select option:selected").val().toString();
        if( couponID.length>0 ){
            for( var i=0; i<coupons.length; i++ ){
                var coupon = coupons[i];
                if( coupon.couponID==couponID ){
                    $("#OdInfo_payImmediately_couponDiscount_input").val(coupon.discount);
                    var totalPay = totalPrice - parseFloat($("#Od_vipDiscount_input").val().toString()) - parseFloat(coupon.discount);
                    totalPay = parseFloat(totalPay.toFixed(2));
                    if( totalPay<0 ){
                        totalPay = 0;
                    }
                    $("#Od_totalPay_input").val(totalPay);
                    break;
                }
            }
        }
        else {
            $("#OdInfo_payImmediately_couponDiscount_input").val("");
            var totalPay = totalPrice - parseFloat($("#Od_vipDiscount_input").val().toString());
            totalPay = parseFloat(totalPay.toFixed(2));
            $("#Od_totalPay_input").val(totalPay);
        }
    }

    function payOdImmediately(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var OdID = temp2[1];
        var couponID = $("#Od_coupon_select option:selected").val().toString();
        var data = {"OdID":OdID, "couponID":couponID};
        $.post("PayOrder", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                alert(res.message);
                OdInfo_payImmediately_div_back_OdList_show_div();
                window.location.reload();
            }
            else {
                alert(res.message);
            }
        });
    }
</script>
<%-- 未完成订单 --%>

<script>
    function OdInfo_show_div_back_OdList_show_div() {
        $("#OdInfo_show_div").hide();
        $("#Od_info_expand_div").hide();
        $("#seatMap_div").hide();
        $("#venueInfo_div").hide();
        $("#Od_timeout_div").hide();

        $("#title_2").show();
        $("#OdList_show_div").show();
        $("#Od_timeout_input").val("");

        clearInterval(intrval);
    }
</script>
</body>
</html>
