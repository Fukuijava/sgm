package webapp.school_grades_mgmt.sgm.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;

public interface SchoolYearRepository extends JpaRepository<SchoolYearEntity,Integer> {
}
