package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.repository.*;

import java.util.List;

@Service
public class HomeService {

    private final CurriculumRepository curriculumRepository;
    private final SchoolYearRepository schoolYearRepository;
    private final DepartmentRepository departmentRepository;
    private final ClassNumberRepository classNumberRepository;
    private final ClassRepository classRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
        this.classRepository = classRepository;
    }

    public List<SchoolYearEntity> findSchoolYear(){
        return schoolYearRepository.findAll();
    }
    public List<DepartmentEntity> findDepartment(){
        return departmentRepository.findAll();
    }
    public List<ClassNumberEntity> findClassNumber(){
        return classNumberRepository.findAll();
    }
    public List<CurriculumEntity> findCurriculum(){
        return curriculumRepository.findAll();
    }





    public Integer addClass(Integer schoolYear, Integer classNumber) {
        ClassEntity entity = new ClassEntity();
        entity.setSchoolYearEntity();
        entity.setClassNumber(classNumber);
        classRepository.save(entity);
        return entity.getId();
    }

//    public void addStudent(Integer classId, String[] stNames) {
//        for (int i = 0; i < stNames.length; i++) {
//            StudentEntity entity = new StudentEntity();
//            entity.setClassEntity(classRepository.getReferenceById(classId));
//            entity.setNumber(i + 1);
//            entity.setName(stNames[i]);
//            studentRepository.save(entity);
//        }
//    }

}
