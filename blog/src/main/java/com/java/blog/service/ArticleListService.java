package com.java.blog.service;

import com.java.blog.dao.ArticleListDao;

import java.util.List;
import java.util.Map;

public class ArticleListService {

    ArticleListDao articleList = new ArticleListDao();

    public List<Map<String,Object>> getArticleList(String currentPage, String pageCount) {

        String strCurrentPage = currentPage;
        String strPageCount = pageCount;
        int intCurrentPage = Integer.parseInt(strCurrentPage);
        int intPageCount = Integer.parseInt(strPageCount);

        List<Map<String,Object>> json = articleList.getArticleList(intCurrentPage*intPageCount, intPageCount);
        return json;
    }
}
