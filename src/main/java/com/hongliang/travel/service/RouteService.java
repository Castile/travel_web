package com.hongliang.travel.service;

import com.hongliang.travel.domain.Route;
import com.hongliang.travel.domain.pageBean;

import java.util.List;

/**
 * 线路
 * @author Hongliang Zhu
 * @create 2020-05-17 16:19
 */
public interface RouteService {

    /**
     * 根据类别 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    pageBean pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(String rid);

    /**
     * 查询前6个路线
     * @return
     */
    List<Route> findSix(String  begin, String end);


}
