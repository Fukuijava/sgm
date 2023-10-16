package webapp.school_grades_mgmt.sgm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "curriculum")
@Data
@Entity
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String languageCulture;                  //言語文化
    private String modernJapanese;                   //現代の国語
    private String japaneseExpression;               //国語表現
    private String newPublic;                        //新公共
    private String generalGeography;                 //地理総合
    private String modernSociety;                    //現代社会
    private String mathematics_I;                    //数学I
    private String mathematics_II;                   //数学II
    private String mathematics_A;                    //数学A
    private String basicsOfCalculus;                 //微分積分の基礎
    private String scienceAndHumanLife;              //科学と人間生活
    private String chemistryBasics;                  //化学基礎
    private String physicsBasics;                    //物理基礎
    private String physics;                          //物理
    private String physicalEducation;                //体育
    private String health;                           //保健
    private String art_I;                            //美術I
    private String englishCommunication_I;           //英語コミュニケーションI
    private String englishCommunication_Il;          //英語コミュニケーションIl
    private String familyBasics;                     //家庭基礎

    private String industrialTechnologyBasics;       //工業技術基礎
    private String practicalTraining;                //実習
    private String themedResearch;                   //課題研究
    private String industrialInformationMathematics;//工業情報数理
    private String mechanicalDesign;                 //機械設計
    private String drafting;                         //製図
    private String machineWork;                      //機械工作
    private String primeMover;                       //原動機
    private String automotiveEngineering;            //自動車工学
    private String productionSystemTechnology;       //生産システム技術
    private String electronicMachinery;              //電子機械
    private String industrialScience;                //生産技術
    private String electricCircuit;                  //電気回路
    private String electricalEquipment;              //電気機器
    private String powerTechnology;                  //電力技術
    private String electronicTechnology;             //電子技術
    private String electronicCircuit;                //電子回路
    private String electronicMeasurementControl;     //電子計測制御
    private String communicationTechnology;          //通信技術
    private String programmingTechnology;            //プログラミング技術
    private String hardwareTechnology;               //ハードウェア技術
    private String computerSystemTechnology;         //コンピュータシステム技術
    private String architecturalStructure;           //建築構造
    private String architecturalPlans;               //建築計画
    private String architecturalStructureDesign;     //建築構造設計
    private String buildingRegulations;              //建築法規
    private String architecturalConstruction;        //建築施工
}
