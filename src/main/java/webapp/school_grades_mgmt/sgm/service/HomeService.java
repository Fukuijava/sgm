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
    private final OverallSubmissionEvaluationRepository overallSubmissionEvaluationRepository;

    @Autowired
    public HomeService(CurriculumRepository curriculumRepository, SchoolYearRepository schoolYearRepository,
                       DepartmentRepository departmentRepository, ClassNumberRepository classNumberRepository,
                       ClassRepository classRepository, StudentRepository studentRepository,
                       ClassCurriculumRepository classCurriculumRepository, SemesterRepository semesterRepository,
                       GradesBySemesterRepository gradesBySemesterRepository, OverallSubmissionEvaluationRepository overallSubmissionEvaluationRepository,
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
        this.overallSubmissionEvaluationRepository = overallSubmissionEvaluationRepository;
    }

    public List<SchoolYearEntity> findSchoolYear() {
        return schoolYearRepository.findAll();
    }

    public List<DepartmentEntity> findDepartment() {
        return departmentRepository.findAll();
    }

    public List<ClassNumberEntity> findClassNumber() {
        return classNumberRepository.findAll();
    }

    public List<CurriculumEntity> findCurriculum() {
        return curriculumRepository.findAll();
    }

    public List<ClassEntity> findClass() {
        return classRepository.findAll();
    }

    /**
     * クラステーブルの登録処理
     */
    public void setClass(Integer schoolYear, Integer department, Integer classNumber) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setSchoolYearEntity(schoolYearRepository.getReferenceById(schoolYear));
        classEntity.setDepartmentEntity(departmentRepository.getReferenceById(department));
        classEntity.setClassNumberEntity(classNumberRepository.getReferenceById(classNumber));
        classRepository.saveAndFlush(classEntity);
    }

    /**
     *クラスID取得
     */
    public Integer getClassId(Integer schoolYear, Integer department, Integer classNumber){
        return classRepository.findId(schoolYear,department,classNumber);
    }

    /**
     * クラスカリキュラムテーブルの登録処理
     */
    public void setClassCurriculum(Integer classId, Integer[] classCurriculums) {
        for (Integer classCurriculum : classCurriculums) {
            ClassCurriculumEntity classCurriculumEntity = new ClassCurriculumEntity();
            classCurriculumEntity.setClassEntity(classRepository.getReferenceById(classId));
            classCurriculumEntity.setCurriculumEntity(curriculumRepository.getReferenceById(classCurriculum));
            classCurriculumRepository.saveAndFlush(classCurriculumEntity);
        }
    }

    /**
     * 生徒テーブルの登録処理
     */
    public void setStudent(Integer classId, String[] studentNames) {
        for (int i = 0; i < studentNames.length; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setClassEntity(classRepository.getReferenceById(classId));
            studentEntity.setAttendanceNumber(i + 1);
            studentEntity.setName(studentNames[i]);
            studentRepository.saveAndFlush(studentEntity);
            //生徒IDとクラスカリキュラムを取得し、setGradesBySemesterへ投げる。
            Integer studentId = studentEntity.getId();
            List<Integer> curriculumIdList = classCurriculumRepository.findId(classId);
            setGradesBySemester(studentId, curriculumIdList);
        }
    }

    /**
     * 学期別成績テーブルの登録処理
     */
    public void setGradesBySemester(Integer studentId, List<Integer> curriculumIdList) {
        for(int semesterNumber = 1; semesterNumber < 4; semesterNumber++){//3学期分回す//条件の値が4なのは、初期値が1のため、3学期分回すため
            for(int i = 0; i < curriculumIdList.size(); i++){//クラス教科の分だけ回す
                GradesBySemesterEntity gradesBySemesterEntity = new GradesBySemesterEntity();
                gradesBySemesterEntity.setStudentEntity(studentRepository.getReferenceById(studentId));
                gradesBySemesterEntity.setSemesterEntity(semesterRepository.getReferenceById(semesterNumber));
                gradesBySemesterEntity.setClassCurriculumEntity(classCurriculumRepository.getReferenceById(curriculumIdList.get(i)));
                gradesBySemesterRepository.saveAndFlush(gradesBySemesterEntity);
                //学期別成績IDを取得し、授業態度、提出物評価、テストの点数テーブルに登録
                Integer gradesBySemesterId = gradesBySemesterEntity.getId();
                setClassAttitude(gradesBySemesterId);
                setSubmissionEvaluation(gradesBySemesterId);
            }
        }
    }

    /**
     * 授業態度テーブルの登録処理
     */
    public void setClassAttitude(Integer gradesBySemesterId) {
        ClassAttitudeEntity classAttitudeEntity = new ClassAttitudeEntity();
        classAttitudeEntity.setGradesBySemesterEntity(gradesBySemesterRepository.getReferenceById(gradesBySemesterId));
        classAttitudeEntity.setEvaluation(5);//授業態度は初期値が最大値の5でそこから減点方式で評価するため最初は5をセットする。
        classAttitudeRepository.saveAndFlush(classAttitudeEntity);
    }

    /**
     * 提出物テーブルの登録処理
     */
    public void setSubmissionEvaluation(Integer gradesBySemesterId) {
        OverallSubmissionEvaluationEntity overallSubmissionEvaluationEntity = new OverallSubmissionEvaluationEntity();
        overallSubmissionEvaluationEntity.setGradesBySemesterEntity(gradesBySemesterRepository.getReferenceById(gradesBySemesterId));
        overallSubmissionEvaluationEntity.setOverallEvaluation(5);//提出物が一回もない教科もあるため、初期値に最大値の5をセット。
        overallSubmissionEvaluationRepository.saveAndFlush(overallSubmissionEvaluationEntity);
    }

    /**
     * テストの点数テーブルの登録処理
     * 【未実装】
     */
    public void setTestScore(Integer gradesBySemesterId) {
    }
}