package com.inno.controller.DauController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/21.
 */
public class TestUi extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.getRequestDispatcher("/test.jsp").forward(request,response);
    }

}
