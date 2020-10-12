package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author 13281
 */
@WebServlet("/download.do")
public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //文件下载
        //下载资源操作
        String fileName =  request.getParameter("fileName");
        String path = getServletContext().getRealPath("/resources/" + fileName);
        response.setContentType(this.getServletContext().getMimeType(fileName));

        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));

        FileInputStream fileInputStream = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        int len = 0;
        byte[] buff = new byte[1024];
        while ((len = fileInputStream.read(buff)) > 0){
            os.write(buff,0,len);
        }
        fileInputStream.close();
        os.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        this.doGet(request,response);
    }
}
