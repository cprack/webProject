package dao;

import tools.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 13281
 */
public class DeleteUserDao {
    private Connection con;
    private PreparedStatement pst;

    public void Delete(String userName){
        //连接数据库，根据userName查找是否存在该用户
        con = new JdbcUtil().getConnection();
        try {
            this.DeleteUser(userName);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean DeleteUser(String userName) throws SQLException {
        String sql = "delete from t_user where userName = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1,userName);
        pst.executeUpdate();
        return true;
    }

}
