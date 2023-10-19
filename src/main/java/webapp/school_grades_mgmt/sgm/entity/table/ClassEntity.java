package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;

import java.util.ArrayList;
import java.util.List;

@Table(name = "class")
@Data
@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer id;

    @OneToMany(mappedBy = "classEntity")
    private List<ClassCurriculumEntity> classCurriculumEntity = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "school_year_id")
    private SchoolYearEntity schoolYearEntity;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    @ManyToOne()
    @JoinColumn(name = "class_number_id")
    private ClassNumberEntity classNumberEntity;

}
