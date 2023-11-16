package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEntity;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Integer> {
    @Query(value =  "SELECT submission.* FROM submission " +
                    "INNER JOIN submission_evaluation " +
                    "ON submission.submission_evaluation_id = submission_evaluation.submission_evaluation_id " +
                    "WHERE submission.submission_evaluation_id = ?", nativeQuery = true)
    SubmissionEntity findEntity(@Param("submissionEvaluationId") Integer submissionEvaluationId);

    @Query(value =  "SELECT submission.name FROM submission " +
                    "INNER JOIN submission_evaluation " +
                    "ON submission.submission_evaluation_id = submission_evaluation.submission_evaluation_id " +
                    "WHERE submission.submission_evaluation_id = ?", nativeQuery = true)
    List<String> findName(@Param("submissionEvaluationId") Integer submissionEvaluationId);

    @Query(value =  "SELECT submission.deadline FROM submission " +
                    "INNER JOIN submission_evaluation " +
                    "ON submission.submission_evaluation_id = submission_evaluation.submission_evaluation_id " +
                    "WHERE submission.submission_evaluation_id = ?", nativeQuery = true)
    List<String> findDeadline(@Param("submissionEvaluationId") Integer submissionEvaluationId);

    @Query(value =  "SELECT submission.status FROM submission " +
                    "INNER JOIN submission_evaluation " +
                    "ON submission.submission_evaluation_id = submission_evaluation.submission_evaluation_id " +
                    "WHERE submission.submission_evaluation_id = ?1 " +
                    "AND submission.name = ?2 " +
                    "AND submission.deadline = ?3" , nativeQuery = true)
    Boolean findStatus(@Param("submissionEvaluationId") Integer submissionEvaluationId,
                       @Param("submissionName") String submissionName,
                       @Param("submissionDeadline") String submissionDeadline);
}
