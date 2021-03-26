package cn.tedu.other;

import cn.tedu.dao.ProductDao;
import cn.tedu.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HellojsonServlet", value = "/hellojson")
public class HellojsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //如果需要给客户端返回比较复杂的数据时 使用json
        ProductDao productDao = new ProductDao();
        List<Product> list = productDao.findall();
        // 异步请求 只能给客户端返回字符串 不能返回对象
        // 需要将装着product对象的list集合转成json字符串
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(list);
        System.out.println(jsonString);
        //将json字符串返回给客户端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(jsonString);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
