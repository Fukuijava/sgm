package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Table(name = "submission")
@Data
@Entity
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="submission_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "individual_submission_evaluation_id")
    private OverallSubmissionEvaluationEntity overallSubmissionEvaluationEntity;

    private String name;

    private String deadline;
}
