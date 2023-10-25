package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.controller.ClassDetailController;
import webapp.school_grades_mgmt.sgm.entity.table.GradesEntity;

import java.util.List;

public interface GradesRepository extends JpaRepository<GradesEntity,Integer> {

}
