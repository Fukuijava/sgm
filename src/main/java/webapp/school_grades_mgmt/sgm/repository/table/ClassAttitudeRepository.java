package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;

import java.util.List;

public interface ClassAttitudeRepository extends JpaRepository<ClassAttitudeEntity,Integer> {
    @Query(value = "SELECT class_attitude.class_attitude FROM class_attitude " +
                   "LEFT OUTER JOIN grades_by_semester " +
                   "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester.grades_by_semester_id " +
                   "WHERE class_attitude.grades_by_semester_id. = ?", nativeQuery = true)
    List<Integer> findClassAttitude(@Param("gradesBySemesterId") Integer gradesBySemesterId);
}
