
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Plan Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
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

        $.post("GetAllVenuePlans", function (rs) {
            var res = $.parseJSON(rs);
            planInfos = res;
            $.each(res, function (index, value, array) {
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
                    "<p><label>计划介绍：</label><textarea type='text' readonly>" + value.description + "</textarea></p>" +
                    "<p>" +
                    "<button id='btn_" + value.hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#main_planList_div").append(infoDiv);
            });
        });

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

    function toBuyTicketUnderline(obj) {
        $("#main_div").hide();
        $("#buyTicketUnderline_div").show();
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
            }
        }

    }
</script>
<script>
    var hallInfos = [];
    var nameReady = false;
    var priceReady = false;
    var descReady = false;

    $("#addNewPlan_btn").click(function () {
        $("#main_div").hide();
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
                        var infoDiv = "<div id='" + planID + "_info_div'>" +
                            "<p><label>计划名称：</label><input type='text' value='" + name + "' readonly /></p>" +
                            "<p><label>计划类型：</label><input type='text' value='" + type + "' readonly /></p>" +
                            "<p><label>开始时间：</label><input type='text' value='" + beginTime + "' readonly /></p>" +
                            "<p><label>结束时间：</label><input type='text' value='" + endTime + "' readonly /></p>" +
                            "<p><label>安排场厅：</label><input type='text' value='" + hallName + "' readonly /></p>" +
                            "<p><label>总卖票数：</label><input type='text' value='" + numOfTicket + "' readonly /></p>" +
                            "<p><label>剩余票数：</label><input type='text' value='" + numOfTicket + "' readonly /></p>" +
                            "<p><label>有座票数：</label><input type='text' value='0' readonly /></p>" +
                            "<p><label>待配票数：</label><input type='text' value='0' readonly /></p>" +
                            "<p><label>基准票价：</label><input type='text' value='" + price + "' readonly /></p>" +
                            "<p><label>计划介绍：</label><textarea type='text' readonly>" + description + "</textarea></p>" +
                            "<p>" +
                                "<button id='btn_" + hallID + "_buyTickets' onclick='toBuyTicketUnderline(this)'>线下买票</button>" +
                            "</p>" +
                            "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                            "</div>";
                        $("#main_planList_div").append(infoDiv);

                        $("#planName").val("");
                        $("#beginDate").val("");
                        $("#endDate").val("");
                        $("#priceOfTicket").val("");
                        $("#planDesc").val("");

                        $("#main_div").show();
                        $("#createPlan_div").hide();
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
</script>
</body>
</html>
