package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassAttitudeEntity;
import webapp.school_grades_mgmt.sgm.entity.table.GradesBySemesterEntity;

import java.util.List;
public interface GradesBySemesterRepository extends JpaRepository<GradesBySemesterEntity,Integer> {

    @Query(value =  "SELECT grades_by_semester_id FROM grades_by_semester " +
                    "WHERE semester_id = ?1 " +
                    "AND student_id = ?2 " +
                    "AND class_curriculum_id = ?3", nativeQuery = true)
    Integer findId(@Param("semester_id") Integer semester_id,
                   @Param("student_id") Integer student_id,
                   @Param("class_curriculum_id") Integer class_curriculum_id);
}
