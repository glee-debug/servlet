package cn.tedu.controller;

import cn.tedu.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * urlpatterns 的几种写法
 * 1.精确匹配
 */
@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsend","/showBanner","*.jpg","/*"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    //此方法是在请求servlet或其他资源之前调用的方法
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //判断是否登录过 登录过 放行 没登录 重定向到登录页面
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session  = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            resp.sendRedirect("/showlogin");
            return;
        }else {
            //放行 运行执行请求的资源(文件资源或servlet动态资源)
            chain.doFilter(request, response);
        }
    }
}
