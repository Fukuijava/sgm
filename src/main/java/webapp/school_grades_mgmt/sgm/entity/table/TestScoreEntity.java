package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.TestEntity;

@Table(name = "test_score")
@Data
@Entity
public class TestScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_score_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_by_semester_id")
    private GradesBySemesterEntity gradesBySemesterEntity;

    @ManyToOne()
    @JoinColumn(name = "test_id")
    private TestEntity testEntity;

    private Integer score;
}
