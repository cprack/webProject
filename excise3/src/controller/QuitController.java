package controller;

import filter.Filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

        //接收到退出指令时，清空session,cookie中的数据，重定向到登录页面
        String msg = request.getParameter("id");
        if(quit.equals(msg)){
            request.getSession().setAttribute("role",null);
            Cookie cookie = new Cookie("userName", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            new Filter().destroy();
            response.sendRedirect("login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        this.doGet(request,response);
    }
}
