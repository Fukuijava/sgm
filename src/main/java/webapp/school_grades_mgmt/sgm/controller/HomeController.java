package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;
import webapp.school_grades_mgmt.sgm.service.ClassCurriculumService;
import webapp.school_grades_mgmt.sgm.service.HomeService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    public record ClassCurriculum(
            String curriculum){
    }

    //クラスの教科呼ぶやつ
    //List<ClassCurriculum> classCurriculum = classCurriculumservice.findClassCurriculum(curriculumList);

    @Autowired
    private HomeService homeService;
    private ClassCurriculumService classCurriculumservice;


    @GetMapping("/")
    public String home(Model model) {
//        List<ClassEntity> classList = service.findAll();
//        model.addAttribute("ClassList", classList);
        return "home";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        List<SchoolYearEntity> schoolYearList = homeService.findSchoolYear();
        List<DepartmentEntity> departmentList = homeService.findDepartment();
        List<ClassNumberEntity> classNumberList = homeService.findClassNumber();
        List<CurriculumEntity> curriculumList = homeService.findCurriculum();
        model.addAttribute("SchoolYearList", schoolYearList);
        model.addAttribute("DepartmentList", departmentList);
        model.addAttribute("ClassNumberList", classNumberList);
        model.addAttribute("CurriculumList", curriculumList);
        return "addClass";
    }

    @PostMapping("/addClass/function")
    public String addClassFunction(@RequestParam("schoolYear") Integer schoolYear,
                                   @RequestParam("department") String department,
                                   @RequestParam("classNumber") Integer classNumber,
                                   @RequestParam("studentNames") String  nameList,
                                   @RequestParam("curriculums") String  curriculumList,
                                   Model model){
        //生徒とクラス教科を配列に直す
        String[] stNames = nameList.split(",", -1);
        Arrays.sort(stNames);
        Integer classId = homeService.addClass(schoolYear,department,classNumber);
        service.addStudent(classId,stNames);
        model.addAttribute("classRegistered","登録完了");
        return "addClass";
    }


    @GetMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
        return "updateCurriculum";
    }
}
