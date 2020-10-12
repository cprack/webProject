package dao;

import tools.JdbcUtil;
import vo.Download;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
public class DownloadDao {
    public Object get(){
        //连接数据库，将数据库中的内容提取出来
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        Object downloads = null;
        try {
            downloads = jdbcUtil.getAll();
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return downloads;
    }
}
