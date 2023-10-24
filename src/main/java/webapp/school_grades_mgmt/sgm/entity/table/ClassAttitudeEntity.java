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

    @ManyToOne()
    @JoinColumn(name = "grades_by_semester_id")
    private GradesBySemesterEntity gradesBySemesterEntity;

    @Column(name="curriculum_name")
    private String curriculumName;

    @Column(name="class_attitude")
    private String classAttitude;

}
