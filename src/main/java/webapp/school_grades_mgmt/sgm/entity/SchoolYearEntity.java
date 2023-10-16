package webapp.school_grades_mgmt.sgm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "schoolYear")
@Data
@Entity
public class SchoolYearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany()
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    private CurriculumEntity curriculumEntity;

    private Integer schoolYear1;
    private Integer schoolYear2;
    private Integer schoolYear3;

}
