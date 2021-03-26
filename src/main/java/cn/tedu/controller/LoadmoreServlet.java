package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadmoreServlet", value = "/loadmore")
public class LoadmoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count = request.getParameter("count");
        ProductDao productDao = new ProductDao();
        List<Product> list = productDao.loadmore(Integer.parseInt(count));
        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(list);
        //将json字符串返回给客户端
        // application/json 告诉客户端返回的是json对象,这样客户端接收到数据时 会自动将json字符串转成json对象
        // 就不用JSON.parse()方法自己转换了
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(jsonStr);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
