package com.hongliang.travel.dao;

import com.hongliang.travel.domain.User;

/**
 * @author Hongliang Zhu
 * @create 2020-05-11 23:00
 */
public interface UserDao {

    /**
     * 根据用户用户名查询用户信息
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);


    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新用户的激活状态
     * @param user
     */
    void updateStatus(User user);

    User findByNameAndPassword(String username, String password);
}
