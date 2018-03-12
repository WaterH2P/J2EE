
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Hall Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
</head>
<body>
<div class="common">
    <div class="main">
        <div id="aSign" class="title">
            <a class="active">场厅管理</a>
            <b>·</b>
            <a href="/VenuePlanManage">计划管理</a>
            <b>·</b>
            <a href="/VenueBaseInfo">个人信息</a>
        </div>

        <button id="addNewHall">新建场厅</button>
        <div id="hallList_div">
        </div>

        <button id="backHallList" style="display: none">🔙</button>
        <div id="createHall_div" style="display:none;">
            <p id="rowColBtn_p">
                <label>排：</label><select id="numOfRow"></select>
                <label>列：</label><select id="numOfCol"></select>
                <button id="submitRowCol">确定</button>
            </p>
            <div class="demo" id="seatMap">
            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script>
    $(function () {
        var numOfRowMax = 15;
        var numOfRolMax = 15;
        for( var i=0; i<numOfRowMax; i++ ){
            var value = i+1;
            var row = "<option value='" + value + "'>" + value + "</option>";
            $("#numOfRow").append(row);
        }
        for( var i=0; i<numOfRolMax; i++ ){
            var value = i+1;
            var rol = "<option value='" + value + "'>" + value + "</option>";
            $("#numOfCol").append(rol);
        }
    });
</script>
<script>
    var isCreateSeat = false;

    $("#addNewHall").click(function () {
        $("#addNewHall").hide();
        $("#hallList_div").hide();
        $("#backHallList").show();
        $("#createHall_div").show();
    });

    $("#backHallList").click(function () {
        var back = confirm("返回将丢失所填写的信息！确认返回请点击确定。");
        if( back ) {
            $("#seatMap").empty();
            $("#addNewHall").show();
            $("#hallList_div").show();
            $("#backHallList").hide();
            $("#createHall_div").hide();
            isCreateSeat = false;
        }
    });

    $("#submitRowCol").click(function () {
        isCreateSeat = true;

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
            for( var j=0; j<numOfCol; j++ ){
                map[i] += "a";
            }
        }

        $("#seat-map").seatCharts({
            map:map,
            naming: {
                top: false, //不显示顶部横坐标（行）
                left:true,
                getLabel: function(character, row, column) { //返回座位信息
                    return column;
                }
            },
            legend: {
                node: $('#legend'),
                items: [
                    [ 'a', 'available',   '位置' ],
                    [ 'a', 'none', '过道']
                ]
            },
            click: function() {
                if (this.status() == 'available') {
                    return 'none';
                }
                else {
                    return "available";
                }
            }
        });
    });

</script>
</body>
</html>
