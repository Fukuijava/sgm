package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.*;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassDetailController {

    public record studentsRecord(
    Integer studentId, Integer attendanceNumber, String name){
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
    ClassEntity classEntity = service.findClassEntity(classId);
    List<SemesterEntity> semesterList = service.findSemesterEntity();
    List<studentsRecord> studentList = service.findStudents(classId);
    List<String > curriculumNameList = service.findClassCurriculumNames(classId);
    List<ClassAttitudeEntity> classAttitudeEntityList = service.findClassAttitude(studentList, classId);
    model.addAttribute("semesterEntityList", semesterList);
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
    List<String > curriculumNameList = service.findClassCurriculumNames(classId);
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
        List<ClassCurriculumEntity> curriculumEntityList = service.findClassCurriculumEntity(classId);
        model.addAttribute("semesterEntityList", semesterEntityList);
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("curriculumEntityList", curriculumEntityList);
        return "submissionChoice";
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
        String curriculumName = service.findCurriculum(curriculumId);
        List<String > curriculumNameList = service.findClassCurriculumNames(classId);
        List<studentsRecord>  studentList = service.findStudents(classId);
        List<SubmissionEvaluationEntity> submissionEvaluationList = service.findSubmissionEvaluations(studentList, semesterId, curriculumId);
        List<String> submissionNameList = service.findSubmissionNames(submissionEvaluationList);
        List<String> submissionDeadlineList = service.findSubmissionDeadlines(submissionEvaluationList);
        List<Boolean> submissionStatusList = service.findSubmissionStatuses(submissionNameList, submissionDeadlineList, submissionEvaluationList);
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("studentList", studentList);
        model.addAttribute("semesterId", semesterId);
        model.addAttribute("curriculumName", curriculumName);
        model.addAttribute("curriculumId", curriculumId);
        model.addAttribute("curriculumNameList", curriculumNameList);
        model.addAttribute("submissionEvaluationList", submissionEvaluationList);
        model.addAttribute("submissionStatusList", submissionStatusList);
        model.addAttribute("submissionNameList", submissionNameList);
        model.addAttribute("submissionDeadlineList", submissionDeadlineList);
        return "submission";
    }

    /**
     * 提出物追加処理
     */
    @PostMapping("/classDetail/{classId}/submission/addSubmission")
    public String addSubmission(@RequestParam("classId") Integer classId,
                                @RequestParam("semesterId") Integer semesterId,
                                @RequestParam("curriculumId") Integer curriculumId,
                                @RequestParam("submissionName") String submissionName,
                                @RequestParam("submissionDeadline") String submissionDeadline,
                                Model model) {
        List<studentsRecord>  studentList = service.findStudents(classId);
        List<SubmissionEvaluationEntity> submissionEvaluationList = service.findSubmissionEvaluations(studentList, semesterId, curriculumId);
        service.setSubmission(submissionEvaluationList, submissionName, submissionDeadline);
        return submission(classId, semesterId, curriculumId, model);
    }

    /**
     * 提出状態更新
     */
    @PostMapping("/classDetail/{classId}/submission/updateSubmission")
    public String updateSubmission(@RequestParam("classId") Integer classId,
                                   @RequestParam("semesterId") Integer semesterId,
                                   @RequestParam("curriculumId") Integer curriculumId,
                                   @RequestParam("statuses") String statuses,
                                   Model model) {
        String[] stringStatusArray = statuses.split(",", -1);
        Boolean[] statusArray = new Boolean[stringStatusArray.length];
        for(int i = 0; i < stringStatusArray.length; i++){
            statusArray[i] = Boolean.parseBoolean(stringStatusArray[i]);
        }
        List<studentsRecord>  studentList = service.findStudents(classId);
        List<SubmissionEvaluationEntity> submissionEvaluationList = service.findSubmissionEvaluations(studentList, semesterId, curriculumId);
        List<String> submissionNameList = service.findSubmissionNames(submissionEvaluationList);
        List<String> submissionDeadlineList = service.findSubmissionDeadlines(submissionEvaluationList);
        List<SubmissionEntity> submissionList = service.findSubmissions(submissionEvaluationList, submissionNameList, submissionDeadlineList);
        service.updateStatus(submissionList, statusArray, submissionNameList, studentList);
        service.updateEvaluation(statusArray, submissionNameList, studentList, semesterId, curriculumId);
        return submission(classId, semesterId, curriculumId, model);
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
