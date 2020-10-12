package dao;

import tools.JdbcUtil;
import vo.City;
import vo.Province;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 13281
 */
public class CityDao {
    private Connection con;
    private PreparedStatement pst;

    public ArrayList<City> get(String province){
        //连接数据库，获取省份信息
        JdbcUtil jdbcUtil = new JdbcUtil();
        con = jdbcUtil.getConnection();
        ArrayList<City> city = null;
        try {
            city = this.getAll(province);
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public ArrayList<City> getAll(String province) throws SQLException {
        //获取数据库中所有的省份信息
        ArrayList<City> cities = new ArrayList<City>();
        String sql = "SELECT t_city.* FROM t_city,t_province WHERE t_city.province = t_province.province AND t_province.id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1,province);

        ResultSet rs = pst.executeQuery();
        while (rs.next()){

            int id = Integer.parseInt(rs.getString(1));
            String city = rs.getString(2);
            String provinces = rs.getString(3);
            City c = new City(id,provinces,city);
            cities.add(c);
        }
        return cities;
    }
}
