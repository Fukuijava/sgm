package webapp.school_grades_mgmt.sgm.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassSubmissionEntity;

import java.util.List;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
public interface ClassRepository extends JpaRepository<ClassEntity,Integer> {
    @Query(value = "SELECT id FROM class " +
                    "WHERE school_year_id = ?1 " +
                    "AND department_id = ?2 " +
                    "AND class_number_id = ?3", nativeQuery = true)
    Integer findId(@Param("schoolYearId") Integer schoolYearId,
                   @Param("departmentId" )Integer departmentId,
                   @Param("classNumberId") Integer classNumberId);
}
