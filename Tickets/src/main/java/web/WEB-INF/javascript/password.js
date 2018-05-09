var passwordReady = false;
$("#userPassword").bind("input propertychange change", function(){
    var password = $("#userPassword").val().toString();
    password = passwordDeleteSpace(password);
    $("#userPassword").val(password);

    var passwordAgain = $("#userPasswordAgain").val().toString();
    if( passwordAgain.length!==0 ){
        if( passwordAgain!==password ){
            $("#userPassword").addClass("border-red");
            $("#userPasswordAgain").addClass("border-red");
            passwordReady = false;
        }
        else {
            $("#userPassword").removeClass("border-red");
            $("#userPasswordAgain").removeClass("border-red");
            passwordReady = true;
        }
    }
    else {
        passwordReady = false;
    }
});

$("#userPasswordAgain").bind("input propertychange change", function(){
    var passwordAgain = $("#userPasswordAgain").val().toString();
    passwordAgain = passwordDeleteSpace(passwordAgain);
    $("#userPasswordAgain").val(passwordAgain);

    var password = $("#userPassword").val().toString();
    if( passwordAgain!==password ){
        $("#userPassword").addClass("border-red");
        $("#userPasswordAgain").addClass("border-red");
        passwordReady = false;
    }
    else {
        $("#userPassword").removeClass("border-red");
        $("#userPasswordAgain").removeClass("border-red");
        passwordReady = true;
    }
});

function passwordDeleteSpace(str) {
    return str.replace(/\s/g, "");
}