package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.TestScoreEntity;

public interface TestScoreRepository extends JpaRepository<TestScoreEntity,Integer> {
//    List<TestScoreEntity> findByStudentEntity(StudentEntity studentEntity);
}