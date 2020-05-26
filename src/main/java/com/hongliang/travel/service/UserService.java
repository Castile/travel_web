package com.hongliang.travel.service;

import com.hongliang.travel.domain.User;

/**
 * @author Hongliang Zhu
 * @create 2020-05-11 22:59
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     *
     * 激活用户
     * @param code
     * @return
     */
    boolean activate(String code);

    /**
     * 登录用户
     * @param user
     * @return
     */
    User login(User user);
}
