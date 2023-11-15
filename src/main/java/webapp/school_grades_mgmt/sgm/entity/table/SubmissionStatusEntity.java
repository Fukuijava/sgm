package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "submission_status")
@Data
@Entity
public class SubmissionStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="submission_status_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "submission_id")
    private SubmissionEntity submissionEntity;

    private Boolean status;
}
