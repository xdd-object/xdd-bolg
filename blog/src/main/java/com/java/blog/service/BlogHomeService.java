package com.java.blog.service;

import com.java.blog.dao.BlogHomeDao;

public class BlogHomeService {

    BlogHomeDao blogHomeDao= new BlogHomeDao();


    public String[] getBlogById(String userName, String password) {
        //处理业务逻辑

        //查询数据库
        String[] res = blogHomeDao.getBlogById(userName, password);

        //处理业务逻辑
        System.out.println(res);


        //返回结果
        return res;
    }
}
