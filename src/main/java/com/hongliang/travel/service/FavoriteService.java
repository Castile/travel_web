package com.hongliang.travel.service;


import com.hongliang.travel.domain.Route;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 16:12
 */
public interface FavoriteService {

    public boolean isFavorite(String rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);

    List<Route> findMyFavorite(int uid);

    List<Route> getFavoriteRank();
}
