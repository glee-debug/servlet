package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entities.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet", value = "/send")
public class SendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        String categoryId = request.getParameter("categoryId");
        Part file = request.getPart("file");
        String info = file.getHeader("content-disposition");
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        String filename = UUID.randomUUID()+suffix;
        String path = request.getServletContext().getRealPath("images/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        Date date = new Date();
        String str = sdf.format(date);
        new File(path+str).mkdirs();
        file.write(path+str+filename);
        Product product = new Product(0,title,author,intro,"images/"+str+filename,
                0,0,System.currentTimeMillis(),Integer.parseInt(categoryId));
        ProductDao productDao = new ProductDao();
        productDao.insert(product);
        response.sendRedirect("/home");
    }
}
