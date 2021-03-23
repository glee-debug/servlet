package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
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

@WebServlet(name = "DetailServlet", value = "/detail")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        Context context = new Context();

        //取出session 对象里面的user对象 并装进容器
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        context.setVariable("user",user);

        ProductDao productDao = new ProductDao();
        Product product = productDao.findById(Integer.parseInt(id));
        context.setVariable("product",product);

        List<Product> vlist = productDao.findViewList();
        context.setVariable("vlist",vlist);

        List<Product> llist = productDao.findLikeList();
        context.setVariable("llist",llist);

        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.findAll();
        context.setVariable("list",list);


        String html = ThUtils.print("detail.html",context);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
