package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "grades")
@Data
@Entity
public class GradesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grades_id")
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Column(name="class_attitude")
    private Integer  classAttitude;

    @Column(name="submission_final_eval")
    private Integer  submissionFinalEval;

    @Column(name="test_final_eval")
    private Integer  testFinalEval;





}
