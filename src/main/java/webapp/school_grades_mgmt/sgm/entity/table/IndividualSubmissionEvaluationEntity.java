package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "individual_submission_evaluation")
@Data
@Entity
public class IndividualSubmissionEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="individual_submission_evaluation_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "overall_submission_evaluation_id")
    private OverallSubmissionEvaluationEntity overallSubmissionEvaluationEntity;

    @Column(name="individual_evaluation")
    private Integer individualEvaluation;
}
