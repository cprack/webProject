package dao;

import tools.JdbcUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

/**
 * @author 13281
 */
public class RegisterDao {
    private Connection con;
    private PreparedStatement pst;

    public boolean insert(User user){
        //连接数据库，根据userName查找是否存在该用户
        con = new JdbcUtil().getConnection();

        try {
            this.insertUser(user);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void insertUser(User user) throws SQLException {
        String province = null;
        String city = null;
        String sql1 = "select * from t_city where id = ?" ;
        pst = con.prepareStatement(sql1);
        pst.setInt(1,parseInt(user.getCity()));
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            province = rs.getString("province");
            city = rs.getString("city");
        }
        String sql = "insert into t_user(userName,password,chrName,role,email,province,city) value(?,?,?,?,?,?,?)";

        pst = con.prepareStatement(sql);
        pst.setString(1,user.getUserName());
        pst.setString(2,user.getPassword());
        pst.setString(3,user.getChrName());
        pst.setString(4,user.getRole());
        pst.setString(5,user.getEmail());
        pst.setString(6,province);
        pst.setString(7,city);
        pst.executeUpdate();

    }
}
