package com.java.blog.servlet;

import com.alibaba.fastjson.JSONObject;
import com.java.blog.bean.Users;
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
            String password = map.get("password").toString();
            if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim())){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", 0);
                jsonObject.put("msg", "登录失败");
                PrintWriter writer = resp.getWriter();
                writer.write(String.valueOf(jsonObject));
            } else {
                JSONObject jsonObject = userService.login(username, password);
                Boolean isLogin = (Boolean)jsonObject.get("isLogin");
                if (isLogin) {
                    req.getSession().setAttribute("userInfo", username);
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("status", 1);
                    jsonObject1.put("data", jsonObject.get("data"));
                    jsonObject1.put("msg", "登录成功");
                    PrintWriter writer = resp.getWriter();
                    writer.write(String.valueOf(jsonObject1));
                } else {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("status", 0);
                    jsonObject1.put("msg", "用户名或密码有误有误");
                    PrintWriter writer = resp.getWriter();
                    writer.write(String.valueOf(jsonObject1));
                }
            }
        } else if ("logout".equals(method)) {
            req.getSession().removeAttribute("userInfo");
        } else if ("register".equals(method)) {
            String username = map.get("username").toString();
            String password = map.get("password").toString();
            Users users = new Users(username, password);
            if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim())){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", 0);
                jsonObject.put("msg", "注册失败");
                PrintWriter writer = resp.getWriter();
                writer.write(String.valueOf(jsonObject));
            } else {
                JSONObject jsonObject = userService.registerService(users);
                PrintWriter writer = resp.getWriter();
                writer.write(String.valueOf(jsonObject));
            }
        }
    }
}
