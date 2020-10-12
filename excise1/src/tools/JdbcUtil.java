package tools;

import vo.Download;
import vo.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author 13281
 */
public class JdbcUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf8";
    private static final String userName = "root";
    private static final String password = "scsf_Y5L";
    private Connection con;
    private PreparedStatement pst;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JdbcUtil(){
        super();
    }

    public Connection getConnection(){
        try {
            con = DriverManager.getConnection(url,userName,password);
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

    public User getByUserName(String userName) throws SQLException {
        User user = new User();
        String sql = "select * from t_user where userName = ?";

        pst = con.prepareStatement(sql);
        pst.setString(1,userName);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setChrName(rs.getString("chrName"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }

    public ArrayList<Download> getAll() throws SQLException {
        ArrayList<Download> arrayList = new ArrayList<>();

        String sql = "select * from t_downloadList";

        pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()){

            int id = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String path = rs.getString(3);
            String description = rs.getString(4);
            String size = rs.getString(5);
            int star = Integer.parseInt(rs.getString(6));
            String image = rs.getString(7);
            Download download = new Download(id,name,path,description,size,star,image);
            arrayList.add(download);
        }
        return arrayList;
    }

}
