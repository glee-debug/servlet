package cn.tedu.other;

import cn.tedu.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckUserServlet", value = "/checkUser")
public class CheckUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserDao userDao =new UserDao();
        boolean flg = userDao.selectUsername(username);
        String msg = flg?"用户名已存在!":"用户名可用!";

/*        if (flg){
            msg="用户名已被占用";
        }else {
            msg = "用户名可用";
        }*/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(msg);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
