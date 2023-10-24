package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassCurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

import java.util.List;

public interface ClassCurriculumRepository extends JpaRepository<ClassCurriculumEntity,Integer> {
    @Query(value = "SELECT curriculum.curriculum_name FROM curriculum " +
                    "LEFT OUTER JOIN class_curriculum_entity " +
                    "ON curriculum.curriculum_id = class_curriculum_entity.curriculum_id " +
                    "WHERE class_curriculum_entity.class_id = ?", nativeQuery = true)
    List<String > findClassIdCurriculum(@Param("classId") Integer classId);

}