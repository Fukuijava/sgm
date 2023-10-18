package webapp.school_grades_mgmt.sgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import webapp.school_grades_mgmt.sgm.controller.HomeController;

import java.util.List;
import java.util.Map;
@Service
public class ClassCurriculumService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    ClassCurriculumService(JdbcTemplate jdbcTemplate) {//フィールド地の初期化
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HomeController.ClassCurriculum> findClassCurriculum(String classCurriculum) {
        String query = "SELECT * FROM curriculum WHERE id = '" + classCurriculum + "'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<HomeController.ClassCurriculum> classCurriculumList = result.stream()
                .map((Map<String, Object> row) -> new HomeController.ClassCurriculum(
                        (String) row.get("CurriculumName")
                )).toList();
        return classCurriculumList;

    }


}
