package com.java.blog.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/articleDetails.jsp").forward(req, resp);
        }
    }
}
