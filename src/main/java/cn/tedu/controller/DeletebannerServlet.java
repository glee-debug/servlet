package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entities.Banner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeletebannerServlet", value = "/deletebanner")
public class DeletebannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BannerDao bannerDao = new BannerDao();
        String url = bannerDao.selectUrl(Integer.parseInt(id));
        String path = request.getServletContext().getRealPath(url);
        new File(path).delete();
        bannerDao.deleteById(Integer.parseInt(id));
        response.sendRedirect("showBanner");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
