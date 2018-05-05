package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Comments;
import com.java.blog.service.CommentSaveService;
import com.java.utils.requset.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String bodyData = Request.getBodyData(req);
        Comments comments = JSONObject.parseObject(bodyData, Comments.class);

        CommentSaveService commentSaveService = new CommentSaveService();
        commentSaveService.saveComment(comments);
    }
}
