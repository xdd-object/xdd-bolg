package com.java.blog.dao;

import com.alibaba.fastjson.JSONObject;
import com.java.utils.jdbc.JDBCCon;
import com.java.utils.jdbc.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDao {

    public List<Map<String,Object>> getArticleList(Integer currentPage, Integer pageCount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            conn = JDBCCon.getConnection();
            String table = "article";

            String ListSQL = "SELECT * FROM " + table + " LIMIT " + currentPage + "," + pageCount;
            stmt = conn.prepareStatement(ListSQL);
            rs = stmt.executeQuery();
            Map<String,Object> map;
            while (rs.next()) {
                map = new HashMap<String,Object>();
                map.put("id",rs.getObject("id"));
                map.put("name",rs.getObject("name"));
                map.put("date_time",rs.getObject("date_time"));
                map.put("classify",rs.getObject("classify"));
                map.put("reading",rs.getObject("reading"));
                map.put("content",rs.getObject("content"));
                map.put("comment_id",rs.getObject("comment_id"));
                map.put("author",rs.getObject("author"));
                map.put("desc",rs.getObject("desc"));
                list.add(map);
            };
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return list;
    }

    public JSONObject articleDetail(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONObject jsonobj = new JSONObject();

        try {
            conn = JDBCCon.getConnection();
            String ListSQL = "SELECT * FROM article WHERE id=" + id;
            stmt = conn.prepareStatement(ListSQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                jsonobj.put("id", rs.getInt("id"));
                jsonobj.put("name", rs.getString("name"));
                jsonobj.put("date_time", rs.getDate("date_time"));
                jsonobj.put("classify", rs.getString("classify"));
                jsonobj.put("reading", rs.getInt("reading"));
                jsonobj.put("desc", rs.getString("desc"));
                jsonobj.put("content", rs.getString("content"));
                jsonobj.put("comment_id", rs.getInt("comment_id"));
                jsonobj.put("author", rs.getString("author"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }

        return jsonobj;
    }

    public int getTableCount() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = JDBCCon.getConnection();
            String table = "article";
            String countSQL = "SELECT count(*) FROM " + table;
            stmt = conn.prepareStatement(countSQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return rowCount;
    }
}
