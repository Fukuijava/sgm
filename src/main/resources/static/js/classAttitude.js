$(function() {
    var classId = document.getElementById('classId').value;
    var studentCount = document.getElementById('studentCount').value;
    var curriculumCount = document.getElementById('curriculumCount').value;
    $('#aaa').on('click', function() {
        var Semester = document.getElementById('semester');
        var selectSemester = Semester.selectedIndex;
        var semesterId = Semester.options[selectSemester].value;
        $.ajax({
            type: "POST",
            url: "/changeSemester",
            data: { semesterId:semesterId, classId:classId},
        }).done(function( response ) {
        $(".classCurriculum").empty();
            var arrayCount = 0;
            for (i=1, count1=0; count1 < studentCount; i++, count1++){
                for(j=2, count2=0; count2 < curriculumCount; j++, count2++, arrayCount++){
                    listForm.rows[i].cells[j].innerHTML =
                    '<input type="number"  class="evaluation" min="1" max="5" value="'+ response[arrayCount][1] +'"/>';
                }
            }
        });
    });
});


function createEvaluationArray(){
    var evaluationArray = new Array();
    const ItemList = document.getElementsByClassName("evaluation");
    for(let i = 0; i < ItemList.length; i++) {
    evaluationArray[i] = ItemList.item(i).value;
    }
    console.log(evaluationArray);
    document.getElementById("output").innerHTML = '<input name="classAttitudes" value="'+evaluationArray+'">';
}

function count(x){

}