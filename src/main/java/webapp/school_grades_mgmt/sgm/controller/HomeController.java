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
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.service.HomeService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService service;

    @GetMapping("/")
    public String home(Model model) {
        List<ClassEntity> classList = service.findClass();
        model.addAttribute("ClassList", classList);
        return "home";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        List<SchoolYearEntity> schoolYearList = service.findSchoolYear();
        List<DepartmentEntity> departmentList = service.findDepartment();
        List<ClassNumberEntity> classNumberList = service.findClassNumber();
        List<CurriculumEntity> curriculumList = service.findCurriculum();
        model.addAttribute("SchoolYearList", schoolYearList);
        model.addAttribute("DepartmentList", departmentList);
        model.addAttribute("ClassNumberList", classNumberList);
        model.addAttribute("CurriculumList", curriculumList);
        return "addClass";
    }

    @PostMapping("/addClass/function")
    public String addClassFunction(@RequestParam("schoolYear") Integer schoolYear,
                                   @RequestParam("department") Integer department,
                                   @RequestParam("classNumber") Integer classNumber,
                                   @RequestParam("studentNames") String  nameList,
                                   @RequestParam("curriculums") String  curriculumList,
                                   Model model){
        //生徒を配列に直す
        String[] stNames = nameList.split(",", -1);
        Arrays.sort(stNames);
        //クラス教科を配列に直す
        String[] CurriculumList = curriculumList.split(",", -1);
        Integer[] classCurriculums = new Integer[CurriculumList.length];
        for(int i = 0; i < CurriculumList.length; i++){
            classCurriculums[i] = Integer.parseInt(CurriculumList[i]);
        }
        Integer classId = service.addClass(schoolYear,department,classNumber);
        service.addClassCurriculum(classId, classCurriculums);
        service.addStudent(classId,stNames);
        model.addAttribute("classRegistered","登録完了");
        //ホーム画面に登録されてるクラスの一覧を表示させる
        List<ClassEntity> classList = service.findClass();
        model.addAttribute("ClassList", classList);
        return "home";
    }


    @GetMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
        return "updateCurriculum";
    }
}
