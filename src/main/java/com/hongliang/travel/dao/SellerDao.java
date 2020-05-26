package com.hongliang.travel.dao;

import com.hongliang.travel.domain.Seller;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 0:05
 */
public interface SellerDao {

    /**
     * 根据id查询商家对象
     * @param id
     * @return
     */
    public Seller findByid(int id);
}
