package com.inno.controller.MonController;

import com.inno.services.MonService;
import com.inno.services.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/29.
 */
public class MonServlet extends HttpServlet {
    MonService dau = ServiceFactory.getInstance().getMonService("com.inno.services.impl.MonServiceImpl");
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String html=dau.select();
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.write(html);
        out.flush();
        out.close();
    }

    protected  void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

}
