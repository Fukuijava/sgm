package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;

import java.util.ArrayList;
import java.util.List;

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
    private OverallSubmissionEvaluationEntity overallSubmissionEvaluationEntity;

    @OneToMany(mappedBy = "submission_evaluation_entity")
    private List<ClassSubmissionEntity> classSubmissionEntity = new ArrayList<>();

    @Column(name="submission_status")
    private Boolean submissionStatus;
}
