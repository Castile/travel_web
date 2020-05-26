package com.hongliang.travel.dao;

import com.hongliang.travel.domain.Route;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-17 16:25
 */
public interface RouteDao {

    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid， start， pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);


    /**
     * 根据rid查询一条旅游线路
     * @param rid
     * @return
     */
    public Route findOne(int rid);


    List<Route> findSix(int begin, int end);
}
