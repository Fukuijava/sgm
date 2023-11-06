package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEvaluationEntity;

public interface SubmissionEvaluationRepository extends JpaRepository<SubmissionEvaluationEntity,Integer> {
}
