package webapp.school_grades_mgmt.sgm.model;

import org.springframework.beans.factory.annotation.Autowired;
import webapp.school_grades_mgmt.sgm.repository.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.StudentRepository;

public class ClassModel {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassModel(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }
}
