package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.entity.master.*;
import webapp.school_grades_mgmt.sgm.entity.table.*;
import webapp.school_grades_mgmt.sgm.repository.master.ClassNumberRepository;
import webapp.school_grades_mgmt.sgm.repository.master.CurriculumRepository;
import webapp.school_grades_mgmt.sgm.repository.master.DepartmentRepository;
import webapp.school_grades_mgmt.sgm.repository.master.SchoolYearRepository;
import webapp.school_grades_mgmt.sgm.repository.table.*;

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
    private final GradesBySemesterRepository gradesBySemesterRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository, StudentRepository studentRepository,
                       ClassCurriculumRepository classCurriculumRepository, GradesRepository gradesRepository,
                       GradesBySemesterRepository gradesBySemesterRepository, SemesterRepository semesterRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesRepository = gradesRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.semesterRepository = semesterRepository;
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
        for (int count1 = 0; count1 < stNames.length; count1++) {
            StudentEntity studentEntity = new StudentEntity();
            GradesEntity gradesEntity = new GradesEntity();
            //生徒テーブル
            studentEntity.setClassEntity(classRepository.getReferenceById(classId));
            studentEntity.setAttendanceNumber(count1 + 1);
            studentEntity.setStudentName(stNames[count1]);
            studentRepository.saveAndFlush(studentEntity);
            //成績テーブル
            int studentId = studentEntity.getId();
            gradesEntity.setStudentEntity(studentRepository.getReferenceById(studentId));
            gradesRepository.saveAndFlush(gradesEntity);
            //学期別成績テーブル
            int gradesId = gradesEntity.getId();
            for(int count2 = 0; count2 < 3; count2++){
                GradesBySemesterEntity gradesBySemesterEntity = new GradesBySemesterEntity();
                gradesBySemesterEntity.setGradesEntity(gradesRepository.getReferenceById(gradesId));
                gradesBySemesterEntity.setSemesterEntity(semesterRepository.getReferenceById(count2 + 1));
                gradesBySemesterRepository.saveAndFlush(gradesBySemesterEntity);
            }
        }
    }
}
