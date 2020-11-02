package dao;

import tools.JdbcUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
public class SearchDao {
    private Connection con;
    private PreparedStatement pst;
    public ArrayList<User> search(String userName, String chrName, String email, String province, String city) {
        con = new JdbcUtil().getConnection();
        ArrayList<User> users = null;
        try {
            users = this.searchAll(userName, chrName, email, province, city);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<User> searchAll(String userName,String chrName,String email,String province,String city) throws SQLException {
        StringBuffer sql = new StringBuffer();
        int i = 0;
        ArrayList<User> users = new ArrayList<>();
        sql.append("select * from t_user");
        if (userName != "") {
            sql.append(" where userName like '%").append(userName).append("%'");
            i = 1;
        }
        if (chrName != "" && i == 1) {
            sql.append(" and chrName like '%").append(chrName).append("%'");
        } else if(chrName != "") {
            i = 1;
            sql.append(" where chrName like '%").append(chrName).append("%'");
        }
        if (email != "" && i == 1) {
            sql.append(" and email like '%").append(email).append("%'");
        } else if(email != ""){
            i = 1;
            sql.append(" where email like '%").append(email).append("%'");
        }
        if (province != "" && i == 1) {
            sql.append(" and province like '%").append(province).append("%'");
        } else if(province != ""){
            i = 1;
            sql.append(" where province like '%").append(province).append("%'");
        }
        if (city != "" && i == 1) {
            sql.append(" and city like '%").append(city).append("%'");
        } else if(city != ""){
            i = 1;
            sql.append(" where city like '%").append(city).append("%'");
        }
        pst = con.prepareStatement(String.valueOf(sql));
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            User user = new User(rs.getString(1),"",rs.getString(3),"",rs.getString(5),rs.getString(6),rs.getString(7));
            users.add(user);
        }
        return users;
    }
}
