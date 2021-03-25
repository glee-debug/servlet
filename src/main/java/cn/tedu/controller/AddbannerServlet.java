package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entities.Banner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddbannerServlet", value = "/addbanner")
public class AddbannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        Part file = request.getPart("file");
        String info = file.getHeader("content-disposition");
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        String filename = UUID.randomUUID()+suffix;
        String path = request.getServletContext().getRealPath("images/");
        new File(path).mkdirs();
        file.write(path+filename);
        Banner banner = new Banner(0,"images/"+filename);
        BannerDao bannerDao = new BannerDao();
        bannerDao.insert(banner);
        response.sendRedirect("/showBanner");
    }
}
