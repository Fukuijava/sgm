package webapp.school_grades_mgmt.sgm.entity.master;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "curriculum")
@Data
@Entity
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="curriculum_id")
    private Integer id;

    @Column(name="curriculum_name")
    private String curriculumName;

}
