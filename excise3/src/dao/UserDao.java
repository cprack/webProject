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
public class UserDao {

    private Connection con;
    private PreparedStatement pst;

    public User get(String userName){
        //连接数据库，根据userName查找是否存在该用户
        con = new JdbcUtil().getConnection();
        User user = new User();

        try {
            user = this.getByUserName(userName);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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
}
