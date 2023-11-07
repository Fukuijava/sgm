package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "overall_submission_evaluation")
@Data
@Entity
public class OverallSubmissionEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="overall_submission_evaluation_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "grades_by_semester_id")
    private GradesBySemesterEntity gradesBySemesterEntity;

    @Column(name="submission_evaluation")
    private Integer submissionEvaluation;

}
