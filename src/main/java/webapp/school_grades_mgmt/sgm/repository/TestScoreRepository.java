package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.StudentEntity;
import webapp.school_grades_mgmt.sgm.entity.TestScoreEntity;

import java.util.List;

public interface TestScoreRepository extends JpaRepository<TestScoreEntity,Integer> {

    List<TestScoreEntity> findByClassEntity(StudentEntity studentEntity);

}
