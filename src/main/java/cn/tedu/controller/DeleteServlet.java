package cn.tedu.controller;

import cn.tedu.dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        ProductDao productDao = new ProductDao();
        String url = productDao.findUrlById(Integer.parseInt(id));
        String path = request.getServletContext().getRealPath(url);
        new File(path).delete();
        productDao.deleteById(Integer.parseInt(id));
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
