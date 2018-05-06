package com.java.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Comments;
import com.java.blog.dao.ArticleDetailsDao;
import com.java.blog.dao.CommentDao;

import java.util.List;
import java.util.Map;

public class ArticleDetailsService {

    ArticleDetailsDao articleDetailsDao = new ArticleDetailsDao();
    CommentDao commentSaveDao = new CommentDao();


    public JSONObject articleDetail(String id) {

        JSONObject jsonobj = articleDetailsDao.articleDetail(id);
        List<Map<String, Object>> list = commentSaveDao.listByArticleId(id);
        jsonobj.put("comments_list", list);
        return jsonobj;
    }
}
