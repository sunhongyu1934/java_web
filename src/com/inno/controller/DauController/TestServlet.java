package com.inno.controller.DauController;

import com.inno.services.DauService;
import com.inno.services.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/20.
 */
public class TestServlet extends HttpServlet {
    DauService dau = ServiceFactory.getInstance().getDauService("com.inno.services.impl.DauServiceImpl");
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            /*String limit=request.getParameter("limit");
            List<AppXin> list=dau.getApp(limit);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("message","ok");
            map.put("statu","200");
            map.put("data",list);
            JSONObject js=JSONObject.fromObject(map);*/
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.write("hahaha");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
