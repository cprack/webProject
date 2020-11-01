package controller;

import com.google.gson.Gson;
import dao.CityDao;
import dao.ProvinceDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 13281
 */
@WebServlet("/provinceCity.do")
public class ProvinceCityController extends HttpServlet {
    private String getProvince = "getProvince";
    private String getCity = "getCity";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String province = request.getParameter("province");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();
        String list;
        if(province.equals(getProvince)){
            list = new Gson().toJson(new ProvinceDao().get());
        }else {
            list = new Gson().toJson(new CityDao().get(province));
        }
        out.println(list);
        out.flush();
        out.close();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
