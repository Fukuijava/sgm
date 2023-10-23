package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.table.ClassCurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.StudentEntity;
import webapp.school_grades_mgmt.sgm.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClassDetailService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final ClassCurriculumRepository classCurriculumRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository,
                              ClassCurriculumRepository classCurriculumRepository,
                              JdbcTemplate jdbcTemplate) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    public ClassEntity findClass(Integer classId){
        return classRepository.getReferenceById(classId);
    }

//    public List<ClassDetailController.studentsRecord> findStudents(Integer classId){
//        return studentRepository.findClassIdStudent(classId);
//    }
//

    public List<ClassDetailController.studentsRecord> findStudents(Integer classId) {
        String query = "SELECT student.attendance_number, student.student_name FROM student " +
                        "LEFT OUTER JOIN class ON class.class_id = student.class_id " +
                        "WHERE student.class_id = '" + classId + "'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<ClassDetailController.studentsRecord> students = result.stream()
                .map((Map<String, Object> row) -> new ClassDetailController.studentsRecord(
                        (Integer) row.get("attendance_number"),
                        (String) row.get("student_name")
                )).toList();
        return students;
    }

    public List<String > findClassCurriculum(Integer classId){
        return classCurriculumRepository.findClassIdCurriculum(classId);
    }
}
