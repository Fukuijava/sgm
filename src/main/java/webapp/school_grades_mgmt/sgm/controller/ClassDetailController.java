package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
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
        model.addAttribute("classInfo", classInfo);
        model.addAttribute("studentList", classStudents);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classId}/classAttitude")
    public String classAttitude(@RequestParam("classId") Integer classId,
                                Model model) {
        //学期とクラスの情報と生徒とクラスの教科を取得
        List<SemesterEntity> semester = service.findSemester();
        ClassEntity classInfo = service.findClassInfo(classId);
        List<studentsRecord>  classStudents = service.findStudents(classId);
        List<String > classCurriculum = service.findClassCurriculum(classId);
        List<ClassAttitudeEntity> classAttitude = service.findClassAttitude(classStudents, classId);
        model.addAttribute("semesterNumber", semester);
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
