package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.StudentEntity;
import webapp.school_grades_mgmt.sgm.repository.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.StudentRepository;

import java.util.List;

@Service
public class HomeService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public HomeService(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

    public Integer addClass(Integer schoolYear, Integer classNumber) {
        ClassEntity entity = new ClassEntity();
        entity.setSchoolYear(schoolYear);
        entity.setClassNumber(classNumber);
        classRepository.save(entity);
        return entity.getId();
    }

    public void addStudent(Integer classId, String[] stNames) {
        for (int i = 0; i < stNames.length; i++) {
            StudentEntity entity = new StudentEntity();
            entity.setClassEntity(classRepository.getReferenceById(classId));
            entity.setNumber(i + 1);
            entity.setName(stNames[i]);
            studentRepository.save(entity);
        }
    }

    public List<ClassEntity> findAll(){
        return classRepository.findAll();
    }

}
