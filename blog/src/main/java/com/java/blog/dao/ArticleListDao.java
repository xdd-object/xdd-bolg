package com.java.blog.dao;

import com.java.utils.jdbc.JDBCCon;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleListDao {

    public List<Map<String,Object>> getArticleList(Integer currentPage, Integer pageCount) {
        String table = "article";
        //操作数据库
        List<Map<String,Object>> con = new ArrayList<Map<String,Object>>();
        try {
            JDBCCon jdbcCon = new JDBCCon("SELECT * FROM " + table + " LIMIT " + currentPage + "," + pageCount);
            ResultSet resultSet = jdbcCon.pst.executeQuery();
            Map<String,Object> map;
            while (resultSet.next()) {
                map = new HashMap<String,Object>();
                map.put("id",resultSet.getObject("id"));
                map.put("name",resultSet.getObject("name"));
                map.put("date_time",resultSet.getObject("date_time"));
                map.put("classify",resultSet.getObject("classify"));
                map.put("reading",resultSet.getObject("reading"));
                map.put("content",resultSet.getObject("content"));
                map.put("comment_id",resultSet.getObject("comment_id"));
                map.put("author",resultSet.getObject("author"));
                map.put("desc",resultSet.getObject("desc"));
                con.add(map);
            };
            jdbcCon.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
