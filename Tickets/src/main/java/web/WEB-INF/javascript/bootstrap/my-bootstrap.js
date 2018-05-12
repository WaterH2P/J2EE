function emptyModelMessage(modal, hearder, body, footer) {
    $(modal).on("hide.bs.modal", function () {
        if( hearder!=undefined ){
            $(hearder).text("");
        }
        if( body!=undefined ){
            $(body).text("");
        }
        if( footer!=undefined ){
            $(footer).text("");
        }
    });
}

function deleteSpace(str) {
    return str.replace(/\s/g, "");
}