package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.StudentEntity;
import webapp.school_grades_mgmt.sgm.repository.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.StudentRepository;

@Service
public class HomeService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public HomeService(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

    public String addClass(Integer schoolYear, Integer classNumber){
        ClassEntity entity  = new ClassEntity();
        entity.setSchoolYear(schoolYear);
        entity.setClassNumber(classNumber);
        classRepository.save(entity);
        return "seikou";
    }

    public String addStudent(String[] stNames){
        StudentEntity entity  = new StudentEntity();
        classRepository.findById(1).get().getName();
        for(int i = 1; i < stNames.length; i++){

            entity.setClassEntity();
            entity.setNumber(i);

        }

        studentRepository.save(entity);
        return ;
    }

}
