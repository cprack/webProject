package tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 13281
 */
public class JdbcUtil {
    private static String DRIVER;
    private static String URL;
    private static String USER ;
    private static String PASSWORD ;
    private Connection con;


    static {
        try {
            Properties pro = new Properties();
            //获取该文件的绝对路径
            InputStream in = new BufferedInputStream(new FileInputStream(JdbcUtil.class.getResource("/").getPath()+"resources/jdbc.properties"));
            pro.load(new InputStreamReader(in,"utf-8"));
            DRIVER = pro.getProperty("DRIVER");
            in.close();
            URL = pro.getProperty("URL");
            USER = pro.getProperty("USER");
            PASSWORD = pro.getProperty("PASSWORD");
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JdbcUtil(){

    }

    public Connection getConnection(){
        try {
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void close(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





}
