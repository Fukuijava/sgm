package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository,
                              ClassCurriculumRepository classCurriculumRepository,
                              GradesBySemesterRepository gradesBySemesterRepository,
                              ClassAttitudeRepository classAttitudeRepository,
                              SemesterRepository semesterRepository, JdbcTemplate jdbcTemplate) {
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.classAttitudeRepository = classAttitudeRepository;
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

    public List<String > findClassCurriculumName(Integer classId){
        return classCurriculumRepository.findName(classId);
    }

    /**
     *授業態度の取得処理
     */
    public List<ClassAttitudeEntity> findClassAttitude(List<ClassDetailController.studentsRecord> studentsRecords,
                                                       Integer classId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findId(classId);
        List<ClassAttitudeEntity> classAttitudeEntityList = new ArrayList<>();
        List<List<ClassAttitudeEntity>>  classList = new ArrayList<>();
            for(int y = 0; y < studentsRecords.size(); y++){//クラスの生徒数分回す
                for(int z = 0; z < classCurriculumIds.size(); z++){//クラスの教科数分回す
                    Integer gradesBySemesterId = gradesBySemesterRepository.findId(1, studentsRecords.get(y).studentId(), classCurriculumIds.get(z));
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
        for(int x = 0; x < studentsRecordList.size(); x++){//クラスの生徒数分回す
            for(int y = 0; y < curriculumIdList.size(); y++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecordList.get(x).studentId(), curriculumIdList.get(y));
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
        for(int x = 0; x < studentsRecords.size(); x++){//クラスの生徒数分回す
            for(int y = 0; y < classCurriculumIdList.size(); y++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecords.get(x).studentId(), classCurriculumIdList.get(y));
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


    public List<SubmissionEvaluationEntity> findSubmissionEvaluation(List<ClassDetailController.studentsRecord> studentsRecords,
                                                                     Integer classId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findId(classId);
        List<ClassAttitudeEntity> classAttitudeEntityList = new ArrayList<>();
        List<List<ClassAttitudeEntity>>  classList = new ArrayList<>();
        for(int y = 0; y < studentsRecords.size(); y++){//クラスの生徒数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(1, studentsRecords.get(y).studentId(), classCurriculumIds.get(z));
                classAttitudeEntityList.add(classAttitudeRepository.findEntity(gradesBySemesterId));
            classList.add(classAttitudeEntityList);
        }
        return classAttitudeEntityList;
    }
}