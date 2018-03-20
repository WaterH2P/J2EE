
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Plan Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/title.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/fix.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/jquery/pikaday.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/DateTimePicker.css">
</head>
<body>
<div class="common">
    <div class="main bigMain">
        <div id="aSign" class="title">
            <a href="VenueSeatLevelManage">座位等第管理</a>
            <b>·</b>
            <a href="VenueHallPage">场厅管理</a>
            <b>·</b>
            <a class="active">计划管理</a>
            <b>·</b>
            <a href="VenueBaseInfo">个人信息</a>
        </div>

        <div class="title_2" id="title_2">
            <a id="venuePlan_isChecked" onclick="show_venuePlan_isChecked()">已完成检票</a>
            <b>·</b>
            <a class="active" id="venuePlan_isChecking" onclick="show_venuePlan_isChecking()">正在检票</a>
            <b>·</b>
            <a id="venuePlan_isNotChecked" onclick="show_venuePlan_isNotChecked()">未检票</a>
        </div>

        <div id="main_div">
            <div id="main_addNewPlan_div">
                <button id="addNewPlan_btn">新建活动</button>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            </div>

            <div id="main_planList_div">
            </div>
        </div>

        <div id="buyTicketUnderline_div" style="display: none">
            <button id="buyTicketUnderline_back_main_div">🔙</button>
            <div id="buyTicket_seatMap_div">

            </div>
            <div class="fixRight" id="showInfoOfTicketSelected_div">
                <p><label>会员Email：</label><input id="VIPEmail_input" type='text'/></p>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                <p><label>影片：</label><input id="nameOfPlan_input" type='text' readonly/></p>
                <p><label>时间：</label></p>
                <p><input id="beginTimeOfPlan_div" type='text' readonly/></p>
                <p>-></p>
                <p><input id="endTimeOfPlan_div" type='text' readonly/></p>
                <p><label>座位：</label><input style="visibility: hidden"/></p>
                <ul class='selected-seats' id='seatSelected_ul'></ul>
                <p><label>总价：</label><input id="totalPrice_input" type="text" readonly></p>
                <button id="submitSeat_btn">确认购票</button>
            </div>
        </div>

        <div id="payTicket_div" style="display: none">
            <div id="Od_Info_div">
                <p><label>会员Email：</label><input id="Od_Info_VIPEmail_input" type='text' readonly/></p>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                <p><label>影片：</label><input id="Od_nameOfPlan_input" type='text' readonly/></p>
                <p><label>时间：</label></p>
                <p><input id="Od_beginTimeOfPlan_div" type='text' readonly/></p>
                <p>-></p>
                <p><input id="Od_endTimeOfPlan_div" type='text' readonly/></p>
                <p><label>座位：</label><input id="Od_seat_input" style="visibility: hidden"/></p>
                <ul id='Od_seatSelected_ul'></ul>
                <p><label>总价：</label><input id="Od_totalPrice_input" type="text" readonly></p>
                <p id="vipDiscount_p"><label>VIP优惠：</label><input id="Od_vipDiscount_input" type="text" readonly></p>
                <p id="souponDiscount_p">
                    <label>优惠券：</label>
                    <select id="Od_coupon_select" onchange="showCouponDiscountAndChangeTotalPay()"></select>
                    <input id="Od_couponDiscount_input" type="text" readonly>
                </p>
                <p id="totalPay_p"><label>实付：</label><input id="Od_totalPay_input" type="text" readonly></p>
            </div>
            <div id="Od_pay_div">

            </div>
        </div>

        <div id="createPlan_div" style="display: none">
            <button id="createPlan_back_main_div">🔙</button>
            <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            <p>
                <label>名称：</label><input type="text" id="planName" />
                <label>类型：</label><select id="planType"></select>
            </p>
            <p>
                <label>开始日期：</label><input id="beginDate"/>
                <label>时间：</label><input type="text" data-field="time" id="beginTime"/>
            </p>
            <p>
                <label>结束日期：</label><input id="endDate" />
                <label>时间：</label><input type="text" data-field="time" id="endTime"/>
            </p>
            <p><label>场厅：</label><select id="planHall_select" onchange="changeHallSeat()"></select></p>
            <p><label>总票数：</label><input type="text" id="numOfTicket" readonly/></p>
            <p><label>基准票价：</label><input type="text" id="priceOfTicket" /></p>
            <p><label>描述：</label><textarea type="text" id="planDesc"></textarea></p>
            <button id="submitPlan_btn">发布活动</button>

            <div id="dtBox"></div>

            <br/>
            <br/>

            <div id="createPlan_hallShow_div">
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                <p>场厅展示</p>
                <div class="demo" id="hallSeatShow_div">

                </div>
            </div>
        </div>

    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script src="../../javascript/DateTimePicker.js" ></script>
<script src="../../javascript/jquery/pikaday.js" ></script>
<script src="../../javascript/date-format.js" ></script>
<script>
    var dateFormat = "yyyy-MM-dd hh:mm:ss";

    var planInfos = [];
    $(function () {
        var types = "<option value='电影'>电影</option>" +
            "<option value='音乐会'>音乐会</option>" +
            "<option value='舞蹈'>舞蹈</option>" +
            "<option value='话剧'>话剧</option>" +
            "<option value='体育比赛'>体育比赛</option>";
        $("#planType").append(types);

        show_venuePlan_isChecked();

        // 初始化新建 Plan beginTime 和 endTime
        var today = new Date();
        var futureDay = new Date(today.getTime()+6*4*7*24*3600*1000);
        var i18n = {
            previousMonth : '上个月',
            nextMonth   : '下个月',
            months      : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            weekdays    : ['周日','周一','周二','周三','周四','周五','周六'],
            weekdaysShort : ['日','一','二','三','四','五','六']
        };

        var beginPicker = new Pikaday({
            field:$("#beginDate")[0],
            position: 'bottom right',
            format: 'YYYY-M-D',
            toString:function(date, format) {
                // you should do formatting based on the passed format,
                // but we will just return 'D/M/YYYY' for simplicity
                var day = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getFullYear();
                return year + "/" + month + "/" + day;
            },
            minDate:today,
            maxDate:futureDay,
            i18n:i18n,
        });

        var endPicker = new Pikaday({
            field:$("#endDate")[0],
            position: 'bottom right',
            format: 'YYYY-M-D',
            toString:function(date, format) {
                // you should do formatting based on the passed format,
                // but we will just return 'D/M/YYYY' for simplicity
                var day = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getFullYear();
                return year + "/" + month + "/" + day;
            },
            minDate:new Date(),
            maxDate:futureDay,
            i18n:i18n,
        });

        $("#dtBox").DateTimePicker({
            dateFormat: "dd-MMM-yyyy"
        });
    });
</script>
<script>
    function show_venuePlan_isNotChecked() {
        allTitle2RemoveActive();
        $("#venuePlan_isNotChecked").addClass("active");

        $.post("GetAllVenuePlansIsNotChecked", function (rs) {
            var res = $.parseJSON(rs);
            planInfos = res;
            $("#main_planList_div").empty();
            $.each(res, function (index, value, array) {
                var infoDiv = getInfoDiv(value) +
                    "<button id='btn_" + value.planID + "_" + value.hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                    "<button id='btn_" + value.planID + "_" + value.hallID + "_buyTickets' onclick=''>开始检票</button>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#main_planList_div").append(infoDiv);
            });
        });
    }

    function allTitle2RemoveActive() {
        $("#venuePlan_isChecked").removeClass("active");
        $("#venuePlan_isChecking").removeClass("active");
        $("#venuePlan_isNotChecked").removeClass("active");
    }

    function getInfoDiv(value) {
        var beginDate = new Date(value.beginTime);
        var endDate = new Date(value.endTime);
        var infoDiv = "<div id='" + value.planID + "_info_div'>" +
            "<p><label>计划名称：</label><input type='text' value='" + value.name + "' readonly /></p>" +
            "<p><label>计划类型：</label><input type='text' value='" + value.type + "' readonly /></p>" +
            "<p><label>开始时间：</label><input type='text' value='" + beginDate.format(dateFormat) + "' readonly /></p>" +
            "<p><label>结束时间：</label><input type='text' value='" + endDate.format(dateFormat) + "' readonly /></p>" +
            "<p><label>安排场厅：</label><input type='text' value='" + value.hallName + "' readonly /></p>" +
            "<p><label>总卖票数：</label><input type='text' value='" + value.numOfTicket + "' readonly /></p>" +
            "<p><label>剩余票数：</label><input type='text' value='" + value.numOfTLeft + "' readonly /></p>" +
            "<p><label>有座票数：</label><input type='text' value='" + value.numOfTSeated + "' readonly /></p>" +
            "<p><label>待配票数：</label><input type='text' value='" + value.numOfTUnallocated + "' readonly /></p>" +
            "<p><label>基准票价：</label><input type='text' value='" + value.price + "' readonly /></p>" +
            "<p><label>计划介绍：</label><textarea type='text' readonly>" + value.description + "</textarea></p>";
        return infoDiv;
    }

    function show_venuePlan_isChecked() {
        allTitle2RemoveActive();
        $("#venuePlan_isChecked").addClass("active");

        $.post("GetAllVenuePlansIsChecked", function (rs) {
            var res = $.parseJSON(rs);
            planInfos = res;
            $("#main_planList_div").empty();
            $.each(res, function (index, value, array) {
                var infoDiv = getInfoDiv(value) + "<p>" +
                    "<button id='btn_" + value.planID + "_" + value.hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#main_planList_div").append(infoDiv);
            });
        });
    }

    function show_venuePlan_isChecking() {
        allTitle2RemoveActive();
        $("#venuePlan_isChecking").addClass("active");

        $.post("GetAllVenuePlansIsChecking", function (rs) {
            var res = $.parseJSON(rs);
            planInfos = res;
            $("#main_planList_div").empty();
            $.each(res, function (index, value, array) {
                var infoDiv = getInfoDiv(value) + "<p>" +
                    "<button id='btn_" + value.hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#main_planList_div").append(infoDiv);
            });
        });
    }

    function changeHallSeat () {
        var option = $("#planHall_select option:selected").val().toString();

        for( var i=0; i<hallInfos.length; i++ ){
            var hallInfo = hallInfos[i];
            if( option==hallInfo.hallID ){
                var seatData = [];
                var seatDist = hallInfo.seatDist;
                var seatDistSave = seatDist;
                var numOfRow = hallInfo.numOfRow;
                var numOfCol = hallInfo.numOfCol;
                for( var i=0; i<numOfRow; i++ ){
                    seatData[i] = seatDist.substring(0, numOfCol);
                    seatDist = seatDist.substring(numOfCol);
                }

                $("#hallSeatShow_div").empty();
                var seat = "<div class='front'>屏幕</div>" +
                    "<div id='seat-map'></div>" +
                    "<div class='booking-details'>" +
                    "<div id='legend'></div>" +
                    "</div>";
                $("#hallSeatShow_div").append(seat);

                $("#seat-map").seatCharts({
                    map:seatData,
                    naming: {
                        top: true,
                        left:true,
                        getLabel: function(character, row, column) { //返回座位信息
                            return column;
                        }
                    },
                    legend: {
                        node: $('#legend'),
                        items: [
                            [ 'a', 'available',   '位置' ],
                            [ '_', 'none', '过道']
                        ]
                    },
                    click: function() {
                        return this.status();
                    }
                });

                var c = "a";
                var regex = new RegExp(c, "g");
                var result = seatDistSave.match(regex);
                var numOfTicket = !result ? 0 : result.length;
                $("#numOfTicket").val(numOfTicket);
                break;
            }
        }
    };

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }

    var totalPrice = 0;
    var seatSelectedRowAndCols = "";
    var planSelected = {};
    function toBuyTicketUnderline(obj) {
        $("#main_div").hide();
        $("#buyTicketUnderline_div").show();

        seatSelectedRowAndCols = "";
        var numOfTSelected = 0;
        var seatInfos = [];
        var seatRowPercent = {};
        totalPrice = 0;

        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var planID = temp2[1];
        for( var i=0; i<planInfos.length; i++ ){
            var planInfo = planInfos[i];
            planSelected = planInfos[i];
            if( planID==planInfo.planID ){
                var hallID = temp2[2];

                var data = {"hallID":hallID};
                var seatDist = planInfo.seatDist;
                var numOfRow = parseInt(planInfo.numOfRow);
                var numOfCol = parseInt(planInfo.numOfCol);
                var price = parseInt(planInfo.price);
                $.post("GetPlanHallSeatInfo", data, function (rs) {
                    var res = $.parseJSON(rs);
                    seatInfos = res;
                    seatRowPercent = {};
                    for( var i=0; i<numOfRow; i++ ){
                        for( var j=0; j<seatInfos.length; j++ ){
                            var seatInfo = seatInfos[j];
                            if( seatInfo.row==(i+1) ){
                                seatRowPercent[(i+1)] = seatInfo.percent;
                                break;
                            }
                        }
                    }
                });

                $("#VIPEmail_input").val("");
                $("#nameOfPlan_input").val(planInfo.name);
                var beginTime = new Date(planInfo.beginTime);
                $("#beginTimeOfPlan_div").val(beginTime.format(dateFormat));
                var endTime = new Date(planInfo.endTime);
                $("#endTimeOfPlan_div").val(endTime.format(dateFormat));
                $("#totalPrice_input").val(totalPrice);
                $("#seatSelected_ul").empty();

                var seatData = [];
                var seatSelected = new Array();
                for( var j=0; j<numOfRow; j++ ){
                    seatData[j] = seatDist.substring(0, numOfCol);
                    seatDist = seatDist.substring(numOfCol);
                    var seatRow = seatData[j];
                    for( var k=0; k<seatRow.length; k++ ){
                        if( seatRow.charAt(k)=='s' ){
                            var row = j + 1;
                            var col = k + 1;
                            seatSelected[seatSelected.length] = row + "_" + col;
                        }
                    }
                }

                $("#buyTicket_seatMap_div").empty();
                var seat = "<div class='front'>屏幕</div>" +
                    "<div id='seat-map'></div>" +
                    "<div class='booking-details'>" +
                    "<div id='legend'></div>" +
                    "</div>";
                $("#buyTicket_seatMap_div").append(seat);
                var seatMap = $("#seat-map").seatCharts({
                    map:seatData,
                    naming: {
                        top: true,
                        left:true,
                        getLabel: function(character, row, column) { //返回座位信息
                            return column;
                        }
                    },
                    legend: {
                        node: $('#legend'),
                        items: [
                            [ 'a', 'available',   '可选' ],
                            [ 'c', 'selected',   '已选' ],
                            [ 's', 'unavailable',   '已售' ],
                            [ '_', 'none', '过道']
                        ]
                    },
                    click: function() {
                        if (this.status() == 'available') {
                            if( numOfTSelected<6 ){
                                var seatLi = "<li id='seatSelected_" + this.settings.id + "_li'>" +
                                    (this.settings.row+1) + "排" + this.settings.label + "座" + "</li>";
                                $("#seatSelected_ul").append(seatLi);

                                numOfTSelected++;
                                var priceOfTicket = price * parseInt( seatRowPercent[this.settings.row+1] ) / 100.0;
                                priceOfTicket = parseFloat( priceOfTicket.toFixed(2) );
                                addSeatSelectedRowAndCol(this.settings.row+1, this.settings.label, priceOfTicket);
                                totalPrice += priceOfTicket;
                                totalPrice = parseFloat( totalPrice.toFixed(2) );
                                $("#totalPrice_input").val(totalPrice);

                                return 'selected';
                            }
                            else {
                                alert("最多选购 6 张票！");
                                return this.status();
                            }
                        }
                        else if(this.status() == 'selected'){
                            //删除已预订座位
                            $('#seatSelected_' + this.settings.id + "_li").remove();

                            numOfTSelected--;
                            var priceOfTicket = price * parseInt( seatRowPercent[this.settings.row+1] ) / 100.0;
                            priceOfTicket = parseFloat( priceOfTicket.toFixed(2) );
                            deleteSeatSelectedRowAndCol(this.settings.row+1, this.settings.label, priceOfTicket);
                            totalPrice -= priceOfTicket;
                            totalPrice = parseFloat( totalPrice.toFixed(2) );
                            $("#totalPrice_input").val(totalPrice);

                            return "available";
                        }
                        else {
                            return this.status();
                        }
                    }
                });
                seatMap.status(seatSelected, "unavailable");

                break;
            }
        }

    }

    function addSeatSelectedRowAndCol(row, col, price) {
        if( seatSelectedRowAndCols.length>0 ){
            seatSelectedRowAndCols += "==" + row + "--" + col + "--" + price;
        }
        else {
            seatSelectedRowAndCols += row + "--" + col + "--" + price;
        }
    }

    function deleteSeatSelectedRowAndCol(row, col, price) {
        var temp1 = seatSelectedRowAndCols.split("==");
        var seatRowAndCol = "";
        $.each(temp1, function (index, value, array) {
            if( value.length>0 ){
                var temp2 = value.split("--");
                if( temp2[0]!=row && temp2[1]!=col ){
                    seatRowAndCol += value + "==";
                }
            }
        });
        seatSelectedRowAndCols = seatRowAndCol;
    }

    function showOdInfo(res, isSeated) {
        $("#buyTicketUnderline_div").hide();
        $("#payTicket_div").show();

        $("#Od_pay_div").empty();
        var submitBtn = "<button id='btn_" + res.message +"_payOdImmediately' onclick='payOdImmediately(this)'>立即付款</button>" +
            "<button id='btn_" + res.message +"_cancel' onclick='cancelBuyTicket(this)' '>取消购票</button>";
        $("#Od_pay_div").append(submitBtn);

        $("#Od_nameOfPlan_input").val($("#nameOfPlan_input").val().toString());
        $("#Od_beginTimeOfPlan_div").val($("#beginTimeOfPlan_div").val().toString());
        $("#Od_endTimeOfPlan_div").val($("#endTimeOfPlan_div").val().toString());

        $("#Od_seatSelected_ul").empty();
        var seatSelectedRowAndCol = seatSelectedRowAndCols.split("==");
        for( var i=0; i<seatSelectedRowAndCol.length; i++ ){
            var seatRowAndCol = seatSelectedRowAndCol[i];
            if( seatRowAndCol.length>0 ){
                var temp = seatRowAndCol.split("--");
                var seatLi = "<li>" + temp[0] + "排" + temp[1] + "座  ¥" + temp[2] + "</li>";
                $("#Od_seatSelected_ul").append(seatLi);
            }
        }

        totalPrice = parseFloat(totalPrice.toFixed(2));
        $("#Od_totalPrice_input").val(totalPrice);
    }

    var coupons = [];
    function getAndShowVIPCouponDiscount(email) {
        var data = {"email":email};
        // 获得 VIP 等级对应优惠，并计算优惠金额
        $.post("GetUserVIPDiscount", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.percent!=undefined ){
                var percent = parseInt(res.percent);
                var discount = (100 - percent)*totalPrice/100.0;
                discount = parseFloat(discount.toFixed(2));
                $("#Od_vipDiscount_input").val(discount);

                var totalPay = totalPrice - discount;
                totalPay = parseFloat(totalPay.toFixed(2));
                $("#Od_totalPay_input").val(totalPay);

                $("#Od_Info_VIPEmail_input").val(email);

            }
            else {
                $("#vipDiscount_p").hide();
                $("#souponDiscount_p").hide();
                $("#totalPay_p").hide();
                $("#Od_Info_VIPEmail_input").val("");
            }
        });

        // 获得所有优惠券
        $.post("GetAllUserCoupons", data, function (rs) {
            var res = $.parseJSON(rs);
            coupons = res;

            $("#Od_coupon_select").empty();

            var nullOption = "<option value='' selected></option>";
            $("#Od_coupon_select").append(nullOption);
            $.each(res, function (index, value, array) {
                var option = "<option value='" + value.couponID + "'>" + value.name + "</option>";
                $("#Od_coupon_select").append(option);
            });

            $("#Od_couponDiscount_input").val("");
        });
    }

    function payOdImmediately(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var OdID = temp2[1];
        var couponID = "";
        var email = $("#Od_Info_VIPEmail_input").val().toString();
        if( email.length>0 ){
            couponID = $("#Od_coupon_select option:selected").val().toString();
        }
        var data = {"email":email, "OdID":OdID, "couponID":couponID};
        $.post("PayOrder", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                alert(res.message);
                window.location.reload();
            }
            else {
                alert(res.message);
            }
        })
    }

    function showCouponDiscountAndChangeTotalPay() {
        var couponID = $("#Od_coupon_select option:selected").val().toString();
        if( couponID.length>0 ){
            for( var i=0; i<coupons.length; i++ ){
                var coupon = coupons[i];
                if( coupon.couponID==couponID ){
                    $("#Od_couponDiscount_input").val(coupon.discount);
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
            $("#Od_couponDiscount_input").val("");
            var totalPay = totalPrice - parseFloat($("#Od_vipDiscount_input").val().toString());
            totalPay = parseFloat(totalPay.toFixed(2));
            $("#Od_totalPay_input").val(totalPay);
        }
    }

    function cancelBuyTicket(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var OdID = temp2[1];
        var email = $("#Od_Info_VIPEmail_input").val().toString();
        var data = {"email":email, "OdID":OdID};
        $.post("CancelOrder", data, function (rs) {
            var res = $.parseJSON(rs);
            if( res.result ){
                alert(res.message);
                window.location.reload();
            }
            else {
                alert(res.message);
            }
        })
    }
</script>
<script>
    var hallInfos = [];
    var nameReady = false;
    var priceReady = false;
    var descReady = false;

    $("#addNewPlan_btn").click(function () {
        $("#main_div").hide();
        $("#title_2").hide();
        $("#createPlan_div").show();

        $("#beginDate").val("");
        $("#endDate").val("");

        $.post("GetAllVenueHalls", function (rs) {
            var res = $.parseJSON(rs);
            hallInfos = res;
            $("#planHall_select").empty();
            for( var i=0; i<res.length; i++ ){
                var hallInfo = res[i];
                var option = "<option value='" + hallInfo.hallID + "'>" + hallInfo.name + "</option>";
                $("#planHall_select").append(option);
                if( i===0 ){
                    var seatData = [];
                    var seatDist = hallInfo.seatDist;
                    var seatDistSave = seatDist;
                    for( var j=0; j<hallInfo.numOfRow; j++ ){
                        seatData[j] = seatDist.substring(0, hallInfo.numOfCol);
                        seatDist = seatDist.substring(hallInfo.numOfCol);
                    }

                    $("#hallSeatShow_div").empty();
                    var seat = "<div class='front'>屏幕</div>" +
                        "<div id='seat-map'></div>" +
                        "<div class='booking-details'>" +
                        "<div id='legend'></div>" +
                        "</div>";
                    $("#hallSeatShow_div").append(seat);

                    $("#seat-map").seatCharts({
                        map:seatData,
                        naming: {
                            top: true,
                            left:true,
                            getLabel: function(character, row, column) { //返回座位信息
                                return column;
                            }
                        },
                        legend: {
                            node: $('#legend'),
                            items: [
                                [ 'a', 'available',   '位置' ],
                                [ '_', 'none', '过道']
                            ]
                        },
                        click: function() {
                            return this.status();
                        }
                    });

                    var c = "a";
                    var regex = new RegExp(c, "g");
                    var result = seatDistSave.match(regex);
                    var numOfTicket = !result ? 0 : result.length;
                    $("#numOfTicket").val(numOfTicket);
                }
            }
        });

    });

    $("#createPlan_back_main_div").click(function () {
        var isConfirmed = confirm("返回将丢失全部信息！");
        if( isConfirmed ){
            $("#main_div").show();
            $("#title_2").show();
            $("#createPlan_div").hide();
        }
    });

    $("#planName").blur(function () {
        var name = $("#planName").val().toString();
        name = deleteSpace(name);
        $("#planName").val(name);
        if( name.length>0 ){
            $("#planName").removeClass("borderRed");
            nameReady = true;
        }
        else {
            $("#planName").addClass("borderRed");
            nameReady = false;
        }
    });

    $("#priceOfTicket").blur(function () {
        var price = $("#priceOfTicket").val().toString();
        var priceReg = /^((0.)[1-9]*)|[1-9]+[0-9]*([.]{0,1})[0-9]*$/;
        if( priceReg.test(price) ){
            $("#priceOfTicket").removeClass("borderRed");
            priceReady = true;
        }
        else {
            $("#priceOfTicket").addClass("borderRed");
            priceReady = false;
        }
    });

    $("#planDesc").blur(function () {
        var description = $("#planDesc").val().toString();
        description = deleteSpace(description);
        $("#planDesc").val(description);
        if( description.length>0 ){
            $("#planDesc").removeClass("borderRed");
            descReady = true;
        }
        else {
            $("#planDesc").addClass("borderRed");
            descReady = false;
        }
    });

    $("#submitPlan_btn").click(function () {
        var beginDate = $("#beginDate").val().toString();
        var beginTime = beginDate + " " + $("#beginTime").val().toString() + ":00";
        var endDate = $("#endDate").val().toString();
        var endTime = endDate + " " + $("#endTime").val().toString() + ":00";
        var beginTimeM = new Date(beginTime).getTime();
        var endTimeM = new Date(endTime).getTime();
        if( endTimeM>beginTimeM ){
            if( nameReady && priceReady && descReady ){
                var name = $("#planName").val().toString();
                name = deleteSpace(name);
                var type = $("#planType option:selected").val().toString();
                var hallID = $("#planHall_select option:selected").val().toString();
                var hallName = $("#planHall_select option:selected").text();
                var numOfTicket = $("#numOfTicket").val().toString();
                var price = $("#priceOfTicket").val().toString();
                var description = $("#planDesc").val().toString();
                description = deleteSpace(description);

                var data = {"name":name, "type":type, "beginTime":beginTime, "endTime":endTime,
                    "hallID":hallID, "hallName":hallName, "numOfTicket":numOfTicket, "price":price,
                    "description":description};
                $.post("AddNewVenuePlan", data, function (rs) {
                    var res = $.parseJSON(rs);
                    var planID = res.message;
                    if( res.result ){
                        // var infoDiv = "<div id='" + planID + "_info_div'>" +
                        //     "<p><label>计划名称：</label><input type='text' value='" + name + "' readonly /></p>" +
                        //     "<p><label>计划类型：</label><input type='text' value='" + type + "' readonly /></p>" +
                        //     "<p><label>开始时间：</label><input type='text' value='" + beginTime + "' readonly /></p>" +
                        //     "<p><label>结束时间：</label><input type='text' value='" + endTime + "' readonly /></p>" +
                        //     "<p><label>安排场厅：</label><input type='text' value='" + hallName + "' readonly /></p>" +
                        //     "<p><label>总卖票数：</label><input type='text' value='" + numOfTicket + "' readonly /></p>" +
                        //     "<p><label>剩余票数：</label><input type='text' value='" + numOfTicket + "' readonly /></p>" +
                        //     "<p><label>有座票数：</label><input type='text' value='0' readonly /></p>" +
                        //     "<p><label>待配票数：</label><input type='text' value='0' readonly /></p>" +
                        //     "<p><label>基准票价：</label><input type='text' value='" + price + "' readonly /></p>" +
                        //     "<p><label>计划介绍：</label><textarea type='text' readonly>" + description + "</textarea></p>" +
                        //     "<p>" +
                        //         "<button id='btn_" + planID + "_" + hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                        //     "</p>" +
                        //     "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        //     "</div>";
                        // $("#main_planList_div").append(infoDiv);

                        // $("#planName").val("");
                        // $("#beginDate").val("");
                        // $("#endDate").val("");
                        // $("#priceOfTicket").val("");
                        // $("#planDesc").val("");

                        // $("#main_div").show();
                        // $("#title_2").show();
                        // $("#createPlan_div").hide();
                        alert("新建成功！");
                        window.location.reload();
                    }
                    else {
                        alert(res.message);
                    }
                });
            }
            else {
                alert("请正确填写信息！");
            }
        }
        else {
            alert("请选择正确起始时间！");
        }

    });

    $("#buyTicketUnderline_back_main_div").click(function () {
        $("#main_div").show();
        $("#buyTicketUnderline_div").hide();
    });

    $("#submitSeat_btn").click(function () {
        var isConfirmed = confirm("确认购买？");
        if( isConfirmed ){
            if( seatSelectedRowAndCols.length!=0 ){
                var email = $("#VIPEmail_input").val().toString();
                email = email.toLowerCase();
                var planID = planSelected.planID;
                var seatSelected = seatSelectedRowAndCols;
                var data = {"email":email, "planID":planID, "seatSelected":seatSelected, "totalPrice":totalPrice};

                // 创建新订单
                $.post("MakeNewOdSeated", data, function (rs) {
                    var res = $.parseJSON(rs);
                    if( res.result ){
                        showOdInfo(res, true);
                    }
                    else {
                        alert(res.message);
                    }
                });
            }
            else {
                alert("请选择座位！");
            }

            var email = $("#VIPEmail_input").val().toString();
            email = email.toLowerCase();
            getAndShowVIPCouponDiscount(email);
        }
    });

</script>
</body>
</html>
