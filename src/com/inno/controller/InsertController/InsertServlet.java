package com.inno.controller.InsertController;

import com.inno.services.InsertService;
import com.inno.services.ServiceFactory;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27.
 */
public class InsertServlet extends HttpServlet{
    InsertService dau = ServiceFactory.getInstance().getInsertService("com.inno.services.impl.InsertServiceImpl");

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String json=request.getParameter("json");
        String result=dau.insertTable(json);
        Map<String,String> map=new HashMap<String,String>();
        map.put("statu","200");
        map.put("message","ok");
        map.put("result",result);
        JSONObject js=JSONObject.fromObject(map);
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.write(js.toString());
        out.flush();
        out.close();
    }
}
