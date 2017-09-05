package com.inno.dao.impl;

import com.inno.bean.DauBean.Spiderlog;
import com.inno.dao.InsertDao;
import com.inno.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Administrator on 2017/6/27.
 */
public class InsertDaoImpl implements InsertDao {
    @Override
    public int InsertTable(String json) {
        /*Connection conn=null;
        PreparedStatement ps=null;

        try{
            Gson gson=new Gson();
            InsertBean insert=gson.fromJson(json,InsertBean.class);
            StringBuffer str=new StringBuffer();
            StringBuffer str2=new StringBuffer();
            List<String> list=insert.tablePro.fields;
            List<InsertBean.Detail> list2=insert.detail;
            for(String s:list){
                str.append("`"+s+"`"+",");
                str2.append("?"+",");
            }
            String fie=str.substring(0,str.length()-1);
            String fu=str2.substring(0,str2.length()-1);
            String sql="insert into "+insert.tablePro.table+"("+fie+") values("+fu+")";
            conn= JdbcUtils_DBCP.getConnection();
            ps=conn.prepareStatement(sql);
            for(int x=0;x<list.size();x++){
                for(int y=0;y<list2.size();y++) {
                    if(list.get(x).equals(list2.get(y).field)) {
                        ps.setString(x + 1, list2.get(y).content);
                    }
                }
            }
            int re=ps.executeUpdate();
            return re;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils_DBCP.release(conn,ps);
        }*/
        return 0;
    }

    @Override
    public int InsertLog(Spiderlog spiderlog) {
        Connection conn=null;
        PreparedStatement ps=null;

        try{
            String sql="insert into spider_log(file_md5,file_size,file_name,spider_time,data_date,store_data_flag) values(?,?,?,?,?,?)";
            conn=JdbcUtils_DBCP.getConnection();
            ps=conn.prepareStatement(sql);

            ps.setString(1,spiderlog.getFile_md5());
            ps.setString(2,spiderlog.getFile_size());
            ps.setString(3,spiderlog.getFile_name());
            ps.setString(4,spiderlog.getSpider_time());
            ps.setString(5,spiderlog.getData_date());
            ps.setString(6,spiderlog.getStore_data_flag());
            return ps.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils_DBCP.release(conn,ps);
        }


        return 0;
    }
}
