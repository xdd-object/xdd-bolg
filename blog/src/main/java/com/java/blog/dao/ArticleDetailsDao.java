package com.java.blog.dao;

import com.alibaba.fastjson.JSONObject;
import com.java.utils.jdbc.DBUtils;
import com.java.utils.jdbc.JDBCCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class ArticleDetailsDao {

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
}
