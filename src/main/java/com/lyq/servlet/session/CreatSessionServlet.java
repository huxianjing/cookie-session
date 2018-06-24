package com.lyq.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CreatSessionServlet")
public class CreatSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //默认是true,表示如果没有session，就会创建一个session，设置成false，如果没有找到session,也不会创建session
        HttpSession session = req.getSession();
        session.setAttribute("userName","强强");
        System.out.println("session已经保存成功了");
    }
}
