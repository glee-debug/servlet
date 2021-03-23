package cn.tedu.dao;

import cn.tedu.entities.Category;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select * from category";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Category category = new Category(id,name);
                list.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
