package com.hongliang.travel.service.impl;

import com.hongliang.travel.dao.CategortDao;
import com.hongliang.travel.dao.impl.CategoryDaoImpl;
import com.hongliang.travel.domain.Category;
import com.hongliang.travel.service.CategoryService;
import com.hongliang.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Hongliang Zhu
 * @create 2020-05-13 0:29
 */
public class CategoryServiceImpl implements CategoryService {

    private CategortDao dao = new CategoryDaoImpl();


    @Override
    public List<Category> findAll() {
        List<Category> categoryList = null;
        // 从Redis中去查询
        // 获取Jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        // 判断查询集合是否为空
//        Set<String> categories = jedis.zrange("category", 0, -1);
        // 查询sortedlist的分数(cid)和值(name)
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);

        if(categories == null || categories.size() == 0){
//            System.out.println("从数据库中查询...");
            // 为 空，要从数据库中进行查询，并存入到redis中
            categoryList = dao.findAll();
            for (int i = 0; i < categoryList.size(); i++) {
                jedis.zadd("category", categoryList.get(i).getCid(), categoryList.get(i).getCname());

            }

        }else {
//            System.out.println("从Redis中查询...");
            //缓存不为空， 将set的数据存入list
            categoryList = new ArrayList<Category>();
            for(Tuple tuple: categories){
                Category c = new Category();
                c.setCname(tuple.getElement());
                c.setCid((int)tuple.getScore());
                categoryList.add(c);
            }

        }

        return categoryList;
    }


}
