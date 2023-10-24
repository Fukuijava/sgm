package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;
import webapp.school_grades_mgmt.sgm.entity.master.SemesterEntity;

@Table(name = "grades_by_semester")
@Data
@Entity
public class GradesBySemesterEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="grades_by_semester_id")
        private Integer id;

        @ManyToOne()
        @JoinColumn(name = "grades_id")
        private GradesEntity gradesEntity;

        @ManyToOne()
        @JoinColumn(name = "semester_id")
        private SemesterEntity semesterEntity;

    }
