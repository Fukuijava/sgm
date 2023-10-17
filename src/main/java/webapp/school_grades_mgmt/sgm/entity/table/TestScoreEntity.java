package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "test_score")
@Data
@Entity
public class TestScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_id", referencedColumnName = "id")
    private GradesEntity gradesEntity;

    private Integer name;
    private Integer score;

}
