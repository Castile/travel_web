package com.hongliang.travel.service.impl;

import com.hongliang.travel.dao.*;
import com.hongliang.travel.dao.impl.*;
import com.hongliang.travel.domain.*;
import com.hongliang.travel.service.RouteService;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-17 16:21
 */
public class RouteServiceImpl implements RouteService {


    private RouteDao dao = new RouteDaoImpl();
    private RouteImgDao imgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private CategortDao categortDao = new CategoryDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();


    @Override
    public pageBean pageQuery(int cid, int currentPage, int pageSize, String rname) {
        pageBean<Route> pb = new pageBean<Route>();
        // 封装pageBean
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = dao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        // 设置当前页的数据集合
        int start = (currentPage -1)*pageSize; // 开始的记录数
        List<Route> list = dao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);
        // 设置总页数
        int totalPage = totalCount % pageSize== 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        // 根据id查询route表中 Route对象
        Route route = dao.findOne(Integer.parseInt(rid));
        // 查询图片集信息
        List<RouteImg> Routeimg_list = imgDao.findByid(route.getRid());
        // 将集合设置到Route对象
        route.setRouteImgList(Routeimg_list);
        // 根据商家id查询商家对象
        Seller seller = sellerDao.findByid(route.getSid());
        // 设置
        route.setSeller(seller);
        // 根据cid查询所属类别
        Category category = categortDao.findbyId(route.getCid());
        // 设置类别对象
        route.setCategory(category);

        // 查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);


        return  route;

    }

    @Override
    public List<Route> findSix(String begin, String end) {

        return dao.findSix(Integer.parseInt(begin), Integer.parseInt(end) );
    }



}
