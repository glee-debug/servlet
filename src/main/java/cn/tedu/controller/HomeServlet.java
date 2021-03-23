package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entities.Banner;
import cn.tedu.entities.Category;
import cn.tedu.entities.Product;
import cn.tedu.entities.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        String keyword = request.getParameter("keyword");

        Context context = new Context();

        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.findAll();
        context.setVariable("list",list);

        BannerDao bannerDao = new BannerDao();
        List<Banner> blist = bannerDao.findAll();
        context.setVariable("blist",blist);

        //取出session 对象里面的user对象 并装进容器
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        context.setVariable("user",user);

        ProductDao productDao = new ProductDao();
        if (cid!=null){
            List<Product> plist = productDao.findallByCid(cid);
            context.setVariable("plist",plist);

        }else if(keyword!=null){
            List<Product> plist = productDao.SearchByKeyWord(keyword);
            context.setVariable("plist",plist);
        }
        else{
            List<Product> plist = productDao.findall();
            context.setVariable("plist",plist);
        }

        List<Product> vlist = productDao.findViewList();
        context.setVariable("vlist",vlist);

        List<Product> llist = productDao.findLikeList();
        context.setVariable("llist",llist);

        String html = ThUtils.print("home.html",context);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
