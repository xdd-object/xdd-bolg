package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.service.ArticleListService;
import com.java.utils.requset.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ArticleListServlet extends HttpServlet {
    ArticleListService articleListService = new ArticleListService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        //接受参数
        String bodyData = Request.getBodyData(req);
        Map<String, Object> map = JSONObject.parseObject(bodyData, Map.class);

        String currentPage = map.get("currentPage").toString();
        String pageCount = map.get("pageCount").toString();

        //返回参数
        List<Map<String,Object>> articleList = articleListService.getArticleList(currentPage, pageCount);
        req.setAttribute("articleList", articleList);
        String json = JSONObject.toJSONString(articleList);

        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.close();
    }
}
