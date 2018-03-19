function getSeatDistAndShow(planID) {
    var data = {"planID":planID};
    $.post("GetPlanHallInfo", data, function (rs) {
        var res = $.parseJSON(rs);
        var seatDist = res.seatDist;
        var numOfRow = parseInt(res.numOfRow);
        var numOfCol = parseInt(res.numOfCol);
        var seatData = [];
        for( var j=0; j<numOfRow; j++ ){
            seatData[j] = seatDist.substring(0, numOfCol);
            seatDist = seatDist.substring(numOfCol);
        }

        $("#seatMap").empty();
        var seat = "<div class='front'>屏幕</div>" +
            "<div id='seat-map'></div>" +
            "<div class='booking-details'>" +
            "<div id='legend'></div>" +
            "</div>";
        $("#seatMap").append(seat);
        var seatMap = $("#seat-map").seatCharts({
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
                return this.status();
            }
        });
        seatMap.status(seatRowAndCol, 'selected');
    });
}