
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.venue.VenueHall " %>
<html>
<head>
    <meta charset="utf-8">
    <title>Hall Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/fix.css">
</head>
<body>
<div class="common">
    <div class="main minMain bigMain">
        <div id="aSign" class="title">
            <a href="VenueSeatLevelManage">座位等第管理</a>
            <b>·</b>
            <a class="active">场厅管理</a>
            <b>·</b>
            <a href="VenuePlanManage">计划管理</a>
            <b>·</b>
            <a href="VenueBaseInfo">个人信息</a>
        </div>

        <div id="main_div">
            <div id="main_addNewHall_div">
                <button id="addNewHall_btn">新建场厅</button>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
            </div>

            <div id="main_hallList_div">
            </div>
        </div>

        <div id="hallInfo_div" style="display: none">
            <button id="hallInfo_back_hallList_btn">🔙</button>
            <div class="demo" id="hallInfo_seatMap_div">

            </div>
        </div>

        <div id="createHall_div" style="display:none;">
            <button id="createHall_back_hallList_btn">🔙</button>
            <p id="createHall_setSeatRowCol_p">
                <label>排：</label><select id="numOfRow"></select>
                <label>列：</label><select id="numOfCol"></select>
                <button id="createHall_setSeatRowCol_btn">确定</button>
            </p>
            <p id="createHall_setSeatLevel_p" style="display: none">
                <button id="createHall_setSeatLevel_btn">设置座位等第</button>
            </p>
            <div class="demo" id="seatMap">
            </div>
            <br />
            <br />
            <br />
            <div class="fixRight" id="seatLevel_div" style="display: none">

            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script>
    var hallInfos = [];
    $(function () {
        var numOfRowMax = 15;
        var numOfRolMax = 15;
        var i=0;
        var value = 0;
        for( i=0; i<numOfRowMax; i++ ){
            value = i+1;
            var row = "<option value='" + value + "'>" + value + "</option>";
            $("#numOfRow").append(row);
        }
        for( i=0; i<numOfRolMax; i++ ){
            value = i+1;
            var rol = "<option value='" + value + "'>" + value + "</option>";
            $("#numOfCol").append(rol);
        }

        $.post("GetAllVenueHalls", function (rs) {
            var res = $.parseJSON(rs);
            hallInfos = res;
            for( var i=0; i<res.length; i++ ){
                var venueHall = res[i];
                var hallID = venueHall.hallID;
                var venueID = venueHall.venueID;
                var infoDiv = "<div id='" + hallID + "_info_div'>" +
                    "<p><label>场厅名称：</label><input type='text' value='" + venueHall.name + "' readonly /></p>" +
                    "<p><label>场厅大小：</label><input type='text' value='" + venueHall.numOfRow + " * " + venueHall.numOfCol + "' readonly /></p>" +
                    "<button id='btn_" + venueID + "_" + hallID + "_check' onclick='checkHallInfo(this)'>查看场厅</button>" +
                    "<button id='btn_" + venueID + "_" + hallID + "_delete' onclick='deleteHall(this)'>删除场厅</button>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#main_hallList_div").append(infoDiv);
            }
        });
    });
</script>
<script>
    function checkHallInfo(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var hallID = temp2[2];
        var hallInfo;

        for( var i=0; i<hallInfos.length; i++ ){
            if( hallInfos[i].hallID==hallID ){
                hallInfo = hallInfos[i];
                break;
            }
        }
        if( hallInfo!==undefined ){
            $("#main_div").hide();
            $("#hallInfo_div").show();

            var seatData = [];
            var seatDist = hallInfo.seatDist;
            for( var i=0; i<hallInfo.numOfRow; i++ ){
                seatData[i] = seatDist.substring(0, hallInfo.numOfCol);
                seatDist = seatDist.substring(hallInfo.numOfCol);
            }

            $("#hallInfo_seatMap_div").empty;
            var seat = "<div class='front'>屏幕</div>" +
                "<div id='seat-map'></div>" +
                "<div class='booking-details'>" +
                "<div id='legend'></div>" +
                "</div>";
            $("#hallInfo_seatMap_div").append(seat);
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
        }
        else {
            alert("未找到场厅数据！");
        }
    }

    function deleteHall(obj) {
        var isConfirmed = confirm("确认删除该场厅？");
        if( isConfirmed ) {
            var temp1 = $(obj).attr("id");
            var temp2 = temp1.split("_");
            var hallID = temp2[2];
            var data = {"hallID": hallID};
            $.post("DeleteVenueHall", data, function (rs) {
                var res = $.parseJSON(rs);
                if (res.result) {
                    $("#" + hallID + "_info_div").remove();
                }
                else {
                    alert(res.message);
                }
            });
        }
    }

    var nameReady = false;
    function nameInputBlur() {
        var name = $("#hallName").val().toString();
        if( name.length>0 ){
            $("#hallName").removeClass("borderRed");
            nameReady = true;
        }
        else {
            $("#hallName").addClass("borderRed");
            nameReady = false;
        }
    }

    function submitHall() {
        if( !nameReady ){
            alert("请输入场馆名称！");
            return;
        }
        var seatLevel = "";
        var seatRows = "";
        for( var i=0; i<seatIDs.length; i++ ){
            var seatID = seatIDs[i];
            var seatRow = $("#" + seatID + "_seatRow").val().toString();
            var mulChar = /_(_)+/g;
            seatRow = seatRow.replace(mulChar, "_");
            if( seatLevel.length==0 ){
                seatLevel = seatID + "--" + seatRow;
            }
            else {
                seatLevel += "==" + seatID + "--" + seatRow;
            }
            seatRows += seatRow + "_";
        }
        var seatRowReg = /^([_0-9])+$/;
        if( seatRowReg.test(seatRows) ){
            var temp1 = seatRows.split("_");
            var numReg = /^([0-9])+$/;
            var temp2 = [];
            var temp2Index = 0;
            for(var i=0; i<temp1.length; i++ ){
                var num = temp1[i];
                if( numReg.test(num) ){
                    temp2[temp2Index]=num;
                    temp2Index++;
                }
            }
            if( temp2.length==seatMap.length ){
                if( !ownRepeatingValue(temp2) ){
                    if( !ownBiggerValue(temp2, seatMap.length) && !ownSmallValue(temp2, 1) ){
                        var name = $("#hallName").val().toString();
                        var numOfRow = seatMap.length;
                        var numOfCol = seatMap[0].length;
                        var seatData = "";
                        for( var i=0; i<seatMap.length; i++ ){
                            seatData += seatMap[i];
                        }
                        var data = {"name":name, "numOfRow":numOfRow, "numOfCol":numOfCol, "seatData":seatData, "seatLevel":seatLevel};
                        $.post("AddVenueNewHall", data, function (rs) {
                            var venueHall = $.parseJSON(rs);
                            hallInfos[hallInfos.length] = venueHall;
                            var hallID = venueHall.hallID;
                            var venueID = venueHall.venueID;
                            if( hallID.length>0 ){
                                var infoDiv = "<div id='" + hallID + "_info_div'>" +
                                    "<p><label>场厅名称：</label><input type='text' value='" + venueHall.name + "' readonly /></p>" +
                                    "<p><label>场厅大小：</label><input type='text' value='" + venueHall.numOfRow + " * " + venueHall.numOfCol + "' readonly /></p>" +
                                    "<button id='btn_" + venueID + "_" + hallID + "_check' onclick='checkHallInfo(this)'>查看场厅</button>" +
                                    "<button id='btn_" + venueID + "_" + hallID + "_delete' onclick='deleteHall(this)'>删除场厅</button>" +
                                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                                    "</div>";
                                $("#main_hallList_div").append(infoDiv);
                                isCreateSeat = false;
                                $("#createHall_back_hallList_btn").click();
                            }
                            else {
                                alert(res.message);
                            }
                        });
                    }
                    else {
                        alert("座位设置存在错误行号！");
                    }
                }
                else {
                    alert("座位设置存在重复行号！");
                }
            }
            else {
                alert("座位设置存在遗漏或重复行号！");
            }
        }
        else {
            alert("请按照样例进行座位设置！");
        }
    }

    function ownRepeatingValue(values) {
        for( var i=0; i<values.length; i++ ){
            for( var j=i+1; j<values.length; j++ ){
                var velue1 = parseInt(values[i]);
                var velue2 = parseInt(values[j]);
                if( velue1===velue2 ){
                    return true;
                }
            }
        }
        return false;
    }

    function ownSmallValue(values, minValue) {
        for( var i=0; i<values.length; i++ ){
            var value = parseInt(values[i]);
            var max = parseInt(minValue);
            if( value<max ){
                return true;
            }
        }
        return false;
    }

    function ownBiggerValue(values, maxValue) {
        for( var i=0; i<values.length; i++ ){
            var value = parseInt(values[i]);
            var max = parseInt(maxValue);
            if( value>max ){
                return true;
            }
        }
        return false;
    }
</script>
<script>
    $("#hallInfo_back_hallList_btn").click(function () {
        $("#hallInfo_seatMap_div").empty();
        $("#main_div").show();
        $("#hallInfo_div").hide();
    });

    var isCreateSeat = false;
    var seatMap = [];
    $("#addNewHall_btn").click(function () {
        $("#main_div").hide();
        $("#createHall_div").show();
        $("#createHall_setSeatRowCol_p").show();
    });

    $("#createHall_back_hallList_btn").click(function () {
        if( isCreateSeat ){
            var isConfirmed = confirm("返回将丢失所填写的信息！确认返回请点击确定。");
            if( isConfirmed ) {
                $("#seatMap").empty();
                $("#main_div").show();
                $("#createHall_div").hide();
                $("#seatLevel_div").hide();
                isCreateSeat = false;
            }
        }
        else {
            $("#seatMap").empty();
            $("#main_div").show();
            $("#createHall_div").hide();
            $("#seatLevel_div").hide();
            isCreateSeat = false;
        }
    });

    $("#createHall_setSeatRowCol_btn").click(function () {
        isCreateSeat = true;
        seatMap = [];

        $("#createHall_setSeatLevel_p").show();
        $("#seatMap").empty();

        var seat = "<div class='front'>屏幕</div>" +
            "<div id='seat-map'></div>" +
            "<div class='booking-details'>" +
            "<div id='legend'></div>" +
            "</div>";
        $("#seatMap").append(seat);

        var numOfRow = parseInt( $("#numOfRow option:selected").text() );
        var numOfCol = parseInt( $("#numOfCol option:selected").text() );
        var map = [];
        for( var i=0; i<numOfRow; i++ ){
            map[i] = "";
            seatMap[i] = "";
            for( var j=0; j<numOfCol; j++ ){
                map[i] += "a";
                seatMap[i] += "a";
            }
        }

        $("#seat-map").seatCharts({
            map:map,
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
                if (this.status() == 'available') {
                    var row = this.settings.row + 1;
                    var col = this.settings.label;
                    var seat = seatMap[row-1];
                    var seatFront = seat.substring(0, col-1);
                    var seatBack = seat.substring(col, seat.length);
                    seatMap[row-1] = seatFront + '_' + seatBack;
                    return 'none';
                }
                else {
                    var row = this.settings.row + 1;
                    var col = this.settings.label;
                    var seat = seatMap[row-1];
                    var seatFront = seat.substring(0, col-1);
                    var seatBack = seat.substring(col, seat.length);
                    seatMap[row-1] = seatFront + 'a' + seatBack;
                    return "available";
                }
            }
        });
    });

    var seatIDs = [];
    $("#createHall_setSeatLevel_btn").click(function () {
        var isConfirmed = confirm("设置座位等第将无法继续调整座位！");
        if( isConfirmed ){
            $("#createHall_setSeatRowCol_p").hide();
            $("#createHall_setSeatLevel_p").hide();
            $.post("GetAllSeatLevels", function (rs) {
                var res = $.parseJSON(rs);
                if( res.length>0 ){
                    $("#seatMap").empty();
                    var seat = "<div class='front'>屏幕</div>" +
                        "<div id='seat-map'></div>" +
                        "<div class='booking-details'>" +
                        "<div id='legend'></div>" +
                        "</div>";
                    $("#seatMap").append(seat);

                    $("#seat-map").seatCharts({
                        map:seatMap,
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

                    $("#seatLevel_div").empty();
                    $("#seatLevel_div").show();
                    var nameInput = "<p><label>场厅名称 : </label><input id='hallName' onblur='nameInputBlur()' type='text' /></p>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />";
                    $("#seatLevel_div").append(nameInput);
                    seatIDs = [];
                    for( var i=0; i<res.length; i++ ){
                        var seatLevel = res[i];
                        seatIDs[i] = seatLevel.seatID;
                        var infoDiv = "<div id='" + seatLevel.seatID + "_info_div'>" +
                            "<p><label>座位类别 : </label><input type='text' value='" + seatLevel.name + "' readonly /></p>" +
                            "<p><label>价格比例 : </label><input type='text' value='" + seatLevel.percent + "%' readonly /></p>" +
                            "<p><label>座位行号 : </label><input type='text' id='" + seatLevel.seatID + "_seatRow' placeholder='例如：1_2_10' /></p>" +
                            "</div>" +
                            "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />";
                        $("#seatLevel_div").append(infoDiv);
                    }
                    var submitBtn = "<button onclick='submitHall()'>确认提交</button>";
                    $("#seatLevel_div").append(submitBtn);
                }
                else{
                    alert("请先添加座位类型！");
                }
            });
        }
    });
</script>
</body>
</html>
