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
        HttpSession session = request.getSession();
        String likeid = (String)session.getAttribute("like"+id);
        if (likeid==null){
            productDao.addLikeCount(Integer.parseInt(id));
            session.setAttribute("like"+id,id);
        }
        response.sendRedirect("/detail?id="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
