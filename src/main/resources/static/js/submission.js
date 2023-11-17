var statusArray = new Array();

function createStatusArray(){
    const ItemList = document.getElementsByClassName("status");
    for(let i = 0; i < ItemList.length; i++) {
    statusArray[i] = ItemList.item(i).checked;
    }
    console.log(statusArray);
    document.getElementById("output").innerHTML = '<label><input name="statuses" value="'+statusArray+'"></label>';
}

$(function() {
    const ItemList = document.getElementsByClassName("status");
    for(let i = 0; i < ItemList.length; i++) {
        if(true == ItemList.item(i).value;){
             ItemList.item(i).value;
        }
     ItemList.item(i).value;
    }
    console.log(statusArray);
    document.getElementById("output").innerHTML = '<label><input name="statuses" value="'+statusArray+'"></label>';
});

