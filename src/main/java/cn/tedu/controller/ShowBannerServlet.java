package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entities.Banner;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowBannerServlet", value = "/showBanner")
public class ShowBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerDao bannerDao = new BannerDao();
        List<Banner> banlist = bannerDao.findAll();
        Context context = new Context();
        context.setVariable("banlist",banlist);

        String html = ThUtils.print("banner.html",context);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
