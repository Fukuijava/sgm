package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassSubmissionEntity;

import java.util.List;

public interface ClassSubmissionRepository extends JpaRepository<ClassSubmissionEntity,Integer> {
    @Query(value = "SELECT class_submission_entity.* FROM class_submission_entity " +
                    "WHERE class_id = ?", nativeQuery = true)
    List<ClassSubmissionEntity> findEntity(@Param("classId") Integer classId);
}
