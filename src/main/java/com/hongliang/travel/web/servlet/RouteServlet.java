package com.hongliang.travel.web.servlet;

import com.hongliang.travel.domain.Ranks;
import com.hongliang.travel.domain.Route;
import com.hongliang.travel.domain.User;
import com.hongliang.travel.domain.pageBean;
import com.hongliang.travel.service.FavoriteService;
import com.hongliang.travel.service.RouteService;
import com.hongliang.travel.service.impl.FavoriteServiceImpl;
import com.hongliang.travel.service.impl.RouteServiceImpl;
import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hongliang Zhu
 * @create 2020-05-17 16:11
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受 参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        // 接受线路名称
        String rname = request.getParameter("rname");
        if(rname != null && rname.length() > 0 && !"null".equals(rname) )
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");


        // 处理参数
        int cid = 0;
        if(cidStr != null && cidStr.length() >0 && !"null".equals(cidStr) ){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1; // 当前页码， 不传递，默认等于第一页
        if(currentPageStr != null && currentPageStr.length() >0 ){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        int pageSize = 0; // 每页显示的条目数， 默认是5
        if(pageSizeStr != null && pageSizeStr.length() >0 ){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }

        // 调用service查询pageBean
        pageBean pb = service.pageQuery(cid, currentPage, pageSize, rname);
        // 将pageBean对象序列化为json数据
        ToJsonData(pb, response);


    }


    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取id
        String rid = request.getParameter("rid");

        // 调用service查询Route对象
        Route route = service.findOne(rid);

        // 转为json写回客户端
        ToJsonData(route, response);


    }

    public void findSix(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");

        List<Route> RouteList = service.findSix(begin, end);

        //写回客户端
        ToJsonData(RouteList, response);

    }


    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取线路id
        String rid = request.getParameter("rid");
        // 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid; // 用户id
        if(user == null){
            //  用户还未登录
            uid = 0;

        }else {
            // 用户已经登录
            uid = user.getUid();
        }

        // 调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        // 写回客户端
        ToJsonData(flag, response);


    }



    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        // 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid; // 用户id
        if(user == null){
            //  用户还未登录
            return;

        }else {
            // 用户已经登录
            uid = user.getUid();
        }

        // 调用service
        favoriteService.add(rid, uid);


    }

    /**
     * 我的收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid; // 用户id
        if(user == null){
            //  用户还未登录
            return;

        }else {
            // 用户已经登录
            uid = user.getUid();
        }

        // 调用service
        List<Route> myFavorite = favoriteService.findMyFavorite( uid);

        //写回
        ToJsonData(myFavorite, response);


    }

    /**
     * 收藏排行榜
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void favoriteRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Route> routes = favoriteService.getFavoriteRank();

        ToJsonData(routes, response);

    }





}
