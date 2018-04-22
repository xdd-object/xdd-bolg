package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class MenuServlet extends HttpServlet {

    MenuService menuService = new MenuService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<Map<String,Object>> menuList = menuService.getMenuList();
        req.setAttribute("menuList", menuList);
        String json = JSONObject.toJSONString(menuList);

        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.close();

        System.out.println("---" + menuList);
    }
}
