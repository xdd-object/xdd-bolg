package com.java.blog.servlet;

import com.java.blog.service.BlogHomeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlogHomeServlet extends HttpServlet{
    BlogHomeService blogHomeService= new BlogHomeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //接受参数
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        //传递参数到service

        String[] res = blogHomeService.getBlogById(userName, password);

        req.setAttribute("user_name", res[0]);
        req.setAttribute("user_password", res[1]);
        req.getRequestDispatcher("/WEB-INF/page/blogHome.jsp").forward(req,resp);
        //返回结果
    }
}
