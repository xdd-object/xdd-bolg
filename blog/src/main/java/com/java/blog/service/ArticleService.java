package com.java.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.dao.ArticleDao;
import com.java.blog.dao.CommentDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {

    ArticleDao articleDao = new ArticleDao();
    CommentDao commentDao = new CommentDao();

    public JSONObject articleDetail(String id) {

        // 返回文章详情
        JSONObject jsonobj = articleDao.articleDetail(id);

        // 返回评论信息
        List<Map<String, Object>> list = commentDao.listByArticleId(id);

        jsonobj.put("comments_list", list);
        return jsonobj;
    }

    public Map<String,Object> getArticleList(String currentPage, String pageCount) {
        Map<String, Object> jsonObj = new HashMap<String,Object>();

        int intCurrentPage = Integer.parseInt(currentPage);
        int intPageCount = Integer.parseInt(pageCount);

        // 返回文章总记录数
        int totalCount = articleDao.getTableCount();

        // 返回文章limit影响记录数
        List<Map<String,Object>> json = articleDao.getArticleList(intCurrentPage*intPageCount, intPageCount);

        jsonObj.put("data", json);
        jsonObj.put("totalCount", totalCount);

        return jsonObj;
    }
}
