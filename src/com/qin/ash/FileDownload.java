package com.qin.ash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/FileDownload")
public class FileDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filename = "/imgs/001.jpg";
        String mimeType = getServletContext().getMimeType(filename);
        response.setContentType(mimeType);

        response.addHeader("Content-Disposition","attachment;filename="+filename);

        filename = getServletContext().getRealPath(filename);

        System.out.println(filename);

        InputStream is = new FileInputStream(filename);

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
