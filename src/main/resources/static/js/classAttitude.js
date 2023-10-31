function changeSemester(){
    var Semester = document.getElementById('semester');
    var selectSemester = Semester.selectedIndex;
    var semesterId = Semester.options[selectSemester].value;
    var classId = document.getElementById('classId').value;
    var studentCount = document.getElementById('studentCount').value;
    var curriculumCount = document.getElementById('curriculumCount').value;
    $.ajax({
        type: "POST",
        url: "/changeSemester",
        data: { semesterId:semesterId, classId:classId},
    }).done(function( response ) {
        $(".classCurriculum").empty();
        var arrayCount = 0;
        for (i=1; i < studentCount+1; i++){
            for(j=2, k=0; k < curriculumCount; j++, k++, arrayCount++){
            listForm.rows[i].cells[j].innerHTML =
                '<input type="number"  min="1" max="5" value="'+ response[arrayCount][1] +'"/>';
            }
        }
    });
}