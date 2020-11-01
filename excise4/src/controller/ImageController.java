package controller;

import dao.CreateImageDao;

import javax.imageio.ImageIO;
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
@WebServlet("/createVerifyImage.do")
public class ImageController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {

        //生成验证码控制器
        CreateImageDao createImage = new CreateImageDao();

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

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }
}