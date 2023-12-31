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
    @JoinColumn(name = "submission_evaluation_id")
    private SubmissionEvaluationEntity submissionEvaluationEntity;

    private String name;

    private String deadline;

    private Boolean status;
}
