package com.qin.ash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CookeDemo")
public class CookeDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=UTF-8");

        Cookie cookie1 = null;
        for (Cookie cookie : cookies) {

            if ("lastvisit".equals(cookie.getName())){
                cookie1 = cookie;
                break;
            }

        }

        if (cookie1 != null){

            response.getWriter().print("您的上一次访问时间为：" + URLDecoder.decode(cookie1.getValue(),"UTF-8"));
            response.getWriter().write("您的上一次访问时间为：" + URLDecoder.decode(cookie1.getValue(),"UTF-8"));

        }else{

            response.getWriter().print("您是第一次访问本网站");

        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");

        String format = sdf.format(new Date());
        format = URLEncoder.encode(format,"UTF-8");

        Cookie cookie = new Cookie("lastvisit",format);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
