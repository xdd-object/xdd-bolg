package com.java.utils.requset;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {

    public static String getBodyData(HttpServletRequest req) throws IOException {

        String bodyData = null;

        InputStream inStream = req.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inStream,"utf-8"));
        String s = null;
        bodyData = "";
        while ((s = br.readLine()) != null) {
            bodyData += s;
        }
        return bodyData;
    }

}
