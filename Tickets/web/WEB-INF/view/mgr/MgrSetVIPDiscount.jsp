
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set VIP Discount</title>
    <link rel="stylesheet" type="text/css" href="../../stylesheet/common.css">
    <link rel="stylesheet" type="text/css" href="../../stylesheet/title.css">
</head>
<body>
<div class="common">
    <div class="main minMain bigMain">
        <div id="aSign" class="title">
            <a class="active">VIP 优惠</a>
            <b>·</b>
            <a href="MgrExamineVenueRegister">审批注册</a>
            <b>·</b>
            <a href="MgrExamineVenueInfoChange">审批修改信息</a>
            <b>·</b>
            <a href="MgrInfo">个人信息</a>
        </div>

        <div class="title_2" id="title_2">
            <a class="active" id="show_VIPLevelDiscount_a" onclick="show_VIPLevelDiscount_div()">等级优惠</a>
            <b>·</b>
            <a id="show_couponDiscount_a" onclick="show_couponDiscount_div()">优惠券</a>
        </div>

        <div id="VIPLevelDiscount_div">
            <div id="VIPLevel_div">
                <p>
                    <input type="text" id="setMaxVIPLevel_input" placeholder="重新设置VIP最大等级(正数)">
                    <button id="setMaxVIPLevel_btn">确定</button>
                </p>
                <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />

                <div id="showVIPLevelDiscount_div">
                </div>

                <div id="setVIPLevelDiscount_div" style="display: none">
                </div>
            </div>
        </div>

        <div id="couponDiscount_div" style="display: none">
            <div id="Coupon_div">
                <div id="createCoupon_div">
                    <button id="createCoupon_btn">添加优惠券</button>
                    <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                </div>

                <div id="showCouponDiscount_div">
                </div>

                <div id="setCouponDiscount_div" style="display: none">
                    <div>
                        <button onclick='setCouponDiscount_back_showCouponDiscount()'>🔙</button>
                        <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                    </div>
                    <div id='createNewCoupon_div'>
                        <p>
                            <label>名称：</label>
                            <input type='text' id='newCouponName_input' onblur='nameValidTest(this)'/>
                        </p>
                        <p>
                            <label>优惠金额：</label>
                            <input type='text' id='newCouponDiscount_input' onblur='discountValidTest(this)'/>
                        </p>
                        <p>
                            <label>所需积分：</label>
                            <input type='text' id='newCouponPoint_input' onclick='pointValidTest(this)'/>
                        </p>
                        <button onclick='submitNewCoupon()'>提交</button>
                        <hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../../javascript/jquery/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        getAllVIPLevelInfosAndShow();
        getAllCouponInfosAndShow();
    })
</script>


<%-- show_VIPLevelDiscount_div 部分的函数 --%>
<script>
    function show_VIPLevelDiscount_div() {
        $("#VIPLevelDiscount_div").show();
        $("#show_VIPLevelDiscount_a").addClass("active");
        $("#couponDiscount_div").hide();
        $("#show_couponDiscount_a").removeClass("active");
    }

    function getAllVIPLevelInfosAndShow() {
        $.post("GetAllVIPLevelInfos", function (rs) {
            var res = $.parseJSON(rs);
            $("#showVIPLevelDiscount_div").empty();
            if( res.length>0 ){
                $.each(res, function (index, value, array) {
                    var vipLevel = value.vipLevel;
                    var percent = value.percent;
                    var point = value.point;
                    var infoDiv = "<div id='vipLevel_" + vipLevel + "_info_div'>" +
                        "<p>" +
                        "<label>等级 " + vipLevel + "：</label>" +
                        "享受折扣：<input type='text' value='" + percent + " %' readonly/>" +
                        "</p>" +
                        "<p>" +
                        "<label style='visibility: hidden'>等级 " + vipLevel + "：</label>" +
                        "所需积分：<input type='text' value='" + point + "' readonly/>" +
                        "</p>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#showVIPLevelDiscount_div").append(infoDiv);
                });
            }
            else{
                var infoDiv = "<p>还未设置会员等级优惠！</p>";
                $("#showVIPLevelDiscount_div").append(infoDiv);
            }
        })
    }

    function deleteSpace(str) {
        return str.replace(/\s/g, "");
    }

    function setVIPLevelDiscount_back_showVIPLevelDiscount() {
        var isConfirmed = confirm("返回将丢失填写的信息！");
        if( isConfirmed ){
            $("#title_2").show();
            $("#showVIPLevelDiscount_div").show();
            $("#setVIPLevelDiscount_div").empty();
            $("#setVIPLevelDiscount_div").hide();
        }
    }

    var percentReg = /^[1-9]([0-9]){0,1}(0){0,1}$/;
    function percentValidTest(obj) {
        var percent = $(obj).val().toString();
        if( percentReg.test(percent) ){
            $(obj).removeClass("borderRed");
        }
        else {
            $(obj).addClass("borderRed");
        }
    }

    var pointReg = /^[1-9]([0-9])*$/;
    function pointValidTest(obj) {
        var point = $(obj).val().toString();
        if( pointReg.test(point) ){
            $(obj).removeClass("borderRed");
        }
        else {
            $(obj).addClass("borderRed");
        }
    }

    function submitVIPLevelChange(obj) {
        var temp1 = $(obj).attr("id");
        var temp2 = temp1.split("_");
        var maxLevel = parseInt(temp2[1]);
        var infoReady = true;
        var percentReg = /^[1-9]([0-9]){0,1}(0){0,1}$/;
        var pointReg = /^[1-9]([0-9])*$/;
        for( var i=0; i<maxLevel; i++ ){
            var vipLevel = i + 1;
            var percent = $("#vipPercent_" + vipLevel + "_input" ).val().toString();
            var point = $("#vipPoint_" + vipLevel + "_input" ).val().toString();
            if( !percentReg.test(percent) || !pointReg.test(point) ){
                infoReady = false;
                break;
            }
        }
        if( infoReady ){
            var vipInfos = [];
            for( var i=0; i<maxLevel; i++ ){
                var vipLevel = i + 1;
                var percent = $("#vipPercent_" + vipLevel + "_input" ).val().toString();
                var point = $("#vipPoint_" + vipLevel + "_input" ).val().toString();
                var vipInfo = {"vipLevel":vipLevel, "percent":percent, "point":point};
                vipInfos[vipInfos.length] = vipInfo;
            }
            var data = {"vipInfos":JSON.stringify(vipInfos)};
            $.post("ChangeVIPLevelInfos", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    getAllVIPLevelInfosAndShow();
                    $("#title_2").show();
                    $("#showVIPLevelDiscount_div").show();
                    $("#setVIPLevelDiscount_div").empty();
                    $("#setVIPLevelDiscount_div").hide();
                }
                else {
                    alert(res.message);
                }
            });
        }
        else {
            alert("请正确填写！");
        }
    }

</script>
<script>
    $("#setMaxVIPLevel_btn").click(function () {
        var maxVIPLevel = $("#setMaxVIPLevel_input").val().toString();
        maxVIPLevel = deleteSpace(maxVIPLevel);
        $("#setMaxVIPLevel_input").val(maxVIPLevel);

        var maxVIPLevelRegex = /^[1-9]([0-9])*$/;
        if( maxVIPLevelRegex.test(maxVIPLevel) ){
            $("#setMaxVIPLevel_input").val("");

            $("#title_2").hide();
            $("#showVIPLevelDiscount_div").hide();
            $("#setVIPLevelDiscount_div").empty();
            $("#setVIPLevelDiscount_div").show();

            var backBtn = "<div>" +
                "<button onclick='setVIPLevelDiscount_back_showVIPLevelDiscount()'>🔙</button>" +
                "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                "</div>";
            $("#setVIPLevelDiscount_div").append(backBtn);

            var maxLevel = parseInt(maxVIPLevel);
            for( var i=0; i<maxLevel; i++ ){
                var vipLevel = i + 1;
                var infoDiv = "<div id='set_" + vipLevel + "_info_div'>" +
                    "<p>" +
                        "<label>等级 " + vipLevel + "：</label>" +
                        "享受折扣：<input type='text' id='vipPercent_" + vipLevel + "_input' onblur='percentValidTest(this)'/>" +
                        "<label>%</label>" +
                    "</p>" +
                    "<p>" +
                        "<label style='visibility: hidden'>等级 " + vipLevel + "：</label>" +
                        "所需积分：<input type='text' id='vipPoint_" + vipLevel + "_input' placeholder='正整数' onblur='pointValidTest(this)' />" +
                        "<label style='visibility: hidden'>%</label>" +
                    "</p>" +
                    "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                    "</div>";
                $("#setVIPLevelDiscount_div").append(infoDiv);
                if( i==(maxLevel-1) ){
                    var submitBtn = "<button id='submitVIPLevelChange_" + maxLevel + "_btn' onclick='submitVIPLevelChange(this)'>提交</button>";
                    $("#setVIPLevelDiscount_div").append(submitBtn);
                }
            }
        }
    });
</script>
<%-- show_VIPLevelDiscount_div 部分的函数 --%>


<%-- couponDiscount_div 部分的函数 --%>
<script>
    function show_couponDiscount_div() {
        $("#VIPLevelDiscount_div").hide();
        $("#show_VIPLevelDiscount_a").removeClass("active");
        $("#couponDiscount_div").show();
        $("#show_couponDiscount_a").addClass("active");
    }

    function getAllCouponInfosAndShow() {
        $.post("GetAllCouponInfos", function (rs) {
            var res = $.parseJSON(rs);
            $("#showCouponDiscount_div").empty();
            if( res.length>0 ){
                if( res.length>0 ){
                    $.each(res, function (index, value, array) {
                        var couponID = value.couponID;
                        var name = value.name;
                        var discount = value.discount;
                        var point = value.point;
                        var infoDiv = "<div id='coupon_" + couponID + "_info_div'>" +
                            "<p><label>优惠券：</label><input type='text' value='" + name + "' readonly/></p>" +
                            "<p><label>优惠金额：</label><input type='text' value='" + discount + "' readonly/></p>" +
                            "<p><label>所需积分：</label><input type='text' value='" + point + "' readonly/></p>" +
                            "<button id='btn_" + couponID + "_delete' onclick='deleteCoupon(this)'>删除优惠券</button>" +
                            "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                            "</div>";
                        $("#showCouponDiscount_div").append(infoDiv);
                    });
                }
                else{
                    var infoDiv = "<p>还未设置优惠券！</p>";
                    $("#showCouponDiscount_div").append(infoDiv);
                }
            }
        });
    }

    function deleteCoupon(obj) {
        var isConfirmed = confirm("确认删除？");
        if( isConfirmed ){
            var temp1 = $(obj).attr("id");
            var temp2 = temp1.split("_");
            var couponID = temp2[1];
            var data = {"couponID":couponID};
            $.post("DeleteCoupon", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    $("#coupon_" + couponID + "_info_div").remove();
                }
                else {
                    alert(res.message);
                }
            });
        }
    }

    function setCouponDiscount_back_showCouponDiscount() {
        var isConfirmed = confirm("返回将丢失填写的信息！");
        if( isConfirmed ){
            $("#title_2").show();
            $("#showCouponDiscount_div").show();
            $("#setCouponDiscount_div").empty();
            $("#setCouponDiscount_div").hide();
        }
    }

    var discountReg = /^((0.)[1-9]*)|[1-9]+[0-9]*([.]{0,1})[0-9]*$/;
    function discountValidTest(obj) {
        var discount = $(obj).val().toString();
        if( discountReg.test(discount) ){
            $(obj).removeClass("borderRed");
        }
        else {
            $(obj).addClass("borderRed");
        }
    }

    var nameReady = false;
    function nameValidTest(obj) {
        var name = $(obj).val().toString();
        name = deleteSpace(name);
        if( name.length>0 ){
            $(obj).removeClass("borderRed");
            nameReady = true;
        }
        else {
            $(obj).addClass("borderRed");
            nameReady = false;
        }
    }

    function submitNewCoupon() {
        var name = $("#newCouponName_input").val().toString();
        name = deleteSpace(name);
        $("#newCouponName_input").val(name);
        var discount = $("#newCouponDiscount_input").val().toString();
        discount = deleteSpace(discount);
        $("#newCouponDiscount_input").val(discount);
        var point = $("#newCouponPoint_input").val().toString();
        point = deleteSpace(point);
        $("#newCouponPoint_input").val(point);
        if( nameReady && discountReg.test(discount) && pointReg.test(point) ){
            var data = {"name":name, "discount":discount, "point":point};
            $.post("AddNewCoupon", data, function (rs) {
                var res = $.parseJSON(rs);
                if( res.result ){
                    $("#title_2").show();
                    $("#showCouponDiscount_div").show();
                    $("#setCouponDiscount_div").hide();
                    $("#createCoupon_div").show();

                    emptyCreateNewCouponInput();
                    var couponID = res.message;
                    var infoDiv = "<div id='coupon_" + couponID + "_info_div'>" +
                        "<p><label>优惠券：</label><input type='text' value='" + name + "' readonly/></p>" +
                        "<p><label>优惠金额：</label><input type='text' value='" + discount + "' readonly/></p>" +
                        "<p><label>所需积分：</label><input type='text' value='" + point + "' readonly/></p>" +
                        "<button id='btn_" + couponID + "_delete' onclick='deleteCoupon(this)'>删除优惠券</button>" +
                        "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                        "</div>";
                    $("#showCouponDiscount_div").append(infoDiv);
                }
                else {
                    alert(res.message);
                }
            });
        }
        else {
            alert("请输入正确信息！");
        }
    }

    function emptyCreateNewCouponInput() {
        $("#newCouponName_input").val("");
        $("#newCouponDiscount_input").val("");
        $("#newCouponPoint_input").val("");
    }
</script>
<script>
    $("#createCoupon_btn").click(function () {
        $("#title_2").hide();
        $("#showCouponDiscount_div").hide();
        $("#setCouponDiscount_div").show();
        $("#createCoupon_div").hide();

        emptyCreateNewCouponInput();
    });
</script>
</body>
</html>
