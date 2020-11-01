package controller;

import com.google.gson.Gson;
import dao.UserDao;
import vo.User;

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
@WebServlet("/checkExist.do")
public class CheckExistController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        User user = new UserDao().get(userName);
        Map<String, Object> map = new HashMap<>(1);
        if(user.getUserName() == null){
            //用户未被注册
            map.put("code",0);
        }else {
            map.put("code",1);
        }

        String jsonStr = new Gson().toJson(map);
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
