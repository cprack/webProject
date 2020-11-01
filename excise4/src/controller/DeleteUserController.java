package controller;

import com.google.gson.Gson;
import dao.DeleteUserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13281
 */
@WebServlet("/deleteUser.do")
public class DeleteUserController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("ids");
        Map<String, Object> map = new HashMap(1);
        if(id.length() != 1){
            String[] ids = id.split(",");
            for (String i: ids) {
                new DeleteUserDao().Delete(i);
            }
        }else {
            new DeleteUserDao().Delete(id);
        }
        map.put("info","删除成功!");
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
