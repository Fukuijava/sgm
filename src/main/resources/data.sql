INSERT IGNORE INTO sgmdb.school_year
(school_year_id,school_year_number)
VALUES
 (1,1)
,(2,2)
,(3,3);

INSERT IGNORE INTO sgmdb.department
(department_id,department_name)
VALUES
 (1,'機械科')
,(2,'機械システム科')
,(3,'電気科')
,(4,'電子科')
,(5,'情報システム科')
,(6,'建築科');

INSERT IGNORE INTO sgmdb.class_number
(class_number_id,class_number)
VALUES
 (1,1)
,(2,2)
,(3,3)
,(4,4)
,(5,5)
,(6,6);

INSERT IGNORE INTO sgmdb.curriculum
(curriculum_id,curriculum_name)
VALUES
 (1,'言語文化')
,(2,'現代の国語')
,(3,'国語表現')
,(4,'公共')
,(5,'地理総合')
,(6,'現代社会')
,(7,'数学I')
,(8,'数学II')
,(9,'数学A')
,(10,'微分積分の基礎')
,(11,'科学と人間生活')
,(12,'化学基礎')
,(13,'物理基礎')
,(14,'物理')
,(15,'体育')
,(16,'保健')
,(17,'美術I')
,(18,'英語コミュニケーションI')
,(19,'英語コミュニケーションIl')
,(20,'家庭基礎')
,(21,'工業技術基礎')
,(22,'実習')
,(23,'課題研究')
,(24,'工業情報数理')
,(25,'機械設計')
,(26,'製図')
,(27,'機械工作')
,(28,'原動機')
,(29,'自動車工学')
,(30,'生産システム技術')
,(31,'電子機械')
,(32,'生産技術')
,(33,'電気回路')
,(34,'電気機器')
,(35,'電力技術')
,(36,'電子技術')
,(37,'電子回路')
,(38,'電子計測制御')
,(39,'通信技術')
,(40,'プログラミング技術')
,(41,'ハードウェア技術')
,(42,'コンピュータシステム技術')
,(43,'建築構造')
,(44,'建築計画')
,(45,'建築構造設計')
,(46,'建築法規')
,(47,'建築施工');

INSERT IGNORE INTO sgmdb.semester
(semester_id,semester_number)
VALUES
 (1,1)
,(2,2)
,(3,3);

INSERT IGNORE INTO sgmdb.test
(test_id,test_name)
VALUES
 (1,"中間テスト")
,(2,"期末テスト")
,(3,"学年末テスト");
