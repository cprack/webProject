package controller;

import dao.UpdateUserDao;
import vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 13281
 */
@WebServlet("/updateUser.do")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String chrName = request.getParameter("chrName");
        User user = new User(userName,password,chrName,"普通用户",email,province,city);
        new UpdateUserDao().update(user);
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException {
        this.doPost(request,response);
    }
}
