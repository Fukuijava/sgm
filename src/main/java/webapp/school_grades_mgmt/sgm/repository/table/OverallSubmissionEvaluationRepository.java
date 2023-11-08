package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.OverallSubmissionEvaluationEntity;

public interface OverallSubmissionEvaluationRepository extends JpaRepository<OverallSubmissionEvaluationEntity,Integer> {
    @Query(value =  "SELECT overall_submission_evaluation.* FROM overall_submission_evaluation " +
                    "INNER JOIN grades_by_semester " +
                    "ON overall_submission_evaluation.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE overall_submission_evaluation.grades_by_semester_id = ?", nativeQuery = true)
    OverallSubmissionEvaluationEntity findEvaluation(@Param("gradesBySemesterId") Integer gradesBySemesterId);
}
