package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "class_submission")
public class ClassSubmissionEntity {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "class_submission_id")
        private Integer id;

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "submission_evaluation_id")
        private SubmissionEvaluationEntity submissionEvaluationEntity;

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "submission_id")
        private SubmissionEntity submissionEntity;
}
