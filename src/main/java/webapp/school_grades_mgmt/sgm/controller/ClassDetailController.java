package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.GradesBySemesterEntity;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.List;

@Controller
public class ClassDetailController {

    public record studentsRecord(
            Integer studentId, Integer attendanceNumber, String studentName){
    }

    @Autowired
    private ClassDetailService service;

    @GetMapping("/classDetail/{classId}")
    public String classDetail(@RequestParam("classId") Integer classId,
                              Model model) {
        ClassEntity classInfo = service.findClassInfo(classId);
        List<studentsRecord>  classStudents = service.findStudents(classId);
        List<String > classCurriculum = service.findClassCurriculum(classId);
//        List<String > gradeslist = service.findGrades(classId);
        model.addAttribute("classInfo", classInfo);
        model.addAttribute("studentList", classStudents);
        model.addAttribute("classCurriculum", classCurriculum);
//        model.addAttribute("gradesList", gradeslist);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classId}/classAttitude")
    public String classAttitude(@RequestParam("classId") Integer classId,
                                Model model) {
        //クラスの情報と生徒とクラスの教科を取得
        ClassEntity classInfo = service.findClassInfo(classId);
        List<studentsRecord>  classStudents = service.findStudents(classId);
        List<String > classCurriculum = service.findClassCurriculum(classId);

        ClassAttitudeEntity classAttitude = service.findClassAttitude(classId);

        model.addAttribute("classInfo", classInfo);
        model.addAttribute("studentList", classStudents);
        model.addAttribute("classCurriculum", classCurriculum);
        model.addAttribute("classAttitude", classAttitude);

        return "classAttitude";
    }

    @GetMapping("/classDetail/{classId}/submission")
    public String submission(@RequestParam("classId") Integer classId,
                             Model model) {
        ClassEntity classInfo = service.findClassInfo(classId);
        List<studentsRecord>  classStudents = service.findStudents(classId);
        List<String > classCurriculum = service.findClassCurriculum(classId);
        model.addAttribute("classInfo", classInfo);
        model.addAttribute("studentList", classStudents);
        model.addAttribute("classCurriculum", classCurriculum);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classId}/testScore")
    public String testScore(@RequestParam("classId") Integer classId,
                             Model model) {
        ClassEntity classInfo = service.findClassInfo(classId);
        List<studentsRecord>  classStudents = service.findStudents(classId);
        List<String > classCurriculum = service.findClassCurriculum(classId);
        model.addAttribute("classInfo", classInfo);
        model.addAttribute("studentList", classStudents);
        model.addAttribute("classCurriculum", classCurriculum);
        return "classDetail";
    }

}
