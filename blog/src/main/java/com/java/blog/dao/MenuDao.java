package com.java.blog.dao;

import com.java.utils.jdbc.JDBCCon;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDao {
    public List<Map<String,Object>> getMenuList() {

        //操作数据库
        List<Map<String,Object>> con = new ArrayList<Map<String,Object>>();

        try {
            JDBCCon jdbcCon = new JDBCCon("SELECT * FROM menu;");
            ResultSet resultSet = jdbcCon.pst.executeQuery();

            Map<String,Object> map;
            while (resultSet.next()) {
                map = new HashMap<String,Object>();
                map.put("name",resultSet.getObject("name"));
                map.put("id",resultSet.getObject("id"));
                con.add(map);
            };
            jdbcCon.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
