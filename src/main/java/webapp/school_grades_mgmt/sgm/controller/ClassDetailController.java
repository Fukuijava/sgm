package webapp.school_grades_mgmt.sgm.controller;

import ch.qos.logback.core.model.Model;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

@Controller
public class ClassDetailController {

    @Autowired
    private ClassDetailService service;

    @GetMapping("/classDetail/{classId}")
    public String classDetail(@PathParam("classId") Integer classId,
                              Model model) {
//        List<StudentEntity> selectClass = service.findClass(classId);
//        model.addAttribute("gomapList", goListItems);
//        model.addAttribute("my_home_List", myHomeItems);
        return "classDetail";
    }
}
