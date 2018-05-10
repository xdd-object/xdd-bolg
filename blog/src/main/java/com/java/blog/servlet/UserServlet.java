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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //接受参数
        String bodyData = Request.getBodyData(req);
        Map<String, Object> map = JSONObject.parseObject(bodyData, Map.class);

        String method = String.valueOf(map.get("method"));
        Object username = map.get("username");
        Object password = map.get("password");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        if ("login".equals(method)) {
            if(username==null||"".equals(username.toString().trim())||password==null||"".equals(password.toString().trim())){
                jsonObject.put("msg", "登录失败");
            } else {
                JSONObject res = userService.login(username.toString(), password.toString());
                Boolean isLogin = (Boolean)res.get("isLogin");
                if (isLogin) {
                    req.getSession().setAttribute("userInfo", username);
                    jsonObject.put("status", 1);
                    jsonObject.put("data", res.get("data"));
                    jsonObject.put("msg", "登录成功");
                } else {
                    jsonObject.put("msg", "用户名或密码有误有误");
                }
            }
        } else if ("logout".equals(method)) {
            req.getSession().removeAttribute("userInfo");
        } else if ("register".equals(method)) {
            if(username==null||"".equals(username.toString().trim())||password==null||"".equals(password.toString().trim())){
                jsonObject.put("msg", "注册失败");
            } else {
                Users users = new Users(username.toString(), password.toString());
                jsonObject = userService.registerService(users);
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject.toJSONString());
        writer.close();
    }
}
