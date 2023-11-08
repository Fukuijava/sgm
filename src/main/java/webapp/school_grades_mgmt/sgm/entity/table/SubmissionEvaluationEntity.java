package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "submission_evaluation")
@Data
@Entity
public class SubmissionEvaluationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "submission_evaluation_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "overall_submission_evaluation_id")
    private OverallSubmissionEvaluationEntity submissionEvaluationEntity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "submission_id")
    private SubmissionEntity submissionEntity;
}
