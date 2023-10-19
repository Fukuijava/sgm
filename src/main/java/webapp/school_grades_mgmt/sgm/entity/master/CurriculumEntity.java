package webapp.school_grades_mgmt.sgm.entity.master;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;

import java.util.ArrayList;
import java.util.List;

@Table(name = "curriculum")
@Data
@Entity
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="curriculum_id")
    private Integer id;

    @Column(name="curriculum_name")
    private String CurriculumName;

}
