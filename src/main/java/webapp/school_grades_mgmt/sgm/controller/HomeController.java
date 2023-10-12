package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.service.HomeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private HomeService service;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        return "addClass";
    }


    /**
     * @param schoolYear
     * @param classNumber
     * をテーブルClassに入れて、
     * @param nameList
     * をテーブルStudentに入れている。
     */
    @PostMapping("/addClass/function")
    public String addClassFunction(@RequestParam("schoolYear") Integer schoolYear,
                                   @RequestParam("classNumber") Integer classNumber,
                                   @RequestParam("studentNames") String  nameList
                                   ){
        String[] stNames = nameList.split(",", -1);
        Arrays.sort(stNames);
        service.addClass(schoolYear,classNumber);
        service.addStudent(stNames);



//        service.addStudent();



        return "redirect:/home/addClass";
    }


    @GetMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
        return "updateCurriculum";
    }
}
