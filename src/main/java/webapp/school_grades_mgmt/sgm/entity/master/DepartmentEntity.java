package webapp.school_grades_mgmt.sgm.entity.master;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "department")
@Data
@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  name;

}
