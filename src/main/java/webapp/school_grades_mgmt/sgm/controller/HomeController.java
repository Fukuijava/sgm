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

    /**
     * ホーム画面
     */
    @GetMapping("/")
    public String home(Model model) {
    List<ClassEntity> classList = service.findClass();
    model.addAttribute("classList", classList);
    return "home";
    }

    /**
     * クラス登録画面
     */
    @GetMapping("/addClass")
    public String addClass(Model model) {
    List<SchoolYearEntity> schoolYearList = service.findSchoolYear();
    List<DepartmentEntity> departmentList = service.findDepartment();
    List<ClassNumberEntity> classNumberList = service.findClassNumber();
    List<CurriculumEntity> curriculumArray = service.findCurriculum();
    model.addAttribute("schoolYearList", schoolYearList);
    model.addAttribute("departmentList", departmentList);
    model.addAttribute("classNumberList", classNumberList);
    model.addAttribute("curriculumArray", curriculumArray);
    return "addClass";
    }

    /**
     * クラス登録処理
     */
    @PostMapping("/addClass/function")
    public String addClassFunction( @RequestParam("schoolYearId") Integer schoolYearId,
                                    @RequestParam("departmentId") Integer departmentId,
                                    @RequestParam("classNumberId") Integer classNumberId,
                                    @RequestParam("studentNames") String  studentNames,
                                    @RequestParam("curriculumIds") String  curriculumIds,
                                    Model model){
    //生徒を配列に直す
    String[] studentNameArray = studentNames.split(",", -1);
    Arrays.sort(studentNameArray);
    //クラス教科を配列に直す
    String[] string_curriculumIdArray = curriculumIds.split(",", -1);
    Integer[] curriculumIdArray = new Integer[string_curriculumIdArray.length];
    for(int i = 0; i < curriculumIdArray.length; i++){
        curriculumIdArray[i] = Integer.parseInt(string_curriculumIdArray[i]);
    }

    service.setClass(schoolYearId,departmentId,classNumberId);
    Integer classId = service.getClassId(schoolYearId,departmentId,classNumberId);
    service.setClassCurriculum(classId, curriculumIdArray);
    service.setStudent(classId,studentNameArray);
    model.addAttribute("classRegistered","登録完了");
    //ホーム画面に登録されてるクラスの一覧を表示させる
//    List<ClassEntity> classList = service.findClass();
//    model.addAttribute("classList", classList);
    return "redirect:/home/";
    }

    /**
     * 教科の編集画面
     * 機能未実装
     */
    @GetMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
    return "updateCurriculum";
    }
}
