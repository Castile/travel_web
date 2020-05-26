package com.hongliang.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Hongliang Zhu
 * @create 2020-05-12 23:35
 */
public class BaseServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 完成方法的分发
        //获取请求路径
        String requestURI = request.getRequestURI();
//        System.out.println("请求的路径"+requestURI);
        // 获取方法的名称
        String methodName = requestURI.substring(requestURI.lastIndexOf('/') + 1);
//        System.out.println("方法名："+methodName);
        // 获取方法对象
//        System.out.println(this);

        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
//            method.setAccessible(true);
            method.invoke(this,request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    /**
     * 直接将传入的对象序列成json数据，并且写回到客户端
     * @param obj
     * @param response
     * @throws IOException
     */
    public void ToJsonData(Object obj, HttpServletResponse response) throws IOException {
        // 序列化json
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 直接将传入的对象序列成json数据， 并且返回给调用者
     * @param obj
     * @param response
     * @return
     * @throws JsonProcessingException
     */
    public String ToJsonDataAsString(Object obj) throws JsonProcessingException {

        return mapper.writeValueAsString(obj);
    }


}
