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
    @JoinColumn(name = "overall_submission_evaluation_id")
    private OverallSubmissionEvaluationEntity submissionEvaluationEntity;

    @Column(name="submission_name")
    private String submissionName;

    @Column(name="submission_deadline")
    private String submissionDeadline;
}
