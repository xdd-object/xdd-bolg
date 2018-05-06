package com.java.blog.dao;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Comments;
import com.java.utils.jdbc.DBUtils;
import com.java.utils.jdbc.JDBCCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDao {

    /**
     * 保存评论
     * @param comments
     */
    public void saveComment(Comments comments) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCCon.getConnection();

            String sql = "INSERT INTO comments (content, date_time, from_id, parent_id, article_id, to_id) " +
                    "VALUES (?, now(), ?, ?, ?, ?);";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comments.getContent());
            stmt.setLong(2, comments.getFrom_id());
            stmt.setLong(3, comments.getParent_id());
            stmt.setLong(4, comments.getArticle_id());
            stmt.setLong(5, comments.getTo_id());
            int rows = stmt.executeUpdate();
            System.out.println("成功插入：" + rows + "次");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }

    }

    /**
     * 评论信息查询
     * @param id
     * @return
     */
    public List<Map<String, Object>> listByArticleId(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONObject jsonobj = new JSONObject();

        try {
            conn = JDBCCon.getConnection();
            String sql = "SELECT a.*,b.name from_name,b.img from_img,\n" +
                    "  c.name to_name,c.img to_img\n" +
                    "  FROM comments a\n" +
                    "    LEFT JOIN users b ON a.from_id = b.id\n" +
                    "    LEFT JOIN users c ON a.to_id = c.id\n" +
                    "  WHERE a.article_id = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, Long.parseLong(id));
            ResultSet resultSet = stmt.executeQuery();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> comments = null;
            while (resultSet.next()) {
                comments = new HashMap<String, Object>();
                comments.put("from_id", resultSet.getLong("from_id"));
                comments.put("to_id", resultSet.getLong("to_id"));
                comments.put("id", resultSet.getLong("id"));
                comments.put("content", resultSet.getString("content"));
                comments.put("date_time", resultSet.getDate("date_time"));
                comments.put("parent_id", resultSet.getLong("parent_id"));
                comments.put("from_name", resultSet.getString("from_name"));
                comments.put("from_img", resultSet.getString("from_img"));
                comments.put("to_name", resultSet.getString("to_name"));
                comments.put("to_img", resultSet.getString("to_img"));
                list.add(comments);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs, stmt, conn);
        }
        return null;
    }
}
