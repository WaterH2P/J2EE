
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tickets.model.VenueSeatLevel" %>
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

        <p id="addNewSeatLevel_p">
            <button id="addNewSeatLevel_btn">添加新的座位类别</button>
        </p>

        <div id="seatLevel_div">

        </div>
        <div id="addNewSeatLevel_div" style="display: none">
            <button id="backSeatLevelList">🔙</button>
            <p><label>名称：</label><input type="text" id="seatLevelName"></p>
            <p><label>价格：</label><input type="text" id="seatLevelPrice" placeholder="例如：9.0"></p>
            <button id="submitSeatLevel">提交</button>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js" ></script>
<script>
    var numOfSeatLevel = 0;
    $(function () {
       $.post("GetAllSeatLevels", function (rs) {
           var res = $.parseJSON(rs);
           for( var i=0; i<res.length; i++ ){
               numOfSeatLevel++;
               var seatLevel = res[i];
               var infoDiv = "<div id='" + seatLevel.seatID + "_info_div'>" +
                   "<p><label>名称 : </label><input type='text' value='" + seatLevel.name + "' readonly /></p>" +
                   "<p><label>价格 : </label><input type='text' value='¥" + seatLevel.price + "' readonly /></p>" +
                   "<button id='btn_" + seatLevel.venueID + "_" + seatLevel.seatID + "_delete' onclick='deleteSeatLevel(this)'>删除该类别</button>" +
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

    var nameReady = false;
    var priceReady = false;
    $("#addNewSeatLevel_btn").click(function () {
        if( numOfSeatLevel>=3 ){
            alert("最多有 三种 类别座位！")
        }
        else {
            $("#addNewSeatLevel_p").hide();
            $("#seatLevel_div").hide();
            $("#addNewSeatLevel_div").show();

            $("#seatLevelName").removeClass("borderRed");
            $("#seatLevelPrice").removeClass("borderRed");
        }
    });

    $("#backSeatLevelList").click(function () {
        var name = $("#seatLevelName").val().toString();
        name = deleteSpace(name);
        var price = $("#seatLevelPrice").val().toString();
        price = deleteSpace(price);
        if( name.length>0 || price.length>0 ){
            var isConfirmed = confirm("返回将丢失填写信息！请三思！");
            if( isConfirmed ){
                $("#addNewSeatLevel_p").show();
                $("#seatLevel_div").show();
                $("#addNewSeatLevel_div").hide();
                $("#seatLevelName").val("");
                $("#seatLevelPrice").val("");
            }
        }
        else {
            $("#addNewSeatLevel_p").show();
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

    $("#seatLevelPrice").blur(function () {
        var priceReg = /^([1-9])+([.0-9])([0-9])+$/;
        var price = $("#seatLevelPrice").val().toString();
        $("#seatLevelPrice").val(price);
        price = deleteSpace(price);
        if( priceReg.test(price) ){
            $("#seatLevelPrice").removeClass("borderRed");
            nameReady = true;
            priceReady = true;
        }
        else {
            $("#seatLevelPrice").addClass("borderRed");
            priceReady = false;
        }
    });

    $("#submitSeatLevel").click(function () {
        if( nameReady && priceReady ){
            var name = $("#seatLevelName").val().toString();
            var price = $("#seatLevelPrice").val().toString();
            var data = {"name":name, "price":price};
            $.post("AddNewSeatLevel", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    var temp1 = res.message;
                    var temp2 = temp1.split("-");
                    var venueID = temp2[0];
                    var seatID = temp2[1];
                    var infoDiv = "<div id='" + seatID + "_info_div'>" +
                        "<p><label>名称 : </label><input type='text' value='" + name + "' readonly /></p>" +
                        "<p><label>价格 : </label><input type='text' value='¥" + price + "' readonly /></p>" +
                        "<p><button id='btn_" + venueID + "_" + seatID + "_delete' onclick='deleteSeatLevel(this)'>删除该类别</button>" +
                        "</div>";
                    $("#seatLevel_div").append(infoDiv);
                    $("#seatLevelName").val("");
                    $("#seatLevelPrice").val("");
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

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }
</script>
</body>
</html>
