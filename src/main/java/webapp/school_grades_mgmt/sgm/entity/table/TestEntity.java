package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "test")
@Data
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne()
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private TestEntity testEntity;

    private Integer name;
    private Integer evaluation;
}
