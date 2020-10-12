package controller;

import com.google.gson.Gson;
import dao.RegisterDao;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13281
 */
@WebServlet("/register.do")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        String chrName = request.getParameter("chrName");
        String email = request.getParameter("email");
        String province = request.getParameter("province");
        String city = request.getParameter("province");
        String password = request.getParameter("password");
        String passwordOk = request.getParameter("passwordOk");
        Map<String, Object> map = new HashMap<String, Object>(1);

        User user = new User(userName,password,chrName,"普通用户");
        if(new RegisterDao().insert(user)){
            map.put("code",0);
            response.setContentType("text/html;charset = UTF-8");
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(map));
            out.flush();
            out.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.doGet(request,response);
    }
}
