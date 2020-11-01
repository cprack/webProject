package dao;

import tools.JdbcUtil;
import vo.Download;
import vo.Province;

import java.security.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
public class ProvinceDao {
    private Connection con;
    private PreparedStatement pst;

    public ArrayList<Province> get(){
        //连接数据库，获取省份信息
        JdbcUtil jdbcUtil = new JdbcUtil();
        con = jdbcUtil.getConnection();
        ArrayList<Province> province = null;
        try {
            province = this.getAll();
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return province;
    }

    public ArrayList<Province> getAll() throws SQLException {
        //获取数据库中所有的省份信息
        ArrayList<Province> provinces = new ArrayList<>();
        String sql = "select * from t_province";

        pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()){

            int id = Integer.parseInt(rs.getString(1));
            String province = rs.getString(2);
            Province p = new Province(id,province);
            provinces.add(p);
        }
        return provinces;
    }
}
