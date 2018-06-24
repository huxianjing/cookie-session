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

/**
 * 显示最后一次访问时间
 */
@WebServlet("/LastAccessTimeServlet")
public class LastAccessTimeServlet extends HttpServlet {

    //cookie的key
    private static final String COOKIE_KEY_LASTACCESSTIME = "COOKIE_KEY_LASTACCESSTIME";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //1.获取Cookie信息
        String lastAccessTime = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cookieKey = cookie.getName();
                if (cookieKey.equals(COOKIE_KEY_LASTACCESSTIME)) {
                    lastAccessTime = cookie.getValue();
                    break;
                }
            }
        }

        //2.如果Cookie信息没有数据，说明第一次访问，有数据的就获取上一次Cookie的值
        if (lastAccessTime == null) {
            resp.getWriter().write("你是首次访问...");
        }else{
            resp.getWriter().write("你上一次访问的时间是："+lastAccessTime);
        }
        //3.现在访问的这个登录时间存放在Cookie里面
        lastAccessTime = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss:SSS").format(new Date());
        Cookie cookie = new Cookie(COOKIE_KEY_LASTACCESSTIME,lastAccessTime);
        cookie.setMaxAge(60*60*24*365);
        resp.addCookie(cookie);
    }
}
