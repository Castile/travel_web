package com.hongliang.travel.service;

import com.hongliang.travel.domain.Category;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-13 0:29
 */
public interface CategoryService {
    /**
     * 查询所有类别
     * @return
     */
    List<Category> findAll();
}
