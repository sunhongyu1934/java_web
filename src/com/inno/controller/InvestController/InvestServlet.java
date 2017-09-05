package com.inno.controller.InvestController;

import com.inno.services.InvestService;
import com.inno.services.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/7/1.
 */
public class InvestServlet extends HttpServlet {
    InvestService dau = ServiceFactory.getInstance().getInvestService("com.inno.services.impl.InvestServiceImpl");
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");

        String year_id=request.getParameter("year_id");
        String indu_id=request.getParameter("indu_id");
        String chain_id=request.getParameter("chain_id");
        String stage_id=request.getParameter("stage_id");
        String json=dau.selectInv(year_id,indu_id,chain_id,stage_id);
        PrintWriter pw=response.getWriter();
        pw.write(json);
        pw.flush();
        pw.close();
    }
}
