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
import webapp.school_grades_mgmt.sgm.repository.master.ClassNumberRepository;
import webapp.school_grades_mgmt.sgm.repository.master.CurriculumRepository;
import webapp.school_grades_mgmt.sgm.repository.master.DepartmentRepository;
import webapp.school_grades_mgmt.sgm.repository.master.SchoolYearRepository;
import webapp.school_grades_mgmt.sgm.repository.table.ClassCurriculumRepository;
import webapp.school_grades_mgmt.sgm.repository.table.ClassRepository;
import webapp.school_grades_mgmt.sgm.repository.table.GradesRepository;
import webapp.school_grades_mgmt.sgm.repository.table.StudentRepository;

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
    private final GradesRepository gradesRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository, StudentRepository studentRepository,
                       ClassCurriculumRepository classCurriculumRepository, GradesRepository gradesRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesRepository = gradesRepository;
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
            StudentEntity studentEntity = new StudentEntity();
            GradesEntity gradesEntity = new GradesEntity();
            studentEntity.setClassEntity(classRepository.getReferenceById(classId));
            studentEntity.setAttendanceNumber(i + 1);
            studentEntity.setStudentName(stNames[i]);
            studentRepository.saveAndFlush(studentEntity);
            int studentId = studentEntity.getId();
            gradesEntity.setStudentEntity(studentRepository.getReferenceById(studentId));
            gradesRepository.saveAndFlush(gradesEntity);
        }
    }
}
