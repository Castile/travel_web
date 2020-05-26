package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.CategortDao;
import com.hongliang.travel.domain.Category;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author Hongliang Zhu
 * @create 2020-05-13 0:23
 */
public class CategoryDaoImpl  implements CategortDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category";

        List<Category> list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return list;
    }

    @Override
    public Category findbyId(int id) {
        String sql = "SELECT * FROM tab_category where cid = ?";

        return template.queryForObject(sql, new BeanPropertyRowMapper<Category>(Category.class), id);
    }
}
