package cn.tedu.dao;

import cn.tedu.entities.Banner;
import cn.tedu.entities.Category;
import cn.tedu.utils.DBUtils;

import java.sql.*;
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

    public String selectUrl(int id) {
        String url = null;
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select url from banner where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                url = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return url;
    }

    public void deleteById(int id) {
        try(Connection conn = DBUtils.getConn()) {
            String sql = "delete from banner where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("删除轮播图"+id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(Banner banner) {
        try(Connection conn = DBUtils.getConn()) {
            String sql = "insert into banner values(null,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,banner.getUrl());
            ps.executeUpdate();
            System.out.println("插入完成");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
