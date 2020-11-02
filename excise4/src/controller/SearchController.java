package controller;

import com.google.gson.Gson;
import dao.SearchDao;
import vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13281
 */
@WebServlet("/search.do")
public class SearchController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        String userName = request.getParameter("userName");
        String chrName = request.getParameter("chrName");
        String email = request.getParameter("email");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        ArrayList<User> users = new SearchDao().search(userName,chrName,email,province,city);
        Map<String,Object> map = new HashMap<>(2);
        map.put("rows",users);
        map.put("total",users.size());
        String jsonStr = new Gson().toJson(map);
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();


    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        this.doPost(request,response);
    }
}
