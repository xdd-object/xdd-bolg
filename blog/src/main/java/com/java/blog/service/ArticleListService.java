package com.java.blog.service;

import com.java.blog.dao.ArticleListDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleListService {

    ArticleListDao articleList = new ArticleListDao();

    public Map<String,Object> getArticleList(String currentPage, String pageCount) {
        Map<String, Object> jsonObj = new HashMap<String,Object>();

        int intCurrentPage = Integer.parseInt(currentPage);
        int intPageCount = Integer.parseInt(pageCount);

        // 返回文章总记录数
        int totalCount = articleList.getTableCount();

        // 返回文章limit影响记录数
        List<Map<String,Object>> json = articleList.getArticleList(intCurrentPage*intPageCount, intPageCount);

        jsonObj.put("data", json);
        jsonObj.put("totalCount", totalCount);

        return jsonObj;
    }
}
