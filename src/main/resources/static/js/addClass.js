var thisCount;

$(function() {
    thisCount = $("#nameLabel").html();
    thisCount = Number(thisCount);
    $("#count").html(thisCount);
    thisCount = Math.max(0, thisCount);
    thisCount = Math.min(50, thisCount);






    $('button#plus1').click(function(){
        thisCount = thisCount + 1;
//        Math.max(minNum, Math.min(maxNum, thisCount + 40));
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-6">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
    });
    $('button#plus5').click(function(){
        for (let i = 0; i < 5; i++) {
        thisCount = thisCount + 1;
//        Math.max(minNum, Math.min(maxNum, thisCount + 40));
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-6">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#plus10').click(function(){
        for (let i = 0; i < 10; i++) {
        thisCount = thisCount + 1;
//        Math.max(minNum, Math.min(maxNum, thisCount + 40));
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-6">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#plus30').click(function(){
        for (let i = 0; i < 30; i++) {
        thisCount = thisCount + 1;
//        Math.max(minNum, Math.min(maxNum, thisCount + 40));
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-6">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#minus1').click(function(){
    thisCount = thisCount - 1;
//    Math.max(minNum, Math.min(maxNum, thisCount - 40));
    $('div').remove('#inNameDiv:last');
    });
    $('button#minus5').click(function(){
        for (let i = 0; i < 5; i++) {
        thisCount = thisCount - 1;
//        Math.max(minNum, Math.min(maxNum, thisCount - 40));
        $('div').remove('#inNameDiv:last');
        }
    });
    $('button#minus10').click(function(){
        for (let i = 0; i < 10; i++) {
        thisCount = thisCount - 1;
//        Math.max(minNum, Math.min(maxNum, thisCount - 40));
        $('div').remove('#inNameDiv:last');
        }
    });
    $('button#minus30').click(function(){
        for (let i = 0; i < 30; i++) {
        thisCount = thisCount - 1;
//        Math.max(minNum, Math.min(maxNum, thisCount - 40));
        $('div').remove('#inNameDiv:last');
        }
    });
});

function countCheck(thisCount) {
    if(thisCount <){

    }

}

