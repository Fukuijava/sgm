package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.school_grades_mgmt.sgm.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private HomeService service;


    @GetMapping("/")
    public String home(Model model) {
//        List<ClassEntity> classList = service.findAll();
//        model.addAttribute("ClassList", classList);
        return "home";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        return "addClass";
    }

//    @PostMapping("/addClass/function")
//    public String addClassFunction(@RequestParam("schoolYear") Integer schoolYear,
//                                   @RequestParam("classNumber") Integer classNumber,
//                                   @RequestParam("studentNames") String  nameList,
//                                   Model model){
//        String[] stNames = nameList.split(",", -1);
//        Arrays.sort(stNames);
//        Integer classId = service.addClass(schoolYear,classNumber);
//        service.addStudent(classId,stNames);
//        model.addAttribute("classRegistered","登録完了");
//        return "addClass";
//    }


    @GetMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
        return "updateCurriculum";
    }
}
