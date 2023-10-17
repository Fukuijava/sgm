package webapp.school_grades_mgmt.sgm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "testScore")
@Data
@Entity
public class TestScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    private Integer nationalLanguage;     //国語
    private Integer math;                 //数学
    private Integer english;              //英語
    private Integer physics;              //物理
    private Integer chemistry;            //化学
    private Integer geography;            //地理
    private Integer history;              //歴史
    private Integer Citizens;             //公民
    private Integer art;                  //美術
    private Integer homeEconomics;        //家庭科
    private Integer health;               //保健
    private Integer physicalEducation;    //体育
    private Integer practicalTraining;    //実習
    private Integer electricCircuit;      //電気回路
    private Integer electronicTechnology; //電子技術
    private Integer programmingTechnology;//プログラミング技術
    private Integer hardwareTechnology;   //ハードウェア技術
}
