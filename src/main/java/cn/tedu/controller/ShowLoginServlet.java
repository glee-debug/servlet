package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowLoginServlet", value = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Context context = new Context();
        //获取所有cookies
        Cookie[] cookies = request.getCookies();
        //添加非空判断 避免空指针异常
        if (cookies!=null){
            for (Cookie c:cookies){
                String n = c.getName();
                String p = c.getValue();
                if (n.equals("username")){
                    context.setVariable("username",p);
                }
                if (n.equals("password")){
                    context.setVariable("password",p);
                }
            }
        }
        String html = ThUtils.print("login.html",context);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
