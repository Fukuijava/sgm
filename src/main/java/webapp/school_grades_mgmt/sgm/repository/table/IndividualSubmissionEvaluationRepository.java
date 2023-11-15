package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.IndividualSubmissionEvaluationEntity;

public interface IndividualSubmissionEvaluationRepository extends JpaRepository<IndividualSubmissionEvaluationEntity,Integer> {
    @Query(value =  "SELECT individual_submission_evaluation.* FROM individual_submission_evaluation " +
                    "INNER JOIN overall_submission_evaluation " +
                    "ON individual_submission_evaluation.overall_submission_evaluation_id = overall_submission_evaluation.overall_submission_evaluation_id " +
                    "WHERE individual_submission_evaluation.overall_submission_evaluation_id = ?", nativeQuery = true)
    IndividualSubmissionEvaluationEntity findEntity(@Param("overallSubmissionEvaluationId") Integer overallSubmissionEvaluationId);

}
