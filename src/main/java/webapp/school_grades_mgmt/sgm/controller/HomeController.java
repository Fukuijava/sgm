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

//    @Autowired
//    HomeService service;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/addClass")
    public String addClass(Model model) {
        return "addClass";
    }


    @RequestMapping("/updateCurriculum")
    public String updateCurriculum(Model model) {
        return "updateCurriculum";
    }
}
