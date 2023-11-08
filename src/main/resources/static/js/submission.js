var idArray = new Array();

function createIdArray(){
    const ItemList = document.getElementsByClassName("submissionEvaluationId");
    for(let i = 0; i < ItemList.length; i++) {
    idArray[i] = ItemList.item(i).value;
    }
    console.log(idArray);
    document.getElementById("output").innerHTML = '<label><input name="submissionEvaluationId" value="'+idArray+'"></label>';
}