package webapp.school_grades_mgmt.sgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
public interface ClassRepository extends JpaRepository<ClassEntity,Integer> {

//    @Query("SELECT DISTINCT e FROM Equipment e INNER JOIN e.room WHERE e.room.roomId = :roomId ORDER BY e.equipmentId")
//    List<Equipment> find(@Param("roomId") Integer roomId);
}
