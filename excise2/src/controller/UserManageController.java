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
@WebServlet("/userManage.do")
public class UserManageController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.getRequestDispatcher("/userManage.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.doGet(request,response);
    }
}
