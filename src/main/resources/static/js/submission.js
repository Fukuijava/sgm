var statusArray = new Array();

window.addEventListener('DOMContentLoaded', function(){
    var ItemList = document.getElementsByClassName("status");
    var studentCount = document.getElementById('studentCount').value;
    var submissionCount = document.getElementById('submissionCount').value;
    for(var i = 0; i < studentCount; i++) {
        for(var j = 0; j < submissionCount; j++) {
            if(ItemList.item(i * submissionCount + j).value == "true"){
                document.getElementById(i * submissionCount + j).innerHTML = '<input checked class="status" type="checkbox" th:value="${submissionStatusList[__${studentCount.index * submissionCount.size + submissionCount.index}__]}">';
            }
        }
    }
});

function createStatusArray(){
    const ItemList = document.getElementsByClassName("status");
    for(let i = 0; i < ItemList.length; i++) {
        statusArray[i] = ItemList.item(i).checked;
    }
    console.log(statusArray);
    document.getElementById("output").innerHTML = '<label><input name="statuses" value="'+statusArray+'"></label>';
}

