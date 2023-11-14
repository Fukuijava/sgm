package webapp.school_grades_mgmt.sgm.entity.master;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "school_year")
@Data
@Entity
public class SchoolYearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="school_year_id")
    private Integer id;

    private Integer number;
}
