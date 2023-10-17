package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
public interface ClassRepository extends JpaRepository<ClassEntity,Integer> {
}
