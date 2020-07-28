package com.qin.ash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String code =(String) session.getAttribute("checkcode_session");

        String checkcode = request.getParameter("checkcode");

        if(checkcode!=null){

            if (checkcode.equalsIgnoreCase(code)){

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if("aaa".equals(username) && "123".equals(password)){

                    response.sendRedirect("http://localhost:8080/cookie_demo/index.jsp");

                }else {

                    response.getWriter().write("亲，您的账号或密码输入错误");

                }

            }else {

                response.getWriter().write("亲，您的验证码输入错误");
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
