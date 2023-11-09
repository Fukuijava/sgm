package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.OverallSubmissionEvaluationEntity;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEntity;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Integer> {
//    @Query(value =  "SELECT submission.* FROM submission " +
//                    "INNER JOIN submission_evaluation " +
//                    "ON submission.overall_submission_evaluation_id = overall_submission_evaluation.overall_submission_evaluation_id " +
//                    "WHERE submission.overall_submission_evaluation_id = ?", nativeQuery = true)
//    SubmissionEntity findEntity(@Param("gradesBySemesterId") Integer submissionEvaluationId);
}
