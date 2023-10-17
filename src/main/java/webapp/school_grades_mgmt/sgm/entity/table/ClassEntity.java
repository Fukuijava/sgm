package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;

@Table(name = "class")
@Data
@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    private CurriculumEntity curriculumEntity;

    @ManyToOne()
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    private SchoolYearEntity schoolYearEntity;

    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity departmentEntity;

    @ManyToOne()
    @JoinColumn(name = "class_number_id", referencedColumnName = "id")
    private ClassNumberEntity classNumberEntity;


    private String  class_curriculum;
    private String  class_name;


}
