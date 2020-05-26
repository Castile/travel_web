package com.hongliang.travel.dao;

import com.hongliang.travel.domain.RouteImg;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-23 23:58
 */
public interface RouteImgDao {

    /**
     * 根据 id查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByid(int rid);

}
