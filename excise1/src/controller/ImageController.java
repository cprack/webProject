package controller;

import dao.CreateImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 13281
 */
@WebServlet("/ImageServlet.do")
public class ImageController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        //生成验证码控制器
        CreateImage createImage = new CreateImage();

        String vCode = createImage.createCode();

        HttpSession session = request.getSession();

        session.setAttribute("verityCode",vCode);

        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        BufferedImage image = createImage.CreateImage(vCode);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",response.getOutputStream());
        out.flush();
        out.close();


    }
}
