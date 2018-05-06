package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Comments;
import com.java.blog.service.CommentService;
import com.java.utils.requset.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommentSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String bodyData = Request.getBodyData(req);
        Comments comments = JSONObject.parseObject(bodyData, Comments.class);

        CommentService commentService = new CommentService();
        commentService.saveComment(comments);
        PrintWriter writer = resp.getWriter();
        writer.write("SUCCESS");
        writer.close();
    }
}
