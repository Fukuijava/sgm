package webapp.school_grades_mgmt.sgm.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.school_grades_mgmt.sgm.entity.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.StudentEntity;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;
import webapp.school_grades_mgmt.sgm.service.HomeService;

import java.util.List;

@Controller
public class ClassDetailController {

    @Autowired
    private ClassDetailService service;

//    @GetMapping("/classDetail/{classId}}")
//    public String classDetail(@PathParam("classId") Integer classId,
//                              Model model) {
//        List<StudentEntity> selectClass = service.findClass(classId);
//        model.addAttribute("gomapList", goListItems);
//        model.addAttribute("my_home_List", myHomeItems);
//        return "classDetail";
//    }
}
