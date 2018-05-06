package com.java.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Users;
import com.java.blog.dao.UserDao;

public class UserService {
    UserDao userDao = new UserDao();
    public JSONObject login(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        Boolean isLogin = userDao.login(username, password);
        if (isLogin) {
            JSONObject personInfo = userDao.PersonInfo(username);
            jsonObject.put("data", personInfo);
            jsonObject.put("isLogin", isLogin);
        } else {
            jsonObject.put("isLogin", false);
        }
        return  jsonObject;
    }

    public JSONObject registerService(Users users) {
        Boolean is = userDao.isExist(users.getName());
        JSONObject jsonObject = new JSONObject();
        if (is) {
            jsonObject.put("name", users.getName());
            jsonObject.put("status", 0);
            jsonObject.put("msg", "用户名已存在");
        } else {
            Boolean isValid = userDao.register(users);
            jsonObject.put("name", users.getName());
            jsonObject.put("status", 1);
            jsonObject.put("msg", "注册成功");
        }

        return jsonObject;
    }
}
