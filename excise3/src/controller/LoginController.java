package controller;

import com.google.gson.Gson;
import dao.UserDao;
import tools.JdbcUtil;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13281
 */
@WebServlet("/ajaxLoginCheck.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        //获取提交的用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //获取生成和提交的验证码
        String vCode = request.getParameter("vCode");
        String verify_code = (String) session.getAttribute("verityCode");
        String autoLogin = request.getParameter("autoLogin");

        Map<String,Object> map = new HashMap<String,Object>(2);
        User user = new UserDao().get(userName);



        if (verify_code.equalsIgnoreCase(vCode)) {
            //验证码正确则判断用户名是否存在
            //在数据库中查询是否存在该用户

            if(userName.equals(user.getUserName())){
                //用户名存在则判断密码是否正确
                if(password.equals(user.getPassword())){
                    //密码正确则跳转到主页面,并且判断是否勾选一周免登陆
                    if(autoLogin != null){
                        //如果勾选了一周免登陆，则将用户的账号和密码添加到cookie
                        Cookie cookie1 = new Cookie("userName", userName);
                        cookie1.setMaxAge(60*60*24*7);
                        response.addCookie(cookie1);
                        Cookie cookie2 = new Cookie("password", password);
                        cookie1.setMaxAge(60*60*24*7);
                        response.addCookie(cookie2);
                    }
                    session.setAttribute("user",user);
                    map.put("code",0);
                    map.put("info","登录成功");
                }else {
                    //密码不正确
                    map.put("code",3);
                    map.put("info","密码不正确!");
                }
            }else {
                //用户不存在
                map.put("code",2);
                map.put("info","用户名不存在!");
            }
        } else {
            //验证码错误
            map.put("code",1);
            map.put("info","验证码不正确!");
        }

        request.getSession().setAttribute("role",user.getRole());
        String jsonStr = new Gson().toJson(map);
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
