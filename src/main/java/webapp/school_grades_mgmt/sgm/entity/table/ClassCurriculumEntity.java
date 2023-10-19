package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "class_curriculum")
public class ClassCurriculumEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "class_curriculum_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "curriculum_id")
    private CurriculumEntity curriculumEntity;
}
