package com.inno.dao.impl;

import com.inno.dao.MonDao;
import com.inno.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public class MonDaoImpl implements MonDao{

    @Override
    public List<Map<String, String>> select() {
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;


        try{
            String sql="select sum(c_ount) as `sum`,sp_name,ty_pe from spider_log_new where count_time > DATE_SUB(now(),INTERVAL 1 DAY) GROUP BY sp_name,ty_pe";
            String sqlsanxiaoshi="select sum(c_ount) as `sum`,sp_name,ty_pe from spider_log_new where count_time > DATE_SUB(now(),INTERVAL 3 hour) GROUP BY sp_name,ty_pe";
            String sqlyi="select sum(c_ount) as `sum`,sp_name,ty_pe from spider_log_new where count_time > DATE_SUB(now(),INTERVAL 1 hour) GROUP BY sp_name,ty_pe";
            String ban="select sum(c_ount) as `sum`,sp_name,ty_pe from spider_log_new where count_time > DATE_SUB(now(),INTERVAL 30 minute) GROUP BY sp_name,ty_pe";

            List<Map<String,String>> list=new ArrayList<Map<String, String>>();
            conn= JdbcUtils_DBCP.getConnection();
            Map<String,String> map1=new HashMap<String, String>();
            Map<String,String> map2=new HashMap<String, String>();
            Map<String,String> map3=new HashMap<String, String>();
            Map<String,String> map4=new HashMap<String, String>();


            PreparedStatement ps1=conn.prepareStatement(sql);
            PreparedStatement ps2=conn.prepareStatement(sqlsanxiaoshi);
            PreparedStatement ps3=conn.prepareStatement(sqlyi);
            PreparedStatement ps4=conn.prepareStatement(ban);

            ResultSet rs1=ps1.executeQuery();
            ResultSet rs2=ps2.executeQuery();
            ResultSet rs3=ps3.executeQuery();
            ResultSet rs4=ps4.executeQuery();

            while (rs1.next()){
                String table=rs1.getString(rs1.findColumn("sp_name"));
                String ty=rs1.getString(rs1.findColumn("ty_pe"));
                String sum=rs1.getString(rs1.findColumn("sum"));
                map1.put(table+"_"+ty,sum);
            }

            while (rs2.next()){
                String table=rs2.getString(rs2.findColumn("sp_name"));
                String ty=rs2.getString(rs1.findColumn("ty_pe"));
                String sum=rs2.getString(rs2.findColumn("sum"));
                map2.put(table+"_"+ty,sum);
            }

            while (rs3.next()){
                String table=rs3.getString(rs3.findColumn("sp_name"));
                String ty=rs3.getString(rs1.findColumn("ty_pe"));
                String sum=rs3.getString(rs3.findColumn("sum"));
                map3.put(table+"_"+ty,sum);
            }

            while (rs4.next()){
                String table=rs4.getString(rs4.findColumn("sp_name"));
                String ty=rs4.getString(rs1.findColumn("ty_pe"));
                String sum=rs4.getString(rs4.findColumn("sum"));
                map4.put(table+"_"+ty,sum);
            }

            list.add(map1);
            list.add(map2);
            list.add(map3);
            list.add(map4);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
