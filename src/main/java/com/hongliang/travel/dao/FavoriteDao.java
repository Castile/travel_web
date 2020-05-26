package com.hongliang.travel.dao;

import com.hongliang.travel.domain.Favorite;
import com.hongliang.travel.domain.Ranks;
import com.hongliang.travel.domain.Route;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 16:13
 */
public interface FavoriteDao {

    /**
     * 根据rid和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏的次数
     *
     *
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    void add(int rid, int uid);

    List<Route> findMyFavorite(int uid);

    List<Ranks> getFavoriteRank();

}
