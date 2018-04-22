package com.java.blog.service;

import com.java.blog.dao.MenuDao;

import java.util.List;
import java.util.Map;

public class MenuService {

    MenuDao Object = new MenuDao();

    public List<Map<String,Object>> getMenuList() {
        List<Map<String,Object>> menuDao = Object.getMenuList();
        return menuDao;
    }

}
