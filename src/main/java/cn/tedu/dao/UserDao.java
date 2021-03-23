package cn.tedu.dao;

import cn.tedu.entities.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User login(String username, String password) {
        User user = null;
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select * from vrduser where username=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt(1);
                user = new User(id,username,password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
