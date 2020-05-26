package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.FavoriteDao;
import com.hongliang.travel.domain.Favorite;
import com.hongliang.travel.domain.Ranks;
import com.hongliang.travel.domain.Route;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 16:14
 */
public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;

        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ? ;";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }
        return favorite;

    }


    public int findCountByRid(int rid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid =? ";
        return template.queryForObject(sql, Integer.class, rid);

    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?, ?,?)";
        template.update(sql,rid, new Date(), uid);
    }

    @Override
    public List<Route> findMyFavorite(int uid) {
        String sql = "SELECT * FROM tab_route WHERE rid IN( SELECT rid FROM tab_favorite WHERE uid= ? ) ";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), uid);

    }

    @Override
    public List<Ranks> getFavoriteRank() {
        String sql = "SELECT rid, COUNT(*) cnt FROM  tab_favorite GROUP BY rid ORDER BY cnt DESC";
        return template.query(sql, new BeanPropertyRowMapper<Ranks>(Ranks.class));

    }
}
