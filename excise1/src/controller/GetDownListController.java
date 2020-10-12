package controller;

import dao.DownloadDao;
import tools.JdbcUtil;
import vo.Download;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
@WebServlet("/downloadRequest.do")
public class GetDownListController extends HttpServlet {
    private static final String jump1 = "jump_download";
    private static final String jump2 = "jump_main";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String id = request.getParameter("id");
        if(jump1.equals(id)){
            //如果接收到跳转到下载页面请求
            //从数据库读取信息到数组
            //转发给download.jsp页面
            HttpSession session = request.getSession();
            Object downloads = new DownloadDao().get();
            session.setAttribute("downloads",downloads);
            request.getRequestDispatcher("/download.jsp").forward(request, response);
        }else if(jump2.equals(id)){
            //如果接收到挑战到主页面的请求，则直接跳转
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        this.doGet(request,response);
    }
}

