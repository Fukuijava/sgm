package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.master.ClassNumberEntity;
import webapp.school_grades_mgmt.sgm.entity.master.CurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.master.DepartmentEntity;
import webapp.school_grades_mgmt.sgm.entity.master.SchoolYearEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassCurriculumEntity;
import webapp.school_grades_mgmt.sgm.entity.table.ClassEntity;
import webapp.school_grades_mgmt.sgm.entity.table.GradesEntity;
import webapp.school_grades_mgmt.sgm.entity.table.StudentEntity;
import webapp.school_grades_mgmt.sgm.repository.*;

import java.util.List;

@Service
public class HomeService {

    private final CurriculumRepository curriculumRepository;
    private final SchoolYearRepository schoolYearRepository;
    private final DepartmentRepository departmentRepository;
    private final ClassNumberRepository classNumberRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final ClassCurriculumRepository classCurriculumRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository, StudentRepository studentRepository,
                       ClassCurriculumRepository classCurriculumRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
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

    public List<ClassEntity> findClass(){
        return classRepository.findAll();
    }

    public Integer addClass(Integer schoolYear, Integer department, Integer classNumber) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setSchoolYearEntity(schoolYearRepository.getReferenceById(schoolYear));
        classEntity.setDepartmentEntity(departmentRepository.getReferenceById(department));
        classEntity.setClassNumberEntity(classNumberRepository.getReferenceById(classNumber));
        classRepository.saveAndFlush(classEntity);
        return classEntity.getId();
    }

    public void addClassCurriculum(Integer classId, Integer[] classCurriculums) {
        for (Integer classCurriculum : classCurriculums) {
            ClassCurriculumEntity classCurriculumEntity = new ClassCurriculumEntity();
            classCurriculumEntity.setClassEntity(classRepository.getReferenceById(classId));
            classCurriculumEntity.setCurriculumEntity(curriculumRepository.getReferenceById(classCurriculum));
            classCurriculumRepository.saveAndFlush(classCurriculumEntity);
        }
    }

    public void addStudent(Integer classId, String[] stNames) {
        for (int i = 0; i < stNames.length; i++) {
            StudentEntity entity = new StudentEntity();
            entity.setClassEntity(classRepository.getReferenceById(classId));
            entity.setAttendanceNumber(i + 1);
            entity.setStudentName(stNames[i]);
            studentRepository.saveAndFlush(entity);
        }
    }

    public void addGrades(Integer classId) {
        for (int i = 0; i < stNames.length; i++) {
            GradesEntity entity = new GradesEntity();
            entity.setClassEntity(classRepository.getReferenceById(classId));
            entity.setAttendanceNumber(i + 1);
            entity.setStudentName(stNames[i]);
            studentRepository.saveAndFlush(entity);
        }
    }
}
