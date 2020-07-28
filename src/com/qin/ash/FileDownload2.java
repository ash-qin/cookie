package com.qin.ash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Base64;

@WebServlet("/FileDownload2")
public class FileDownload2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filename = "/imgs/路飞.jpg";
        String realpath = getServletContext().getRealPath(filename);
        String mimeType = getServletContext().getMimeType(filename);
        response.setContentType(mimeType);



        InputStream is = new FileInputStream(realpath);

        String header = request.getHeader("User-Agent");
        System.out.println(header);

        if(header!=null){

            if (header.contains("Firefox")){

                filename ="=?UTF-8?B?"+ new String(Base64.getEncoder().encode(filename.getBytes("UTF-8"))) + "?=";

            }else {

                filename = URLEncoder.encode(filename,"UTF-8");

            }

        }

        System.out.println(filename);

        response.addHeader("Content-Disposition","attachment;filename="+filename);

        byte[] buf = new byte[1024];
        int len = 0;

        while((len = is.read(buf)) != -1){

            response.getOutputStream().write(buf,0,len);

        }

        is.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
