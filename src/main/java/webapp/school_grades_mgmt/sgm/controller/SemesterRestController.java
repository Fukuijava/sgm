package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.List;
@RestController
public class SemesterRestController {
    private final ClassDetailService service;

    @Autowired
    public SemesterRestController(ClassDetailService service) {
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
