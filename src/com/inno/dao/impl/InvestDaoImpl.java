package com.inno.dao.impl;

import com.inno.bean.DauBean.InvestBean;
import com.inno.dao.InvestDao;
import com.inno.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
public class InvestDaoImpl implements InvestDao{

    @Override
    public InvestBean selectInv(String year_id, String indu_id, String chain_id, String stage_id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            conn= JdbcUtils_DBCP.getConnection();
            String sql="select sum(fin_num) as `fin_num`,sum(fin_amount) as `fin_amount`,substr(day_id,1,6) as `month` from analy_company_financing where SUBSTR(day_id,1,4)="+year_id;
            if(!indu_id.equals("0")){
                sql=sql+" and third_indus_id="+indu_id;
            }
            if(!chain_id.equals("0")){
                sql=sql+" and third_chain_id="+chain_id;
            }
            if(!stage_id.equals("0")){
                sql=sql+" and stage_id="+stage_id;
            }
            sql=sql+" group by substr(day_id,1,6)";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            List<String> list1=new ArrayList<String>();
            List<String> list2=new ArrayList<String>();
            List<String> list3=new ArrayList<String>();
            int p=0;
            while (rs.next()){
                String fin_num=rs.getString(rs.findColumn("fin_num"));
                String fin_amount= String.valueOf(Float.parseFloat(rs.getString(rs.findColumn("fin_amount")))/10000);
                String month=rs.getString(rs.findColumn("month"));
                list1.add(fin_num);
                list2.add(fin_amount);
                list3.add(month);
                p++;
            }
            InvestBean invest=new InvestBean();
            invest.setNum(list1);
            invest.setAmount(list2);
            invest.setMonth(list3);
            invest.setCount_value(String.valueOf(p));

            return invest;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils_DBCP.release(conn,ps,rs);
        }
        return null;
    }
}
