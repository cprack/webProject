package dao;

import tools.JdbcUtil;
import vo.User;

import java.sql.SQLException;

/**
 * @author 13281
 */
public class UserDao {
    public User get(String userName){
        //连接数据库，根据userName查找是否存在该用户
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        User user = new User();

        try {
            user = jdbcUtil.getByUserName(userName);
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
