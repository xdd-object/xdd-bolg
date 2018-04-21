package com.java.utils.jdbc;

import java.sql.*;

public class JDBCCon {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String USER="root";
    private static final String PASSWORD="root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public JDBCCon(String sql) {
        try {
            Class.forName(DRIVER);//指定连接类型
            conn = DriverManager.getConnection(URL, USER, PASSWORD);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}