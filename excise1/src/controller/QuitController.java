package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 13281
 */
@WebServlet("/quit.do")
public class QuitController extends HttpServlet {
    private static final String quit = "quit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");

        //接收到退出指令时，清空session中的数据，重定向到登录页面
        String msg = request.getParameter("id");
        if(quit.equals(msg)){
            request.getSession().invalidate();
            response.sendRedirect("login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        this.doGet(request,response);
    }
}
