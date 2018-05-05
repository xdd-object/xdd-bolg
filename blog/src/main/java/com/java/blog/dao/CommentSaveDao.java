package com.java.blog.dao;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Comments;
import com.java.utils.jdbc.DBUtils;
import com.java.utils.jdbc.JDBCCon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentSaveDao {

    public void saveComment(Comments comments) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONObject jsonobj = new JSONObject();

        try {
            conn = JDBCCon.getConnection();

            String sql = "insert into comments(content, date_time, user_id, parent_id) VALUES(?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comments.getContent());
            stmt.setDate(2, comments.getDate_time());
            stmt.setLong(3, comments.getUser_id());
            stmt.setLong(4, comments.getParent_id());
            int rows = stmt.executeUpdate();
            System.out.println("成功插入：" + rows + "次");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }

    }
}
