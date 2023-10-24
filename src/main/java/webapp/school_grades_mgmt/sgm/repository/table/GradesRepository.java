package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.GradesEntity;
public interface GradesRepository extends JpaRepository<GradesEntity,Integer> {
}
