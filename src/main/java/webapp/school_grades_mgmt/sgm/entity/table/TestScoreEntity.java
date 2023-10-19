package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "test_score")
@Data
@Entity
public class TestScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_score_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_id")
    private GradesEntity gradesEntity;

    @Column(name="test_curriculum_name")
    private Integer curriculumName;

    @Column(name="test_score")
    private Integer testScore;
}
