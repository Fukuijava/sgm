package webapp.school_grades_mgmt.sgm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "class")
@Data
@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany()
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    private CurriculumEntity curriculumEntity;

    private Integer schoolYear;

    private Integer classNumber;


}
