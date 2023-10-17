package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
//    List<StudentEntity> findByClassEntity(ClassEntity classEntity);
}
