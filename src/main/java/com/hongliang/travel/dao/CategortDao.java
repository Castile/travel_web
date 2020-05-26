package com.hongliang.travel.dao;

import com.hongliang.travel.domain.Category;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-13 0:22
 */
public interface CategortDao {

    /**
     * 查询所有类别
     * @return
     */
    List<Category> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Category findbyId(int id);


}