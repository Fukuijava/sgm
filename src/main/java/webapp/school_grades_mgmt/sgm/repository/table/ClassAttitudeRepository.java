package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;

public interface ClassAttitudeRepository extends JpaRepository<ClassAttitudeEntity,Integer> {
    @Query(value = "SELECT class_attitude.* FROM class_attitude " +
                   "INNER JOIN grades_by_semester " +
                   "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                   "WHERE class_attitude.grades_by_semester_id = ?", nativeQuery = true)
    ClassAttitudeEntity findEntity(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value = "SELECT class_attitude.class_attitude_id FROM class_attitude " +
                    "INNER JOIN grades_by_semester " +
                    "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE class_attitude.grades_by_semester_id = ?", nativeQuery = true)
    Integer findId(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value = "SELECT class_attitude.evaluation FROM class_attitude " +
                    "INNER JOIN grades_by_semester " +
                    "ON class_attitude.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE class_attitude.grades_by_semester_id = ?", nativeQuery = true)
    Integer findEvaluation(@Param("gradesBySemesterId") Integer gradesBySemesterId);

}
