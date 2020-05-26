package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.SellerDao;
import com.hongliang.travel.domain.Seller;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 0:05
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findByid(int id) {
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), id);

    }
}
