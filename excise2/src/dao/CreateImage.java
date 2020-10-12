package dao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 13281
 */
public class CreateImage {
    private static final int width= 100;
    private static final int height = 35;
    private static final int length = 4;
    public static final int lineCount = 20;

    //生成验证码

    private static final String str = "23456789" +
            "ABCDEFGHJKLMNPQRSTUVWXYZ" + "abcdefghijkmnpqrstuvwxyz";

    private static Random random = new Random();

    public String createCode(){
        String code = "";
        for(int i = 0;i < length;i++){
            char c = str.charAt(random.nextInt(str.length()));
            code += c;
        }
        return code;
    }

    public Color getColor(){
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));

    }
    public Font getFont(){
        return new Font("宋体",Font.BOLD,22);
    }

    public void drawChar(Graphics g,String code){
        g.setFont(getFont());
        for(int i = 0;i < length;i++){
            char c = code.charAt(i);
            g.setColor(getColor());
            g.drawString(c + "",20 * i + 10,20);
        }
    }

    public void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        g.setColor(getColor());
        g.drawLine(x,y,x + x1,y + y1);
    }

    public BufferedImage CreateImage(String code){
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);

        Graphics g = image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);

        drawChar(g,code);
        for(int i = 0;i < lineCount;i++){
            drawLine(g);
        }
        g.dispose();

        return image;
    }
}
