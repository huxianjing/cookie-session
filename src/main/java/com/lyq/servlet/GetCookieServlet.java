package com.lyq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取Cookie
 */
@WebServlet("/GetCookieServlet")
public class GetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null && cookies.length>0){
            for(Cookie cookie : cookies){
                if("userName".equals(cookie.getName())){
                    String value = cookie.getValue();
                    System.out.println("key:"+cookie.getName()+"    value:"+value);
                }
            }
        }

    }
}
