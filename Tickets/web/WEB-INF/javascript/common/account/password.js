var passwordReady = false;
$("#userPassword").bind("input propertychange change", function(){
    var passwordAgain = $("#userPasswordAgain").val().toString();
    if( passwordAgain.length!==0 ){
        var password = $("#userPassword").val().toString();
        if( passwordAgain!==password ){
            $("#userPassword").css("border","1px solid red");
            $("#userPasswordAgain").css("border","1px solid red");
            passwordReady = false;
        }
        else {
            $("#userPassword").css("border","1px solid #c8c8c8");
            $("#userPassword").css("border-bottom","none");
            $("#userPasswordAgain").css("border","1px solid #c8c8c8");
            $("#userPasswordAgain").css("border-bottom","none");
            passwordReady = true;
        }
    }
    else {
        passwordReady = false;
    }
});

$("#userPasswordAgain").bind("input propertychange change", function(){
    var password = $("#userPassword").val().toString();
    var passwordAgain = $("#userPasswordAgain").val().toString();
    if( passwordAgain!==password ){
        $("#userPassword").css("border","1px solid red");
        $("#userPasswordAgain").css("border","1px solid red");
        passwordReady = false;
    }
    else {
        $("#userPassword").css("border","1px solid #c8c8c8");
        $("#userPassword").css("border-bottom","none");
        $("#userPasswordAgain").css("border","1px solid #c8c8c8");
        $("#userPasswordAgain").css("border-bottom","none");
        passwordReady = true;
    }
});