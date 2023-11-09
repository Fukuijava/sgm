package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.*;
import webapp.school_grades_mgmt.sgm.repository.table.*;

import java.util.ArrayList;
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
    private final OverallSubmissionEvaluationRepository overallSubmissionEvaluationRepository;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEvaluationRepository submissionEvaluationRepository;
    private final ClassSubmissionRepository classSubmissionRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository,
                              ClassCurriculumRepository classCurriculumRepository, ClassSubmissionRepository classSubmissionRepository,
                              GradesBySemesterRepository gradesBySemesterRepository,
                              ClassAttitudeRepository classAttitudeRepository, SubmissionRepository submissionRepository,
                              SemesterRepository semesterRepository, OverallSubmissionEvaluationRepository overallSubmissionEvaluationRepository,
                              SubmissionEvaluationRepository submissionEvaluationRepository, JdbcTemplate jdbcTemplate) {
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.classAttitudeRepository = classAttitudeRepository;
        this.overallSubmissionEvaluationRepository = overallSubmissionEvaluationRepository;
        this.submissionRepository = submissionRepository;
        this.submissionEvaluationRepository = submissionEvaluationRepository;
        this.classSubmissionRepository = classSubmissionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SemesterEntity> findSemesterEntity(){
        return semesterRepository.findAll();
    }

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
                        (String) row.get("student_name")
                )).toList();
        return students;
    }

    /**
     *クラスカリキュラムの名前を取得
     */
    public List<String > findClassCurriculumName(Integer classId){
        return classCurriculumRepository.findName(classId);
    }

    public List<ClassCurriculumEntity> findClassCurriculumEntity(Integer classId){
        return classCurriculumRepository.findClassCurriculumEntity(classId);
    }

    /**
     *授業態度の取得処理
     */
    public List<ClassAttitudeEntity> findClassAttitude(List<ClassDetailController.studentsRecord> studentsRecords,
                                                       Integer classId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findId(classId);
        List<ClassAttitudeEntity> classAttitudeEntityList = new ArrayList<>();
        List<List<ClassAttitudeEntity>>  classList = new ArrayList<>();
            for(int i = 0; i < studentsRecords.size(); i++){//クラスの生徒数分回す
                for(int j = 0; j < classCurriculumIds.size(); j++){//クラスの教科数分回す
                    Integer gradesBySemesterId = gradesBySemesterRepository.findId(1, studentsRecords.get(i).studentId(), classCurriculumIds.get(j));
                    classAttitudeEntityList.add(classAttitudeRepository.findEntity(gradesBySemesterId));
                }
                classList.add(classAttitudeEntityList);
            }
        return classAttitudeEntityList;
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
                        "SET CA.class_attitude_evaluation = '" + ClassAttitudeArray[i] + "' " +
                        "WHERE CA.grades_by_semester_id = '" + classAttitudeIdList.get(i) + "'");
        }
    }

    public String findCurriculum(Integer curriculumId){
        return classCurriculumRepository.findById(curriculumId).get().getCurriculumEntity().getCurriculumName();
    }

    /**
     *提出物評価の取得
     */
    public List<OverallSubmissionEvaluationEntity> findOverallSubmissionEvaluation(List<ClassDetailController.studentsRecord> studentList,
                                                                     Integer semesterId, Integer curriculumId){
        List<OverallSubmissionEvaluationEntity> submissionEvaluationList = new ArrayList<>();
        for(int i = 0; i < studentList.size(); i++){//クラスの生徒数分回す
            Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentList.get(i).studentId(), curriculumId);
            submissionEvaluationList.add(overallSubmissionEvaluationRepository.findEvaluation(gradesBySemesterId));
        }
        return submissionEvaluationList;
    }

    /**
     *提出物追加処理
     */
    public Integer setSubmission(String submissionName, String submissionDeadline) {
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setSubmissionName(submissionName);
        submissionEntity.setSubmissionDeadline(submissionDeadline);
        submissionRepository.saveAndFlush(submissionEntity);
        Integer submissionId = submissionEntity.getId();
        return submissionId;
    }

    /**
     *提出物評価テーブル登録
     */
    public void setSubmissionEvaluation(List<OverallSubmissionEvaluationEntity> overallSubmissionEvaluationEntityList){
        for(int i = 0; i < overallSubmissionEvaluationEntityList.size(); i++) {
            SubmissionEvaluationEntity submissionEvaluationEntity = new SubmissionEvaluationEntity();
            submissionEvaluationEntity.setOverallSubmissionEvaluationEntity(overallSubmissionEvaluationRepository.getReferenceById(overallSubmissionEvaluationEntityList.get(i).getId()));
            submissionEvaluationEntity.setSubmissionStatus(false);
            submissionEvaluationRepository.saveAndFlush(submissionEvaluationEntity);
        }
    }

    public List<SubmissionEvaluationEntity> findSubmissionEvaluationId(List<OverallSubmissionEvaluationEntity> overallSubmissionEvaluationEntityList){
        List<SubmissionEvaluationEntity> submissionEvaluationEntityList = new ArrayList<>();
        for(int i = 0; i <overallSubmissionEvaluationEntityList.size(); i++){
            submissionEvaluationEntityList.add(submissionEvaluationRepository.getReferenceById(overallSubmissionEvaluationEntityList.get(i).getId()));
        }
        return submissionEvaluationEntityList;
    }



    /**
     *提出物取得処理
     */
    public List<Boolean> findSubmissionStatus(List<OverallSubmissionEvaluationEntity> overallSubmissionEvaluationEntityList,
                                              List<ClassSubmissionEntity> classSubmissionEntityList){
        List<Boolean> submissionStatusList = new ArrayList<>();
        List<List<Boolean>> statusList = new ArrayList<>();
        for(int i = 0; i < overallSubmissionEvaluationEntityList.size(); i++){//生徒の数と考えていい
            for(int j = 0; j < classSubmissionEntityList.size(); j++){//クラス教科の数
                submissionStatusList.add(submissionEvaluationRepository.findStatus(
                        overallSubmissionEvaluationEntityList.get(i).getId(),
                        classSubmissionEntityList.get(j).getId()));
            }
            statusList.add(submissionStatusList);
        }
        return submissionStatusList;
    }

    /**
     *提出物名取得処理
     */
    public List<ClassSubmissionEntity> findClassSubmissionEntity(Integer classId){
        return classSubmissionRepository.findEntity(classId);
    }

    public Integer setClassSubmission(Integer submissionId, ){
        ClassSubmissionEntity classSubmissionEntity = new ClassSubmissionEntity();
        classSubmissionEntity.setSubmissionEntity(submissionRepository.getReferenceById(submissionId));
        classSubmissionEntity.setSubmissionEvaluationEntity(submissionEvaluationRepository.getReferenceById());
        classSubmissionRepository.saveAndFlush(classSubmissionEntity);
        return classSubmissionEntity.getId();
    }

}