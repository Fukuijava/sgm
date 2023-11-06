package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.List;

@Controller
public class ClassDetailController {

    public record studentsRecord(
    Integer studentId, Integer attendanceNumber, String studentName){
    }

    @Autowired
    private ClassDetailService service;

    /**
     * クラス画面
     */
    @GetMapping("/classDetail/{classId}")
    public String classDetail(@RequestParam("classId") Integer classId,
                              Model model) {
    ClassEntity classEntity = service.findClassEntity(classId);
    List<studentsRecord>  studentList = service.findStudents(classId);
    model.addAttribute("classEntity", classEntity);
    model.addAttribute("studentList", studentList);
    return "classDetail";
    }

    /**
     * 授業態度画面
     */
    @GetMapping("/classDetail/{classId}/classAttitude")
    public String classAttitude(@RequestParam("classId") Integer classId,
                                Model model) {
    List<SemesterEntity> semesterEntityList = service.findSemesterEntity();
    ClassEntity classEntity = service.findClassEntity(classId);
    List<studentsRecord>  studentList = service.findStudents(classId);
    List<String > curriculumNameList = service.findClassCurriculumName(classId);
    List<ClassAttitudeEntity> classAttitudeEntityList = service.findClassAttitude(studentList, classId);
    model.addAttribute("semesterEntityList", semesterEntityList);
    model.addAttribute("classEntity", classEntity);
    model.addAttribute("studentList", studentList);
    model.addAttribute("curriculumNameList", curriculumNameList);
    model.addAttribute("classAttitudeEntityList", classAttitudeEntityList);
    return "classAttitude";
    }

    /**
     * 授業態度更新処理
     */
    @PostMapping("/classDetail/{classId}/classAttitude/update")
    public String classAttitudeUpdate(@RequestParam("classId") Integer classId,
                                      @RequestParam("semesterId") Integer semesterId,
                                      @RequestParam("classAttitudes") String  classAttitudes,
                                      Model model) {
    //更新処理部
    String[] StringClassAttitudeArray = classAttitudes.split(",", -1);
    Integer[] ClassAttitudeArray = new Integer[StringClassAttitudeArray.length];
    for(int i = 0; i < StringClassAttitudeArray.length; i++){
        ClassAttitudeArray[i] = Integer.parseInt(StringClassAttitudeArray[i]);
    }
    List<studentsRecord> studentList = service.findStudents(classId);
    List<Integer> classAttitudeIdList = service.findClassAttitudeIds(studentList, classId,semesterId);
    service.updateClassAttitude(ClassAttitudeArray, classAttitudeIdList);

    //画面表示部
    List<SemesterEntity> semesterEntityList = service.findSemesterEntity();
    ClassEntity classEntity = service.findClassEntity(classId);
    List<String > curriculumNameList = service.findClassCurriculumName(classId);
    List<ClassAttitudeEntity> classAttitudeEntityList = service.findClassAttitude(studentList, classId);
    model.addAttribute("semesterEntityList", semesterEntityList);
    model.addAttribute("classEntity", classEntity);
    model.addAttribute("studentList", studentList);
    model.addAttribute("curriculumNameList", curriculumNameList);
    model.addAttribute("classAttitudeEntityList", classAttitudeEntityList);
    return "classAttitude";
    }

    /**
     * 提出物画面
     * 学期、教科選択画面
     */
    @GetMapping("/classDetail/{classId}/submission/choice")
    public String submissionChoice(@RequestParam("classId") Integer classId,
                             Model model) {
        List<SemesterEntity> semesterEntityList = service.findSemesterEntity();
        ClassEntity classEntity = service.findClassEntity(classId);
        List<String > curriculumNameList = service.findClassCurriculumName(classId);
        model.addAttribute("semesterEntityList", semesterEntityList);
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("curriculumNameList", curriculumNameList);
        return "submission";
    }

    /**
     * 提出物画面
     */
    @GetMapping("/classDetail/{classId}/submission")
    public String submission(@RequestParam("classId") Integer classId,
                             @RequestParam("semesterId") Integer semesterId,
                             @RequestParam("curriculumId") Integer curriculumId,
                             Model model) {
        ClassEntity classEntity = service.findClassEntity(classId);
        List<String > curriculumNameList = service.findClassCurriculumName(classId);
        List<studentsRecord>  studentList = service.findStudents(classId);
        List<SubmissionEvaluationEntity> submissionEvaluationList = service.findSubmissionEvaluation(studentList, classId);
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("curriculumNameList", curriculumNameList);
        return "submission";
    }



    /**
     * テスト画面
     * 機能未実装
     */
    @GetMapping("/classDetail/{classId}/testScore")
    public String testScore(@RequestParam("classId") Integer classId,
                            Model model) {
        return "classDetail";
        }
}
