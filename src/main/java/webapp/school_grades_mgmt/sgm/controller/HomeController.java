package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.school_grades_mgmt.sgm.service.HomeService;

@Controller
public class HomeController {

//    @Autowired
//    HomeService service;

    @RequestMapping("/home")
    public String  hello(Model model){
        return "home";
    }
}
