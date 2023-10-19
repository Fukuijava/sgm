package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "test")
@Data
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_id")
    private GradesEntity gradesEntity;

    @Column(name="test_name")
    private Integer test_name;

    @Column(name="test_evaluation")
    private Integer testEvaluation;
}
