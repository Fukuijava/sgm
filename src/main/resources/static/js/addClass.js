var thisCount;
var countEvent;
var nameArray = new Array();
$(function() {

    thisCount = $("#nameLabel").html();
    thisCount = Number(thisCount);
    $("#count").html(thisCount);

    $('button#plus1').click(function(){
        if(thisCount >= 50){
            return false;
        }
        thisCount = thisCount + 1;
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-4">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
    });
    $('button#plus5').click(function(){
        for (let i = 0; i < 5; i++) {
        if(thisCount >= 50){
            return false;
        }
        thisCount = thisCount + 1;
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-4">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#plus10').click(function(){
        for (let i = 0; i < 10; i++) {
        if(thisCount >= 50){
            return false;
        }
        thisCount = thisCount + 1;
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-4">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#plus30').click(function(){
        for (let i = 0; i < 30; i++) {
        if(thisCount >= 50){
            return false;
        }
        thisCount = thisCount + 1;
        var nameText = '' +
        '<div class="row mb-3" id="inNameDiv">' +
        '<label for="nameText" class="col-sm-1 col-form-label">(' + thisCount +')</label>' +
        '<div class="col-sm-4">' +
        '<input type="text" class="form-control" id="nameText">' +
        '</div>' +
        '</div>';
        $(nameText).appendTo($('#outNameDiv'));
        }
    });
    $('button#minus1').click(function(){
        if(thisCount <= 0){
            return false;
        }
        thisCount = thisCount - 1;
        $('div').remove('#inNameDiv:last');
    });
    $('button#minus5').click(function(){
        for (let i = 0; i < 5; i++) {
        if(thisCount <= 0){
            return false;
        }
        thisCount = thisCount - 1;
        $('div').remove('#inNameDiv:last');
        }
    });
    $('button#minus10').click(function(){
        for (let i = 0; i < 10; i++) {
        if(thisCount <= 0){
            return false;
        }
        thisCount = thisCount - 1;
        $('div').remove('#inNameDiv:last');
        }
    });
    $('button#minus30').click(function(){
        for (let i = 0; i < 30; i++) {
        if(thisCount <= 0){
            return false;
        }
        thisCount = thisCount - 1;
        $('div').remove('#inNameDiv:last');
        }
    });
});

function createNameArray(){
    const ItemList = document.getElementsByClassName("form-control");
    for(let i = 0; i < ItemList.length; i++) {
    nameArray[i] = ItemList.item(i).value;
    }
    console.log(nameArray);

    document.getElementById("out").innerHTML = '<label><input name="studentNames" value="'+nameArray+'"></label>';
}