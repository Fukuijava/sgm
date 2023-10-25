package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "submission")
@Data
@Entity
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="submission_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_by_semester_id")
    private GradesBySemesterEntity gradesBySemesterEntity;

    @ManyToOne()
    @JoinColumn(name = "class_curriculum_id")
    private ClassCurriculumEntity classCurriculumEntity;

    @Column(name="submission_name")
    private String submissionName;

    @Column(name="submission_evaluation")
    private Integer submissionEvaluation;

}
