package controller;

import dao.UserDao;
import tools.JdbcUtil;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        String isLogin = request.getParameter("isLogin");

        //错误信息
        String errorMsg = "";
        //在数据库中查询是否存在该用户
        User user = new UserDao().get(userName);


        if (verify_code.equalsIgnoreCase(verifyCode)) {
            //验证码正确则判断用户名是否存在
            if(userName.equals(user.getUserName())){
                //用户名存在则判断密码是否正确
                if(password.equals(user.getPassword())){
                    //密码正确则跳转到主页面,并且判断是否勾选一周免登陆
                    if(isLogin != null){
                        //如果勾选了一周免登陆，则将用户的账号和密码添加到cookie
                        Cookie cookie1 = new Cookie("userName", userName);
                        cookie1.setMaxAge(60*60*24*7);
                        response.addCookie(cookie1);
                    }else {
                        //如果没勾选一周免登陆，则使cookie失效
                        Cookie cookie1 = new Cookie("userName", null);
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                    String chrName = user.getChrName();
                    String role = user.getRole();
                    session.setAttribute("chrName", chrName);
                    session.setAttribute("role", role);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                }else {
                    //密码不正确转到错误页面，提示密码错误
                    errorMsg += "抱歉，您输入的密码不正确！";
                    session.setAttribute("errorMsg", errorMsg);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }else {
                //如果不存在该用户转到错误提示页面，提示用户名不存在
                errorMsg += "抱歉，您输入的用户名不存在！";
                session.setAttribute("errorMsg", errorMsg);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            //验证码错误则跳转到错误页面，提示验证码错误
            errorMsg += "抱歉，您输入的验证码不正确！";
            session.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
