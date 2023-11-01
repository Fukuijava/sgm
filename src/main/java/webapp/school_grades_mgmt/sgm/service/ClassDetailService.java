package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.GradesBySemesterEntity;
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

    public List<SemesterEntity> findSemester(){
        return semesterRepository.findAll();
    }

    public ClassEntity findClassInfo(Integer classId){
        return classRepository.getReferenceById(classId);
    }


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

    public List<String > findClassCurriculum(Integer classId){
        return classCurriculumRepository.findClassCurriculumName(classId);
    }

    public List<ClassAttitudeEntity> findClassAttitude(List<ClassDetailController.studentsRecord> studentsRecords,
                                                       Integer classId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findClassCurriculumId(classId);
        List<ClassAttitudeEntity> classAttitudeEntityList = new ArrayList<>();
        List<List<ClassAttitudeEntity>>  classList = new ArrayList<>();
            for(int y = 0; y < studentsRecords.size(); y++){//クラスの生徒数分回す
                for(int z = 0; z < classCurriculumIds.size(); z++){//クラスの教科数分回す
                    Integer gradesBySemesterId = gradesBySemesterRepository.findId(1, studentsRecords.get(y).studentId(), classCurriculumIds.get(z));
                    classAttitudeEntityList.add(classAttitudeRepository.findClassAttitude1(gradesBySemesterId));
                }
                classList.add(classAttitudeEntityList);
            }
        return classAttitudeEntityList;
    }

    public List<Integer> changeSemester(List<ClassDetailController.studentsRecord> studentsRecords,
                                                        Integer classId, Integer semesterId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findClassCurriculumId(classId);
        List<Integer> classAttitudeList = new ArrayList<>();
        for(int y = 0; y < studentsRecords.size(); y++){//クラスの生徒数分回す
            for(int z = 0; z < classCurriculumIds.size(); z++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecords.get(y).studentId(), classCurriculumIds.get(z));
                classAttitudeList.add(classAttitudeRepository.findClassAttitude3(gradesBySemesterId));
            }
        }
        return classAttitudeList;
    }

    public List<Integer> findClassAttitudeIds(List<ClassDetailController.studentsRecord> studentsRecords,
                                       Integer classId, Integer semesterId){
        List<Integer> classCurriculumIds = classCurriculumRepository.findClassCurriculumId(classId);
        List<Integer> classAttitudeIds = new ArrayList<>();
        for(int x = 0; x < studentsRecords.size(); x++){//クラスの生徒数分回す
            for(int y = 0; y < classCurriculumIds.size(); y++){//クラスの教科数分回す
                Integer gradesBySemesterId = gradesBySemesterRepository.findId(semesterId, studentsRecords.get(x).studentId(), classCurriculumIds.get(y));
                classAttitudeIds.add(classAttitudeRepository.findClassAttitude2(gradesBySemesterId));
            }
        }
        return classAttitudeIds;
    }

    public void updateClassAttitude(Integer[] IntegerClassAttitudes, List<Integer>  classAttitudeIdList){
        for(int i = 0; i < IntegerClassAttitudes.length; i++){
            jdbcTemplate.update(
                    "UPDATE class_attitude AS CA " +
                        "INNER JOIN grades_by_semester AS GBA " +
                        "ON CA.grades_by_semester_id = GBA.grades_by_semester_id " +
                        "SET CA.class_attitude_evaluation = '" + IntegerClassAttitudes[i] + "' " +
                        "WHERE CA.grades_by_semester_id = '" + classAttitudeIdList.get(i) + "'");
        }
    }
}