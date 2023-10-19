package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.school_grades_mgmt.sgm.entity.table.ClassCurriculumEntity;

public interface ClassCurriculumRepository extends JpaRepository<ClassCurriculumEntity,Integer> {
}
