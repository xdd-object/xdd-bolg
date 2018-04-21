package com.java.blog.dao;

import com.java.utils.jdbc.JDBCCon;

import java.sql.ResultSet;
public class BlogHomeDao {
    public String[] getBlogById(String userName, String password) {

        //操作数据库
        String[] con = new String[2];
        try {
            JDBCCon jdbcCon = new JDBCCon("SELECT * FROM user;");
            ResultSet resultSet = jdbcCon.pst.executeQuery();
            while (resultSet.next()) {
                con[0] = resultSet.getString("user_name");
                con[1] = resultSet.getString("user_password");
            }
            jdbcCon.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
