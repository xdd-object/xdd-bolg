package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.service.UserService;
import com.java.utils.requset.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String bodyData = Request.getBodyData(req);
        Map<String, Object> map = JSONObject.parseObject(bodyData, Map.class);

        String method = map.get("method").toString();

        if ("login".equals(method)) {
            String username = map.get("username").toString();
            String password = map.get("password").toString();;
            if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim())){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", 0);
                jsonObject.put("msg", "登录失败");
                PrintWriter writer = resp.getWriter();
                writer.write(String.valueOf(jsonObject));
            } else {
                Boolean is = userService.login(username, password);
                if (is) {
                    req.getSession().setAttribute("userInfo", username);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status", 1);
                    jsonObject.put("msg", "登录成功");
                    PrintWriter writer = resp.getWriter();
                    writer.write(String.valueOf(jsonObject));
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status", 0);
                    jsonObject.put("msg", "用户名或密码有误有误");
                    PrintWriter writer = resp.getWriter();
                    writer.write(String.valueOf(jsonObject));
                }
            }
        } else if ("logout".equals(method)) {

        } else if ("register".equals(method)) {

        }
    }
}
