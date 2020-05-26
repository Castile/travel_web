package com.hongliang.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongliang.travel.domain.Category;
import com.hongliang.travel.service.CategoryService;
import com.hongliang.travel.service.UserService;
import com.hongliang.travel.service.impl.CategoryServiceImpl;
import com.hongliang.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Hongliang Zhu
 * @create 2020-05-12 23:48
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询所有方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有
        List<Category> all = service.findAll();
        // 序列化json
        ToJsonData(all, response);
    }

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
