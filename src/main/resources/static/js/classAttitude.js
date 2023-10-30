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
          var str = JSON.stringify(response);
          for (i=1; i < studentCount; i++){
            for(j=2; j < curriculumCount; j++){
                listForm.rows[i].cells[j].innerText = response[0][1];
            }
          }

      });
  }

//listForm.rows[i].cells[j].innerHTML = response[0][1];
//     <input type="number"  min="1" max="5" th:value="${classAttitude[__${studentCount.index}__].classAttitudeEvaluation}"/>
