package com.java.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.dao.ArticleDetailsDao;

public class ArticleDetailsService {

    ArticleDetailsDao articleDetailsDao = new ArticleDetailsDao();

    public JSONObject articleDetail(String id) {

        JSONObject jsonobj = articleDetailsDao.articleDetail(id);

        return jsonobj;
    }
}
