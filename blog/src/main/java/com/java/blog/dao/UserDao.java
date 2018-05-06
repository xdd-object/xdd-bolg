package com.java.blog.dao;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Users;
import com.java.utils.jdbc.DBUtils;
import com.java.utils.jdbc.JDBCCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            conn = JDBCCon.getConnection();

            String sql = "select * from users where name='"+username+"' and password='"+password+"'";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()){
                isValid = true;
            }
            System.out.println(isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }
        return isValid;
    }

    /**
     * 个人信息查询
     * @param username
     * @return
     */
    public JSONObject PersonInfo(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONObject jsonobj = new JSONObject();
        try {
            conn = JDBCCon.getConnection();

            String sql = "select * from users where name='"+username+"'";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                jsonobj.put("id", rs.getInt("id"));
                jsonobj.put("name", rs.getString("name"));
                jsonobj.put("img", rs.getString("img"));
                jsonobj.put("register_date", rs.getDate("register_date"));
                jsonobj.put("last_login", rs.getDate("last_login"));
                jsonobj.put("sex", rs.getInt("sex"));
                jsonobj.put("phone", rs.getInt("phone"));
                jsonobj.put("email", rs.getString("email"));
                jsonobj.put("password", rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }
        return jsonobj;
    }


    /**
     * 检查是否登录
     * @param username
     * @return
     */
    public boolean isExist(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            conn = JDBCCon.getConnection();

            String sql = "select * from users where name='"+username+"'";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()){
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }
        return isValid;
    }

    /**
     * 注册
     * @param users
     */
    public boolean register(Users users) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            conn = JDBCCon.getConnection();

            String sql = "INSERT INTO users (name, password) " +
                    "VALUES (?, ?);";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, users.getName());
            stmt.setString(2, users.getPassword());
            int rows = stmt.executeUpdate();
            if(rs.next()){
                isValid = true;
            }
            System.out.println("成功插入：" + rows + "次");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }
        return isValid;
    }
}
