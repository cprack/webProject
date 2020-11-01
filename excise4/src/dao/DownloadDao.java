package dao;

import tools.JdbcUtil;
import vo.Download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
public class DownloadDao {

    private Connection con;
    private PreparedStatement pst;

    public Object get(){
        //连接数据库，将数据库中的内容提取出来
        JdbcUtil jdbcUtil = new JdbcUtil();
        con = jdbcUtil.getConnection();
        Object downloads = null;
        try {
            downloads = this.getAll();
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return downloads;
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
