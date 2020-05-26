package com.hongliang.travel.dao.impl;

import com.hongliang.travel.dao.UserDao;
import com.hongliang.travel.domain.User;
import com.hongliang.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author Hongliang Zhu
 * @create 2020-05-11 23:00
 */
public class UserDaoImpl implements UserDao {


    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByName(String username) {
        User user = null;
        try {
            //定义sql语句
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code) VALUES (?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(),user.getEmail(), user.getStatus(), user.getCode());
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    @Override
    public User findByNameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;

    }
}
