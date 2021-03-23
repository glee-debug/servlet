package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.entities.Category;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowSendServlet", value = "/showsend")
public class ShowSendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Context context = new Context();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.findAll();
        context.setVariable("list",list);

        String html = ThUtils.print("send.html",context);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
