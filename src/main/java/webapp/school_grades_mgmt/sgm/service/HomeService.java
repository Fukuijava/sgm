package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;
import webapp.school_grades_mgmt.sgm.repository.ClassNumberRepository;
import webapp.school_grades_mgmt.sgm.repository.CurriculumRepository;
import webapp.school_grades_mgmt.sgm.repository.DepartmentRepository;
import webapp.school_grades_mgmt.sgm.repository.SchoolYearRepository;

import java.util.List;

@Service
public class HomeService {

//    private final ClassRepository classRepository;
//    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;
    private final SchoolYearRepository schoolYearRepository;
    private final DepartmentRepository departmentRepository;
    private final ClassNumberRepository classNumberRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
    }

//    public Integer addClass(Integer schoolYear, Integer classNumber) {
//        ClassEntity entity = new ClassEntity();
//        entity.setSchoolYear(schoolYear);
//        entity.setClassNumber(classNumber);
//        classRepository.save(entity);
//        return entity.getId();
//    }

//    public void addStudent(Integer classId, String[] stNames) {
//        for (int i = 0; i < stNames.length; i++) {
//            StudentEntity entity = new StudentEntity();
//            entity.setClassEntity(classRepository.getReferenceById(classId));
//            entity.setNumber(i + 1);
//            entity.setName(stNames[i]);
//            studentRepository.save(entity);
//        }
//    }

    public List<SchoolYearEntity> findSchoolYear(){
        return schoolYearRepository.findAll();
    }
    public List<DepartmentEntity> findDepartment(){
        return departmentRepository.findAll();
    }
    public List<ClassNumberEntity> findClassNumber(){
        return classNumberRepository.findAll();
    }

}
