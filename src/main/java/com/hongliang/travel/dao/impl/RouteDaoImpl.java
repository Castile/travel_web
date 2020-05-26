package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.RouteDao;
import com.hongliang.travel.domain.Route;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-17 16:27
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public int findTotalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid=?";
        // 定义sql模板
        String  sql = "select count(*) from tab_route where 1=1  ";
        StringBuilder sb = new StringBuilder(sql);

        List params= new ArrayList(); // 条件们
        // 判断参数是否有值
        if(cid!=0){
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0 && !"null".equals(rname)){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();

        int count = template.queryForObject(sql, Integer.class, params.toArray());
        return count;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid = ? and rname like ? limit ?,  ?";

        // 定义sql模板
        String  sql = "select * from tab_route where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        List params= new ArrayList(); // 条件们
        // 判断参数是否有值
        if(cid!=0){
            sb.append(" and cid = ?  ");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0 && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? "); // 分页条件

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);



        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());


    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    @Override
    public List<Route> findSix(int begin, int end) {
        String sql = "SELECT * FROM tab_route LIMIT ?, ? ";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), begin, end);

    }
}
