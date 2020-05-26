package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.RouteImgDao;
import com.hongliang.travel.domain.Route;
import com.hongliang.travel.domain.RouteImg;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 0:00
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
    @Override
    public List<RouteImg> findByid(int rid) {
        String sql = "SELECT * FROM tab_route_img WHERE rid = ?";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }
}
