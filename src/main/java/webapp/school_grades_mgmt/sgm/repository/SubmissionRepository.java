package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.SubmissionEntity;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Integer> {
}
