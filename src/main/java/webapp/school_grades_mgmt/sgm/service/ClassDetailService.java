package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.repository.ClassCurriculumRepository;
import webapp.school_grades_mgmt.sgm.repository.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.StudentRepository;

import java.util.List;

@Service
public class ClassDetailService {


    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }


    public List<ClassEntity> findClass(){
        return classRepository.findAll();
    }
}
