package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/loginaction")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.login(username,password);
        if (user==null){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println("用户名或密码错误");
            pw.close();
        }else {
            //得到session对象
            HttpSession session = request.getSession();
            //将user对象保存进session里面
            session.setAttribute("user",user);

            //获取记住用户名和密码多选框的值的判断
            String rem = request.getParameter("rem");
            if (rem!=null){
                System.out.println("需要记住用户名和密码");
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //设置保存时间
                c1.setMaxAge(60*60*24*30);
                response.addCookie(c1);
                response.addCookie(c2);
            }
            response.sendRedirect("/home");
        }
    }
}
