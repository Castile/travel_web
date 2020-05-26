package com.hongliang.travel.service.impl;

import com.hongliang.travel.dao.FavoriteDao;
import com.hongliang.travel.dao.RouteDao;
import com.hongliang.travel.dao.impl.FavoriteDaoImpl;
import com.hongliang.travel.dao.impl.RouteDaoImpl;
import com.hongliang.travel.domain.Favorite;
import com.hongliang.travel.domain.Ranks;
import com.hongliang.travel.domain.Route;
import com.hongliang.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-24 16:13
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private RouteDao routeDao = new RouteDaoImpl();


    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        if(favorite == null){
            return false;
        }else {
            return true;
        }


    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);

    }

    @Override
    public List<Route> findMyFavorite(int uid) {

        return favoriteDao.findMyFavorite(uid);
    }

    @Override
    public List<Route> getFavoriteRank() {

        // 查询到对应路线的收藏次数
        List<Ranks> favoriteRank = favoriteDao.getFavoriteRank();
        List<Route> list = new ArrayList<>();

        for (Ranks rank: favoriteRank){
            int rid = rank.getRid();
            //根据rid查询路线
            Route route = routeDao.findOne(rid);
            route.setCount(rank.getCnt()); // 设置收藏的次数
            list.add(route);

        }
        Collections.sort(list);

        return list;


    }
}
