package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource dds;
    static {
        Properties p = new Properties();
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbDriver = p.getProperty("db.driver");
        String dbUrl = p.getProperty("db.url");
        String dbUsername = p.getProperty("db.username");
        String dbPwd = p.getProperty("db.password");

        dds = new DruidDataSource();
        dds.setDriverClassName(dbDriver);
        dds.setUrl(dbUrl);
        dds.setUsername(dbUsername);
        dds.setPassword(dbPwd);
        dds.setInitialSize(3);
        dds.setMaxActive(5);
    }
    public static Connection getConn() throws SQLException {
        Connection conn = dds.getConnection();
        return conn;
    }
}
