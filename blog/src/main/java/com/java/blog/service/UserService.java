package com.java.blog.service;

import com.java.blog.dao.UserDao;

public class UserService {
    UserDao userDao = new UserDao();
    public boolean login(String username, String password) {
        Boolean isLogin = userDao.login(username, password);
        return  isLogin;
    }
}
