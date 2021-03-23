package cn.tedu.dao;

import cn.tedu.entities.Banner;
import cn.tedu.entities.Product;
import cn.tedu.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void insert(Product product) {
        try(Connection conn = DBUtils.getConn()) {
            String sql = "insert into product values(null,?,?,?,?,0,0,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,product.getTitle());
            ps.setString(2,product.getAuthor());
            ps.setString(3,product.getIntro());
            ps.setString(4,product.getUrl());
            ps.setLong(5,product.getCreated());
            ps.setInt(6,product.getCategory_id());
            ps.executeUpdate();
            System.out.println("插入完成");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Product> findall() {
        List<Product> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,url,viewCount,likeCount from product";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String url = rs.getString(3);
                int viewCount = rs.getInt(4);
                int likeCount = rs.getInt(5);
                Product product = new Product(id,title,url,viewCount,likeCount);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Product> findViewList() {
        List<Product> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,intro,url from product order by viewCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String intro = rs.getString(3);
                String url = rs.getString(4);
                Product product = new Product(id,title,intro,url);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Product> findLikeList() {
        List<Product> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,intro,url from product order by likeCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String intro = rs.getString(3);
                String url = rs.getString(4);
                Product product = new Product(id,title,intro,url);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Product> findallByCid(String cid) {
        List<Product> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,url,viewCount,likeCount from product where category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String url = rs.getString(3);
                int viewCount = rs.getInt(4);
                int likeCount = rs.getInt(5);
                Product product = new Product(id,title,url,viewCount,likeCount);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Product> SearchByKeyWord(String keyword) {
        List<Product> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,url,viewCount,likeCount from product where title like ? or author like ? or " +
                    "intro like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            ps.setString(2,"%"+keyword+"%");
            ps.setString(3,"%"+keyword+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String url = rs.getString(3);
                int viewCount = rs.getInt(4);
                int likeCount = rs.getInt(5);
                Product product = new Product(id,title,url,viewCount,likeCount);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;

    }

    public Product findById(int id) {

        Product product =null;
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select * from product where id =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int uid = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                long created  = rs.getLong(8);
                int catagoryId = rs.getInt(9);
                product = new Product(id,title,author,intro,url,viewCount,likeCount,created,catagoryId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public String findUrlById(int id) {
        String url=null;
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select url from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                url = rs.getString(1);
            }
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return url;
    }

    public void deleteById(int id) {
        try(Connection conn = DBUtils.getConn()) {
            String sql = "delete from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addLikeCount(int id) {
        try(Connection conn = DBUtils.getConn()) {
            String sql = "update product set likeCount=likeCount+1 where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
