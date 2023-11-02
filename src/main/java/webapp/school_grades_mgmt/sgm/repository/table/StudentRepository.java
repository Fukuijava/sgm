package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.StudentEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

}
