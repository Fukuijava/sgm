package webapp.school_grades_mgmt.sgm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "testScore")
@Data
@Entity
public class TestScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    private Integer ;
}
