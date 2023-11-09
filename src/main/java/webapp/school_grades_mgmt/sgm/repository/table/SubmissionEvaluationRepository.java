package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;

import java.util.List;

public interface SubmissionEvaluationRepository extends JpaRepository<SubmissionEvaluationEntity,Integer> {
        @Query(value =  "SELECT submission_evaluation.submission_status FROM submission_evaluation " +
                        "WHERE submission_evaluation.overall_submission_evaluation_id = ?1 " +
                        "AND submission_evaluation.class_submission_id = ?2", nativeQuery = true)
        Boolean findStatus(@Param("overallSubmissionEvaluationId") Integer overallSubmissionEvaluationId,
                           @Param("classSubmissionId") Integer classSubmissionId);
}
