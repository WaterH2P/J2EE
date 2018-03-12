
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Hall Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/account/sign.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/hallSeat.css">
</head>
<body>
<div class="sign">
    <div class="main">
        <div id="aSign" class="title">
            <a class="active">场厅管理</a>
            <b>·</b>
            <a href="/VenuePlanManage">计划管理</a>
            <b>·</b>
            <a href="/VenueBaseInfo">个人信息</a>
        </div>
        <div id="hallList_div">
            <div>
                <button id="addNewHall">新建场厅</button>
            </div>
        </div>
        <div id="createHall_div" style="display:none;">
            <button id="backHallList">🔙</button>
            <div>
                <p>
                    <label>排：</label><select id="numOfRow"></select>
                    <label>列：</label><select id="numOfCol"></select>
                    <button id="submitRowCol">确定</button>
                </p>
                <div>屏幕</div>
                <div id="seat-map">

                </div>

                <div id="legend"></div>
            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../../javascript/jquery/jquery.seat-charts.min.js" ></script>
<script>
    $(function () {
        var numOfRowMax = 25;
        var numOfRolMax = 30;
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
    $("#addNewHall").click(function () {
        $("#hallList_div").hide();
        $("#createHall_div").show();
    });

    $("#backHallList").click(function () {
        var back = confirm("返回将丢失所填写的信息！确认返回请点击确定。");
        if( back ){
            $("#hallList_div").show();
            $("#createHall_div").hide();
        }
    });

    $("#submitRowCol").click(function () {
        var numOfRow = parseInt( $("#numOfRow option:selected").text() );
        var numOfCol = parseInt( $("#numOfCol option:selected").text() );
        var map = [];
        for( var i=0; i<numOfRow; i++ ){
            map[i] = "";
            for( var j=0; j<numOfCol; j++ ){
                map[i] += "a";
            }
        }
        $("#seat-map").empty();
        $("#legend").empty();
        var sc = $("#seat-map").seatCharts({
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
                if (this.status() == 'available') { //若为可选座状态，添加座位
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
