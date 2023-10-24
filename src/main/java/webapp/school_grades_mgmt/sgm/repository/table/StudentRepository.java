package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.table.StudentEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    @Query( value="SELECT student.attendance_number, student.student_name " +
                "FROM student " +
                "LEFT OUTER JOIN class " +
                "ON class.class_id = student.class_id " +
                "WHERE student.class_id = ? ", nativeQuery = true)
    List<ClassDetailController.studentsRecord> findClassIdStudent(@Param("classId") Integer classId);
}
