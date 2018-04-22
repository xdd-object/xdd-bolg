package com.java.blog.service;

import com.java.blog.dao.ArticleListDao;

import java.util.List;
import java.util.Map;

public class ArticleListService {

    ArticleListDao articleList = new ArticleListDao();

    public List<Map<String,Object>> getArticleList(String currentPage, String pageCount) {
        List<Map<String,Object>> json = articleList.getArticleList(currentPage, pageCount);
        return json;
    }
}
