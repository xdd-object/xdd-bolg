package com.java.blog.dao;

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

}
