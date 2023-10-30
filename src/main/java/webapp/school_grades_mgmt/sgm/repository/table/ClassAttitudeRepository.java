package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.controller.SemesterRestController;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

import java.util.List;

public interface ClassAttitudeRepository extends JpaRepository<ClassAttitudeEntity,Integer> {
    @Query(value = "SELECT class_attitude.* FROM class_attitude " +
                   "INNER JOIN grades_by_semester " +
                   "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                   "WHERE class_attitude.grades_by_semester_id = ?", nativeQuery = true)
    ClassAttitudeEntity findClassAttitude1(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value = "SELECT class_attitude.* FROM class_attitude " +
            "INNER JOIN grades_by_semester " +
            "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
            "WHERE class_attitude.grades_by_semester_id = ?", nativeQuery = true)
    Object findClassAttitude2(@Param("gradesBySemesterId") Integer gradesBySemesterId);

}
