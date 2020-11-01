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
public class UpdateUserDao {
    private Connection con;
    private PreparedStatement pst;

    public void update(User user){
        con = new JdbcUtil().getConnection();
        try {
            this.updateUser(user);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateUser(User user) throws SQLException {
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
        String sql = "update t_user set password = ?,chrName = ?,email = ?,province = ?,city = ? where userName=?";
        pst = con.prepareStatement(sql);
        pst.setString(1,user.getPassword());
        pst.setString(2,user.getChrName());
        pst.setString(3,user.getEmail());
        pst.setString(4,province);
        pst.setString(5,city);
        pst.setString(6,user.getUserName());
        pst.executeUpdate();

        return true;
    }
}
