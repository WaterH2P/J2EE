function show_userOd_deleted() {
    $("#userOd_historical").removeClass("active");
    $("#userOd_timeout").removeClass("active");
    $("#userOd_deleted").addClass("active");
    $("#userOd_future").removeClass("active");
    $("#userOd_unfinished").removeClass("active");
    $.post("GetAllUserOdDeleted", function (rs) {
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
                    "<button id='btn_" + value.odID + "_" + value.planID + "_check_info' onclick='showUserOd_deleted_info(this)'>详细信息</button>" +
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

function showUserOd_deleted_info(obj) {
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