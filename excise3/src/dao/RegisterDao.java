package dao;

import tools.JdbcUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "insert into t_user(userName,password,chrName,role) value(?,?,?,?)";

        pst = con.prepareStatement(sql);
        pst.setString(1,user.getUserName());
        pst.setString(2,user.getPassword());
        pst.setString(3,user.getChrName());
        pst.setString(4,user.getRole());
        pst.executeUpdate();

    }
}
