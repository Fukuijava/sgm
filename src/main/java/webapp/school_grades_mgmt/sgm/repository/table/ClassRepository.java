package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

import java.util.List;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
public interface ClassRepository extends JpaRepository<ClassEntity,Integer> {

}
