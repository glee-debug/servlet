package cn.tedu.controller;

import cn.tedu.dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddLikeCountServlet", value = "/addLikeCount")
public class AddLikeCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        ProductDao productDao = new ProductDao();
        productDao.addLikeCount(Integer.parseInt(id));
        response.sendRedirect("/detail?id="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
