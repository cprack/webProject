package controller;

import dao.UserDao;
import tools.JdbcUtil;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 13281
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");

        HttpSession session = request.getSession();

        //获取提交的用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //获取生成和提交的验证码
        String verifyCode = request.getParameter("verify");
        String verify_code = (String) session.getAttribute("verityCode");

        //错误信息
        String errorMsg = "";
        //在数据库中查询是否存在该用户
        User user = new UserDao().get(userName);
        //如果存在该用户则判断密码是否正确
        if (userName.equals(user.getUserName())) {
            if (password.equals(user.getPassword())) {
                //密码正确则判断验证码是否正确
                if (verify_code.equalsIgnoreCase(verifyCode)) {
                    //验证码正确则跳转到主页面
                    String chrName = user.getChrName();
                    session.setAttribute("chrName", chrName);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                } else {
                    //验证码错误则跳转到错误页面，提示验证码错误
                    errorMsg += "抱歉，您输入的验证码不正确！";
                    session.setAttribute("errorMsg", errorMsg);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }

            } else {
                //密码不正确转到错误页面，提示密码错误
                errorMsg += "抱歉，您输入的密码不正确！";
                session.setAttribute("errorMsg", errorMsg);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }

        } else {
            //如果不存在该用户转到错误提示页面，提示用户名不存在
            errorMsg += "抱歉，您输入的用户名不存在！";
            session.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
