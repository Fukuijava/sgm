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
    SubmissionEvaluationEntity findEvaluation(@Param("gradesBySemesterId") Integer gradesBySemesterId);
}
