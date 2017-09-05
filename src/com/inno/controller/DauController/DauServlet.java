package com.inno.controller.DauController;

import com.inno.bean.DauBean.QueryResult;
import com.inno.services.DauService;
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
 * Created by Administrator on 2017/6/22.
 */
public class DauServlet extends HttpServlet {
    DauService dau = ServiceFactory.getInstance().getDauService("com.inno.services.impl.DauServiceImpl");
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String firstname=request.getParameter("FirstName");
        String secondname=request.getParameter("SecondName");
        String projectName = request.getParameter("ProjectName");

        QueryResult queryResult = new QueryResult();
        if (projectName!=null) {
            queryResult = dau.getSecondDetail(projectName);
        }else {
            queryResult = dau.getSecondDetail(firstname);
        }
        queryResult = dau.getProjectDetail(firstname,secondname);

        Map<String,Object> map=new HashMap<String, Object>();

        map.put("classify_name",queryResult.getName_arry());
        map.put("count_value",queryResult.getDau_arry());
        map.put("id_arry",queryResult.getId_arry());
        map.put("new_classify_name",queryResult.getNew_name_arry());
        map.put("new_count_value",queryResult.getNew_dau_arry());
        map.put("new_id_arry",queryResult.getNew_id_arry());
        map.put("date_time",queryResult.getDate_time());

        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        JSONObject jsonObject=JSONObject.fromObject(map);
        out.write(jsonObject.toString());
        out.flush();
        out.close();

    }

    protected  void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
