package cn.tedu.other;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BMIServlet", value = "/bmi")
public class BMIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String h = request.getParameter("height");
        String w = request.getParameter("weight");
        float height = Float.parseFloat(h);
        float weight = Float.parseFloat(w);
        float bmi = (float) (weight/Math.pow(height,2));
        String msg = null;
        if (bmi<18.5){
            msg = "太瘦了";
        }else if (bmi<=24){
            msg = "体重正常";
        }else if (bmi<=28){
            msg = "微胖";
        }else {
            msg = "肥胖";
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(bmi+":"+msg);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
