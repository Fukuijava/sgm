package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.List;

@Controller
public class ClassDetailController {

    public record studentsRecord(
            Integer attendanceNumber, String studentName){
    }

    @Autowired
    private ClassDetailService service;

    @GetMapping("/classDetail/{classId}")
    public String classDetail(@RequestParam("classId") Integer classId,
                              Model model) {
        ClassEntity selectClass = service.findClass(classId);
        List<studentsRecord>  selectStudents = service.findStudents(classId);
        List<String > selectClassCurriculum = service.findClassCurriculum(classId);
//        List<String > gradeslist = service.findGrades(classId);
        model.addAttribute("classInfo", selectClass);
        model.addAttribute("studentList", selectStudents);
        model.addAttribute("classCurriculum", selectClassCurriculum);
//        model.addAttribute("gradesList", gradeslist);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classId}/submission")
    public String submission(@RequestParam("classId") Integer classId,
                              Model model) {
        ClassEntity selectClass = service.findClass(classId);
        List<studentsRecord>  selectStudents = service.findStudents(classId);
        List<String > selectClassCurriculum = service.findClassCurriculum(classId);
        model.addAttribute("classInfo", selectClass);
        model.addAttribute("studentList", selectStudents);
        model.addAttribute("classCurriculum", selectClassCurriculum);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classInfo.id}/classAttitude")
    public String submission(@RequestParam("classId") Integer classId,
                             Model model) {
        ClassEntity selectClass = service.findClass(classId);
        List<studentsRecord>  selectStudents = service.findStudents(classId);
        List<String > selectClassCurriculum = service.findClassCurriculum(classId);
        model.addAttribute("classInfo", selectClass);
        model.addAttribute("studentList", selectStudents);
        model.addAttribute("classCurriculum", selectClassCurriculum);
        return "classDetail";
    }

    @GetMapping("/classDetail/{classInfo.id}/testScore")
    public String submission(@RequestParam("classId") Integer classId,
                             Model model) {
        ClassEntity selectClass = service.findClass(classId);
        List<studentsRecord>  selectStudents = service.findStudents(classId);
        List<String > selectClassCurriculum = service.findClassCurriculum(classId);
        model.addAttribute("classInfo", selectClass);
        model.addAttribute("studentList", selectStudents);
        model.addAttribute("classCurriculum", selectClassCurriculum);
        return "classDetail";
    }

}
