
function show_userOd_unfinished() {
    getInfoAndShow("GetAllUserOdUnfinished", "showUserOd_unfinished_info(this)");
    $("#userOd_unfinished").addClass("active");
}

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