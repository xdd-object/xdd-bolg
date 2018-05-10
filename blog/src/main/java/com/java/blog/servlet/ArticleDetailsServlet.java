package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleDetailsServlet extends HttpServlet {

    ArticleService articleService = new ArticleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || "".equals(id.trim())) {
            req.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(req, resp);
        } else {
            JSONObject jsonObject =  articleService.articleDetail(id);
            req.setAttribute("jsonObject", jsonObject);
            req.getRequestDispatcher("/WEB-INF/views/articleDetails.jsp").forward(req, resp);
        }
    }
}
