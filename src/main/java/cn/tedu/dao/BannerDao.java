package cn.tedu.dao;

import cn.tedu.entities.Banner;
import cn.tedu.entities.Category;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BannerDao {
    public List<Banner> findAll() {
        List<Banner> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select * from banner";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String url = rs.getString(2);
                Banner banner = new Banner(id,url);
                list.add(banner);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
