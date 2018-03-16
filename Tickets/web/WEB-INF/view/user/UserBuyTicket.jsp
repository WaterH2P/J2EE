
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buy Ticket</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
</head>
<body>
<div class="common">
    <div class="main minMain bigMain">
        <div id="aSign" class="title">
            <a class="active">订票</a>
            <b>·</b>
            <a href="UserOrder">订单查看</a>
            <b>·</b>
            <a href="UserInfo">个人信息</a>
        </div>

        <div id="main_div">
            <div id="searchPlan_div">
                <input id="searchPlan_input" type="text" placeholder="搜索"/>
                <button id="searchPlan_submit_btn">🔍</button>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            </div>

            <div id="searchResult_div">

            </div>
        </div>

        <div id="buyTicket_div" style="display: none">
            <button id="buyTicketOnline_back_main_div">🔙</button>
            <div class="demo" id="buyTicket_seatMap_div">

            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script>
    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }

    function buyTicketSelectSeat(obj) {
        $("#main_div").hide();
        $("#buyTicket_div").show();

        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var hallID = temp2[1];

        for( var i=0; i<planInfos.length; i++ ){
            var planInfo = planInfos[i];
            if( hallID==planInfo.hallID ){
                var seatDist = planInfo.seatDist;
                var numOfRow = parseInt(planInfo.numOfRow);
                var numOfCol = parseInt(planInfo.numOfCol);
                var seatData = [];
                for( var j=0; j<numOfRow; j++ ){
                    seatData[j] = seatDist.substring(0, numOfCol);
                    seatDist = seatDist.substring(numOfCol);
                }

                $("#buyTicket_seatMap_div").empty();
                var seat = "<div class='front'>屏幕</div>" +
                    "<div id='seat-map'></div>" +
                    "<div class='booking-details'>" +
                    "<div id='legend'></div>" +
                    "</div>";
                $("#buyTicket_seatMap_div").append(seat);
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
                            [ 'a', 'available',   '可选' ],
                            [ 'c', 'selected',   '已选' ],
                            [ 's', 'unavailable',   '已售' ],
                            [ '_', 'none', '过道']
                        ]
                    },
                    click: function() {
                        if (this.status() == 'available') {
                            return 'selected';
                        }
                        else if(this.status() == 'selected'){
                            return "available";
                        }
                        else {
                            return this.status();
                        }
                    }
                });

                break;
            }
        }
    }

    function buyTicketNotSelectSeat(obj) {
        $("#main_div").hide();
        $("#buyTicket_div").show();

        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var hallID = temp2[1];

        for( var i=0; i<planInfos.length; i++ ){
            var planInfo = planInfos[i];
            if( hallID==planInfo.hallID ){
                var seatDist = planInfo.seatDist;
                var numOfRow = parseInt(planInfo.numOfRow);
                var numOfCol = parseInt(planInfo.numOfCol);
                var seatData = [];
                for( var j=0; j<numOfRow; j++ ){
                    seatData[j] = seatDist.substring(0, numOfCol);
                    seatDist = seatDist.substring(numOfCol);
                }

                $("#buyTicket_seatMap_div").empty();
                var numOfTicketDiv = "<div>" +
                    "<p>" +
                        "<input type='text' id='numOfTicket_input' placeholder='购票数量' />" +
                        "<button id='numOfTicket_submit'>确认购买</button>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#buyTicket_seatMap_div").append(numOfTicketDiv);
                var seat = "<div class='front'>屏幕</div>" +
                    "<div id='seat-map'></div>" +
                    "<div class='booking-details'>" +
                    "<div id='legend'></div>" +
                    "</div>";
                $("#buyTicket_seatMap_div").append(seat);
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
                            [ 'a', 'available',   '可选' ],
                            [ 'c', 'selected',   '已选' ],
                            [ 's', 'unavailable',   '已售' ],
                            [ '_', 'none', '过道']
                        ]
                    },
                    click: function() {
                        return this.status();
                    }
                });
                break;
            }
        }
    }
</script>
<script>
    var planInfos = [];

    var planNameReady = false;

    $("#searchPlan_input").blur(function () {
        var planName = $("#searchPlan_input").val().toString();
        planName = deleteSpace(planName);
        $("#searchPlan_input").val(planName);
        if( planName.length>0 ){
            $("#searchPlan_input").removeClass("borderRed");
            planNameReady = true;
        }
        else {
            $("#searchPlan_input").addClass("borderRed");
            planNameReady = false;
        }
    });

    $("#searchPlan_submit_btn").click(function () {
        if( planNameReady ){
            var planName = $("#searchPlan_input").val().toString();
            planName = deleteSpace(planName);
            $("#searchPlan_input").val(planName);
            var data = {"planName":planName};
            $.post("SearchVenuePlanByPlanName", data, function (rs) {
                var res = $.parseJSON(rs);
                planInfos = res;
                $.each(res, function (index, value, array) {
                   var infoDiv = "<div id='" + value.planID + "_info_div'>" +
                       "<p><label>计划名称：</label><input type='text' value='" + value.name + "' readonly /></p>" +
                       "<p><label>计划类型：</label><input type='text' value='" + value.type + "' readonly /></p>" +
                       "<p><label>开始时间：</label><input type='text' value='" + value.beginTime + "' readonly /></p>" +
                       "<p><label>结束时间：</label><input type='text' value='" + value.endTime + "' readonly /></p>" +
                       "<p><label>安排场厅：</label><input type='text' value='" + value.hallName + "' readonly /></p>" +
                       "<p><label>总卖票数：</label><input type='text' value='" + value.numOfTicket + "' readonly /></p>" +
                       "<p><label>剩余票数：</label><input type='text' value='" + value.numOfTLeft + "' readonly /></p>" +
                       "<p><label>基准票价：</label><input type='text' value='" + value.price + "' readonly /></p>" +
                       "<p><label>计划介绍：</label><textarea type='text' readonly>" + value.description + "</textarea></p>" +
                       "<p>" +
                            "<button id='btn_" + value.hallID + "_buyTickets_selectSeat' onclick='buyTicketSelectSeat(this)'>选座买票</button>" +
                            "<button id='btn_" + value.hallID + "_buyTickets_notSelect' onclick='buyTicketNotSelectSeat(this)'>不选座座买票</button>" +
                       "</p>" +
                       "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                       "</div>";
                   $("#searchResult_div").append(infoDiv);
                });
            });
        }
        else {
            alert("请输入关键词！");
        }
    });

    $("#buyTicketOnline_back_main_div").click(function () {
        $("#main_div").show();
        $("#buyTicket_div").hide();
    });
</script>
</body>
</html>
