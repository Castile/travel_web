package com.hongliang.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongliang.travel.domain.ResultInfo;
import com.hongliang.travel.domain.User;
import com.hongliang.travel.service.UserService;
import com.hongliang.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Hongliang Zhu
 * @create 2020-05-12 23:34
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();
    private ResultInfo info = new ResultInfo();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCode(request, response)) return;


        // 获取数据
        Map<String, String[]> map = request.getParameterMap();

        // 封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service完成注册
//        UserService service = new UserServiceImpl();
        boolean flag = service.register(user);

//        ResultInfo info = new ResultInfo();

        // 响应结果
        if(flag){
            // 注册成功
            info.setFlag(true);

        }else{
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        // 将info对象序列化为json对象
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
        String json = ToJsonDataAsString(info);
        // 将json返回给客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }

    private boolean checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 验证码的验证
        String check = request.getParameter("check");
        String checkcode_server = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER"); // 保证验证码只使用一次
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            // 验证码错误
//            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码有误！");
            // 将info对象序列化为json对象
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(info);
            String json = ToJsonDataAsString(info);
            // 将json返回给客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return true;
        }
        return false;
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 验证码的验证
        if(checkCode(request, response)) return;
        // 获取用户名和密码
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service查询
//        UserService service = new UserServiceImpl();
        User u = service.login(user);
        // 判断
        ResultInfo info = new ResultInfo();
        if (u == null){
            // 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }
        // 判断是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            // 用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请登录邮箱激活！");
        }
        // 登录成功判断
        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user", u);

            info.setFlag(true);
        }

        // 响应数据
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(), info);
        ToJsonData(info, response);

    }

    /**
     * 查询一个
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session获取登录用户
        Object user = request.getSession().getAttribute("user");
        // 将user写回客户端
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(), user);
        ToJsonData(user, response);
    }

    /**
     * 用户退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 销毁session
        request.getSession().invalidate();

        // 跳转
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 用户激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取激活码
        String code = request.getParameter("code");
        if(code != null){
            // 调用service完成激活
//            UserService service = new UserServiceImpl();
            boolean flag = service.activate(code);
            String msg = null;
            if(flag){
                // 激活成功
                msg = "激活成功, 请<a href='login.html'>登录</a>";

            }else{
                msg = "激活失败，请联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

}
