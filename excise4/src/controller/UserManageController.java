package controller;

import com.google.gson.Gson;
import dao.UserDao;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13281
 */
@WebServlet("/userManage.do")
public class UserManageController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<User> userList = new ArrayList<>();
        try {
            userList = new UserDao().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("rows",userList);
        map.put("total",userList.size());
        String jsonStr = new Gson().toJson(map);
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.doGet(request,response);
    }
}
