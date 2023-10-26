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

    private final GradesBySemesterRepository gradesBySemesterRepository;
    private final SemesterRepository semesterRepository;
    private final ClassAttitudeRepository classAttitudeRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository, StudentRepository studentRepository,
                       ClassCurriculumRepository classCurriculumRepository, SemesterRepository semesterRepository,
                       GradesBySemesterRepository gradesBySemesterRepository,
                       ClassAttitudeRepository classAttitudeRepository) {
        this.curriculumRepository = curriculumRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.departmentRepository = departmentRepository;
        this.classNumberRepository = classNumberRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classCurriculumRepository = classCurriculumRepository;
        this.gradesBySemesterRepository = gradesBySemesterRepository;
        this.semesterRepository = semesterRepository;
        this.classAttitudeRepository = classAttitudeRepository;
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

    public void addStudent(Integer classId, String[] studentNames) {
        for (int studentNumber = 0; studentNumber < studentNames.length; studentNumber++) {
            StudentEntity studentEntity = new StudentEntity();
            //生徒テーブル
            studentEntity.setClassEntity(classRepository.getReferenceById(classId));
            studentEntity.setAttendanceNumber(studentNumber + 1);
            studentEntity.setStudentName(studentNames[studentNumber]);
            studentRepository.saveAndFlush(studentEntity);
            //学期別成績テーブル
            int studentId = studentEntity.getId();
            List<Integer > classCurriculumId = classCurriculumRepository.findClassCurriculumId(classId);
            //3学期分回す
            for(int semesterNumber = 1; semesterNumber < 4; semesterNumber++){//条件の値が4なのは3学期分回すため
                //クラス教科の分だけ回す
                for(int i = 0; i < classCurriculumId.size(); i++){
                    GradesBySemesterEntity gradesBySemesterEntity = new GradesBySemesterEntity();
                    gradesBySemesterEntity.setStudentEntity(studentRepository.getReferenceById(studentId));
                    gradesBySemesterEntity.setSemesterEntity(semesterRepository.getReferenceById(semesterNumber));
                    gradesBySemesterEntity.setClassCurriculumEntity(classCurriculumRepository.getReferenceById(classCurriculumId.get(i)));
                    gradesBySemesterRepository.saveAndFlush(gradesBySemesterEntity);
                    //授業態度テーブル
                    int gradesBySemesterId = gradesBySemesterEntity.getId();
                    ClassAttitudeEntity classAttitudeEntity = new ClassAttitudeEntity();
                    classAttitudeEntity.setGradesBySemesterEntity(gradesBySemesterRepository.getReferenceById(gradesBySemesterId));
                    classAttitudeEntity.setClassAttitudeEvaluation(5);//授業態度は初期値が最大値の5でそこから減点方式で評価するため最初は5をセットする。
                    classAttitudeRepository.saveAndFlush(classAttitudeEntity);
                }

            }
        }
    }
}
