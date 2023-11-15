package webapp.school_grades_mgmt.sgm.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.*;
import webapp.school_grades_mgmt.sgm.service.ClassDetailService;

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
        String curriculum = service.findCurriculum(curriculumId);
        List<String > curriculumNameList = service.findClassCurriculumNames(classId);
        List<studentsRecord>  studentList = service.findStudents(classId);
        List<OverallSubmissionEvaluationEntity> overallSubmissionEvaluationList = service.findOverallSubmissionEvaluations(studentList, semesterId, curriculumId);
        List<IndividualSubmissionEvaluationEntity> individualSubmissionEvaluationList = service.findIndividualSubmissionEvaluations(overallSubmissionEvaluationList);
        List<SubmissionEntity> submissionList = service.findSubmissions(individualSubmissionEvaluationList);
//        List<String> submissionNameList = service.findSubmissionNames();
        model.addAttribute("classEntity", classEntity);
        model.addAttribute("studentList", studentList);
        model.addAttribute("semester", semesterId);
        model.addAttribute("curriculum", curriculum);
        model.addAttribute("curriculumId", curriculumId);

        model.addAttribute("curriculumNameList", curriculumNameList);
        model.addAttribute("overallSubmissionEvaluationEntityList", overallSubmissionEvaluationEntityList);
        model.addAttribute("submissionStatusList", submissionStatusList);
        model.addAttribute("classSubmissionEntityList", submissionNameList);
        model.addAttribute("submissionEvaluationEntityList", submissionEvaluationEntityList);
        return "submission";
    }

//    /**
//     * 提出物追加処理
//     */
//    @PostMapping("/classDetail/{classId}/submission/addSubmission")
//    public String addSubmission(@RequestParam("classId") Integer classId,
//                                @RequestParam("semesterId") Integer semesterId,
//                                @RequestParam("curriculumId") Integer curriculumId,
//                                @RequestParam("curriculumName") String curriculumName,
//                                @RequestParam("submissionName") String submissionName,
//                                @RequestParam("submissionDeadline") String submissionDeadline,
//                                @RequestParam("submissionEvaluationId") String submissionEvaluationId,
//                                Model model) {
//        String[] strSubmissionEvaluationId = submissionEvaluationId.split(",", -1);
//        Integer[] submissionArray = new Integer[strSubmissionEvaluationId.length];
//        for(int i = 0; i < submissionArray.length; i++){
//            submissionArray[i] = Integer.parseInt(strSubmissionEvaluationId[i]);
//        }
//        ClassEntity classEntity = service.findClassEntity(classId);
//        List<String > curriculumNameList = service.findClassCurriculumNames(classId);
//        List<studentsRecord>  studentList = service.findStudents(classId);
//        List<OverallSubmissionEvaluationEntity> overallSubmissionEvaluationEntityList = service.findOverallSubmissionEvaluations(studentList, semesterId, curriculumId);
//        Integer submissionId = service.setSubmission(submissionName, submissionDeadline);
//        Integer classSubmissionId = service.setClassSubmission(submissionId, );
//
//        service.setSubmissionEvaluation(overallSubmissionEvaluationEntityList);
//        List<SubmissionEvaluationEntity> submissionEvaluationEntityList = service.findSubmissionEvaluationId(overallSubmissionEvaluationEntityList);
//        List<ClassSubmissionEntity> classSubmissionEntityList = service.findClassSubmissionEntity(classId);
//        List<Boolean> submissionStatusList = service.findSubmissionStatus(overallSubmissionEvaluationEntityList, classSubmissionEntityList);
////        model.addAttribute("submission", submissionEntityList);
//        model.addAttribute("classEntity", classEntity);
//        model.addAttribute("studentList", studentList);
//        model.addAttribute("semester", semesterId);
//        model.addAttribute("curriculumId", curriculumId);
//        model.addAttribute("curriculum", curriculumName);
////        model.addAttribute("curriculumNameList", curriculumNameList);
//        model.addAttribute("overallSubmissionEvaluationEntityList", overallSubmissionEvaluationEntityList);
//        model.addAttribute("submissionEntityList", submissionStatusList);
//        model.addAttribute("classSubmissionEntityList", classSubmissionEntityList);
//        model.addAttribute("submissionEvaluationEntityList", submissionEvaluationEntityList);
//
//        return "submission";
//    }
//
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
