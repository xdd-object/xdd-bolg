package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.service.ArticleService;
import com.java.utils.requset.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ArticleListServlet extends HttpServlet {
    ArticleService articleService = new ArticleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //接受参数
        String bodyData = Request.getBodyData(req);
        Map<String, Object> map = JSONObject.parseObject(bodyData, Map.class);

        String currentPage = map.get("currentPage").toString();
        String pageCount = map.get("pageCount").toString();

        //返回参数
        Map<String,Object> articleList = articleService.getArticleList(currentPage, pageCount);
        req.setAttribute("articleList", articleList);
        String json = JSONObject.toJSONString(articleList);

        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.close();
    }
}
