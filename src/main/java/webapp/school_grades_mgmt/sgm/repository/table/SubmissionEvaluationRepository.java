package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;

public interface SubmissionEvaluationRepository extends JpaRepository<SubmissionEvaluationEntity,Integer> {
    @Query(value =  "SELECT submission_evaluation.* FROM submission_evaluation " +
                    "INNER JOIN grades_by_semester " +
                    "ON submission_evaluation.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE submission_evaluation.grades_by_semester_id = ?", nativeQuery = true)
    SubmissionEvaluationEntity findEntity(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value =  "SELECT submission_evaluation.evaluation FROM submission_evaluation " +
                    "INNER JOIN grades_by_semester " +
                    "ON submission_evaluation.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE submission_evaluation.grades_by_semester_id = ?", nativeQuery = true)
    Integer findEvaluation(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value =  "SELECT submission_evaluation.submission_evaluation_id FROM submission_evaluation " +
                    "INNER JOIN grades_by_semester " +
                    "ON submission_evaluation.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "WHERE submission_evaluation.grades_by_semester_id = ?", nativeQuery = true)
    Integer findId(@Param("gradesBySemesterId") Integer gradesBySemesterId);

    @Query(value =  "UPDATE submission_evaluation " +
                    "LEFT JOIN grades_by_semester " +
                    "ON submission_evaluation.grades_by_semester_id = grades_by_semester.grades_by_semester_id " +
                    "SET submission_evaluation.evaluation = ? " +
                    "WHERE submission_evaluation.submission_evaluation_id = ?", nativeQuery = true)
    void updateEvaluation(@Param("evaluation") Integer evaluation,
                          @Param("submissionEvaluationId") Integer submissionEvaluationId);
}