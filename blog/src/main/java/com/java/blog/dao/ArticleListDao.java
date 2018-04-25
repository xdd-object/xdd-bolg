package com.java.blog.dao;

import com.java.utils.jdbc.JDBCCon;
import com.java.utils.jdbc.DBUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleListDao {

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
//            try {
//                conn.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return list;
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

        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return rowCount;
    }
}
