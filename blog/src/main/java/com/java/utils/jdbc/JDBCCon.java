package com.java.utils.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCCon {
    private static String JDBC_DRIVER;
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        String path = JDBCCon.class.getClassLoader().getResource("JDBCConfig.properties").getPath();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            props.load(in);
            JDBC_DRIVER = props.getProperty("JDBC_DRIVER");
            DB_URL = props.getProperty("DB_URL");
            USER = props.getProperty("USER");
            PASSWORD = props.getProperty("PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}