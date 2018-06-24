package com.lyq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/LastAccessTimeServlet1")
public class LastAccessTimeServlet1 extends HttpServlet {

    private static final String LAST_ACCESS_TIME_COOKIE_KEY = "LAST_ACCESS_TIME_COOKIE_KEYS";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html;charset=utf-8");
        //1.获取上一次访问的时间
        String lastAccessTime = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LAST_ACCESS_TIME_COOKIE_KEY)) {
                    lastAccessTime = cookie.getValue();
                    break;
                }
            }
        }

        //2.如果Cookie有值，返回上一次访问的时间，没有值，返回首次登录
        if(lastAccessTime == null){
            resp.getWriter().write("你是首次访问...");
        }else {
            resp.getWriter().write("你是上一次访问的时间是："+lastAccessTime);
        }
        //3.将当前时间保存在Cookie中
        lastAccessTime = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss:SSS").format(new Date());
        Cookie cookie = new Cookie(LAST_ACCESS_TIME_COOKIE_KEY,lastAccessTime);
        cookie.setMaxAge(60*60*24*360);
        resp.addCookie(cookie);
    }
}
