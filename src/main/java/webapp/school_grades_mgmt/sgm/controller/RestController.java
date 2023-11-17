package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.List;
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ClassDetailService service;

    @Autowired
    public RestController(ClassDetailService service) {
        this.service = service;
    }

    /**
     * 授業態度画面の学期変更処理
     */
    @PostMapping(value="/changeSemester")
    List<Integer> changeSemester(@RequestParam("semesterId") Integer  semesterId,
                                @RequestParam("classId") Integer  classId) {
        List<ClassDetailController.studentsRecord> classStudentList = service.findStudents(classId);
        return service.findClassAttitudeEvaluation(classStudentList,classId,semesterId);
    }
}
