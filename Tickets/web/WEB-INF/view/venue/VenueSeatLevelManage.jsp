
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.venue.VenueSeatLevel" %>
<%@ page import="tickets.model.Result" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Seat Level Manage</title>

    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
</head>
<body>
<div class="common">
    <div class="main bigMain">
        <div id="aSign" class="title">
            <a class="active">座位等第管理</a>
            <b>·</b>
            <a href="VenueHallPage">场厅管理</a>
            <b>·</b>
            <a href="VenuePlanManage">计划管理</a>
            <b>·</b>
            <a href="VenueBaseInfo">个人信息</a>
        </div>

        <div id="addNewSeatLevel_btn_div">
            <button id="addNewSeatLevel_btn">添加新的座位类别</button>
            <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
        </div>

        <div id="seatLevel_div">

        </div>
        <div id="addNewSeatLevel_div" style="display: none">
            <button id="backSeatLevelList">🔙</button>
            <p><label>座位名称：</label><input type="text" id="seatLevelName"></p>
            <p><label>价格比例：</label><input type="text" id="seatLevelPricePercent" placeholder="例如：90"><label>%</label></p>
            <button id="submitSeatLevel">提交</button>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script src="../javascript/deleteSpace.js"></script>
<script>
    var numOfSeatLevel = 0;
    $(function () {
       $.post("GetAllSeatLevels", function (rs) {
           var res = $.parseJSON(rs);
           for( var i=0; i<res.length; i++ ){
               numOfSeatLevel++;
               var seatLevel = res[i];
               var infoDiv = "<div id='" + seatLevel.seatID + "_info_div'>" +
                   "<p><label>座位名称：</label><input type='text' value='" + seatLevel.name + "' readonly /></p>" +
                   "<p><label>价格比例：</label><input type='text' value='" + seatLevel.percent + "%' readonly /></p>" +
                   "<button id='btn_" + seatLevel.venueID + "_" + seatLevel.seatID + "_delete' onclick='deleteSeatLevel(this)'>删除该类别</button>" +
                   "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                   "</div>";
               $("#seatLevel_div").append(infoDiv);
           }
       });
    });
</script>
<script>
    function deleteSeatLevel(obj) {
        var isConfirmed = confirm("确认删除该类别座位？");
        if( isConfirmed ){
            var temp1 = $(obj).attr("id");
            var temp2 = temp1.split("_");
            var venueID = temp2[1];
            var seatID = temp2[2];
            var data = {"venueID":venueID, "seatID":seatID};
            $.post("DeleteSeatLevel", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    numOfSeatLevel--;
                    $("#" + seatID + "_info_div").remove();
                }
                else{
                    alert(res.message);
                }
            });
        }
    }
</script>
<script>
    var nameReady = false;
    var percentReady = false;
    $("#addNewSeatLevel_btn").click(function () {
        if( numOfSeatLevel>=3 ){
            alert("最多有 三种 类别座位！")
        }
        else {
            $("#addNewSeatLevel_btn_div").hide();
            $("#seatLevel_div").hide();
            $("#addNewSeatLevel_div").show();

            $("#seatLevelName").removeClass("borderRed");
            $("#seatLevelPricePercent").removeClass("borderRed");
        }
    });

    $("#backSeatLevelList").click(function () {
        var name = $("#seatLevelName").val().toString();
        name = deleteSpace(name);
        var percent = $("#seatLevelPricePercent").val().toString();
        percent = deleteSpace(percent);
        if( name.length>0 || percent.length>0 ){
            var isConfirmed = confirm("返回将丢失填写信息！请三思！");
            if( isConfirmed ){
                $("#addNewSeatLevel_btn_div").show();
                $("#seatLevel_div").show();
                $("#addNewSeatLevel_div").hide();
                $("#seatLevelName").val("");
                $("#seatLevelPricePercent").val("");
            }
        }
        else {
            $("#addNewSeatLevel_btn_div").show();
            $("#seatLevel_div").show();
            $("#addNewSeatLevel_div").hide();
        }
    });

    $("#seatLevelName").blur(function () {
        var name = $("#seatLevelName").val().toString();
        name = deleteSpace(name);
        $("#seatLevelName").val(name);
        if( name.length>0 ){
            $("#seatLevelName").removeClass("borderRed");
            nameReady = true;
        }
        else {
            $("#seatLevelName").addClass("borderRed");
            nameReady = false;
        }
    });

    $("#seatLevelPricePercent").blur(function () {
        var percentReg = /^([1-9])([0-9])*$/;
        var percent = $("#seatLevelPricePercent").val().toString();
        $("#seatLevelPricePercent").val(percent);
        percent = deleteSpace(percent);
        if( percentReg.test(percent) ){
            $("#seatLevelPricePercent").removeClass("borderRed");
            nameReady = true;
            percentReady = true;
        }
        else {
            $("#seatLevelPricePercent").addClass("borderRed");
            percentReady = false;
        }
    });

    $("#submitSeatLevel").click(function () {
        if( nameReady && percentReady ){
            var name = $("#seatLevelName").val().toString();
            var percent = $("#seatLevelPricePercent").val().toString();
            var data = {"name":name, "percent":percent};
            $.post("AddNewSeatLevel", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    var temp1 = res.message;
                    var temp2 = temp1.split("-");
                    var venueID = temp2[0];
                    var seatID = temp2[1];
                    var infoDiv = "<div id='" + seatID + "_info_div'>" +
                        "<p><label>座位名称：</label><input type='text' value='" + name + "' readonly /></p>" +
                        "<p><label>价格比例：</label><input type='text' value='" + percent + "%' readonly /></p>" +
                        "<p><button id='btn_" + venueID + "_" + seatID + "_delete' onclick='deleteSeatLevel(this)'>删除该类别</button>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#seatLevel_div").append(infoDiv);
                    $("#seatLevelName").val("");
                    $("#seatLevelPricePercent").val("");
                    $("#backSeatLevelList").click();
                    numOfSeatLevel++;
                }
                else {
                    alert(res.message);
                }
            });
        }
        else {
            alert("请填写正确 名称 和 价格！")
        }
    });
</script>
</body>
</html>
