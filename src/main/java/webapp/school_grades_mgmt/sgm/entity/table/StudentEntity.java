package webapp.school_grades_mgmt.sgm.entity.table;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "student")
@Data
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity classEntity;

    @Column(name="attendance_number")
    private Integer attendanceNumber;

    @Column(name="student_name")
    private String studentName;




}
