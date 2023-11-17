var statusArray = new Array();

//$(function() {
//    var arrayCount = 0;
//    var classId = document.getElementById('classId').value;
//    var studentCount = document.getElementById('studentCount').value;
//    var curriculumCount = document.getElementById('curriculumCount').value;
//    $('.choiceSemester').on('click', function() {
//    var semesterId = $(this).val();
//        $.ajax({
//            type: "POST",
//            url: "/changeSemester",
//            data: { semesterId:semesterId, classId:classId},
//        }).done(function( response ) {
//        $('#list_form').css({
//        'width':'70%',
//        'height':'auto',
//        'float':'left',
//        'visibility':'visible'
//        });
//        $('#choiceSemesterDiv').css({
//        'display':'none'
//        });
//        $(".classCurriculum").empty();
//            arrayCount = 0;
//            for (i = 1, count1=0; count1 < studentCount; i++, count1++){
//                for(j = 2, count2=0; count2 < curriculumCount; j++, count2++, arrayCount++){
//                    listForm.rows[i].cells[j].innerHTML =
//                    '<input type="number"  class="evaluation" min="1" max="5" value="'+ response[arrayCount] +'"/>';
//                }
//            }
//        });
//    });
//});


function createStatusArray(){
    const ItemList = document.getElementsByClassName("status");
    statusArray[i] = ItemList.item(i).value;
    document.getElementById("output").innerHTML = '<label><input name="statuses" value="'+statusArray+'"></label>';
}