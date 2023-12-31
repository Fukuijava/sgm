package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.*;
import webapp.school_grades_mgmt.sgm.repository.table.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;


@Service
public class ClassDetailService {
    private final ClassRepository classRepository;
    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final ClassCurriculumRepository classCurriculumRepository;
    private final GradesBySemesterRepository gradesBySemesterRepository;
    private final ClassAttitudeRepository classAttitudeRepository;
    private final SubmissionEvaluationRepository submissionEvaluationRepository;
    private final SubmissionRepository submissionRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository,
                              ClassCurriculumRepository classCurriculumRepository,
                              GradesBySemesterRepository gradesBySemesterRepository,
                              ClassAttitudeRepository classAttitudeRepository, SubmissionRepository submissionRepository,
                              SemesterRepository semesterRepository, SubmissionEvaluationRepository submissionEvaluationRepository,
                              JdbcTemplate jdbcTemplate) {
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.classAttitudeRepository = classAttitudeRepository;
        this.submissionEvaluationRepository = submissionEvaluationRepository;
        this.submissionRepository = submissionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *全学期を取得
     */
    public List<SemesterEntity> findSemesterEntity(){
        return semesterRepository.findAll();
    }

    /**
     *クラスエンティティを取得
     */
    public ClassEntity findClassEntity(Integer classId){
        return classRepository.getReferenceById(classId);
    }

    /**
     *  生徒をクラスIDで探す
     */
    public List<ClassDetailController.studentsRecord> findStudents(Integer classId) {
        String query = "SELECT * FROM student " +
                        "INNER JOIN class ON class.class_id = student.class_id " +
                        "WHERE student.class_id = '" + classId + "'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<ClassDetailController.studentsRecord> students = result.stream()
                .map((Map<String, Object> row) -> new ClassDetailController.studentsRecord(
                        (Integer) row.get("student_id"),
                        (Integer) row.get("attendance_number"),
                        (String) row.get("name")
                )).toList();
        return students;
    }

    /**
     *クラスカリキュラムの名前を取得
     */
    public List<String > findClassCurriculumNames(Integer classId){
        return classCurriculumRepository.findName(classId);
    }

    /**
     *クラスカリキュラムエンティティを取得
     */
    public List<ClassCurriculumEntity> findClassCurriculumEntity(Integer classId){
        return classCurriculumRepository.findEntity(classId);
    }

    /**
     *授業態度の取得処理
     */
    public List<ClassAttitudeEntity> findClassAttitude(List<ClassDetailController.studentsRecord> studentsRecords,
                                                       Integer classId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findId(classId);
        List<ClassAttitudeEntity> classAttitudeList = new ArrayList<>();
//        List<List<ClassAttitudeEntity>>  classList = new ArrayList<>();
            for(int i = 0; i < studentsRecords.size(); i++){//クラスの生徒数分回す
                for(int j = 0; j < classCurriculumIds.size(); j++){//クラスの教科数分回す
                    Integer gradesBySemesterId = gradesBySemesterRepository.findId(1, studentsRecords.get(i).studentId(), classCurriculumIds.get(j));
                    classAttitudeList.add(classAttitudeRepository.findEntity(gradesBySemesterId));
                }
//                classList.add(classAttitudeList);
            }
        return classAttitudeList;
    }

    /**
     *授業態度評価の取得処理
     */
    public List<Integer> findClassAttitudeEvaluation(List<ClassDetailController.studentsRecord> studentsRecordList,
                                        Integer classId, Integer semesterId){
        List<Integer> curriculumIdList = classCurriculumRepository.findId(classId);
        List<Integer> classAttitudeEvaluationList = new ArrayList<>();
        for(int i = 0; i < studentsRecordList.size(); i++){//クラスの生徒数分回す
            for(int j = 0; j < curriculumIdList.size(); j++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecordList.get(i).studentId(), curriculumIdList.get(j));
                classAttitudeEvaluationList.add(classAttitudeRepository.findEvaluation(gradesBySemesterId));
            }
        }
        return classAttitudeEvaluationList;
    }

    /**
     *授業態度IDの取得処理
     */
    public List<Integer> findClassAttitudeIds(List<ClassDetailController.studentsRecord> studentsRecords,
                                              Integer classId, Integer semesterId){
        List<Integer> classCurriculumIdList = classCurriculumRepository.findId(classId);
        List<Integer> classAttitudeIdList = new ArrayList<>();
        for(int i = 0; i < studentsRecords.size(); i++){//クラスの生徒数分回す
            for(int j = 0; j < classCurriculumIdList.size(); j++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecords.get(i).studentId(), classCurriculumIdList.get(j));
                classAttitudeIdList.add(classAttitudeRepository.findId(gradesBySemesterId));
            }
        }
        return classAttitudeIdList;
    }

    /**
     *授業態度の更新処理
     */
    public void updateClassAttitude(Integer[] ClassAttitudeArray, List<Integer>  classAttitudeIdList){
        for(int i = 0; i < classAttitudeIdList.size(); i++){
            jdbcTemplate.update(
                    "UPDATE class_attitude AS CA " +
                        "INNER JOIN grades_by_semester AS GBA " +
                        "ON CA.grades_by_semester_id = GBA.grades_by_semester_id " +
                        "SET CA.evaluation = '" + ClassAttitudeArray[i] + "' " +
                        "WHERE CA.grades_by_semester_id = '" + classAttitudeIdList.get(i) + "'");
        }
    }

    /**
     *単体のカリキュラムネームを取得
     */
    public String findCurriculum(Integer curriculumId){
        return classCurriculumRepository.findById(curriculumId).get().getCurriculumEntity().getName();
    }

    /**
     *提出物評価の取得
     */
    public List<SubmissionEvaluationEntity> findSubmissionEvaluations(List<ClassDetailController.studentsRecord> studentList,
                                                                      Integer semesterId, Integer curriculumId){
        List<SubmissionEvaluationEntity> submissionEvaluationList = new ArrayList<>();
        for(int i = 0; i < studentList.size(); i++){//クラスの生徒数分回す
            Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentList.get(i).studentId(), curriculumId);
            submissionEvaluationList.add(submissionEvaluationRepository.findEntity(gradesBySemesterId));
        }
        return submissionEvaluationList;
    }

    /**
     *提出物の取得
     */
    public List<SubmissionEntity> findSubmissions(List<SubmissionEvaluationEntity> submissionEvaluationList,
                                                  List<String> submissionNameList, List<String> submissionDeadlineList) {
        List<SubmissionEntity> submissionList = new ArrayList<>();
        for (int i = 0; i < submissionNameList.size(); i++) {
            for (int j = 0; j < submissionEvaluationList.size(); j++) {
                submissionList.add(
                        submissionRepository.findEntity(
                                submissionEvaluationList.get(j).getId(),
                                submissionNameList.get(i),
                                submissionDeadlineList.get(i)));
            }
        }
        return submissionList;
    }

    /**
     *提出状況を取得
     */
    public List<Boolean> findSubmissionStatuses(List<String> submissionNameList, List<String> submissionDeadlineList, List<SubmissionEvaluationEntity> submissionEvaluationList) {
        List<Boolean> statusList = new ArrayList<>();
            for(int i = 0; i < submissionEvaluationList.size(); i++){
                for(int j = 0; j < submissionNameList.size(); j++){
                    statusList.add(
                        submissionRepository.findStatus(submissionEvaluationList.get(i).getId(),
                                                        submissionNameList.get(j),
                                                        submissionDeadlineList.get(j)));
            }
        }
        return statusList;
    }

    /**
     *提出物の名前を取得
     */
    public List<String> findSubmissionNames(List<SubmissionEvaluationEntity> submissionEvaluationList) {
        List<String> submissionNameList = submissionRepository.findName(submissionEvaluationList.get(0).getId());
        return submissionNameList;
    }

    /**
     *提出物の期限を取得
     */
    public List<String> findSubmissionDeadlines(List<SubmissionEvaluationEntity> submissionEvaluationList) {
        List<String> submissionDeadlineList = submissionRepository.findDeadline(submissionEvaluationList.get(0).getId());
        return submissionDeadlineList;
    }

    /**
     *提出物追加処理
     */
    public void setSubmission(List<SubmissionEvaluationEntity> submissionEvaluationList,
                              String submissionName, String submissionDeadline) {
        for(int i = 0; i < submissionEvaluationList.size(); i++){
            SubmissionEntity submissionEntity = new SubmissionEntity();
            submissionEntity.setSubmissionEvaluationEntity(submissionEvaluationRepository.getReferenceById(submissionEvaluationList.get(i).getId()));
            submissionEntity.setName(submissionName);
            submissionEntity.setDeadline(submissionDeadline);
            submissionEntity.setStatus(false);
            submissionRepository.saveAndFlush(submissionEntity);
        }
    }

    /**
     *提出状況の更新処理
     */
    public void updateStatus(List<SubmissionEntity> submissionList,Boolean[] status,
                             List<String> submissionNameList, List<ClassDetailController.studentsRecord>  studentList){
        for(int i = 0; i < studentList.size(); i++) {
            for(int j = 0; j < submissionNameList.size(); j++){
                SubmissionEntity s = submissionRepository.findBySubId(submissionList.get(j * studentList.size() + i).getId());
                s.setStatus(status[i * submissionNameList.size() + j]);
                submissionRepository.saveAndFlush(s);
            }
        }
    }

    /**
     *提出物評価の更新
     */
    public void updateEvaluation(Boolean[] status,
                                 List<String> submissionNameList, List<ClassDetailController.studentsRecord>  studentList,
                                 Integer semesterId, Integer curriculumId){
        for(int i = 0; i < studentList.size(); i++) {
            for(int j = 0, trueCount = 0; j < submissionNameList.size(); j++){
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentList.get(i).studentId(), curriculumId);
                int evaluation = 0;
                if(status[i * submissionNameList.size() + j]) {//中身がtrueの場合
                    trueCount++;
                }

                if(j == submissionNameList.size()-1){//submissionNameList.size()の最後に起動する処理
                    if(submissionNameList.size() == 1 && j == 0 && trueCount == 0) {//提出物が一個で未提出の場合
                        evaluation = 4;
                    } else if (submissionNameList.size() != 1 && trueCount == 0) {//提出物が2個以上ですべて未提出の場合
                        evaluation = 1;
                    } else {//上記２つ以外の場合
                        float floatEvaluation = (((float)5 / submissionNameList.size()) * trueCount);
                        evaluation = Math.round(floatEvaluation);
                    }
                    SubmissionEvaluationEntity se = submissionEvaluationRepository.findEntity(gradesBySemesterId);
                    se.setEvaluation(evaluation);
                    submissionEvaluationRepository.saveAndFlush(se);
                }
            }
        }
    }
}