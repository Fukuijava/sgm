package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.GradesBySemesterEntity;
import webapp.school_grades_mgmt.sgm.repository.table.*;

import java.util.List;
import java.util.Map;


@Service
public class ClassDetailService {
    private final ClassRepository classRepository;
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
                              JdbcTemplate jdbcTemplate) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.classAttitudeRepository = classAttitudeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public ClassEntity findClassInfo(Integer classId){
        return classRepository.getReferenceById(classId);
    }


    public List<ClassDetailController.studentsRecord> findStudents(Integer classId) {
        String query = "SELECT * FROM student " +
                        "LEFT OUTER JOIN class ON class.class_id = student.class_id " +
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

    public ClassAttitudeEntity findClassAttitude(Integer classId){
        ClassAttitudeEntity classAttitude = new ClassAttitudeEntity();
        List<Integer > classCurriculumId = classCurriculumRepository.findClassCurriculumId(classId);
        classAttitudeRepository.findClassAttitude();


        return ;
    }




//    public ClassAttitudeEntity findClassAttitude(Integer classId){
//        GradesBySemesterEntity gradesBySemester = new GradesBySemesterEntity();
//        gradesBySemester.getSemesterEntity().getId();
//
//

//        return classCurriculumRepository.findClassIdCurriculum(classId);
//    }


}
