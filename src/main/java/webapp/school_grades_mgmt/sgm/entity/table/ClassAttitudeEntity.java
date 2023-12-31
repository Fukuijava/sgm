package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "class_attitude")
@Data
@Entity
public class ClassAttitudeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_attitude_id")
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "grades_by_semester_id")
    private GradesBySemesterEntity gradesBySemesterEntity;

    private Integer evaluation;

}
