package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.StudentEntity;
import webapp.school_grades_mgmt.sgm.repository.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.StudentRepository;
import java.util.List;
import java.util.Map;

@Service
public class ClassDetailService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassDetailService(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }


//    public List<StudentEntity> findClass(Integer classId){
//        List<StudentEntity> studentEntities =studentRepository.(classId);
//        return studentEntities;
//    }
}
